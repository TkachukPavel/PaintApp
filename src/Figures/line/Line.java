package Figures.line;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;


public class Line extends Ray {


    public Line(Point2D theCenter, Point2D endPoint, int frameWidth, Paint frameColor) {
        super(theCenter, endPoint, frameWidth, frameColor);
    }

    @Override
    public void draw(GraphicsContext g) {
        super.draw(g);
        Point2D theCenter = getLocation();
        Point2D endPoint = getEndPoint();
        setEndPoint(new Point2D(2*theCenter.getX()-endPoint.getX(), 2*theCenter.getY()-endPoint.getY()));
        super.draw(g);
    }
}