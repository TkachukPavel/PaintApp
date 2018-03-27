package Figures.polygon;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import java.util.ArrayList;
import java.util.List;

public class SymetricPolygon extends Polygon {

    private int sideNum;
    private boolean isRotating = false;


    public SymetricPolygon(Point2D theCenter, Point2D pointOnCircle, int sideNum) {
        setLocation(theCenter);
        setPoints(getPolygonPoints(theCenter, pointOnCircle, sideNum));
    }

    public SymetricPolygon(Point2D theCenter, Point2D pointOnCircle, int sideNum, int frameWidth, Paint frameColor, Paint fillColor) {
        super(theCenter, frameWidth, frameColor, fillColor);
        this.sideNum = sideNum;
        setPoints(getPolygonPoints(theCenter, pointOnCircle, sideNum));
    }

    private List<Point2D> getPolygonPoints(Point2D theCenter, Point2D pointOnCircle, int sideNum) {
        List<Point2D> points = new ArrayList<>(sideNum + 1);
        double radius = Math.sqrt(Math.pow((pointOnCircle.getX()) - theCenter.getX(), 2) + Math.pow(pointOnCircle.getY() - theCenter.getY(), 2));
        double z;
        double angle = 360.0 / sideNum;
        if (isRotating) {
            z = Math.asin((theCenter.getY() - pointOnCircle.getY()) / radius) * 180 / Math.PI;
            if (pointOnCircle.getX() < theCenter.getX())
                z = 180.0 - z;
        } else {
            if (sideNum % 2 != 0)
                z = 90;
            else
                z = 90 - angle / 2;
        }
        for (int i = 0; i < sideNum; i++) {
            points.add(new Point2D(theCenter.getX() + (int) (Math.cos(z / 180 * Math.PI) * radius),
                    theCenter.getY() - (int) (Math.sin(z / 180 * Math.PI) * radius)));
            z = z + angle;
        }
        return points;
    }

    public void setPointOnCircle(Point2D p) {
        setPoints(getPolygonPoints(getLocation(), p, sideNum));
    }

    public int getSideNum() {
        return sideNum;
    }

    public void setSideNum(int sideNum) {
        this.sideNum = sideNum;
    }

    public boolean isRotating() {
        return isRotating;
    }

    public void setRotating(boolean rotating) {
        isRotating = rotating;
    }
}