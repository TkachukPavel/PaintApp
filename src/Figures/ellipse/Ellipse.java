package Figures.ellipse;

import Figures.base.Shape2D;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;


public class Ellipse extends Shape2D {

    protected Point2D cornerPoint;
    private double width;
    private double height;

    public Ellipse() {

    }

    public Ellipse(Point2D theCenter, Point2D cornerPoint) {
        super(theCenter);
        this.cornerPoint = cornerPoint;
        width = Math.abs(theCenter.getX() - cornerPoint.getX());
        height = Math.abs(theCenter.getY() - cornerPoint.getY());
    }

    public Ellipse(Point2D theCenter, Point2D cornerPoint, double frameWidth, Paint frameColor, Paint fillColor) {
        super(theCenter, frameWidth, frameColor, fillColor);
        this.cornerPoint = cornerPoint;
    }

    @Override
    public void draw(GraphicsContext g) {
        double[] size = setCornerPoint(cornerPoint);
        double width = size[0];
        double height = size[1];
        g.setLineWidth(getLineWidth());
        g.setFill(getFillColor());
        g.fillOval(cornerPoint.getX(), cornerPoint.getY(), width, height);
        g.setStroke(getStrokeColor());
        g.strokeOval(cornerPoint.getX(), cornerPoint.getY(), width, height);
    }

    @Override
    public boolean contains(Point2D pt) {
        double[] size = setCornerPoint(cornerPoint);
        double width = size[0];
        double height = size[1];
        Point2D theCenter = getLocation();
        double alpha = (double) (pt.getX() - theCenter.getX()) / width;
        double beta = (double) (pt.getY() - theCenter.getY()) / height;
        return 4 * (alpha * alpha + beta * beta) < 1;
    }


    public void move(Point2D pt) {
        Point2D theCenter = getLocation();
        double x = cornerPoint.getX() + pt.getX() - theCenter.getX();
        double y = cornerPoint.getY() + pt.getY() - theCenter.getY();
        cornerPoint = new Point2D(x, y);
        super.move(pt);
    }

    public double[] setCornerPoint(Point2D cornerPoint) {
        this.cornerPoint = cornerPoint;
        Point2D theCenter = getLocation();
        double [] size = new double[2];
        size[0] = 2 * (theCenter.getX() - cornerPoint.getX());
        size[1] = 2 * (theCenter.getY() - cornerPoint.getY());
        return size;
    }
}