package ElectricityV018;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by user on 13.07.2018.
 */
public class Model_VV_10kV extends ModelShape {

    public Model_VV_10kV(String name) {
        double zoom = 5d;

        Rectangle2D rectangle2D_1 = new Rectangle2D.Double(-3 * zoom, -20 * zoom, 6 * zoom, 4 * zoom);
        super.addShapeAndColor(new ShapeAndColor(new Color(0, 0, 255), rectangle2D_1));

        rectangle2D_1 = new Rectangle2D.Double(-15 * zoom, -16 * zoom, 30 * zoom, 32 * zoom);
        super.addShapeAndColor(new ShapeAndColor(new Color(0, 0, 255), rectangle2D_1));

        rectangle2D_1 = new Rectangle2D.Double(-3 * zoom, 16 * zoom, 6 * zoom, 4 * zoom);
        super.addShapeAndColor(new ShapeAndColor(new Color(0, 0, 255), rectangle2D_1));

        Ellipse2D ellipse2D = new Ellipse2D.Double(-1.5d * zoom, -10.5d * zoom, 3 * zoom, 3 * zoom);
        super.addShapeAndColor(new ShapeAndColor(new Color(255, 0, 9), ellipse2D));


        this.name = name;

    }

    @Override
    public void setFrame(Point deltaPointDragged) {
        double x = 0;
        double y = 0;

        for (int i = 0; i < this.getShapeAndColors().size(); i++) {








            Rectangle2D rectangle2D = (Rectangle2D) this.getShapeAndColors().get(i).getShape();

            x = rectangle2D.getX() + deltaPointDragged.getX();
            y = rectangle2D.getY() + deltaPointDragged.getY();

            rectangle2D.setFrame(x, y, rectangle2D.getWidth(), rectangle2D.getHeight());

        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D)g;
        for (ShapeAndColor shapeAndColor: getShapeAndColors()) {
            graphics2D.setColor(shapeAndColor.getColor()); // получаем цвет фигуры
            graphics2D.fill(shapeAndColor.getShape()); // отресовываем переданную фигуру
        }
    }

    @Override
    public String toString() {
        return "Model_VV_10kV{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Model_VV_10kV(this.name);
    }

    @Override
    public void setPoint(Point point) {
        Point2D point2D = (Point2D) point;
        double x, y;

        for (ShapeAndColor shapeAndColor: getShapeAndColors()) {
            if (shapeAndColor.getShape() instanceof Rectangle2D) {
                Rectangle2D rectangle2D = (Rectangle2D) shapeAndColor.getShape();
//                Point2D point2D = setPointPainShape(point, rectShape);

                x = point2D.getX() + rectangle2D.getX();
                y = point2D.getY() + rectangle2D.getY();
                rectangle2D.setFrame(x, y, rectangle2D.getWidth(), rectangle2D.getHeight());
            }

            if (shapeAndColor.getShape() instanceof Ellipse2D) {
                Ellipse2D ellipse2D = (Ellipse2D) shapeAndColor.getShape();
//                Point2D point2D = setPointPainShape(point, rectShape);

                x = point2D.getX() + ellipse2D.getX();
                y = point2D.getY() + ellipse2D.getY();
                ellipse2D.setFrame(x, y, ellipse2D.getWidth(), ellipse2D.getHeight());
            }
        }
    }
}
