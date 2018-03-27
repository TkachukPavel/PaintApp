package Figures;

import java.awt.*;

public abstract class Shape {
    private Point center;
    private Color color;

    public abstract void draw();


    public Point getLocation() {
        return center;
    }

    public void move(Point center) {
        this.center = center;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
