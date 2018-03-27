package Figures.base;


import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public abstract class Shape2D extends Shape {

	private Paint fillColor = Paint.valueOf("White");

    public Shape2D(Point2D theCenter) {
        super(theCenter);
    }

    public Shape2D(Point2D theCenter, int frameWidth, Paint frameColor, Paint fillColor) {
        super(theCenter, frameWidth, frameColor);
        this.fillColor = fillColor;
    }

    public Shape2D() {

    }

    public Paint getFillColor() {
		return fillColor;
	}

	public void setFillColor(Paint fillColor) {
		this.fillColor = fillColor;
	}
}