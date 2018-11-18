package ElectricityV018;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by user on 13.07.2018.
 */
public abstract class ModelShape  implements Cloneable  {
    String name = "NAME_ModelShape";
    private ArrayList<ShapeAndColor> shapeAndColors = new ArrayList<>();

    ModelShape(){}

    ModelShape(String name) {
        this.name = name;

    }

    public void addShapeAndColor(ShapeAndColor shapeAndColor) {
        this.shapeAndColors.add(shapeAndColor);
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<ShapeAndColor> getShapeAndColors() {
        return this.shapeAndColors;
    }

    boolean isFind(Point point) {
        if (point != null) {
            for (int count = shapeAndColors.size() - 1; count >= 0; count--) {
                if (shapeAndColors.get(count).getShape().contains(point)) { // Проверяем есть ли в указанном месте точке наличие фигуры
                    return true;
                }
            }
        }
        return false;
    }

    public abstract void paint(Graphics g);
    public abstract void setFrame(Point deltaPointDragged);

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public abstract void setPoint(Point point);
}
