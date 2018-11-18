package ElectricityV019;

import ElectricityV019.ModelShape.ContactName;

import java.awt.*;
import java.awt.geom.RectangularShape;

/**
 * Created by user on 27.08.2018.
 */
public class ShapeColorContact extends ShapeColor {
    private ContactName contactName;

    public ShapeColorContact(ContactName contactName, RectangularShape rectangularShape, Color color) {
        super(rectangularShape, color);
        this.contactName = contactName;
    }

    public ContactName getContactName() {
        return contactName;
    }
}
