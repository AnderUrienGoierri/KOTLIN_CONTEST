package org.goierri.lecturesync.desktop.controllers

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.Label
import java.net.URL
import java.util.*

class MindMapsController : Initializable {

    @FXML private lateinit var btnGenerateMindMap: Button
    @FXML private lateinit var btnNodeRoot: Button
    @FXML private lateinit var btnNodeScope: Button
    @FXML private lateinit var btnNodeSupervisor: Button
    @FXML private lateinit var btnNodeStateFlow: Button
    @FXML private lateinit var btnNodeFlow: Button
    @FXML private lateinit var btnNodeJavaFx: Button
    @FXML private lateinit var btnNodeIO: Button
    @FXML private lateinit var lblConceptTitle: Label
    @FXML private lateinit var lblConceptDescription: Label
    @FXML private lateinit var lblLinkedFlashcards: Label
    @FXML private lateinit var btnStudyLinkedCards: Button

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        setupNodeListeners()
    }

    private fun setupNodeListeners() {
        btnNodeRoot.setOnAction {
            updateInspector(
                title = "Kotlin Coroutines (Core)",
                desc = "Lightweight threads for asynchronous and non-blocking multitasking across commonMain Kotlin code.",
                cards = "6 Cards (Mastery 92%)"
            )
        }

        btnNodeScope.setOnAction {
            updateInspector(
                title = "CoroutineScope",
                desc = "Defines the lifetime and boundary of newly launched coroutines. Enforces structured concurrency.",
                cards = "3 Cards (Leitner Box 3)"
            )
        }

        btnNodeSupervisor.setOnAction {
            updateInspector(
                title = "SupervisorJob",
                desc = "A specialized Job implementation where a failure of one child does not cancel the entire scope.",
                cards = "2 Cards (Due Today)"
            )
        }

        btnNodeStateFlow.setOnAction {
            updateInspector(
                title = "StateFlow<T> (Hot Stream)",
                desc = "A state-holder observable flow that emits current and new state updates to active collectors in Desktop JavaFX and Android Compose.",
                cards = "4 Cards (Leitner Box 2)"
            )
        }

        btnNodeFlow.setOnAction {
            updateInspector(
                title = "Flow<T> (Cold Stream)",
                desc = "A cold asynchronous data stream sequentially emitting values upon collection, ideal for database queries and network polling.",
                cards = "5 Cards (Leitner Box 4)"
            )
        }

        btnNodeJavaFx.setOnAction {
            updateInspector(
                title = "Dispatchers.JavaFx",
                desc = "Ensures all UI state updates and animations execute strictly on the JavaFX Application Main Thread.",
                cards = "4 Cards (Leitner Box 2)"
            )
        }

        btnNodeIO.setOnAction {
            updateInspector(
                title = "Dispatchers.IO",
                desc = "Dispatcher designed for offloading blocking I/O tasks like SQLite queries and HTTP requests off the UI thread.",
                cards = "3 Cards (Leitner Box 5)"
            )
        }

        btnGenerateMindMap.setOnAction {
            lblConceptTitle.text = "AI Mind Map Extraction"
            lblConceptDescription.text = "Gemini 2.5 scanned classroom transcript and generated 7 interconnected concept nodes with 14 flashcards."
            lblLinkedFlashcards.text = "Total 14 Flashcards Synced"
        }
    }

    private fun updateInspector(title: String, desc: String, cards: String) {
        lblConceptTitle.text = title
        lblConceptDescription.text = desc
        lblLinkedFlashcards.text = cards
    }
}
