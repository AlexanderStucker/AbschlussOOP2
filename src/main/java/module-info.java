module movies.rate {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens movies.rate.model to javafx.base;
    opens movies.rate.controller to javafx.fxml;
    exports movies.rate;
    exports movies.rate.controller;
}
