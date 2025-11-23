package ru.vsu.cs.cg.cherrevkoo_d_e.task2.bezier_curves;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ProtoCurveController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Canvas canvas;
    private BezierCurve bezierCurve;
    private final List<Point2D> points = new ArrayList<>();
    private int draggedPointIndex = -1;
    private final double radius = 6;

    @FXML
    private void initialize() {
        bezierCurve = new BezierCurve();

        canvas.widthProperty().bind(anchorPane.widthProperty());
        canvas.heightProperty().bind(anchorPane.heightProperty());

        canvas.setOnMousePressed(event -> handleMousePressed(event));
        canvas.setOnMouseDragged(event -> handleMouseDragged(event));
        canvas.setOnMouseReleased(event-> draggedPointIndex = -1);
    }

    private void handleMousePressed(MouseEvent event) {
        Point2D click = new Point2D(event.getX(), event.getY());

        for (int i = 0; i < points.size(); i++) {
            if (click.distance(points.get(i)) <= radius) {

                if (event.getButton() == MouseButton.SECONDARY) {
                    points.remove(i);
                    redraw();
                    return;
                }

                if (event.getButton() == MouseButton.PRIMARY) {
                    draggedPointIndex = i;
                    return;
                }
            }
        }

        if (event.getButton() == MouseButton.PRIMARY) {
            points.add(click);
            redraw();
        }
    }

    private void handleMouseDragged(MouseEvent event) {
        if (draggedPointIndex < 0) return;
        if (!event.isPrimaryButtonDown()) return;

        points.set(draggedPointIndex, new Point2D(event.getX(), event.getY()));
        redraw();
    }

    private void redraw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        double r = 3;

        gc.setFill(Color.RED);
        for (Point2D p : points) {
            gc.fillOval(p.getX() - r, p.getY() - r, 2*r, 2*r);
        }

        gc.setStroke(Color.GRAY);
        for (int i = 0; i < points.size() - 1; i++) {
            gc.strokeLine(points.get(i).getX(), points.get(i).getY(),
                    points.get(i + 1).getX(), points.get(i + 1).getY());
        }

        if (points.size() < 2) return;

        bezierCurve.setControlPoints(points);
        List<Point2D> curve = bezierCurve.computeCurves(0.002);

        gc.setStroke(Color.BLACK);
        for (int i = 0; i < curve.size() - 1; i++) {
            gc.strokeLine(curve.get(i).getX(), curve.get(i).getY(),
                    curve.get(i + 1).getX(), curve.get(i + 1).getY());
        }
    }
}