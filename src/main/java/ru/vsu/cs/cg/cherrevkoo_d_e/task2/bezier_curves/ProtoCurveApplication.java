package ru.vsu.cs.cg.cherrevkoo_d_e.task2.bezier_curves;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProtoCurveApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        System.setProperty("prism.forceGPU", "true");

        FXMLLoader fxmlLoader = new FXMLLoader(
                ProtoCurveApplication.class.getResource(
                        "/ru/vsu/cs/cg/cherrevkoo_d_e/task2/bezier_curves/mainwindow.fxml"
                )
        );

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Proto Curve App");
        stage.setScene(scene);
        stage.show();
    }
}