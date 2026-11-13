package org.goierri.lecturesync.desktop.controllers

import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.util.Duration
import java.net.URL
import java.util.*

class LiveStudioController : Initializable {

    @FXML private lateinit var btnRecToggle: Button
    @FXML private lateinit var lblLiveTimer: Label
    @FXML private lateinit var txtTranscript: Label
    @FXML private lateinit var btnGenerateSummary: Button
    @FXML private lateinit var cardFlashcards: VBox

    private var isRecording = true
    private var elapsedSeconds = 1458L
    private lateinit var timer: Timeline

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        setupTimer()
        setupActions()
    }

    private fun setupTimer() {
        timer = Timeline(KeyFrame(Duration.seconds(1.0), {
            if (isRecording) {
                elapsedSeconds++
                val h = elapsedSeconds / 3600
                val m = (elapsedSeconds % 3600) / 60
                val s = elapsedSeconds % 60
                lblLiveTimer.text = String.format("● REC %02d:%02d:%02d", h, m, s)
            }
        }))
        timer.cycleCount = Timeline.INDEFINITE
        timer.play()
    }

    private fun setupActions() {
        btnRecToggle.setOnAction {
            isRecording = !isRecording
            if (isRecording) {
                btnRecToggle.text = "Stop Recording"
                btnRecToggle.style = "-fx-background-color: #E06C75;"
            } else {
                btnRecToggle.text = "Resume Recording"
                btnRecToggle.style = "-fx-background-color: #28A745;"
            }
        }

        btnGenerateSummary.setOnAction {
            txtTranscript.text = "${txtTranscript.text}\n\n[AI Live Analysis - 10:25 AM]: Key takeaway extracted. Kotlin Coroutines structured concurrency guarantees cancellation hierarchy."
        }
    }
}
