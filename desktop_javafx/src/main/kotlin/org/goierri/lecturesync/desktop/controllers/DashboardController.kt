package org.goierri.lecturesync.desktop.controllers

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import org.goierri.lecturesync.domain.models.Lecture
import org.goierri.lecturesync.domain.models.LectureStatus
import java.net.URL
import java.util.*

class DashboardController : Initializable {

    @FXML private lateinit var lecturesTable: TableView<Lecture>
    @FXML private lateinit var btnRecordNew: Button
    @FXML private lateinit var btnFilter: Button

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        setupTableColumns()
        loadLectures()
    }

    private fun setupTableColumns() {
        val colTitle = TableColumn<Lecture, String>("Subject / Topic").apply {
            cellValueFactory = PropertyValueFactory("title")
            prefWidth = 260.0
        }

        val colSubject = TableColumn<Lecture, String>("Subject").apply {
            cellValueFactory = PropertyValueFactory("subject")
            prefWidth = 160.0
        }

        val colDate = TableColumn<Lecture, String>("Date").apply {
            cellValueFactory = PropertyValueFactory("createdAt")
            prefWidth = 140.0
        }

        val colDuration = TableColumn<Lecture, Long>("Duration (s)").apply {
            cellValueFactory = PropertyValueFactory("durationSeconds")
            prefWidth = 100.0
        }

        val colStatus = TableColumn<Lecture, LectureStatus>("Status").apply {
            cellValueFactory = PropertyValueFactory("status")
            prefWidth = 120.0
        }

        val colCards = TableColumn<Lecture, Int>("Cards").apply {
            cellValueFactory = PropertyValueFactory("flashcardCount")
            prefWidth = 80.0
        }

        lecturesTable.columns.setAll(colTitle, colSubject, colDate, colDuration, colStatus, colCards)
    }

    private fun loadLectures() {
        val sampleLectures = listOf(
            Lecture(
                id = 1,
                title = "Kotlin Multiplatform Concurrency",
                subject = "Distributed Systems",
                lecturer = "Prof. Martinez",
                createdAt = "2026-11-13 10:14",
                durationSeconds = 4460,
                status = LectureStatus.COMPLETED,
                flashcardCount = 14
            ),
            Lecture(
                id = 2,
                title = "JavaFX MVC Architecture & FXML",
                subject = "Desktop Development",
                lecturer = "Prof. Telleria",
                createdAt = "2026-11-12 09:00",
                durationSeconds = 3250,
                status = LectureStatus.COMPLETED,
                flashcardCount = 8
            )
        )
        lecturesTable.items = FXCollections.observableArrayList(sampleLectures)
    }
}
