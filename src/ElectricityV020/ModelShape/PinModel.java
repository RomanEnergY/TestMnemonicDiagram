package ElectricityV020.ModelShape;

import ElectricityV020.RectShape;
import ElectricityV020.RectShapes;

import java.awt.*;

/**
 * Created by user on 23.08.2018.
 */
public class PinModel implements Cloneable {
    // поля собственного контакта соединения
    private boolean enableConnection; // значение true разрешает подключение
    private boolean activeConnection; // значение true говорит об активном контакте

    // Описание собственного объекта соединения
    private ModelRectangularShape modelRectShape; // модель собственного объекта
    private ContactName contactName; // имя контакта собственного объекта
    private RectShapes rectMyContact; // точка подключения собственного объекта

    private RectShapes rectConnectionContact; // точка подключения  подключаемого объекта
    private PinModel connectionPin;

    private RectShapes rectOnConnect; // фигура отображения при разрешенном подключении
    private RectShapes rectOffConnect; // фигура отображения при запрещенном подключении

    public PinModel(ModelRectangularShape modelRectShape,
                    ContactName contactName,
                    RectShapes rectMyContact,
                    RectShapes rectConnectionContact,
                    RectShapes rectOnConnect,
                    RectShapes rectOffConnect) {

        this.enableConnection = true;
        this.modelRectShape = modelRectShape;
        this.contactName = contactName;
        this.rectMyContact = rectMyContact;
        this.rectConnectionContact = rectConnectionContact;
        this.connectionPin = null;
        this.rectOnConnect = rectOnConnect;
        this.rectOffConnect = rectOffConnect;
    }

    public PinModel() {
        this(null, ContactName.PIN_NULL, null, null, null, null);
    }

    public void setEnableConnection(boolean enableConnection) {
        this.enableConnection = enableConnection;
    }

    public void setActiveConnection(boolean activeConnection) {
        this.activeConnection = activeConnection;
        if (this.connectionPin != null) {
            connectionPin.activeConnection = activeConnection; // требуется для визулизации в момент подключения
        }
    }

    public void paint(Graphics2D graphics2D, Color colorOnConnection, Color colorActiveConnection, Color colorOffConnection) {
        // отрисовка условных точек подключения объекта
        if (this.activeConnection)
            if (this.connectionPin == null)
                this.paint(this.rectConnectionContact, graphics2D, this.enableConnection ? colorActiveConnection : colorOffConnection);

        if (this.connectionPin != null)
            this.paint(rectOnConnect, graphics2D, this.activeConnection ? colorActiveConnection : colorOnConnection);

    }

    private void paint(RectShapes shapes, Graphics2D g, Color color) {
        if (shapes != null) {
            shapes.paint(g, color);
        }
    }

    public void setConnection(PinModel connectionPin) {
        this.connectionPin = connectionPin;

        connectionPin.connectionPin = this;
        connectionPin.activeConnection = true; // требуется для визулизации в момент подключения
    }

    public void breakConnection() {
        if (this.connectionPin != null) {
            this.connectionPin.activeConnection = false;
//            this.connectionPin.activeConnection = false;
            this.connectionPin.connectionPin = null;
            this.connectionPin = null;
        }
    }


    public void setFrameDragged(Point point) {
        this.setFrameDragged(point, rectMyContact);
        this.setFrameDragged(point, rectConnectionContact);
        this.setFrameDragged(point, rectOnConnect);
        this.setFrameDragged(point, rectOffConnect);
    }

    private void setFrameDragged(Point point, RectShapes rectShapes) {
        rectShapes.setFrameDragged(point);
    }

    public String contactNameToString() {
        String str = "";
        str = "{" +
                " " + modelRectShape.getName() +
                " " + contactName +
                " ------> ";
        if (connectionPin != null) {
            str = str + " " + connectionPin.modelRectShape.getName() +
                    " " + connectionPin.contactName;
        } else {
            str = str + " null";
        }

        return str + '}';
    }

    public boolean isConnection(PinModel pinModelConnection) {
        return this.rectMyContact.isEquals(pinModelConnection.rectConnectionContact) && this.rectConnectionContact.isEquals(pinModelConnection.rectMyContact);
    }

    public void deploy(ModelType modelType, ModelType modelToType, Point pointObject) {
        this.rectMyContact.deploy(modelType, modelToType, pointObject);
        this.rectConnectionContact.deploy(modelType, modelToType, pointObject);
        this.rectOnConnect.deploy(modelType, modelToType, pointObject);
        this.rectOffConnect.deploy(modelType, modelToType, pointObject);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        super.clone();

        PinModel pinModel = new PinModel();
        pinModel.enableConnection = this.enableConnection;
        pinModel.activeConnection = this.activeConnection;

        // Описание собственного объекта соединения
        pinModel.modelRectShape = (ModelRectangularShape) this.modelRectShape.clone(); // модель собственного объекта
        pinModel.contactName = this.contactName; // имя контакта собственного объекта
        pinModel.rectMyContact = (RectShapes) this.rectMyContact.clone(); // точка подключения собственного объекта

        pinModel.rectConnectionContact = (RectShapes) rectConnectionContact.clone(); // точка подключения  подключаемого объекта
        pinModel.connectionPin = (PinModel) this.connectionPin.clone();

        pinModel.rectOnConnect = (RectShapes) this.rectOnConnect.clone(); // фигура отображения при разрешенном подключении
        pinModel.rectOffConnect = (RectShapes) this.rectOffConnect.clone(); // фигура отображения при запрещенном подключении

        return pinModel;
    }
}
