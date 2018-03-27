package Figures.base;

import java.awt.*;


import Figures.base.Shape;
import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;

public abstract class Shape1D extends Shape {
    protected Point2D secondPoint;

    public Shape1D(){}

    public Shape1D(Point2D theCenter) {
        super(theCenter);
    }

    public Shape1D(Point2D theCenter, int frameWidth, Paint frameColor) {
        super(theCenter, frameWidth, frameColor);
    }
}
