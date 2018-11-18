package ElectricityV017;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by user on 13.07.2018.
 */
public class PaintShape {
    String name = "NAME_PaintShape";
    static boolean aBoolean = true;

    protected Point2D point2D;
    protected ArrayList<ColorShape> colorShapes = new ArrayList<>();

    static MyPanel paintPanel;

    PaintShape() {

    }

    PaintShape(String name, Point2D point2D) {
        this.name = name;
        this.point2D = point2D;
        colorShapes = new ArrayList<>();
    }

    public void addColorShapes(ColorShape colorShape) {
        this.colorShapes.add(colorShape);
    }

    public ArrayList<ColorShape> getColorShapes() {
        return colorShapes;
    }

    static void setPaintPanel(MyPanel MyPanel) {
        paintPanel = MyPanel;
    }

    boolean isFind(Point point) {
        if (point != null) {
            for (int count = colorShapes.size() - 1; count >= 0; count--) {
                if (colorShapes.get(count).shape.contains(point)) { // Проверяем есть ли в указанном месте точке наличие фигуры
                    return true;
                }
            }
        }
        return false;
    }

    public void paint(Graphics g) {
//        System.out.println("public void paint(Graphics g) " + this.name);

        Graphics2D graphics2D = (Graphics2D) g;
        for (ColorShape colorShape : colorShapes) {
            colorShape.paint(graphics2D);
        }
    }



    public static PaintShape addNewShape(Point point) {
        PaintShape paintShape = null;

        if (PaintShape.aBoolean) {
            paintShape = new V_10("ВВ-10кВ", point);
            PaintShape.aBoolean = !PaintShape.aBoolean;

        }
        else {
//            Rectangle2D rectangle2D = new Rectangle2D.Double(point2D.getX() - 20, point2D.getY() - 20, 40, 40);
//            colorShape = new ColorShape(ColorShape.setColor(), rectangle2D);
            PaintShape.aBoolean = !PaintShape.aBoolean;
        }

        return paintShape;
    }

    public void setFrame(Point deltaPointDragged) {

    }
}
