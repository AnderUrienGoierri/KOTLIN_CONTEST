plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.javafx)
    application
}

javafx {
    version = "21.0.2"
    modules = listOf("javafx.controls", "javafx.fxml")
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.javafx)
}

application {
    mainClass.set("org.goierri.lecturesync.desktop.MainApp")
}
