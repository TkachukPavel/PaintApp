package Figures.polygon;


import Figures.base.Shape2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Polygon extends Shape2D {

    protected int nPoints;
    protected Point[] points;
    protected Point secondPoint;

    private static final int MIN_LENGTH = 4;

    public Polygon(){

    }

    public Polygon(Point theCenter, int frameWidth, Color frameColor, Color fillColor) {
        super(theCenter, frameWidth, frameColor, fillColor);
    }

    public Polygon(Point theCenter, List<Point> points, int frameWidth, Color frameColor, Color fillColor) {
        super(theCenter, frameWidth, frameColor, fillColor);
        setPoints(points);
    }

    public void setPoints(List<Point> points) {
        nPoints = points.size();
        this.points = new Point[nPoints];
        int i = 0;
        for (Point p : points) {
            this.points[++i] = p;
        }
    }

    public List<Point> getPoints() {
        List<Point> points = new ArrayList<>(nPoints);
        for (int i = 0; i < nPoints; ++i)
            points.add(this.points[i]);
        return points;
    }

    public int getPointsSize() {
        return nPoints;
    }

    public void addPoint(Point pt) {
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

    public void setLastPoint(Point pt){
        points[nPoints-1] = pt;
        setLocation(computeCenter());
    }

    private Point computeCenter(){
        Point centroid = new Point(0,0);
        double signedArea = 0.0;
        double x0; // Current vertex X
        double y0; // Current vertex Y
        double x1; // Next vertex X
        double y1; // Next vertex Y
        double a;  // Partial signed area

        for (int i=0; i<nPoints-1; ++i)
        {
            x0 = points[i].x;
            y0 = points[i].y;
            x1 = points[i+1].x;
            y1 = points[i+1].y;
            a = x0*y1 - x1*y0;
            signedArea += a;
            centroid.x += (x0 + x1)*a;
            centroid.y += (y0 + y1)*a;
        }

        x0 = points[nPoints-1].x;
        y0 = points[nPoints-1].y;
        x1 = points[0].x;
        y1 = points[0].y;
        a = x0*y1 - x1*y0;
        signedArea += a;
        centroid.x += (x0 + x1)*a;
        centroid.y += (y0 + y1)*a;

        signedArea *= 0.5;
        centroid.x /= (6.0*signedArea);
        centroid.y /= (6.0*signedArea);

        return centroid;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(getFrameWidth()));
        g.setColor(getFillColor());
        int[] pointX = new int[nPoints];
        int [] pointY = new int[nPoints];
        for (int i = 0; i < nPoints; i++){
            pointX[i] = points[i].x;
            pointY[i] = points[i].y;
        }
        g.fillPolygon(pointX, pointY, nPoints);
        g.setColor(getFrameColor());
        g.drawPolygon(pointX, pointY, nPoints);
    }

    @Override
    public boolean contains(Point pt) {
        int hits = 0;

        int lastx = points[nPoints - 1].x;
        int lasty = points[nPoints - 1].y;
        int curx, cury;

        for (int i = 0; i < nPoints; lastx = curx, lasty = cury, i++) {
            curx = points[i].x;
            cury = points[i].y;

            if (cury == lasty) {
                continue;
            }

            int leftx;
            if (curx < lastx) {
                if (pt.x >= lastx) {
                    continue;
                }
                leftx = curx;
            } else {
                if (pt.x >= curx) {
                    continue;
                }
                leftx = lastx;
            }

            double test1, test2;
            if (cury < lasty) {
                if (pt.y < cury || pt.y >= lasty) {
                    continue;
                }
                if (pt.x < leftx) {
                    hits++;
                    continue;
                }
                test1 = pt.x - curx;
                test2 = pt.y - cury;
            } else {
                if (pt.y < lasty || pt.y >= cury) {
                    continue;
                }
                if (pt.x < leftx) {
                    hits++;
                    continue;
                }
                test1 = pt.x - lastx;
                test2 = pt.y - lasty;
            }

            if (test1 < (test2 / (lasty - cury) * (lastx - curx))) {
                hits++;
            }
        }
        return ((hits & 1) != 0);
    }

    @Override
    public void move(Point pt) {
        Point theCenter = getLocation();
        int deltaX = pt.x - theCenter.x;
        int deltaY = pt.y - theCenter.y;
        for (int i = 0; i < nPoints; i++) {
            points[i].x += deltaX;
            points[i].y += deltaY;
        }
        super.move(pt);
    }
}