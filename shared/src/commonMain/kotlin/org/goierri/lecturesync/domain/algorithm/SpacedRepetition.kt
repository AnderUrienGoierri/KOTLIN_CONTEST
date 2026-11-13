package org.goierri.lecturesync.domain.algorithm

import org.goierri.lecturesync.domain.models.Flashcard
import kotlin.math.max
import kotlin.math.roundToLong

/**
 * SuperMemo-2 (SM-2) Spaced Repetition Algorithm.
 * Optimizes retention by scheduling reviews based on recall difficulty (0 to 5).
 */
object SpacedRepetitionAlgorithm {

    data class ReviewResult(
        val newIntervalDays: Long,
        val newBoxLevel: Int,
        val newEaseFactor: Double
    )

    /**
     * Calculates the next review parameters based on the SM-2 algorithm.
     * @param currentCard the flashcard being reviewed
     * @param quality grade from 0 (complete blackout) to 5 (perfect recall)
     */
    fun calculateNextReview(currentCard: Flashcard, quality: Int): ReviewResult {
        val q = quality.coerceIn(0, 5)

        // 1. Calculate new Ease Factor (EF)
        // EF' = EF + (0.1 - (5 - q) * (0.08 + (5 - q) * 0.02))
        val delta = 0.1 - (5 - q) * (0.08 + (5 - q) * 0.02)
        val newEf = max(1.3, currentCard.easeFactor + delta)

        // 2. Calculate new repetitions / box level and interval
        val (newBox, intervalDays) = if (q >= 3) {
            when (currentCard.boxLevel) {
                1 -> Pair(2, 1L)
                2 -> Pair(3, 6L)
                else -> {
                    val days = (currentCard.boxLevel * newEf).roundToLong().coerceAtLeast(6L)
                    Pair((currentCard.boxLevel + 1).coerceAtMost(5), days)
                }
            }
        } else {
            // Failed recall: reset to box 1 with 1 day review
            Pair(1, 1L)
        }

        return ReviewResult(
            newIntervalDays = intervalDays,
            newBoxLevel = newBox,
            newEaseFactor = (newEf * 100.0).roundToLong() / 100.0
        )
    }
}
