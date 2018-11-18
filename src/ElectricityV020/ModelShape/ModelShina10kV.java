package ElectricityV020.ModelShape;

import ElectricityV020.RectShape;
import ElectricityV020.RectShapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by user on 21.08.2018.
 */
public class ModelShina10kV extends ModelShape {

    public ModelShina10kV(String name, boolean isOn) {
        super(name, isOn);
    }

    public ModelShina10kV(ModelType modelType, String name, boolean isOn) {
        super(modelType, name, isOn);
    }

    @Override
    protected final RectShapes setShapeObject(Color colorObject) {
        ArrayList<RectShape> rectShape = new ArrayList<>();
        rectShape.add(new RectShape(new Rectangle2D.Double(-3.0 * ZOOM, -3.0 * ZOOM, 6.0 * ZOOM, 6.0 * ZOOM)));

        return new RectShapes(rectShape);
    }

    @Override
    protected final RectShapes setShapeOff(Color colorOff) {
        return null;
    }

    @Override
    protected final RectShapes setShapeFlag(Color colorFlag) {
        return null;
    }

    @Override
    protected void setPinModel(ArrayList<PinModel> pinModels, Color colorOnConnection, Color colorActiveConnection, Color colorOffConnection) {
        pinModels.add(new PinModel(
                this,
                ContactName.PIN_1,
                new RectShapes(new Ellipse2D.Double(-0.5 * ZOOM, -0.5 * ZOOM, 1.0 * ZOOM, 1.0 * ZOOM)),
                new RectShapes(new Ellipse2D.Double(-0.5 * ZOOM, -10.5 * ZOOM, 1.0 * ZOOM, 1.0 * ZOOM)),
                new RectShapes(new Rectangle2D.Double(-3.0 * ZOOM, -5.0 * ZOOM, 6.0 * ZOOM, 2.0 * ZOOM)),
                new RectShapes(new Rectangle2D.Double(-3.5 * ZOOM, -5.0 * ZOOM, 7.0 * ZOOM, 0.5 * ZOOM)))
        );

        pinModels.add(new PinModel(
                this,
                ContactName.PIN_2,
                new RectShapes(new Ellipse2D.Double(-0.5 * ZOOM, -0.5 * ZOOM, 1.0 * ZOOM, 1.0 * ZOOM)),
                new RectShapes(new Ellipse2D.Double(9.5 * ZOOM, -0.5 * ZOOM, 1.0 * ZOOM, 1.0 * ZOOM)),
                new RectShapes(new Rectangle2D.Double(3.0 * ZOOM, -3.0 * ZOOM, 2.0 * ZOOM, 6.0 * ZOOM)),
                new RectShapes(new Rectangle2D.Double(4.5 * ZOOM, -3.5 * ZOOM, 0.5 * ZOOM, 7.0 * ZOOM)))
        );

        pinModels.add(new PinModel(
                this,
                ContactName.PIN_3,
                new RectShapes(new Ellipse2D.Double(-0.5 * ZOOM, -0.5 * ZOOM, 1.0 * ZOOM, 1.0 * ZOOM)),
                new RectShapes(new Ellipse2D.Double(-0.5 * ZOOM, 9.5 * ZOOM, 1.0 * ZOOM, 1.0 * ZOOM)),
                new RectShapes(new Rectangle2D.Double(-3.0 * ZOOM, 3.0 * ZOOM, 6.0 * ZOOM, 2.0 * ZOOM)),
                new RectShapes(new Rectangle2D.Double(-3.5 * ZOOM, 4.5 * ZOOM, 7.0 * ZOOM, 0.5 * ZOOM)))
        );

        pinModels.add(new PinModel(
                this,
                ContactName.PIN_4,
                new RectShapes(new Ellipse2D.Double(-0.5 * ZOOM, -0.5 * ZOOM, 1.0 * ZOOM, 1.0 * ZOOM)),
                new RectShapes(new Ellipse2D.Double(-10.5 * ZOOM, -0.5 * ZOOM, 1.0 * ZOOM, 1.0 * ZOOM)),
                new RectShapes(new Rectangle2D.Double(-5.0 * ZOOM, -3.0 * ZOOM, 2.0 * ZOOM, 6.0 * ZOOM)),
                new RectShapes(new Rectangle2D.Double(-5.0 * ZOOM, -3.5 * ZOOM, 0.5 * ZOOM, 7.0 * ZOOM)))
        );
    }

    @Override
    public String toString() {
        return "ModelShina10kV_v2{" +
                "name='" + name + '\'' +
                "}";
    }

    @Override
    public Object clone() {
        return new ModelShina10kV(this.modelType, this.name, this.isOn());
    }
}