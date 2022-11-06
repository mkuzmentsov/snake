module com.mkuzmentsov.snake2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mkuzmentsov.snake2 to javafx.fxml;
    exports com.mkuzmentsov.snake2;
    exports com.mkuzmentsov.snake2.game;
    opens com.mkuzmentsov.snake2.game to javafx.fxml;
}