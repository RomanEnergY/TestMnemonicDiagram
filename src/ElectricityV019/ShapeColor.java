package ElectricityV019;

import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

/**
 * Created by user on 09.07.2018.
 */
public class ShapeColor extends RectangularShape {

    public double x;
    public double y;
    public double width;
    public double height;

    private RectangularShape rectangularShape;
    private Color color;

    public ShapeColor(){
        this.color = null;
        this.rectangularShape = null;
    }

    public ShapeColor(RectangularShape rectangularShape, Color color) {
        this.color = color;
        this.rectangularShape = rectangularShape;
        x = rectangularShape.getX();
        y = rectangularShape.getY();
        width = rectangularShape.getWidth();
        height = rectangularShape.getHeight();
    }

    public void setRandomColor(){
        Random random = new Random();

        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        this.color = new Color(r, g, b);
    }

    public Shape getShape() {
        return rectangularShape;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public double getX() {
        return this.rectangularShape.getX();
    }

    @Override
    public double getY() {
        return this.rectangularShape.getY();
    }

    @Override
    public double getWidth() {
        return this.rectangularShape.getWidth();
    }

    @Override
    public double getHeight() {
        return this.rectangularShape.getHeight();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void setFrame(double x, double y, double w, double h) {
        this.rectangularShape.setFrame(x, y, w, h);
    }

    public void setFrameDragged(Point setFrame) {
        this.setFrame(setFrame.getX() + this.x, setFrame.getY() + this.y, width, height);
    }

    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }

    @Override
    public boolean contains(double x, double y) {
        return false;
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return false;
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return false;
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return null;
    }


}
