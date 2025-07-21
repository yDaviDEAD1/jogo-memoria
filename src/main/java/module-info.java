module com.example.memorygame {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson; // Você já deve ter esta linha

    // Linhas que você já pode ter
    opens com.example.memorygame to javafx.fxml;
    exports com.example.memorygame;
    exports com.example.memorygame.Controller;
    opens com.example.memorygame.Controller to javafx.fxml;

    // --- ADICIONE ESTA LINHA ABAIXO ---
    // Ela permite que a biblioteca Gson acesse suas classes de modelo via reflexão.
    opens com.example.memorygame.Model to com.google.gson;
}