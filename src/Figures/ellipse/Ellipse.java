package Figures.ellipse;

import Figures.base.Shape2D;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;


public class Ellipse extends Shape2D {

    protected Point2D cornerPoint;

    public Ellipse() {

    }

    public Ellipse(javafx.geometry.Point2D theCenter, Point2D cornerPoint) {
        super(theCenter);
        this.cornerPoint = cornerPoint;
    }

    public Ellipse(Point2D theCenter, Point2D cornerPoint, int frameWidth, Paint frameColor, Paint fillColor) {
        super(theCenter, frameWidth, frameColor, fillColor);
        this.cornerPoint = cornerPoint;
    }

    @Override
    public void draw(GraphicsContext g) {
        int[] size = setCornerPoint(cornerPoint);
        int width = size[0];
        int height = size[1];
//        g.setStroke(new BasicStroke(getFrameWidth()));
        g.setFill(javafx.scene.paint.Paint.valueOf(getFrameColor().toString()));
        g.fillOval(cornerPoint.getX(), cornerPoint.getY(), width, height);
        g.setStroke(javafx.scene.paint.Paint.valueOf(getFrameColor().toString()));
        g.strokeOval(cornerPoint.getX(), cornerPoint.getY(), width, height);
    }

    @Override
    public boolean contains(Point2D pt) {
        int[] size = setCornerPoint(cornerPoint);
        int width = size[0];
        int height = size[1];
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

    public int[] setCornerPoint(Point2D cornerPoint) {
        this.cornerPoint = cornerPoint;
        Point2D theCenter = getLocation();
        adaptCornerPoint(theCenter);
        int [] size = new int[2];
        size[0] = 2 * (int) (theCenter.getX() - cornerPoint.getX());
        size[1] = 2 * (int) (theCenter.getY() - cornerPoint.getY());
        return size;
    }

    protected void adaptCornerPoint(Point2D theCenter) {
        double deltaX = theCenter.getX() - cornerPoint.getX();
        double deltaY = theCenter.getY() - cornerPoint.getX();
        if (deltaX < 0) {
            double x = cornerPoint.getX() + 2 * deltaX;
            double y = cornerPoint.getY();
            cornerPoint = new Point2D(x, y);
        }
        if (deltaY < 0) {
            double x = cornerPoint.getX();
            double y = cornerPoint.getY() + 2 * deltaY;
            cornerPoint = new Point2D(x, y);
        }
    }
}