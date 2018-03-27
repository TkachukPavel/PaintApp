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
        int[] size = setCornerPoint(cornerPoint);
        int width = size[0];
        int height = size[1];
//        g.setStroke(new BasicStroke(getFrameWidth()));
//        g.setColor(getFillColor());
//        g.fillRect(cornerPoint.x, cornerPoint.y, width, height);
//        g.setColor(getFrameColor());
//        g.drawRect(cornerPoint.x, cornerPoint.y, width, height);
        g.setFill(javafx.scene.paint.Paint.valueOf(getFillColor().toString()));
        g.fillRect(cornerPoint.getX(), cornerPoint.getY(), width, height);
        g.setStroke(javafx.scene.paint.Paint.valueOf(getFrameColor().toString()));
        g.strokeRect(cornerPoint.getX(), cornerPoint.getY(), width, height);

    }

    @Override
    public boolean contains(Point2D pt) {
        int[] size = setCornerPoint(cornerPoint);
        int width = size[0];
        int height = size[1];
        return pt.getX() >= cornerPoint.getX() && pt.getX() <= cornerPoint.getX() + width &&
                pt.getY() >= cornerPoint.getY() && pt.getY() <= cornerPoint.getY() + height;
    }

}
