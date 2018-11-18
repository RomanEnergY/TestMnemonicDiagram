package ElectricityV020;

import ElectricityV020.ModelShape.ModelType;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

/**
 * Created by user on 10.09.2018.
 */
public class RectShape extends RectangularShape {
    public double x;
    public double y;
    public double width;
    public double height;

    private RectangularShape rectangularShape;

    public RectShape(RectangularShape rectangularShape) {
        this.rectangularShape = rectangularShape;
        x = rectangularShape.getX();
        y = rectangularShape.getY();
        width = rectangularShape.getWidth();
        height = rectangularShape.getHeight();
    }

    public void paint(Graphics2D graphics2D, Color color) {
        graphics2D.setColor(color);
        graphics2D.fill(this.rectangularShape);
    }

    public RectangularShape getShape() {
        return rectangularShape;
    }

    @Override
    public double getX() {
        return this.rectangularShape.getFrame().getX();
    }

    @Override
    public double getY() {
        return this.rectangularShape.getFrame().getY();
    }

    @Override
    public double getWidth() {
        return this.rectangularShape.getFrame().getWidth();
    }

    @Override
    public double getHeight() {
        return this.rectangularShape.getFrame().getHeight();
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
        return this.rectangularShape.contains(x, y);
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return this.rectangularShape.intersects(x, y, w, h);
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return this.rectangularShape.contains(x, y, w, h);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return null;
    }

    public boolean intersects(RectShape shapeConnect) {
        if (this.rectangularShape.getFrame().intersects(shapeConnect.getX(), shapeConnect.getY(), shapeConnect.getWidth(), shapeConnect.getHeight()))
            return true;
        else
            return false;
    }

    public boolean isEquals(RectShape shapeConnect) {
        return this.rectangularShape.equals(shapeConnect.rectangularShape);
    }

    public void deploy(ModelType modelNowType, ModelType modelToType, Point point) {
        double tempX = x;
        double tempY = y;
        double tempWidth = width;
        double tempHeight = height;

        switch (modelNowType) {
            case TOP:
                switch (modelToType) {
                    case TOP:
                        break;

                    case RIGHT:
                        this.x = - tempY - tempHeight;
                        this.y = tempX;
                        this.width = tempHeight;
                        this.height = tempWidth;

                        this.setFrameDragged(point);
                        break;

                    case BOTTOM:
                        this.x = tempX;
                        this.y = - tempY - tempHeight;
                        this.width = tempWidth;
                        this.height = tempHeight;

                        this.setFrameDragged(point);
                        break;

                    case LEFT:
                        this.x = tempY;
                        this.y = tempX;
                        this.width = tempHeight;
                        this.height = tempWidth;

                        this.setFrameDragged(point);
                        break;
                }
                break;

            case RIGHT:
                switch (modelToType) {
                    case TOP:
                        this.x = tempY;
                        this.y = - tempX - tempWidth;
                        this.width = tempHeight;
                        this.height = tempWidth;

                        this.setFrameDragged(point);
                        break;

                    case RIGHT:
                        break;

                    case BOTTOM:
                        this.x = tempY;
                        this.y = tempX;
                        this.width = tempHeight;
                        this.height = tempWidth;

                        this.setFrameDragged(point);
                        break;

                    case LEFT:
                        this.x = - tempX - tempWidth;
                        this.y = tempY;
                        this.width = tempWidth;
                        this.height = tempHeight;

                        this.setFrameDragged(point);
                        break;
                }
                break;

            case BOTTOM:
                switch (modelToType) {
                    case TOP:
                        this.x = tempX;
                        this.y = - tempY - tempHeight;
                        this.width = tempWidth;
                        this.height = tempHeight;

                        this.setFrameDragged(point);
                        break;

                    case RIGHT:
                        this.x = tempY;
                        this.y = tempX;
                        this.width = tempHeight;
                        this.height = tempWidth;

                        this.setFrameDragged(point);
                        break;

                    case BOTTOM:
                        break;

                    case LEFT:
                        this.x = - tempY - tempHeight;
                        this.y = tempX;
                        this.width = tempHeight;
                        this.height = tempWidth;

                        this.setFrameDragged(point);
                        break;
                }
                break;

            case LEFT:
                switch (modelToType) {
                    case TOP:
                        this.x = tempY;
                        this.y = tempX;
                        this.width = tempHeight;
                        this.height = tempWidth;

                        this.setFrameDragged(point);
                        break;

                    case RIGHT:
                        this.x = - tempX - tempWidth;
                        this.y = tempY;
                        this.width = tempWidth;
                        this.height = tempHeight;

                        this.setFrameDragged(point);
                        break;

                    case BOTTOM:
                        this.x = tempY;
                        this.y = - tempX - tempWidth;
                        this.width = tempHeight;
                        this.height = tempWidth;

                        this.setFrameDragged(point);
                        break;

                    case LEFT:
                        break;
                }
                break;

        }
    }
}
