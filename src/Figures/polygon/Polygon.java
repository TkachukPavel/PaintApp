package Figures.polygon;


import Figures.base.Shape2D;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Polygon extends Shape2D {

    protected int nPoints;
    protected Point2D[] points;
    protected Point2D secondPoint;

    private static final int MIN_LENGTH = 4;

    public Polygon(){

    }

    public Polygon(Point2D theCenter, int frameWidth, Paint frameColor, Paint fillColor) {
        super(theCenter, frameWidth, frameColor, fillColor);
    }

    public Polygon(Point2D theCenter, List<Point2D> points, int frameWidth, Paint frameColor, Paint fillColor) {
        super(theCenter, frameWidth, frameColor, fillColor);
        setPoints(points);
    }

    public void setPoints(List<Point2D> points) {
        nPoints = points.size();
        this.points = new Point2D[nPoints];
        int i = 0;
        for (Point2D p : points) {
            this.points[++i] = p;
        }
    }

    public List<Point2D> getPoints() {
        List<Point2D> points = new ArrayList<>(nPoints);
        for (int i = 0; i < nPoints; ++i)
            points.add(this.points[i]);
        return points;
    }

    public int getPointsSize() {
        return nPoints;
    }

    public void addPoint(Point2D pt) {
        if (nPoints >= points.length) {
            int newLength = nPoints * 2;
            if (newLength < MIN_LENGTH) {
                newLength = MIN_LENGTH;
            } else if ((newLength & (newLength - 1)) != 0) {
                newLength = Integer.highestOneBit(newLength);
            }
            points = Arrays.copyOf(points, newLength);
        }
        points[nPoints] = pt;
        nPoints++;
        setLocation(computeCenter());
    }

    public void setLastPoint(Point2D pt){
        points[nPoints-1] = pt;
        setLocation(computeCenter());
    }

    private Point2D computeCenter(){
        Point2D centroid = new Point2D(0,0);
        double signedArea = 0.0;
        double x0; // Current vertex X
        double y0; // Current vertex Y
        double x1; // Next vertex X
        double y1; // Next vertex Y
        double a;  // Partial signed area

        for (int i=0; i<nPoints-1; ++i)
        {
            x0 = points[i].getX();
            y0 = points[i].getY();
            x1 = points[i+1].getX();
            y1 = points[i+1].getY();
            a = x0*y1 - x1*y0;
            signedArea += a;

            double x = centroid.getX() + (x0 + x1)*a;
            double y = centroid.getY() + (y0 + y1)*a;

            centroid = new Point2D(x, y);
        }

        x0 = points[nPoints-1].getX();
        y0 = points[nPoints-1].getY();
        x1 = points[0].getX();
        y1 = points[0].getY();
        a = x0*y1 - x1*y0;
        signedArea += a;

        double x = centroid.getX() + (x0 + x1)*a;
        double y = centroid.getY() + (y0 + y1)*a;

        centroid = new Point2D(x, y);

        signedArea *= 0.5;

        x = centroid.getX()/(6.0*signedArea);
        y = centroid.getY()/(6.0*signedArea);

        centroid = new Point2D(x, y);

        return centroid;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setFill(javafx.scene.paint.Paint.valueOf(getFillColor().toString()));
        double [] pointX = new double[nPoints];
        double [] pointY = new double[nPoints];
        for (int i = 0; i < nPoints; i++){
            pointX[i] =  points[i].getX();
            pointY[i] =  points[i].getY();
        }
        g.fillPolygon(pointX, pointY, nPoints);
        g.setFill(javafx.scene.paint.Paint.valueOf(getStrokeColor().toString()));
        g.strokePolygon(pointX, pointY, nPoints);
    }

    @Override
    public boolean contains(Point2D pt) {
        int hits = 0;

        int lastx = (int) points[nPoints - 1].getX();
        int lasty = (int) points[nPoints - 1].getY();
        int curx, cury;

        for (int i = 0; i < nPoints; lastx = curx, lasty = cury, i++) {
            curx = (int) points[i].getX();
            cury = (int) points[i].getY();

            if (cury == lasty) {
                continue;
            }

            int leftx;
            if (curx < lastx) {
                if (pt.getX() >= lastx) {
                    continue;
                }
                leftx = curx;
            } else {
                if (pt.getX() >= curx) {
                    continue;
                }
                leftx = lastx;
            }

            double test1, test2;
            if (cury < lasty) {
                if (pt.getY() < cury || pt.getY() >= lasty) {
                    continue;
                }
                if (pt.getX() < leftx) {
                    hits++;
                    continue;
                }
                test1 = pt.getX() - curx;
                test2 = pt.getY() - cury;
            } else {
                if (pt.getY() < lasty || pt.getY() >= cury) {
                    continue;
                }
                if (pt.getX() < leftx) {
                    hits++;
                    continue;
                }
                test1 = pt.getX() - lastx;
                test2 = pt.getY() - lasty;
            }

            if (test1 < (test2 / (lasty - cury) * (lastx - curx))) {
                hits++;
            }
        }
        return ((hits & 1) != 0);
    }

    @Override
    public void move(Point2D pt) {
        Point2D theCenter = getLocation();
        int deltaX = (int) (pt.getX() - theCenter.getX());
        int deltaY = (int) (pt.getY() - theCenter.getY());
        for (int i = 0; i < nPoints; i++) {
            double x = points[i].getX() + deltaX;
            double y = points[i].getY() + deltaY;

            points[i] = new Point2D(x, y);
        }
        super.move(pt);
    }
}