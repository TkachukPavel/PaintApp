package Figures.ellipse;

import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;

import java.awt.*;

public class Circle extends Ellipse {

	public Circle(){

	}

    public Circle(Point2D theCenter, Point2D cornerPoint) {
        super(theCenter, cornerPoint);
    }

    public Circle(Point2D theCenter, Point2D cornerPoint, int frameWidth, Paint frameColor, Paint fillColor) {
        super(theCenter, cornerPoint, frameWidth, frameColor, fillColor);
    }

    @Override
    public double[] setCornerPoint(Point2D cornerPoint) {
        this.cornerPoint = cornerPoint;
        Point2D theCenter = getLocation();
        double [] size = new double[2];
        double max = Math.max(2 * (theCenter.getX() - cornerPoint.getX()), 2 * (theCenter.getY() - cornerPoint.getY()));
        size[0] = max;
        size[1] = max;
        return size;
    }
}