package Figures.ellipse;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;


public class Rectangle extends Ellipse {

    public Rectangle() {

    }

    public Rectangle(Point2D theCenter, Point2D secondPoint) {
        super(theCenter, secondPoint);
    }

    public Rectangle(Point2D theCenter, Point2D cornerPoint, int frameWidth, Paint frameColor, Paint fillColor) {
        super(theCenter, cornerPoint, frameWidth, frameColor, fillColor);
    }

    @Override
    public void draw(GraphicsContext g) {
        double [] size = setCornerPoint(cornerPoint);
        int width = (int) size[0];
        int height = (int) size[1];
        g.setLineWidth(1);
        g.setFill(getFillColor());
        g.fillRect(cornerPoint.getX(), cornerPoint.getY(), width, height);
        g.setStroke(getStrokeColor());
        g.strokeRect(cornerPoint.getX(), cornerPoint.getY(), width, height);

    }

    @Override
    public boolean contains(Point2D pt) {
        double[] size = setCornerPoint(cornerPoint);
        int width = (int) size[0];
        int height = (int) size[1];
        return pt.getX() >= cornerPoint.getX() && pt.getX() <= cornerPoint.getX() + width &&
                pt.getY() >= cornerPoint.getY() && pt.getY() <= cornerPoint.getY() + height;
    }

}
