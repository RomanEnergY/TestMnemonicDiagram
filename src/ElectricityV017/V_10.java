package ElectricityV017;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by user on 13.07.2018.
 */
public class V_10 extends PaintShape {

    public V_10(String name, Point2D point2D) {
        Rectangle2D rectangle2D_1 = new Rectangle2D.Double(point2D.getX() - 20, point2D.getY() - 20, 30, 80);
        super.addColorShapes(new ColorShape(new Color(255, 0, 0), rectangle2D_1));

//        Rectangle2D rectangle2D_2 = new Rectangle2D.Double(point2D.getX() - 20 + 40, point2D.getY() - 20, 40, 40);
//        super.addColorShapes(new ColorShape(new Color(0, 255, 0), rectangle2D_2));
//
//        Rectangle2D rectangle2D_3 = new Rectangle2D.Double(point2D.getX() - 20 + 40, point2D.getY() - 20 + 40, 40, 40);
//        super.addColorShapes(new ColorShape(new Color(0, 0, 255), rectangle2D_3));

        this.name = name;

    }

    @Override
    public void setFrame(Point deltaPointDragged) {
        double x = 0;
        double y = 0;

        for (int i = 0; i < this.colorShapes.size(); i++) {
            Rectangle2D rectangle2D = (Rectangle2D) this.colorShapes.get(i).shape;

            x = rectangle2D.getX() + deltaPointDragged.getX();
            y = rectangle2D.getY() + deltaPointDragged.getY();

            rectangle2D.setFrame(x, y, rectangle2D.getWidth(), rectangle2D.getHeight());

        }
    }
}
