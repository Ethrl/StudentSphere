package org.studentsphere.helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

public class SceneLoader {

    private static Object currentController;

    public static void loadScene(String fxmlPath) {
        try {
            Stage stage = (Stage) Stage.getWindows()
                    .stream()
                    .filter(window -> window instanceof Stage && window.isShowing())
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("No active window found."));

            FXMLLoader loader = new FXMLLoader(SceneLoader.class.getResource(fxmlPath));
            Scene scene = new Scene(loader.load());
            currentController = loader.getController(); // Przechowywanie kontrolera
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load scene: " + fxmlPath + " - " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static <T> void loadSceneWithData(String fxmlPath, Consumer<T> controllerConsumer) {
        try {
            Stage stage = (Stage) Stage.getWindows()
                    .stream()
                    .filter(window -> window instanceof Stage && window.isShowing())
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("No active window found."));

            FXMLLoader loader = new FXMLLoader(SceneLoader.class.getResource(fxmlPath));
            Scene scene = new Scene(loader.load());

            T controller = loader.getController();
            controllerConsumer.accept(controller);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load scene with data: " + fxmlPath + " - " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static <T> T getController() {
        return (T) currentController;
    }
}