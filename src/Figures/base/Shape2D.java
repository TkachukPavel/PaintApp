package Figures.base;


import java.awt.*;

public abstract class Shape2D extends Shape {

	private Color fillColor = new Color(255,255,255);

    public Shape2D(Point theCenter) {
        super(theCenter);
    }

    public Shape2D(Point theCenter, int frameWidth, Color frameColor, Color fillColor) {
        super(theCenter, frameWidth, frameColor);
        this.fillColor = fillColor;
    }

    public Shape2D() {

    }

    public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
}