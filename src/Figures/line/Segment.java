package Figures.line;

import Figures.base.Shape1D;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.awt.*;

public class Segment extends Shape1D {

    public Segment(Point2D theCenter, Point2D endPoint, int frameWidth, Paint frameColor) {
        super(theCenter, frameWidth, frameColor);
        secondPoint = endPoint;
    }

    @Override
    public void draw(GraphicsContext g) {
        Point2D startPoint = getLocation();
//        g.setStroke(new BasicStroke(getFrameWidth()));
        g.setFill(javafx.scene.paint.Paint.valueOf(getFrameColor().toString()));
        g.moveTo(startPoint.getX(), startPoint.getY());
        g.lineTo( secondPoint.getX(), secondPoint.getY());
    }

    @Override
    public boolean contains(Point2D pt) {
        Point2D theCenter = getLocation();
        int a = (int)(secondPoint.getY() - theCenter.getY());
        int b = (int)(secondPoint.getX() - theCenter.getX());
        double d = (a * pt.getX() - b * pt.getY() + b * theCenter.getY() - a * theCenter.getX()) / (Math.sqrt(a * a + b * b));
        return Math.abs(d) < getFrameWidth() / 2;
    }

    @Override
    public void move(Point2D pt) {
        Point2D theCenter = getLocation();
        setEndPoint(new Point2D(secondPoint.getX() + pt.getX() - theCenter.getX(), secondPoint.getY() + pt.getY() - theCenter.getY()));
        super.move(pt);
    }

    public Point2D getEndPoint() {
        return secondPoint;
    }

    public void setEndPoint(Point2D endPoint) {
        setEndPoint(endPoint, false);
    }

    public void setEndPoint(Point2D endPoint, boolean smooth) {
        if (!smooth)
            secondPoint = endPoint;
        else {
            Point2D theCenter = getLocation();
            if (Math.abs(theCenter.getX() - endPoint.getX()) < Math.abs(theCenter.getY() - endPoint.getY()))
                secondPoint = new Point2D(theCenter.getX(), endPoint.getY());
            else
                secondPoint = new Point2D(endPoint.getX(), theCenter.getY());
        }
    }
}