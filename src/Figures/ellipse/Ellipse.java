package Figures.ellipse;

import Figures.base.Shape2D;

import java.awt.*;

public class Ellipse extends Shape2D {

    protected Point cornerPoint;

    public Ellipse() {

    }

    public Ellipse(Point theCenter, Point cornerPoint) {
        super(theCenter);
        this.cornerPoint = cornerPoint;
    }

    public Ellipse(Point theCenter, Point cornerPoint, int frameWidth, Color frameColor, Color fillColor) {
        super(theCenter, frameWidth, frameColor, fillColor);
        this.cornerPoint = cornerPoint;
    }

    @Override
    public void draw(Graphics2D g) {
        int[] size = setCornerPoint(cornerPoint);
        int width = size[0];
        int height = size[1];
        g.setStroke(new BasicStroke(getFrameWidth()));
        g.setColor(getFillColor());
        g.fillOval(cornerPoint.x, cornerPoint.y, width, height);
        g.setColor(getFrameColor());
        g.drawOval(cornerPoint.x, cornerPoint.y, width, height);
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

    protected int[] setCornerPoint(Point cornerPoint) {
        this.cornerPoint = cornerPoint;
        Point theCenter = getLocation();
        adaptCornerPoint(theCenter);
        int [] size = new int[2];
        size[0] = 2 * (theCenter.x - cornerPoint.x);
        size[1] = 2 * (theCenter.y - cornerPoint.y);
        return size;
    }

    protected void adaptCornerPoint(Point theCenter) {
        int deltaX = theCenter.x - cornerPoint.x;
        int deltaY = theCenter.y - cornerPoint.y;
        if (deltaX < 0)
            cornerPoint.translate(2 * deltaX, 0);
        if (deltaY < 0)
            cornerPoint.translate(0, 2 * deltaY);
    }
}