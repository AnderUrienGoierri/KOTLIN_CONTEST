package org.goierri.lecturesync.desktop.controllers

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import org.goierri.lecturesync.domain.models.Lecture
import org.goierri.lecturesync.domain.models.LectureStatus
import java.net.URL
import java.util.*

class AudioArchiveController : Initializable {

    @FXML private lateinit var btnPlayPause: Button
    @FXML private lateinit var lblNowPlayingTitle: Label
    @FXML private lateinit var lblNowPlayingMeta: Label
    @FXML private lateinit var btnSpeedToggle: Button
    @FXML private lateinit var prgAudioPlayback: ProgressBar
    @FXML private lateinit var lblCurrentTime: Label
    @FXML private lateinit var lblTotalDuration: Label

    @FXML private lateinit var tblAudioArchive: TableView<Lecture>
    @FXML private lateinit var colAudioTitle: TableColumn<Lecture, String>
    @FXML private lateinit var colAudioSubject: TableColumn<Lecture, String>
    @FXML private lateinit var colAudioDuration: TableColumn<Lecture, Long>
    @FXML private lateinit var colAudioDate: TableColumn<Lecture, String>
    @FXML private lateinit var colAudioAction: TableColumn<Lecture, String>

    private var isPlaying = false
    private var playbackSpeed = 1.0

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        setupPlayerControls()
        setupTable()
    }

    private fun setupPlayerControls() {
        btnPlayPause.setOnAction {
            isPlaying = !isPlaying
            if (isPlaying) {
                btnPlayPause.text = "❚❚ Pause"
                btnPlayPause.style = "-fx-background-color: #E06C75; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 8 16; -fx-background-radius: 6;"
            } else {
                btnPlayPause.text = "▶ Play"
                btnPlayPause.style = "-fx-background-color: #7F52FF; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 8 16; -fx-background-radius: 6;"
            }
        }

        btnSpeedToggle.setOnAction {
            playbackSpeed = when (playbackSpeed) {
                1.0 -> 1.25
                1.25 -> 1.5
                1.5 -> 2.0
                else -> 1.0
            }
            btnSpeedToggle.text = "${playbackSpeed}x Speed"
        }
    }

    private fun setupTable() {
        colAudioTitle.cellValueFactory = PropertyValueFactory("title")
        colAudioSubject.cellValueFactory = PropertyValueFactory("subject")
        colAudioDuration.cellValueFactory = PropertyValueFactory("durationSeconds")
        colAudioDate.cellValueFactory = PropertyValueFactory("createdAt")

        val sampleLectures = listOf(
            Lecture(
                id = 1,
                title = "Kotlin Multiplatform Concurrency & StateFlow",
                subject = "Distributed Systems",
                durationSeconds = 4460,
                status = LectureStatus.COMPLETED,
                createdAt = "2026-11-13 10:14:00"
            ),
            Lecture(
                id = 2,
                title = "JavaFX MVC Architecture & Scene Builder FXML",
                subject = "Desktop Development",
                durationSeconds = 3250,
                status = LectureStatus.COMPLETED,
                createdAt = "2026-11-12 09:00:00"
            ),
            Lecture(
                id = 3,
                title = "SQLite Database Persistence & Spaced Repetition (SM-2)",
                subject = "Software Architecture",
                durationSeconds = 3945,
                status = LectureStatus.COMPLETED,
                createdAt = "2026-11-10 11:30:00"
            )
        )

        tblAudioArchive.items = FXCollections.observableArrayList(sampleLectures)

        tblAudioArchive.selectionModel.selectedItemProperty().addListener { _, _, selected ->
            selected?.let {
                lblNowPlayingTitle.text = it.title
                lblNowPlayingMeta.text = "${it.subject} · Recorded ${it.createdAt} · Opus 48kHz"
                val h = it.durationSeconds / 3600
                val m = (it.durationSeconds % 3600) / 60
                val s = it.durationSeconds % 60
                lblTotalDuration.text = String.format("%02d:%02d:%02d", h, m, s)
                prgAudioPlayback.progress = 0.0
                lblCurrentTime.text = "00:00:00"
            }
        }
    }
}
