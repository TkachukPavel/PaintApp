package GUI;

import Figures.base.Shape;
import Figures.ellipse.Circle;
import Figures.ellipse.Ellipse;
import Figures.ellipse.Rectangle;
import Figures.line.Line;
import Figures.line.Ray;
import Figures.line.Segment;
import Figures.polygon.Parallelogram;
import Figures.polygon.Rhombus;
import Figures.polygon.SymetricPolygon;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
    public Button bttnParallelogram;
    public Button bttnRectangle;
    public Button bttnRhombus;
    public Button bttnIsosecelesTriangle;
    public Button bttnSymmetricPolygon;
    public Button bttnRightTriangle;
    public ColorPicker clrPckrLineColor;
    public ColorPicker clrPckrFillColor;
    private Mode currentMode = Mode.SELECTION;
    private int symPolEdgesAmount = 0;
    private Paint fillColor = Color.valueOf("White");
    private Paint strokeColor = Color.valueOf("White");


    private boolean isDrawing = false;
    private List<Shape> figures = new ArrayList<>();
    private Shape currentFigure;
    private Point2D currentCenter;

    @FXML
    public void initialize() {
        bttnSelection.setOnAction(event -> currentMode = Mode.SELECTION);
        bttnLine.setOnAction(event -> currentMode = Mode.DRAW_lINE);
        bttnRay.setOnAction(event -> currentMode = Mode.DRAW_RAY);
        bttnSegment.setOnAction(event -> currentMode = Mode.DRAW_SEGMENT);
        bttnEllipse.setOnAction(event -> currentMode = Mode.DRAW_ELLIPSE);
        bttnCircle.setOnAction(event -> currentMode = Mode.DRAW_CIRCLE);
        bttnParallelogram.setOnAction(event -> currentMode = Mode.DRAW_PARALLELOGRAM);
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
        clrPckrLineColor.setOnAction(event -> strokeColor = clrPckrLineColor.getValue());
    }

    @FXML
    public void onMousePressed(MouseEvent mouseEvent) {
        switch (currentMode) {
            case SELECTION:
                currentFigure = null;
                Point2D currentMouse = new Point2D(mouseEvent.getSceneX(), mouseEvent.getSceneY());
                int i = figures.size() - 1;
                while (i >= 0 && !figures.get(i).contains(currentMouse)){
                    i--;
                }
                if (i >= 0){
                    System.out.println("find");
                    currentFigure = figures.get(i);
                    figures.remove(i);
                }
                break;
            case DRAW_RAY:
                currentCenter = new Point2D(mouseEvent.getSceneX(),
                        mouseEvent.getSceneY());
                currentFigure = new Ray(currentCenter, currentCenter, 1, strokeColor);
//                System.out.println(isDrawing);
//                isDrawing = true;
                updateCanvas();
                currentFigure.draw(canvas.getGraphicsContext2D());
                break;
            case DRAW_lINE:
                currentCenter = new Point2D(mouseEvent.getSceneX(),
                        mouseEvent.getSceneY());
                currentFigure = new Line(currentCenter, currentCenter, 1, strokeColor);
//                System.out.println(isDrawing);
//                isDrawing = true;
                updateCanvas();
                currentFigure.draw(canvas.getGraphicsContext2D());
                break;
            case DRAW_SEGMENT:
                currentCenter = new Point2D(mouseEvent.getSceneX(),
                        mouseEvent.getSceneY());
                currentFigure = new Segment(currentCenter, currentCenter, 1, strokeColor);
//                System.out.println(isDrawing);
//                isDrawing = true;
                updateCanvas();
                currentFigure.draw(canvas.getGraphicsContext2D());
                break;
            case DRAW_ELLIPSE:
                currentCenter = new Point2D(mouseEvent.getSceneX(),
                        mouseEvent.getSceneY());
                currentFigure = new Ellipse(currentCenter, currentCenter, 1, strokeColor, fillColor);
//                System.out.println(isDrawing);
//                isDrawing = true;
                updateCanvas();
                currentFigure.draw(canvas.getGraphicsContext2D());
                break;
            case DRAW_CIRCLE:
                currentCenter = new Point2D(mouseEvent.getSceneX(),
                        mouseEvent.getSceneY());
                currentFigure = new Circle(currentCenter, currentCenter, 1, strokeColor, fillColor);
//                System.out.println(isDrawing);
//                isDrawing = true;
                updateCanvas();
                currentFigure.draw(canvas.getGraphicsContext2D());
                break;
            case DRAW_PARALLELOGRAM:
                currentCenter = new Point2D(mouseEvent.getSceneX(),
                        mouseEvent.getSceneY());
                currentFigure = new Parallelogram(currentCenter, currentCenter, 1, strokeColor, fillColor);
//                System.out.println(isDrawing);
//                isDrawing = true;
                updateCanvas();
                currentFigure.draw(canvas.getGraphicsContext2D());
                break;
            case DRAW_RECTANGLE:
                currentCenter = new Point2D(mouseEvent.getSceneX(),
                        mouseEvent.getSceneY());
                currentFigure = new Rectangle(currentCenter, currentCenter, 1, strokeColor, fillColor);
//                System.out.println(isDrawing);
//                isDrawing = true;
                updateCanvas();
                currentFigure.draw(canvas.getGraphicsContext2D());
                break;
            case DRAW_RIGHT_TRIANGLE:
                break;
            case DRAW_ISOSECELES_TRIANGLE:
                break;
            case DRAW_RHOMBUS:
                currentCenter = new Point2D(mouseEvent.getSceneX(),
                        mouseEvent.getSceneY());
                currentFigure = new Rhombus(currentCenter, currentCenter, 1, strokeColor, fillColor);
//                System.out.println(isDrawing);
//                isDrawing = true;
                updateCanvas();
                currentFigure.draw(canvas.getGraphicsContext2D());
                break;
            case DRAW_SYMMETRIC_POLYGON:
                currentCenter = new Point2D(mouseEvent.getSceneX(),
                        mouseEvent.getSceneY());
                currentFigure = new SymetricPolygon(currentCenter, currentCenter, symPolEdgesAmount, 1, strokeColor, fillColor);
//                System.out.println(isDrawing);
//                isDrawing = true;
                updateCanvas();
                currentFigure.draw(canvas.getGraphicsContext2D());
                break;
        }
    }


    private void updateCanvas() {
        canvas.getGraphicsContext2D().clearRect(0, 0, 600, 400);
        for (Shape figure : figures) {
            figure.draw(canvas.getGraphicsContext2D());
        }
    }

    public void onMouseReleased(MouseEvent mouseEvent) {
        switch (currentMode) {

            case SELECTION:
                if (currentFigure != null) {
                    figures.add(currentFigure);
                }
                break;
            case DRAW_RAY:
                figures.add(currentFigure);
                break;
            case DRAW_lINE:
                figures.add(currentFigure);
                break;
            case DRAW_SEGMENT:
                figures.add(currentFigure);
                break;
            case DRAW_ELLIPSE:
                figures.add(currentFigure);
                break;
            case DRAW_CIRCLE:
                figures.add(currentFigure);
                break;
            case DRAW_PARALLELOGRAM:
                figures.add(currentFigure);
                break;
            case DRAW_RECTANGLE:
                figures.add(currentFigure);
//                isDrawing = false;
//                currentFigure = null;
                break;
            case DRAW_RIGHT_TRIANGLE:
                break;
            case DRAW_ISOSECELES_TRIANGLE:
                break;
            case DRAW_RHOMBUS:
                figures.add(currentFigure);
                break;
            case DRAW_SYMMETRIC_POLYGON:
                figures.add(currentFigure);
                break;
        }
    }

    public void onMouseDragged(MouseEvent mouseEvent) {
        switch (currentMode) {
            case SELECTION:
                Point2D currentMouse = new Point2D(mouseEvent.getSceneX(), mouseEvent.getSceneY());

                if (currentFigure != null){
                    currentFigure.move(currentMouse);
                    updateCanvas();
                    currentFigure.draw(canvas.getGraphicsContext2D());
                }
                break;
            case DRAW_RAY:
                updateCanvas();

                currentMouse = new Point2D(mouseEvent.getSceneX(), mouseEvent.getSceneY());
                currentFigure = new Ray(currentCenter,
                        currentMouse,
                        1,
                        strokeColor);
                currentFigure.draw(canvas.getGraphicsContext2D());
                break;
            case DRAW_lINE:
                updateCanvas();

                currentMouse = new Point2D(mouseEvent.getSceneX(), mouseEvent.getSceneY());
                currentFigure = new Line(currentCenter,
                        currentMouse,
                        1,
                        strokeColor);
                currentFigure.draw(canvas.getGraphicsContext2D());
                break;
            case DRAW_SEGMENT:
                updateCanvas();

                currentMouse = new Point2D(mouseEvent.getSceneX(), mouseEvent.getSceneY());
                currentFigure = new Segment(currentCenter,
                        currentMouse,
                        1,
                        strokeColor);
                currentFigure.draw(canvas.getGraphicsContext2D());
                break;
            case DRAW_ELLIPSE:
                updateCanvas();

                currentMouse = new Point2D(mouseEvent.getSceneX(), mouseEvent.getSceneY());
                Point2D center = new Point2D((currentCenter.getX() + currentMouse.getX())/2, (currentMouse.getY() + currentCenter.getY())/2);
                Point2D right = new Point2D (Math.min(currentCenter.getX(), currentMouse.getX()), Math.min(currentCenter.getY(), currentMouse.getY()));
                currentFigure = new Ellipse(center,
                        right,
                        1,
                        strokeColor,
                        fillColor);
                currentFigure.draw(canvas.getGraphicsContext2D());
                break;
            case DRAW_CIRCLE:
                updateCanvas();

                currentMouse = new Point2D(mouseEvent.getSceneX(), mouseEvent.getSceneY());
                center = new Point2D((currentCenter.getX() + currentMouse.getX())/2, (currentMouse.getY() + currentCenter.getY())/2);
                right = new Point2D (Math.min(currentCenter.getX(), currentMouse.getX()), Math.min(currentCenter.getY(), currentMouse.getY()));
                currentFigure = new Circle(center,
                        right,
                        1,
                        strokeColor,
                        fillColor);
                currentFigure.draw(canvas.getGraphicsContext2D());
                break;
            case DRAW_PARALLELOGRAM:
                updateCanvas();

                currentMouse = new Point2D(mouseEvent.getSceneX(), mouseEvent.getSceneY());
                center = new Point2D((currentCenter.getX() + currentMouse.getX())/2, (currentMouse.getY() + currentCenter.getY())/2);
                right = new Point2D (Math.min(currentCenter.getX(), currentMouse.getX()), Math.min(currentCenter.getY(), currentMouse.getY()));
                currentFigure = new Parallelogram(center,
                        right,
                        1,
                        strokeColor,
                        fillColor);
                currentFigure.draw(canvas.getGraphicsContext2D());
                break;
            case DRAW_RECTANGLE:
                updateCanvas();

                currentMouse = new Point2D(mouseEvent.getSceneX(), mouseEvent.getSceneY());
                center = new Point2D((currentCenter.getX() + currentMouse.getX())/2, (currentMouse.getY() + currentCenter.getY())/2);
                right = new Point2D (Math.min(currentCenter.getX(), currentMouse.getX()), Math.min(currentCenter.getY(), currentMouse.getY()));
                currentFigure = new Rectangle(center,
                                                right,
                                                1,
                                                strokeColor,
                                                fillColor);
                currentFigure.draw(canvas.getGraphicsContext2D());
                break;
            case DRAW_RIGHT_TRIANGLE:
                break;
            case DRAW_ISOSECELES_TRIANGLE:
                break;
            case DRAW_RHOMBUS:
                updateCanvas();

                currentMouse = new Point2D(mouseEvent.getSceneX(), mouseEvent.getSceneY());
                center = new Point2D((currentCenter.getX() + currentMouse.getX())/2, (currentMouse.getY() + currentCenter.getY())/2);
                right = new Point2D (Math.min(currentCenter.getX(), currentMouse.getX()), Math.min(currentCenter.getY(), currentMouse.getY()));
                currentFigure = new Rhombus(center,
                        right,
                        1,
                        strokeColor,
                        fillColor);
                currentFigure.draw(canvas.getGraphicsContext2D());
                break;
            case DRAW_SYMMETRIC_POLYGON:
                updateCanvas();

                currentMouse = new Point2D(mouseEvent.getSceneX(), mouseEvent.getSceneY());
                center = new Point2D((currentCenter.getX() + currentMouse.getX())/2, (currentMouse.getY() + currentCenter.getY())/2);
                right = new Point2D (Math.min(currentCenter.getX(), currentMouse.getX()), Math.min(currentCenter.getY(), currentMouse.getY()));
                currentFigure = new SymetricPolygon(center,
                        right, symPolEdgesAmount,
                        1,
                        strokeColor,
                        fillColor);
                currentFigure.draw(canvas.getGraphicsContext2D());
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
        DRAW_PARALLELOGRAM,
        DRAW_RECTANGLE,
        DRAW_RIGHT_TRIANGLE,
        DRAW_ISOSECELES_TRIANGLE,
        DRAW_RHOMBUS,
        DRAW_SYMMETRIC_POLYGON,
    }
}
