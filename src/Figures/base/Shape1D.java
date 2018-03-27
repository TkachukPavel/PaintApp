package Figures.base;

import java.awt.*;
import Figures.base.Shape;

public abstract class Shape1D extends Shape {
    protected Point secondPoint;

    public Shape1D(){

    }

    public Shape1D(Point theCenter) {
        super(theCenter);
    }

    public Shape1D(Point theCenter, int frameWidth, Color frameColor) {
        super(theCenter, frameWidth, frameColor);
    }
}
