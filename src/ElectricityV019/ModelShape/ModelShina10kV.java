package ElectricityV019.ModelShape;

import ElectricityV019.ShapeColor;
import ElectricityV019.ShapeColorContact;

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

    @Override
    protected final void setShapeObject(ArrayList<ShapeColor> shapeObject, Color colorObject) {
        shapeObject.add(new ShapeColor(
                new Rectangle2D.Double(-3.0 * ZOOM, -3.0 * ZOOM, 6.0 * ZOOM, 6.0 * ZOOM),
                colorObject));
    }

    @Override
    protected final void setShapeOff(ArrayList<ShapeColor> shapeOff, Color colorOff) {

    }

    @Override
    protected final void setShapeFlag(ArrayList<ShapeColor> shapeFlag, Color colorFlag) {

    }

    @Override
    protected void setShapeConnection(ArrayList<ShapeColorContact> shapeConnection, Color colorConnection) {
        shapeConnection.add(new ShapeColorContact(
                ContactName.PIN_1,
                new Rectangle2D.Double(-3.0 * ZOOM, -5.0 * ZOOM, 6.0 * ZOOM, 2.0 * ZOOM),
                colorConnection)
        );

        shapeConnection.add(new ShapeColorContact(
                ContactName.PIN_2,
                new Rectangle2D.Double(3.0 * ZOOM, -3.0 * ZOOM, 2.0 * ZOOM, 6.0 * ZOOM),
                colorConnection)
        );

        shapeConnection.add(new ShapeColorContact(
                ContactName.PIN_3,
                new Rectangle2D.Double(-3.0 * ZOOM, 3.0 * ZOOM, 6.0 * ZOOM, 2.0 * ZOOM),
                colorConnection)
        );

        shapeConnection.add(new ShapeColorContact(
                ContactName.PIN_4,
                new Rectangle2D.Double(-5.0 * ZOOM, -3.0 * ZOOM, 2.0 * ZOOM, 6.0 * ZOOM),
                colorConnection)
        );
    }

    @Override
    protected void setPinModel(ArrayList<PinModel> pinModel, Color colorContact) {
        pinModel.add(new PinModel(
                this,
                ContactName.PIN_1,
                new ShapeColor(
                        new Ellipse2D.Double(-0.5 * ZOOM, -0.5 * ZOOM, 1.0 * ZOOM, 1.0 * ZOOM),
                        colorContact),
                new ShapeColor(
                        new Ellipse2D.Double(-0.5 * ZOOM, -10.5 * ZOOM, 1.0 * ZOOM, 1.0 * ZOOM),
                        colorContact))
        );

        pinModel.add(new PinModel(
                this,
                ContactName.PIN_2,
                new ShapeColor(
                        new Ellipse2D.Double(-0.5 * ZOOM, -0.5 * ZOOM, 1.0 * ZOOM, 1.0 * ZOOM),
                        colorContact),
                new ShapeColor(
                        new Ellipse2D.Double(9.5 * ZOOM, -0.5 * ZOOM, 1.0 * ZOOM, 1.0 * ZOOM),
                        colorContact))
        );

        pinModel.add(new PinModel(
                this,
                ContactName.PIN_3,
                new ShapeColor(
                        new Ellipse2D.Double(-0.5 * ZOOM, -0.5 * ZOOM, 1.0 * ZOOM, 1.0 * ZOOM),
                        colorContact),
                new ShapeColor(
                        new Ellipse2D.Double(-0.5 * ZOOM, 9.5 * ZOOM, 1.0 * ZOOM, 1.0 * ZOOM),
                        colorContact))
        );

        pinModel.add(new PinModel(
                this,
                ContactName.PIN_4,
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
        return "ModelShina10kV_v2{" +
                "name='" + name + '\'' +
                "}";
    }

    @Override
    public Object clone() {
        return new ModelShina10kV(this.name, this.isOn());
    }
}