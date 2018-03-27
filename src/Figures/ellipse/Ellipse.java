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
        g.fillOval(cornerPoint.x, cornerPoint.y, width, height);
        g.setStroke(javafx.scene.paint.Paint.valueOf(getFrameColor().toString()));
        g.strokeOval(cornerPoint.x, cornerPoint.y, width, height);
    }

    @Override
    public boolean contains(Point pt) {
        int[] size = setCornerPoint(cornerPoint);
        int width = size[0];
        int height = size[1];
        Point theCenter = getLocation();
        double alpha = (double) (pt.x - theCenter.x) / width;
        double beta = (double) (pt.y - theCenter.y) / height;
        return 4 * (alpha * alpha + beta * beta) < 1;
    }


    public void move(Point pt) {
        Point theCenter = getLocation();
        cornerPoint.translate(pt.x - theCenter.x, pt.y - theCenter.y);
        super.move(pt);
    }

    public int[] setCornerPoint(Point2D cornerPoint) {
        this.cornerPoint = cornerPoint;
        Point2D theCenter = getLocation();
        adaptCornerPoint(theCenter);
        int [] size = new int[2];
        size[0] = 2 * (theCenter.x - cornerPoint.x);
        size[1] = 2 * (theCenter.y - cornerPoint.y);
        return size;
    }

    protected void adaptCornerPoint(Point2D theCenter) {
        double deltaX = theCenter.getX() - cornerPoint.getX();
        double deltaY = theCenter.getY() - cornerPoint.getX();
        if (deltaX < 0)
            cornerPoint.translate(2 * deltaX, 0);
        if (deltaY < 0)
            cornerPoint.translate(0, 2 * deltaY);
    }
}