module movies.rate {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens movies.rate.controller to javafx.fxml;
    exports movies.rate;
    exports movies.rate.controller;
}
