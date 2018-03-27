package GUI;

import Figures.base.Shape;
import Figures.ellipse.Ellipse;
import Figures.ellipse.Rectangle;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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


    private boolean isDrawing = false;
    private List<Shape> figures = new ArrayList<>();
    private Shape currentFigure;
    private Point currentCenter;
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

    @FXML
    public void onMousePressed(MouseEvent mouseEvent) {
        switch (currentMode){
            case SELECTION:
//                if (!isDrawing){
//                    currentFigure = figures
//                                            .stream()
//                                            .filter(shape -> shape.contains(new Point(Double.valueOf(mouseEvent.getX()).intValue(),
//                                                                            Double.valueOf(mouseEvent.getY()).intValue())))
//                                            .findFirst()
//                                            .get();
//                    figures.remove(currentFigure);
//                    figures.add(0, currentFigure);
//                } else {
//                    currentFigure.move(new Point());
//                }
                break;
            case DRAW_RAY:
                break;
            case DRAW_lINE:
                break;
            case DRAW_SEGMENT:
                break;
            case DRAW_ELLIPSE:
                break;
            case DRAW_CIRCLE:
                break;
            case DRAW_POLYGON:
                break;
            case DRAW_RECTANGLE:
                if (!isDrawing){
                    currentCenter = new Point(Double.valueOf(mouseEvent.getX()).intValue(),
                                              Double.valueOf(mouseEvent.getX()).intValue());
                    currentFigure = new Rectangle(currentCenter, currentCenter);
                    figures.add(currentFigure);
                    isDrawing = true;
                } else {
                    ((Rectangle) currentFigure).setCornerPoint(new Point(Double.valueOf(mouseEvent.getX()).intValue(),
                            Double.valueOf(mouseEvent.getX()).intValue()));


                }
                break;
            case DRAW_RIGHT_TRIANGLE:
                break;
            case DRAW_ISOSECELES_TRIANGLE:
                break;
            case DRAW_RHOMBUS:
                break;
            case DRAW_SYMMETRIC_POLYGON:
                break;
        }
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
