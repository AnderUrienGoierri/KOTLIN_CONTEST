package org.goierri.lecturesync.backend.service

import org.goierri.lecturesync.domain.models.Flashcard
import org.goierri.lecturesync.domain.models.MindMapNode

class GeminiService {

    fun generateFlashcardsFromTranscript(lectureId: Long, transcript: String): List<Flashcard> {
        val cards = mutableListOf<Flashcard>()
        if (transcript.contains("StateFlow", ignoreCase = true) || transcript.contains("Concurrency", ignoreCase = true)) {
            cards.add(
                Flashcard(
                    lectureId = lectureId,
                    question = "What is StateFlow in Kotlin Coroutines?",
                    answer = "StateFlow is a state-holder observable flow that emits the current and new state updates to its collectors.",
                    boxLevel = 1
                )
            )
            cards.add(
                Flashcard(
                    lectureId = lectureId,
                    question = "Why is Dispatchers.JavaFx used in Desktop KMP?",
                    answer = "It ensures UI mutations run safely on the JavaFX Application Thread, preventing IllegalStateException.",
                    boxLevel = 1
                )
            )
        } else {
            cards.add(
                Flashcard(
                    lectureId = lectureId,
                    question = "What is the primary architectural rule of KMP?",
                    answer = "Business logic and domain models live in commonMain; UI layers live in platform-specific modules.",
                    boxLevel = 1
                )
            )
        }
        return cards
    }

    fun generateMindMapNodes(lectureId: Long, topic: String): List<MindMapNode> {
        return listOf(
            MindMapNode(lectureId = lectureId, label = "Kotlin Multiplatform", category = "Core", parentNodeId = null, weight = 2.0),
            MindMapNode(lectureId = lectureId, label = "Coroutines & Flow", category = "Concurrency", parentNodeId = 1, weight = 1.5),
            MindMapNode(lectureId = lectureId, label = "JavaFX Desktop UI", category = "Desktop", parentNodeId = 1, weight = 1.2),
            MindMapNode(lectureId = lectureId, label = "Android Compose", category = "Mobile", parentNodeId = 1, weight = 1.2)
        )
    }
}
