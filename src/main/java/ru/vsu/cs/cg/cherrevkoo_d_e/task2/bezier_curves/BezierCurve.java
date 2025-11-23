package ru.vsu.cs.cg.cherrevkoo_d_e.task2.bezier_curves;

import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.List;

public class BezierCurve {

    private List<Point2D> controlPoints;

    public void setControlPoints(List<Point2D> controlPoints) {
        this.controlPoints = controlPoints;
    }

    Point2D computePoint(double t) {
        if (controlPoints == null || controlPoints.isEmpty()) {
            return null;
        }
        if (controlPoints.size() == 1) {
            return controlPoints.get(0);
        }
        return deCasteljau(controlPoints, t);
    }

    List<Point2D> computeCurves(double step) {
        List<Point2D> curve = new ArrayList<>();
        if (controlPoints == null || controlPoints.size() < 2) {
            return curve;
        }

        for (double t = 0.0; t <= 1.0; t += step) {
            Point2D p = computePoint(t);
            if (p != null) {
                curve.add(p);
            }
        }
        return curve;
    }

    private Point2D deCasteljau(List<Point2D> points, double t) {
        List<Point2D> temp = new ArrayList<>(points);

        while (temp.size() > 1) {
            temp = deCasteljauStep(temp, t);
        }

        return temp.get(0);
    }

    private List<Point2D> deCasteljauStep(List<Point2D> points, double t) {
        List<Point2D> next = new ArrayList<>();

        for (int i = 0; i < points.size() - 1; i++) {
            Point2D p0 = points.get(i);
            Point2D p1 = points.get(i + 1);

            double x = (1 - t) * p0.getX() + t * p1.getX();
            double y = (1 - t) * p0.getY() + t * p1.getY();

            next.add(new Point2D(x, y));
        }

        return next;
    }
}