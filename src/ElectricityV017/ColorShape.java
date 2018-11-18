package ElectricityV017;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 09.07.2018.
 */
public class ColorShape {
    static MyPanel paintPanel;
    static boolean isShape = true;
    Color color;
    Shape shape;

    public ColorShape(){

    }

    public ColorShape(Color color, Shape shape) {
        this.color = color;
        this.shape = shape;
    }

    static void setPaintPanel(MyPanel MyPanel) {
        paintPanel = MyPanel;
    }

    static Color setColor(){
        Random random = new Random();

        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);


        return new Color(r, g, b);
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

        if (point2D.getX() < rectShape.getWidth()/2) {
            point2D.setLocation(rectShape.getWidth()/2, point2D.getY());
        }
        else if (point2D.getX() > paintPanel.getBounds().getWidth() - rectShape.getWidth()/2) {
            point2D.setLocation(paintPanel.getBounds().getWidth() - rectShape.getWidth()/2 , point2D.getY());
        }

        if (point2D.getY() < rectShape.getHeight()/2) {
            point2D.setLocation(point2D.getX(), rectShape.getHeight()/2);
        }
        else if (point2D.getY() > paintPanel.getBounds().getHeight() - rectShape.getHeight()/2) {
            point2D.setLocation(point2D.getX(), paintPanel.getBounds().getHeight() - rectShape.getHeight()/2);
        }

        return point2D;
    }

    public static ColorShape addNewShape(Point2D point2D) {
        ColorShape colorShape = null;

        if (ColorShape.isShape) {
            Ellipse2D ellipse2D = new Ellipse2D.Double(point2D.getX() - 20, point2D.getY() - 20, 40, 40);
            colorShape = new ColorShape(ColorShape.setColor(), ellipse2D);
            ColorShape.isShape = !ColorShape.isShape;

        }
        else {
            Rectangle2D rectangle2D = new Rectangle2D.Double(point2D.getX() - 20, point2D.getY() - 20, 40, 40);
            colorShape = new ColorShape(ColorShape.setColor(), rectangle2D);
            ColorShape.isShape = !ColorShape.isShape;
        }

        return colorShape;
    }

    public void paint(Graphics2D g) {
        g.setColor(this.color); // получаем цвет фигуры
        g.fill(this.shape); // отресовываем переданную фигуру
    }
}
