package ru.vsu.cs.cg.cherrevkoo_d_e.task2.bezier_curves;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class BezierCurve {
    List<Point2D> controlPoints;

    public void setControlPoints(List<Point2D> controlPoints) {
        this.controlPoints = controlPoints;
    }

    Point2D computePoint(double t) {
//        int n = controlPoints.size() - 1;
//        if (n < 0) {
//            return null;
//        }
//        if (n == 0) {
//            return controlPoints.get(0);
//        }
//        double x = 0.0;
//        double y = 0.0;
//        for (int k = 0; k <= n; k++) {
//            Point2D pk = controlPoints.get(k);
//            x += pk.getX() * bernstein(k, n, t);
//            y += pk.getY() * bernstein(k, n, t);
//        }
//        return new Point2D(x, y);
//    }
//
//    List<Point2D> computeCurves(double step){
//        List<Point2D> curvePoints = new ArrayList<>();
//        for (double t = 0.0; t <= 1.0; t += step) {
//            Point2D p = computePoint(t);
//            curvePoints.add(p);
//        }
//        return curvePoints;
//    }
//
//    private long factorial(int n){
//        long result = 1;
//        for (int i = 2; i <= n; i++) {
//            result *= i;
//        }
//        return result;
//    }
//
//    long binomal(int n, int k){
//        return factorial(n)/(factorial(k) * factorial(n-k));
//    }
//
//    double bernstein(int k, int n, double t){
//        return binomal(n,k) * Math.pow(t, k) * Math.pow(1-t, n-k);
//    }
//}

        if (controlPoints == null || controlPoints.isEmpty()) {
            return null;
        }
        if (controlPoints.size() == 1) {
            return controlPoints.get(0);
        }

        List<Point2D> temp = new ArrayList<>(controlPoints);

        while (temp.size() > 1) {
            List<Point2D> next = new ArrayList<>();
            for (int i = 0; i < temp.size() - 1; i++) {
                Point2D p0 = temp.get(i);
                Point2D p1 = temp.get(i + 1);

                double x = (1 - t) * p0.getX() + t * p1.getX();
                double y = (1 - t) * p0.getY() + t * p1.getY();

                next.add(new Point2D(x, y));
            }
            temp = next;
        }

        return temp.get(0);
    }

    List<Point2D> computeCurves(double step) {
        List<Point2D> curve = new ArrayList<>();

        if (controlPoints == null || controlPoints.size() < 2) {
            return curve;
        }

        for (double t = 0.0; t <= 1.0; t += step) {
            curve.add(computePoint(t));
        }

        return curve;
    }
}
