package Figures.line;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.awt.*;

public class Ray extends Segment {

    public Ray(Point2D theCenter, Point2D endPoint, int frameWidth, Paint frameColor) {
        super(theCenter, endPoint, frameWidth, frameColor);
    }

    @Override
    public void setEndPoint(Point2D endPoint, boolean smooth) {
        if(endPoint.getX()>0 && endPoint.getX()<Toolkit.getDefaultToolkit().getScreenSize().getWidth() &&
                endPoint.getY()>0 && endPoint.getY()<Toolkit.getDefaultToolkit().getScreenSize().getHeight())
            endPoint = getOutScreenPoint(endPoint);
        super.setEndPoint(endPoint, smooth);
    }


    public Point2D getOutScreenPoint(Point2D pt) {
        Point2D theCenter = getLocation();
        Point2D result;
        double deltaX = pt.getX() - theCenter.getX();
        double deltaY = pt.getY() - theCenter.getY();
        if (deltaX==0 && deltaY==0)
            return pt;
        if (Math.abs(deltaX) < Math.abs(deltaY)) {
            double height;
            if (deltaY < 0)
                height = -1;
            else
                height = Toolkit.getDefaultToolkit().getScreenSize().getHeight() + 1;
            result = new Point2D(deltaX / deltaY * (height - theCenter.getY()) + theCenter.getX(), height);
        } else {
            double width;
            if (deltaX < 0)
                width = -1;
            else
                width = Toolkit.getDefaultToolkit().getScreenSize().getWidth() + 1;
            result = new Point2D(width, deltaY / deltaX * (width - theCenter.getX()) + theCenter.getY());
        }
        return result;
    }
}