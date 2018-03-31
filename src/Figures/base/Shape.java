package Figures.base;


import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;


public abstract class Shape {

    private Paint strokeColor = Paint.valueOf("black");
    private double lineWidth = 1;
    private Point2D centerPt;

    public Shape() {}

    public Shape(Point2D centerPt) {
        this.centerPt = centerPt;
    }

    public Shape(Point2D centerPt, double lineWidth, Paint strokeColor) {
        this.centerPt = centerPt;
        this.lineWidth = lineWidth;
        this.strokeColor = strokeColor;
    }

    public abstract void draw(GraphicsContext gc);

    public abstract boolean contains(Point2D pt);

    public Point2D getLocation() {
        return centerPt;
    }

    public void setLocation(Point2D theCenter) {
        this.centerPt = theCenter;
    }

    public void move(Point2D pt) {
        centerPt = pt;
    }

    public Paint getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Paint strokeColor) {
        this.strokeColor = strokeColor;
    }

    public double getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

}