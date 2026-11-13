package org.goierri.lecturesync.desktop.controllers

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import java.net.URL
import java.util.*

class MainController : Initializable {

    @FXML private lateinit var rootPane: BorderPane
    @FXML private lateinit var btnNavDashboard: Button
    @FXML private lateinit var btnNavLive: Button
    @FXML private lateinit var btnNavFlashcards: Button
    @FXML private lateinit var btnNavMindMaps: Button
    @FXML private lateinit var btnNavAudioArchive: Button
    @FXML private lateinit var btnNavSettings: Button
    @FXML private lateinit var btnStartSession: Button
    @FXML private lateinit var txtSearch: TextField
    @FXML private lateinit var statusLabel: Label

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        setupNavigation()
        loadDashboardView()
    }

    private fun setupNavigation() {
        btnNavDashboard.setOnAction {
            setActiveButton(btnNavDashboard)
            loadDashboardView()
        }

        btnNavLive.setOnAction {
            setActiveButton(btnNavLive)
            loadSubView("/fxml/live_studio_view.fxml")
        }

        btnNavFlashcards.setOnAction {
            setActiveButton(btnNavFlashcards)
            loadSubView("/fxml/flashcards_view.fxml")
        }

        btnNavMindMaps.setOnAction {
            setActiveButton(btnNavMindMaps)
            loadSubView("/fxml/mind_maps_view.fxml")
        }

        btnNavAudioArchive.setOnAction {
            setActiveButton(btnNavAudioArchive)
            loadSubView("/fxml/audio_archive_view.fxml")
        }

        btnNavSettings.setOnAction {
            setActiveButton(btnNavSettings)
            loadSubView("/fxml/settings_view.fxml")
        }

        btnStartSession.setOnAction {
            setActiveButton(btnNavLive)
            loadSubView("/fxml/live_studio_view.fxml")
        }
    }

    private fun setActiveButton(activeBtn: Button) {
        listOf(
            btnNavDashboard,
            btnNavLive,
            btnNavFlashcards,
            btnNavMindMaps,
            btnNavAudioArchive,
            btnNavSettings
        ).forEach { btn ->
            btn.styleClass.remove("nav-button-active")
        }
        activeBtn.styleClass.add("nav-button-active")
    }

    private fun loadDashboardView() {
        loadSubView("/fxml/dashboard_view.fxml")
    }

    private fun loadSubView(fxmlPath: String) {
        runCatching {
            val url = javaClass.getResource(fxmlPath)
            if (url != null) {
                val node = FXMLLoader.load<Node>(url)
                rootPane.center = node
                statusLabel.text = "Loaded: $fxmlPath | Kotlin Multiplatform Contest 2027"
            } else {
                statusLabel.text = "Error: Resource $fxmlPath not found"
            }
        }.onFailure { ex ->
            statusLabel.text = "Error loading view: ${ex.message}"
            ex.printStackTrace()
        }
    }
}
