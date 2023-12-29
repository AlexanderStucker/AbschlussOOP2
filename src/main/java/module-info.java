module movies.rate {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens movies.rate to javafx.fxml;
    exports movies.rate;
}
