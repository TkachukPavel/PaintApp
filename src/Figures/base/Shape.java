package Figures.base;


import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.awt.*;


public abstract class Shape {

    private Paint frameColor = Paint.valueOf(Color.black.toString());
    private int frameWidth = 1;
    private Point2D theCenter;

    public Shape() {

    }

    public Shape(Point2D theCenter) {
        this.theCenter = theCenter;
    }

    public Shape(Point2D theCenter, int frameWidth, Paint frameColor) {
        this.theCenter = theCenter;
        this.frameWidth = frameWidth;
        this.frameColor = frameColor;
    }

    public abstract void draw(GraphicsContext g);

    public abstract boolean contains(Point2D pt);

    public Point2D getLocation() {
        return theCenter;
    }

    public void setLocation(Point2D theCenter) {
        this.theCenter=theCenter;
    }

    public void move(Point2D pt) {
        theCenter = pt;
    }

    public Paint getFrameColor() {
        return frameColor;
    }

    public void setFrameColor(Paint frameColor) {
        this.frameColor = frameColor;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

}