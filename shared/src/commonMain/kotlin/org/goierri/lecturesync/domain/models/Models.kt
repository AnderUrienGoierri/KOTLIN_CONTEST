package org.goierri.lecturesync.domain.models

import kotlinx.serialization.Serializable

@Serializable
enum class LectureStatus {
    RECORDING,
    PROCESSING,
    COMPLETED,
    FAILED
}

@Serializable
data class Lecture(
    val id: Long = 0,
    val title: String,
    val subject: String,
    val lecturer: String = "Irakaslea",
    val createdAt: String = "",
    val durationSeconds: Long = 0,
    val audioFilePath: String? = null,
    val status: LectureStatus = LectureStatus.RECORDING,
    val flashcardCount: Int = 0
) {
    fun getFormattedDuration(): String {
        val hours = durationSeconds / 3600
        val minutes = (durationSeconds % 3600) / 60
        val seconds = durationSeconds % 60
        return if (hours > 0) {
            String.format("%02d:%02d:%02d", hours, minutes, seconds)
        } else {
            String.format("%02d:%02d", minutes, seconds)
        }
    }
}

@Serializable
data class TranscriptSegment(
    val id: Long = 0,
    val lectureId: Long,
    val timestampStartMs: Long,
    val timestampEndMs: Long,
    val speaker: String = "Speaker",
    val textContent: String,
    val isKeyPoint: Boolean = false
)

@Serializable
data class Flashcard(
    val id: Long = 0,
    val lectureId: Long,
    val question: String,
    val answer: String,
    val boxLevel: Int = 1,
    val nextReviewDate: String = "",
    val easeFactor: Double = 2.5
)

@Serializable
data class MindMapNode(
    val id: Long = 0,
    val lectureId: Long,
    val label: String,
    val category: String = "Concept",
    val parentNodeId: Long? = null,
    val weight: Double = 1.0
)

@Serializable
data class AIAnalysisSummary(
    val id: Long = 0,
    val lectureId: Long,
    val executiveSummary: String,
    val keyConcepts: List<String> = emptyList(),
    val actionItems: List<String> = emptyList(),
    val generatedAt: String = ""
)
