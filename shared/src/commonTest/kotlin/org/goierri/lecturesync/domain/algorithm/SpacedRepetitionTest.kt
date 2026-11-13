package org.goierri.lecturesync.domain.algorithm

import org.goierri.lecturesync.domain.models.Flashcard
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SpacedRepetitionTest {

    @Test
    fun testInitialSuccessfulReviewMovesToBoxTwo() {
        val card = Flashcard(
            id = 1,
            lectureId = 1,
            question = "What is StateFlow?",
            answer = "State-holder observable flow",
            boxLevel = 1,
            easeFactor = 2.5
        )

        val result = SpacedRepetitionAlgorithm.calculateNextReview(card, quality = 4)

        assertEquals(2, result.newBoxLevel)
        assertEquals(1L, result.newIntervalDays)
        assertEquals(2.5, result.newEaseFactor)
    }

    @Test
    fun testFailedReviewResetsBoxToOne() {
        val card = Flashcard(
            id = 2,
            lectureId = 1,
            question = "KMP Architecture?",
            answer = "commonMain + platform modules",
            boxLevel = 3,
            easeFactor = 2.4
        )

        val result = SpacedRepetitionAlgorithm.calculateNextReview(card, quality = 1)

        assertEquals(1, result.newBoxLevel)
        assertEquals(1L, result.newIntervalDays)
        assertTrue(result.newEaseFactor < 2.4)
    }
}
