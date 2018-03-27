package Figures.polygon;


import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;

import java.util.*;

public class Parallelogram extends Polygon {

    public Parallelogram(Point2D theCenter, Point2D cornerPoint, int frameWidth, Paint frameColor, Paint fillColor) {
        super(theCenter, frameWidth, frameColor, fillColor);
        setPoints(getParallelogramPoints(cornerPoint));
    }

    public List<Point2D> getParallelogramPoints(Point2D cornerPoint){
        Point2D theCenter = getLocation();
        List<Point2D> points = new ArrayList(4);
        Point2D upperLeft = new Point2D(2*theCenter.getX()-cornerPoint.getX(), 2*theCenter.getY()-cornerPoint.getY());
        Point2D upperRight = new Point2D(cornerPoint.getX()+cornerPoint.getY()-upperLeft.getY(),upperLeft.getY());
        Point2D bottomLeft = new Point2D(2*theCenter.getX()-upperRight.getX(), 2*theCenter.getY()-upperRight.getY());
        points.add(cornerPoint);
        points.add(upperRight);
        points.add(upperLeft);
        points.add(bottomLeft);
        return points;
    }

    public void setCornerPoint(Point2D pt){
        setPoints(getParallelogramPoints(pt));
    }
}