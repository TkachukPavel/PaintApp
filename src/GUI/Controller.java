package GUI;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;

public class Controller {
    public Canvas canvas;
    public Button bttnSelection;
    public Button bttnLine;
    public Button bttnRay;
    public Button bttnSegment;
    public Button bttnEllipse;
    public Button bttnCircle;
    public Button bttnPolygon;
    public Button bttnRectangle;
    public Button bttnRhombus;
    public Button bttnIsosecelesTriangle;
    public Button bttnSymmetricPolygon;
    public Button bttnRightTriangle;
    public ColorPicker clrPckrLineColor;
    public ColorPicker clrPckrFillColor;
    private Mode currentMode = Mode.SELECTION;
    private int symPolEdgesAmount = 0;
    private Color fillColor;
    private Color lineColor;

    @FXML
    public void initialize() {
        bttnSelection.setOnAction(event -> currentMode = Mode.SELECTION);
        bttnLine.setOnAction(event -> currentMode = Mode.DRAW_lINE);
        bttnRay.setOnAction(event -> currentMode = Mode.DRAW_RAY);
        bttnSegment.setOnAction(event -> currentMode = Mode.DRAW_SEGMENT);
        bttnEllipse.setOnAction(event -> currentMode = Mode.DRAW_ELLIPSE);
        bttnCircle.setOnAction(event -> currentMode = Mode.DRAW_CIRCLE);
        bttnPolygon.setOnAction(event -> currentMode = Mode.DRAW_POLYGON);
        bttnRectangle.setOnAction(event -> currentMode = Mode.DRAW_RECTANGLE);
        bttnRhombus.setOnAction(event -> currentMode = Mode.DRAW_RHOMBUS);
        bttnRightTriangle.setOnAction(event -> currentMode = Mode.DRAW_RIGHT_TRIANGLE);
        bttnIsosecelesTriangle.setOnAction(event -> currentMode = Mode.DRAW_ISOSECELES_TRIANGLE);
        bttnSymmetricPolygon.setOnAction(event -> {
            TextInputDialog inputDialog = new TextInputDialog("3");
            inputDialog.setHeaderText("Edges Amount of Symmetric Polynom");
            inputDialog.setTitle("Edges Amount");
            inputDialog.setContentText("Enter edges amount of symmetric polynom");
            inputDialog.showAndWait().ifPresent(str -> {
                try {
                    symPolEdgesAmount = Integer.valueOf(str);
                    currentMode = Mode.DRAW_SYMMETRIC_POLYGON;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            });
        });
        clrPckrFillColor.setOnAction(event -> fillColor = clrPckrFillColor.getValue());
        clrPckrLineColor.setOnAction(event -> lineColor = clrPckrLineColor.getValue());
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
