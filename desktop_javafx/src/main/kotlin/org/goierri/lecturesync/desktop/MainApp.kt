package org.goierri.lecturesync.desktop

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class MainApp : Application() {
    override fun start(primaryStage: Stage) {
        val fxmlUrl = javaClass.getResource("/fxml/main_view.fxml") 
            ?: throw IllegalStateException("Resource /fxml/main_view.fxml not found! Check resources folder.")
        
        val loader = FXMLLoader(fxmlUrl)
        val root = loader.load<javafx.scene.Parent>()
        
        val scene = Scene(root, 1280.0, 800.0)
        
        val cssUrl = javaClass.getResource("/css/styles.css")
        if (cssUrl != null) {
            scene.stylesheets.add(cssUrl.toExternalForm())
        }

        primaryStage.title = "LectureSync AI - Kotlin Multiplatform (KotlinConf '27)"
        primaryStage.scene = scene
        primaryStage.minWidth = 1024.0
        primaryStage.minHeight = 700.0
        primaryStage.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(MainApp::class.java, *args)
}
