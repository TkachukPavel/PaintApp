package Figures.polygon;


import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;

public class Rhombus extends Parallelogram {

    public Rhombus(Point2D theCenter, Point2D cornerPoint, int frameWidth, Paint frameColor, Paint fillColor) {
        super(theCenter, cornerPoint, frameWidth, frameColor, fillColor);
    }

    @Override
    public List<Point2D> getParallelogramPoints(Point2D cornerPoint) {
        Point2D theCenter = getLocation();
        List<Point2D> points = new ArrayList<>(4);
        points.add(new Point2D(cornerPoint.getX(), theCenter.getY()));
        points.add(new Point2D(theCenter.getX(), 2*theCenter.getY()-cornerPoint.getY()));
        points.add(new Point2D(2*theCenter.getX()-cornerPoint.getX(), theCenter.getY()));
        points.add(new Point2D(theCenter.getX(), cornerPoint.getY()));
        return points;
    }
}