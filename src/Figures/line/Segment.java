package Figures.line;

import Figures.base.Shape1D;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Segment extends Shape1D {

    public Segment(Point theCenter, Point endPoint, int frameWidth, Color frameColor) {
        super(theCenter, frameWidth, frameColor);
        secondPoint = endPoint;
    }

    @Override
    public void draw(GraphicsContext g) {
        Point startPoint = getLocation();
//        g.setStroke(new BasicStroke(getFrameWidth()));
        g.setFill(javafx.scene.paint.Paint.valueOf(getFrameColor().toString()));
        g.moveTo(startPoint.x, startPoint.y);
        g.lineTo( secondPoint.x, secondPoint.y);
    }

    @Override
    public boolean contains(Point pt) {
        Point theCenter = getLocation();
        int a = secondPoint.y - theCenter.y;
        int b = secondPoint.x - theCenter.x;
        double d = (a * pt.x - b * pt.y + b * theCenter.y - a * theCenter.x) / (Math.sqrt(a * a + b * b));
        return Math.abs(d) < getFrameWidth() / 2;
    }

    @Override
    public void move(Point pt) {
        Point theCenter = getLocation();
        setEndPoint(new Point(secondPoint.x + pt.x - theCenter.x, secondPoint.y + pt.y - theCenter.y));
        super.move(pt);
    }

    public Point getEndPoint() {
        return secondPoint;
    }

    public void setEndPoint(Point endPoint) {
        setEndPoint(endPoint, false);
    }

    public void setEndPoint(Point endPoint, boolean smooth) {
        if (!smooth)
            secondPoint = endPoint;
        else {
            Point theCenter = getLocation();
            if (Math.abs(theCenter.x - endPoint.x) < Math.abs(theCenter.y - endPoint.y))
                secondPoint = new Point(theCenter.x, endPoint.y);
            else
                secondPoint = new Point(endPoint.x, theCenter.y);
        }
    }
}