package ElectricityV020;

import ElectricityV020.ModelShape.ModelRectangularShape;
import ElectricityV020.ModelShape.ModelType;

import java.awt.*;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

/**
 * Created by user on 09.07.2018.
 */
public class RectShapes implements Cloneable {
    private ArrayList<RectShape> rectangularShape = new ArrayList<>();

    /**
     * Конструктор с нулевыми параметрами
     */
    public RectShapes() {
        this.rectangularShape = null;
    }

    public RectShapes(ArrayList<RectShape> rectangularShape) {
        this.rectangularShape = rectangularShape;
    }

    public RectShapes(RectangularShape rectangularShape) {
        this.rectangularShape.add(new RectShape(rectangularShape));
    }

    public void setFrameDragged(Point pointDragged) {
        for (RectShape rectShape : rectangularShape)
            rectShape.setFrameDragged(pointDragged);
    }

    public void paint(Graphics2D graphics2D, Color color) {
        for (RectShape rectShape : rectangularShape) {
            rectShape.paint(graphics2D, color);
        }
    }

    public boolean isFind(Point point) {
        for (RectShape rectShape : rectangularShape) {
            if (rectShape.contains(point)) {
                return true;
            }
        }

        return false;
    }

    public boolean intersects(RectShapes connectionShapes) {
        if (connectionShapes != null && this.rectangularShape != null && connectionShapes.rectangularShape != null)
            for (RectShape shapeThis : this.rectangularShape)
                for (RectShape shapeConnect : connectionShapes.rectangularShape)
                    if (shapeThis.intersects(shapeConnect))
                        return true;

        return false;
    }

    public boolean isEquals(RectShapes connectionShapes) {
        if (connectionShapes != null && this.rectangularShape != null && connectionShapes.rectangularShape != null)
            for (RectShape shapeThis : this.rectangularShape)
                for (RectShape shapeConnect : connectionShapes.rectangularShape)
                    if (shapeThis.isEquals(shapeConnect))
                        return true;

        return false;
    }

    public void deploy(ModelType modelType, ModelType modelToType, Point point) {
        if (this.rectangularShape != null) {
            for (RectShape rectShape : rectangularShape) {
                rectShape.deploy(modelType, modelToType, point);
            }
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        super.clone();
        ArrayList<RectShape> rectangularShapeClone = new ArrayList<>();
        for (RectShape rectShape : rectangularShape) {
            rectangularShapeClone.add(rectShape);
        }


        return new RectShapes(rectangularShapeClone);

    }
}
