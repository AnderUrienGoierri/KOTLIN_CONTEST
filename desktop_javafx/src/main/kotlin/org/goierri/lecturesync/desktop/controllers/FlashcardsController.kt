package org.goierri.lecturesync.desktop.controllers

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.ProgressBar
import javafx.scene.layout.VBox
import org.goierri.lecturesync.domain.algorithm.SpacedRepetitionAlgorithm
import org.goierri.lecturesync.domain.models.Flashcard
import java.net.URL
import java.util.*

class FlashcardsController : Initializable {

    @FXML private lateinit var cardFlipContainer: VBox
    @FXML private lateinit var lblCardBadge: Label
    @FXML private lateinit var lblCardQuestion: Label
    @FXML private lateinit var lblCardAnswer: Label
    @FXML private lateinit var btnFlipCard: Button
    @FXML private lateinit var btnMarkReview: Button
    @FXML private lateinit var btnMarkGood: Button
    @FXML private lateinit var btnMarkKnown: Button
    @FXML private lateinit var prgMastery: ProgressBar
    @FXML private lateinit var lblProgress: Label

    private val studyDeck = mutableListOf(
        Flashcard(
            id = 1,
            lectureId = 1,
            question = "What does Dispatchers.JavaFx guarantee in Desktop KMP?",
            answer = "It schedules execution on the JavaFX Application Thread to prevent thread-access exceptions during UI mutations.",
            boxLevel = 1,
            easeFactor = 2.5
        ),
        Flashcard(
            id = 2,
            lectureId = 1,
            question = "What is the difference between StateFlow and SharedFlow?",
            answer = "StateFlow represents state (always has an initial replay of 1 and equality checks). SharedFlow is an event bus without state conflation.",
            boxLevel = 2,
            easeFactor = 2.5
        )
    )

    private var currentIndex = 0
    private var isAnswerVisible = false

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        showCurrentCard()
        setupListeners()
    }

    private fun setupListeners() {
        btnFlipCard.setOnAction {
            isAnswerVisible = !isAnswerVisible
            lblCardAnswer.isVisible = isAnswerVisible
            btnFlipCard.text = if (isAnswerVisible) "Hide Answer" else "Show Answer"
        }

        btnMarkReview.setOnAction { handleReview(quality = 1) }
        btnMarkGood.setOnAction { handleReview(quality = 3) }
        btnMarkKnown.setOnAction { handleReview(quality = 5) }
    }

    private fun handleReview(quality: Int) {
        if (studyDeck.isNotEmpty()) {
            val current = studyDeck[currentIndex]
            val result = SpacedRepetitionAlgorithm.calculateNextReview(current, quality)
            studyDeck[currentIndex] = current.copy(
                boxLevel = result.newBoxLevel,
                easeFactor = result.newEaseFactor
            )

            currentIndex = (currentIndex + 1) % studyDeck.size
            isAnswerVisible = false
            showCurrentCard()
        }
    }

    private fun showCurrentCard() {
        if (studyDeck.isNotEmpty()) {
            val card = studyDeck[currentIndex]
            lblCardBadge.text = "Card ${currentIndex + 1} of ${studyDeck.size} (Box ${card.boxLevel})"
            lblCardQuestion.text = card.question
            lblCardAnswer.text = card.answer
            lblCardAnswer.isVisible = false
            btnFlipCard.text = "Show Answer"

            val progress = (currentIndex + 1).toDouble() / studyDeck.size.toDouble()
            prgMastery.progress = progress
            lblProgress.text = "Session Progress: ${(progress * 100).toInt()}% completed"
        }
    }
}
