module com.cgvsu.protocurvefxapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens ru.vsu.cs.cg.cherrevkoo_d_e.task2.bezier_curves to javafx.fxml;
    exports ru.vsu.cs.cg.cherrevkoo_d_e.task2.bezier_curves;
}