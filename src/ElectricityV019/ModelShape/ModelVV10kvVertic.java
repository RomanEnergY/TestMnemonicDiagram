package ElectricityV019.ModelShape;

import ElectricityV019.ShapeColor;
import ElectricityV019.ShapeColorContact;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by user on 13.07.2018.
 */
public class ModelVV10kvVertic extends ModelShape {

    public ModelVV10kvVertic(String name, boolean isOn) {
        super(name, isOn);
    }

    @Override
    protected final void setShapeObject(ArrayList<ShapeColor> shapeObject, Color colorObject) {
        shapeObject.add(new ShapeColor(
                new Rectangle2D.Double(-5.0 * ZOOM, -1.5 * ZOOM, 20.0 * ZOOM, 3.0 * ZOOM),
                colorObject));

        shapeObject.add(new ShapeColor(
                new Rectangle2D.Double(-3.0 * ZOOM, -7.5 * ZOOM, 16.0 * ZOOM, 15.0 * ZOOM),
                colorObject));
    }

    @Override
    protected final void setShapeOff(ArrayList<ShapeColor> shapeOff, Color colorOff) {
        shapeOff.add(new ShapeColor(
                new Rectangle2D.Double(-3.0 * ZOOM, -7.5 * ZOOM, 16.0 * ZOOM, 15.0 * ZOOM),
                colorOff));
    }

    @Override
    protected final void setShapeFlag(ArrayList<ShapeColor> shapeFlag, Color colorFlag) {
        shapeFlag.add(new ShapeColor(
                new Ellipse2D.Double(-0.5 * ZOOM, -0.5 * ZOOM, 1.0 * ZOOM, 1.0 * ZOOM),
                colorFlag));

        shapeFlag.add(new ShapeColor(
                new Ellipse2D.Double(9.5 * ZOOM, -0.5 * ZOOM, 1.0 * ZOOM, 1.0 * ZOOM),
                colorFlag));
    }

    @Override
    protected void setShapeConnection(ArrayList<ShapeColorContact> shapeConnection, Color colorConnection) {

    }

    @Override
    protected void setPinModel(ArrayList<PinModel> pinModel, Color colorContact) {
        pinModel.add(new PinModel(
                this,
                ContactName.PIN_1,
                new ShapeColor(
                        new Ellipse2D.Double(9.5 * ZOOM, -0.5 * ZOOM, 1.0 * ZOOM, 1.0 * ZOOM),
                        colorContact),
                new ShapeColor(
                        new Ellipse2D.Double(19.5 * ZOOM, -0.5 * ZOOM, 1.0 * ZOOM, 1.0 * ZOOM),
                        colorContact))
        );

        pinModel.add(new PinModel(
                this,
                ContactName.PIN_2,
                new ShapeColor(
                        new Ellipse2D.Double(-0.5 * ZOOM, -0.5 * ZOOM, 1.0 * ZOOM, 1.0 * ZOOM),
                        colorContact),
                new ShapeColor(
                        new Ellipse2D.Double(-10.5 * ZOOM, -0.5 * ZOOM, 1.0 * ZOOM, 1.0 * ZOOM),
                        colorContact))
        );
    }

    @Override
    public String toString() {
        return "ModelVV10kvVertic{" +
                "name='" + name + '\'' +
                "}";
    }

    @Override
    public Object clone() {
        return new ModelVV10kvVertic(this.name, this.isOn());
    }
}
