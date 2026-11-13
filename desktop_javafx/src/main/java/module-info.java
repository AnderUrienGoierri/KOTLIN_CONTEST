module org.goierri.lecturesync.desktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires kotlinx.coroutines.core;

    opens org.goierri.lecturesync.desktop to javafx.fxml;
    opens org.goierri.lecturesync.desktop.controllers to javafx.fxml;
    opens org.goierri.lecturesync.domain.models to javafx.base;

    exports org.goierri.lecturesync.desktop;
    exports org.goierri.lecturesync.desktop.controllers;
}
