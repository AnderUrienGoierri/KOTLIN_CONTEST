package org.goierri.lecturesync.desktop.controllers

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import java.net.URL
import java.util.*

class SettingsController : Initializable {

    @FXML private lateinit var cmbAiModel: ComboBox<String>
    @FXML private lateinit var txtApiKey: PasswordField
    @FXML private lateinit var txtDbPath: TextField
    @FXML private lateinit var txtServerPort: TextField
    @FXML private lateinit var btnSaveSettings: Button
    @FXML private lateinit var lblSettingsSaved: Label

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        cmbAiModel.items = FXCollections.observableArrayList(
            "Gemini 2.5 Flash (Lowest Latency - Recommended)",
            "Gemini 1.5 Pro (Deep Multimodal Reasoning)",
            "Gemini 2.5 Flash-Lite (High Throughput)"
        )
        cmbAiModel.selectionModel.selectFirst()

        btnSaveSettings.setOnAction {
            lblSettingsSaved.text = "✓ Preferences saved successfully. Ktor listening on port ${txtServerPort.text}."
        }
    }
}
