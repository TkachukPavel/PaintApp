package GUI;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

public class Controller {
    public Canvas canvas;
    private Mode currentMode = Mode.SELECTION;

    @FXML
    public void initialize() {
//        GraphicsContext g = canvas.getGraphicsContext2D();
//        g.setFill(Paint.valueOf("black"));
//        g.fillRect(10, 10, 100, 100);
    }

    private enum Mode {
        SELECTION,
        DRAW_RAY,
        DRAW_lINE,
        DRAW_SEGMENT,
        DRAW_ELLIPSE,
        DRAW_CIRCLE,
        DRAW_POLYGON,
        DRAW_RECTANGLE,
        DRAW_RIGHT_TRIANGLE,
        DRAW_ISOSECELES_TRIANGLE,
        DRAW_RHOMBUS,
        DRAW_SYMMETRIC_POLYGON,
    }
}
