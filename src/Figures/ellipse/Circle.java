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
    protected void adaptCornerPoint(Point2D theCenter){
        int deltaX = (int)(theCenter.getX()-cornerPoint.getX());
        int deltaY = (int) (theCenter.getY()-cornerPoint.getY());
        if (deltaX<0) {
            double x = cornerPoint.getX() + 2 * deltaX;
            double y = cornerPoint.getY();
            cornerPoint = new Point2D(x, y);
        }
        if (deltaY<0) {
            double x = cornerPoint.getX();
            double y = cornerPoint.getY() + 2 * deltaY;
            cornerPoint = new Point2D(x, y);
        }
        cornerPoint = new Point2D(cornerPoint.getX(), theCenter.getY()-theCenter.getX()+cornerPoint.getX());
    }
}