package ElectricityV018;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * Created by user on 09.07.2018.
 */
public class ShapeAndColor {
    private Shape shape;
    private Color color;

    public ShapeAndColor(){
        this.color = null;
        this.shape = null;
    }

    public ShapeAndColor(Color color, Shape shape) {
        this.color = color;
        this.shape = shape;
    }

    public void setRandomColor(){
        Random random = new Random();

        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        this.color = new Color(r, g, b);
    }

    public Shape getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFrame(Point point) {
        Rectangle2D rectShape = this.shape.getBounds2D();

        if (shape instanceof Ellipse2D) {
            Ellipse2D ellipse2D = (Ellipse2D) shape;
            Point2D point2D = setPointPainShape(point, rectShape);
            ellipse2D.setFrame(point2D.getX() - 20, point2D.getY() - 20, 40, 40);
        }

        if (shape instanceof Rectangle2D) {
            Rectangle2D rectangle2D = (Rectangle2D) shape;
            Point2D point2D = setPointPainShape(point, rectShape);
            rectangle2D.setFrame(point2D.getX() - 20, point2D.getY() - 20, 40, 40);
        }
    }

    private Point2D setPointPainShape(Point2D point2D, Rectangle2D rectShape) {

        return point2D;
    }

//    public static ShapeAndColor addNewShape(Point2D point2D) {
//        return null;
//    }
//
//    public void paint(Graphics2D g) {
//        g.setColor(this.color); // получаем цвет фигуры
//        g.fill(this.shape); // отресовываем переданную фигуру
//    }
}
