package ElectricityV019.ModelShape;

import ElectricityV019.ShapeColor;
import ElectricityV019.ShapeColorContact;

import java.awt.*;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

/**
 * Created by user on 13.07.2018.
 */
public abstract class ModelRectangularShape extends RectangularShape implements Cloneable {
    protected double x;
    protected double y;
    protected double width;
    protected double height;

    String name = "NAME_ModelShape";
    private boolean isOn = false; // определяет состояние, true - положение включене
    public static double ZOOM = 4.0; // поле зумирование объекта
    public boolean active; // определяет активное состояние объекта
    private boolean allowInstallation; // разрешает установку объекта на панель

    private Color colorObject = new Color(0, 150, 0); // цвет постоянного состояния объекта (включенное положение)
    private Color colorOff = new Color(255, 0, 0); // цвет отображения при отключенном положении объекта
    private Color colorFlag = new Color(0, 0, 50); // цвет отображения места установки флажков
    private Color colorConnection = new Color(0, 255, 0); // цвет отображения в момент подключения объектов
    private Color colorContact = new Color(0, 255, 0); // цвет отображения контакта подключения

    private ArrayList<ShapeColor> shapeObject = new ArrayList<>(); // Мнемоническое описание объекта при включенном положении
    private ArrayList<ShapeColor> shapeOff = new ArrayList<>(); // Мнемоническое описание объекта при отключенном положении
    private ArrayList<ShapeColor> shapeFlag = new ArrayList<>(); // Мнемоническое описание места установки флажка
    private ArrayList<ShapeColorContact> shapeConnection = new ArrayList<>(); // Мнемоническое описание при подключении к объекту
    private ArrayList<PinModel> pinModel = new ArrayList<>(); // Мнемоническое описание точек подключения к объекту

    ModelRectangularShape(String name, boolean isOn) {
        this.name = name;
        this.isOn = isOn;
        this.active = false;
        this.allowInstallation = true;

        this.setShapeObject(this.shapeObject, this.colorObject);
        this.setShapeOff(this.shapeOff, this.colorOff);
        this.setShapeFlag(this.shapeFlag, this.colorFlag);
        this.setShapeConnection(this.shapeConnection, this.colorConnection);
        this.setPinModel(this.pinModel, this.colorContact);
    }

    protected abstract void setShapeObject(ArrayList<ShapeColor> shapeObject, Color colorObject);

    protected abstract void setShapeOff(ArrayList<ShapeColor> shapeOff, Color colorOff);

    protected abstract void setShapeFlag(ArrayList<ShapeColor> shapeFlag, Color colorFlag);

    protected abstract void setShapeConnection(ArrayList<ShapeColorContact> shapeConnection, Color colorConnection);

    protected abstract void setPinModel(ArrayList<PinModel> pinModel, Color colorContact);

    public String getName() {
        return this.name;
    }

    public boolean isSet() {
        return allowInstallation;
    }

    public void setAllowInstallation(boolean set) {
        this.allowInstallation = set;
    }

    /**
     * Метод проверяет, если ли в точке Point ModelShape
     *
     * @param point указанная точка
     * @return true в точке point имеется объект ModelShape
     */
    public boolean isFind(Point point) {
        if (point != null) {
            for (int count = this.shapeObject.size() - 1; count >= 0; count--) {
                if (this.shapeObject.get(count).getShape().contains(point)) { // Проверяем есть ли в указанном месте точке наличие фигуры
                    return true;
                }
            }

            for (int count = shapeOff.size() - 1; count >= 0; count--) {
                if (shapeOff.get(count).getShape().contains(point)) { // Проверяем есть ли в указанном месте точке наличие фигуры
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Метод рисует объекты ShapeAndColor на панели
     *
     * @param g ссылка объект Graphics
     */
    public final void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        // Отрисовка объекта при включенном положении
        this.paint(graphics2D, this.shapeObject);

        // Отрисовка объекта при отключенном положении - не включен
        if (!this.isOn)
            this.paint(graphics2D, this.shapeOff);

        // Отрисовка места установки флажка
        this.paint(graphics2D, this.shapeFlag);

        // Отрисовка точек подключения
        if (this.active) {
            for (PinModel pinModel : this.pinModel) {
                graphics2D.setColor(this.allowInstallation ? pinModel.getMyContactNumberView().getColor() : colorOff); // получаем цвет фигуры
                graphics2D.fill(pinModel.getMyContactNumberView().getShape()); // отресовываем переданную фигуру

                graphics2D.setColor(this.allowInstallation ? pinModel.getConnectionContactNumberView().getColor() : colorOff); // получаем цвет фигуры
                graphics2D.fill(pinModel.getConnectionContactNumberView().getShape()); // отресовываем переданную фигуру
            }
        }

        // Отрисовка линий связи, временных и постоянных
        this.paint(this.shapeConnection, graphics2D);

    }

    private void paint(ArrayList<ShapeColorContact> shapeColors, Graphics2D graphics2D) {
        for (ShapeColorContact shapeColor : shapeColors) {
            for (PinModel connection : this.pinModel) {
                if (shapeColor.getContactName() == connection.getMyContactName()) {
                    if (connection.getConnectionModelRectangularShape() != null) {
                        graphics2D.setColor(connection.isNowConnection() ? this.colorConnection : colorObject); // получаем цвет фигуры
                        graphics2D.fill(shapeColor.getShape()); // отресовываем переданную фигуру

                        // возвращием нормальный цвет соединения контактов
                        if (connection.isNowConnection())
                            connection.setNowConnection(false);
                    }
                }
            }
        }
    }

    private void paint(Graphics2D graphics2D, ArrayList<ShapeColor> shapeColors) {
        for (ShapeColor shapeColor : shapeColors) {
            graphics2D.setColor(shapeColor.getColor()); // получаем цвет фигуры
            graphics2D.fill(shapeColor.getShape()); // отресовываем переданную фигуру
        }
    }

    private void paint(Graphics2D graphics2D, ShapeColor shapeColor) {
        graphics2D.setColor(shapeColor.getColor()); // получаем цвет фигуры
        graphics2D.fill(shapeColor.getShape()); // отресовываем переданную фигуру
    }

    /**
     * Метод устанавливает объект на панели рисования (стартовое положение и при перетаскивании)
     *
     * @param point точка, куда следует установить/передвинуть объект
     */
    public void setFrame(Point point) {

        // Перенос объекта при включенном положении
        this.setFrame(this.shapeObject, point);

        // Перенос объекта при отключенном положении - не включен
        this.setFrame(this.shapeOff, point);

        // Перенос места установки флажка
        this.setFrame(this.shapeFlag, point);

        // Перенос места расположения собственного контакта подключения и контакта подключения
        for (PinModel pinModel : this.pinModel) {
            // Изменение расположения собственного контакта подключения
            pinModel.getMyContactNumberView().setFrameDragged(point);

            // Изменение расположения контакта подключения
            pinModel.getConnectionContactNumberView().setFrameDragged(point);
        }

        this.setFrame(point, this.shapeConnection);
    }

    private void setFrame(Point point, ArrayList<ShapeColorContact> shapeColorsContact) {
        for (ShapeColorContact shapeColorContact : shapeColorsContact) {
            shapeColorContact.setFrameDragged(point);
        }
    }

    private void setFrame(ArrayList<ShapeColor> shapeColors, Point point) {
        for (ShapeColor shapeColor : shapeColors) {
            shapeColor.setFrameDragged(point);
        }
    }

    @Override
    public Object clone() {
        return super.clone();
    }

    public boolean isOn() {
        return isOn;
    }

    public boolean setOn(boolean isOn) {
        boolean b = false;
        if (!isOn) { // команда отключить
            if (!this.isOn) { // если положение отключено
//                System.out.println(this.name + " отключен ранее");
            } else {
//                System.out.println(this.name + " отключен");
                this.isOn = isOn;
                b = true;

            }
        } else { // команда включить
            if (!this.isOn) { // если положение отключено
//                System.out.println(this.name + " включен");
                this.isOn = isOn;
                b = true;
            } else {
//                System.out.println(this.name + " включен ранее");
            }
        }
        return b;
    }

    // TODO рабочий код метода, наличие бага соеденения
    public boolean isCheckConnectionContacts(ModelRectangularShape shape) {
        for (PinModel thisPinModel : this.pinModel) {
            for (PinModel connectionPinModel : shape.pinModel) {
                // проверяем расположение точек подключения графическим способом
                if (thisPinModel.getConnectionContactNumberView().getFrame().equals(connectionPinModel.getMyContactNumberView().getFrame())) {
                    if (thisPinModel.getMyContactNumberView().getFrame().equals(connectionPinModel.getConnectionContactNumberView().getFrame())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void setConnectionContacts(ModelRectangularShape shape) {
        for (PinModel thisPinModel : this.pinModel) {
            for (PinModel connectionPinModel : shape.pinModel) {
                // проверяем расположение точек подключения графическим способом
                if (thisPinModel.getConnectionContactNumberView().getFrame().equals(connectionPinModel.getMyContactNumberView().getFrame())) {
                    if (thisPinModel.getMyContactNumberView().getFrame().equals(connectionPinModel.getConnectionContactNumberView().getFrame())) {
                        thisPinModel.setConnection(this, shape, connectionPinModel);
                    }
                }
            }
        }
    }

    public void breakConnectionContacts() {
        for (PinModel pinModel : this.pinModel) {
            if (pinModel.getConnectionModelRectangularShape() != null) {
                for (PinModel connectionPinModel : pinModel.getConnectionModelRectangularShape().pinModel) {
                    if (connectionPinModel.getConnectionModelRectangularShape() != null) {
                        if (pinModel.getMyModelRectangularShape().equals(connectionPinModel.getConnectionModelRectangularShape())) {
                            pinModel.breakConnection();
                            connectionPinModel.breakConnection();
                        }
                    }
                }
            }
        }

        for (PinModel pinModel : this.pinModel) {
            if (pinModel.getConnectionModelRectangularShape() != null) {
                pinModel.breakConnection();
            }
        }
    }

    private String contactNameToString() {
        String s = "";
        for (PinModel thisModelRectangularShape : pinModel) {
            s = s + thisModelRectangularShape.contactNameToString() + '\n';
        }
        return s;
    }

    public String connectionToString() {
        return this.contactNameToString();
    }




    public void connectionContacts(ArrayList<ModelRectangularShape> modelRectangularShapes) {
        this.breakConnectionContacts();

        for (ModelRectangularShape shape : modelRectangularShapes) {
            if (this.isCheckConnectionContacts(shape)) {
                this.setConnectionContacts(shape);
            }

        }
    }

    /**
     * Возвращет ModelRectangularShape shape фигуры пересекаемой с внутренней частью фигур в листе this.modelRectangularShapes
     *
     * @return ModelRectangularShape shape фигуры пересекаемой с внутренней частью фигур в листе this.modelRectangularShapes
     */
    public ModelRectangularShape returnIntersectsModelRectangularShape(ArrayList<ModelRectangularShape> modelRectangularShapes) {
        if (modelRectangularShapes.size() != 0) {
            for (ModelRectangularShape modelRectangularShape : modelRectangularShapes) {
                if (this.equals(modelRectangularShape)) {
                    continue;
                }

                if (this.intersects(modelRectangularShape)) {
                    return modelRectangularShape;
                }
            }
        }

        return null;
    }

    private boolean intersects(ModelRectangularShape modelRectangularShape) {
        if (this.intersects(this.shapeObject, modelRectangularShape.shapeObject)) {
            return true;
        } else if (this.intersects(this.shapeOff, modelRectangularShape.shapeOff)) {
            return true;
        } else if (this.intersects(this.shapeFlag, modelRectangularShape.shapeFlag)) {
            return true;
        }

        return false;
    }

    private boolean intersects(ArrayList<ShapeColor> thisShapes, ArrayList<ShapeColor> connectionShapes) {
        for (ShapeColor thisShape : thisShapes) {
            for (ShapeColor connectionShape : connectionShapes) {
                if (thisShape.getFrame().intersects(connectionShape.getFrame().getX(), connectionShape.getFrame().getY(), connectionShape.getFrame().getWidth(), connectionShape.getFrame().getHeight())) {
                    return true;
                }
            }
        }
        return false;
    }
}