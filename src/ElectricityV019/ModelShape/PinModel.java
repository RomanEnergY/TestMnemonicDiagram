package ElectricityV019.ModelShape;

import ElectricityV019.ShapeColor;

import java.awt.*;

/**
 * Created by user on 23.08.2018.
 */
public class PinModel {
    // поля собственного контакта соединения
    private boolean enableConnection; // значение true разрещает подключение
    private boolean nowConnection;

    private ContactName myContactName;
    private ShapeColor myContactNumberView;
    private ModelRectangularShape myModelRectangularShape;

    // поля соединяемого контакта
    private ShapeColor connectionContactNumberView;
    private ContactName connectionContactName;
    private ModelRectangularShape connectionModelRectangularShape;


    public PinModel(boolean enableConnection, ModelRectangularShape myModelRectangularShape, ContactName contactName, ShapeColor myContactNumberView, ShapeColor connectionContactNumberView) {
        this.enableConnection = enableConnection;
        this.nowConnection = false;

        this.myModelRectangularShape = myModelRectangularShape;
        this.myContactName = contactName;
        this.myContactNumberView = myContactNumberView;
        this.connectionContactNumberView = connectionContactNumberView;

        connectionContactName = ContactName.PIN_NULL;
        connectionModelRectangularShape = null;
    }

    public PinModel(ModelRectangularShape myModelRectangularShape, ContactName contactName, ShapeColor myContactNumberView, ShapeColor connectionContactNumberView) {
        this(true, myModelRectangularShape, contactName, myContactNumberView, connectionContactNumberView);
    }

    // Getters

    public ModelRectangularShape getMyModelRectangularShape() {
        return myModelRectangularShape;
    }

    public ShapeColor getMyContactNumberView() {
        return myContactNumberView;
    }

    public ShapeColor getConnectionContactNumberView() {
        return connectionContactNumberView;
    }

    public ContactName getConnectionContactName() {
        return connectionContactName;
    }

    public boolean isNowConnection() {
        return nowConnection;
    }

    public void setNowConnection(boolean nowConnection) {
        this.nowConnection = nowConnection;
    }

    public ContactName getMyContactName() {
        return myContactName;
    }

    public ModelRectangularShape getConnectionModelRectangularShape() {
        return connectionModelRectangularShape;
    }

    // Setters
    public void setConnectionContactName(ContactName connectionContactName) {
        this.connectionContactName = connectionContactName;
    }

    public void setConnectionModelRectangularShape(ModelRectangularShape connectionModelRectangularShape) {
        this.connectionModelRectangularShape = connectionModelRectangularShape;
    }

    public boolean isEnableConnection() {
        return enableConnection;
    }

    public void paint(Graphics2D graphics2D) {
        // Прорисовка собственного контакта подключения
        this.paint(graphics2D, myContactNumberView);
        // Прорисовка контакта подключения
        this.paint(graphics2D, connectionContactNumberView);
    }

    private void paint(Graphics2D graphics2D, ShapeColor shapeColor) {
        graphics2D.setColor(shapeColor.getColor()); // получаем цвет фигуры
        graphics2D.fill(shapeColor.getShape()); // отресовываем переданную фигуру
    }

    @Override
    public String toString() {
        return "ConnectionModelRectangularShape{" +
                "enableConnection=" + enableConnection +
                ", myContactName=" + myContactName +
                ", myContactNumberView=" + myContactNumberView +
                ", myModelRectangularShape=" + myModelRectangularShape +
                ", connectionContactNumberView=" + connectionContactNumberView +
                ", connectionContactName=" + connectionContactName +
                ", connectionModelRectangularShape=" + connectionModelRectangularShape +
                '}';
    }

    public String contactNameToString() {
        return "{" +
                " " + myModelRectangularShape.getName() +
                " " + myContactName +
                " ------> " + connectionModelRectangularShape +
                " " + connectionContactName +
                '}';
    }

    // TODO корректировка метода
    public void setConnection(ModelRectangularShape modelRectangularShape, ModelRectangularShape connectionShape, PinModel connectionModel) {

        this.connectionModelRectangularShape = connectionShape;
        this.connectionContactName = connectionModel.getMyContactName();

        connectionModel.connectionModelRectangularShape = modelRectangularShape;
        connectionModel.connectionContactName = this.getMyContactName();

        // реализуем видимость подключения контакта, далее в ModelRectangularShape.paint после отрисовки снимаем выделение контакта
        this.nowConnection = true;
        connectionModel.nowConnection = true;
    }


    public void breakConnection() {
        // Проверяем если есть у объекта подключенные объекты
        this.connectionModelRectangularShape = null;
        this.connectionContactName = ContactName.PIN_NULL;
    }
}
