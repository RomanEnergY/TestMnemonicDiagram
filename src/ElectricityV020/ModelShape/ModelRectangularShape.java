package ElectricityV020.ModelShape;

import ElectricityV020.RectShapes;

import java.awt.*;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

/**
 * Created by user on 13.07.2018.
 */
public abstract class ModelRectangularShape extends RectangularShape implements Cloneable {
    private Point pointObject;
    protected double x;
    protected double y;
    protected double width;
    protected double height;

    String name = "NAME_ModelShape";
    protected ModelType modelType;

    private boolean isOn = false; // определяет состояние, true - положение включене
    public static double ZOOM = 5.0; // поле зумирование объекта
    private boolean active; // определяет активное состояние объекта
    private boolean allowInstallation; // разрешает установку объекта на панель

    private Color colorObject = new Color(0, 150, 0); // цвет постоянного состояния объекта (включенное положение)
    private Color colorOff = new Color(255, 0, 0); // цвет отображения при отключенном положении объекта
    private Color colorFlag = new Color(0, 0, 50); // цвет отображения места установки флажков
    private Color colorActiveConnection = new Color(0, 255, 0); // цвет отображения в момент подключения объектов
    private Color colorContact = new Color(0, 255, 0); // цвет отображения контакта подключения

    private RectShapes shapeObject; // Мнемоническое описание объекта при включенном положении
    private RectShapes shapeOff; // Мнемоническое описание объекта при отключенном положении
    private RectShapes shapeFlag; // Мнемоническое описание места установки флажка
    private ArrayList<PinModel> pinModels = new ArrayList<>(); // Мнемоническое описание точек подключения к объекту

    /**
     * Конструктор с нулевыми параметрами (по умолчанию)
     */
    private ModelRectangularShape(){
        this.modelType = ModelType.BOTTOM;
        this.isOn = false;

        this.active = false;
        this.allowInstallation = true;

        shapeObject = this.setShapeObject(this.colorObject);
        shapeOff = this.setShapeOff(this.colorOff);
        shapeFlag = this.setShapeFlag(this.colorFlag);
        this.setPinModel(this.pinModels, this.colorObject, this.colorActiveConnection, this.colorOff);
        this.pointObject = new Point(0,0);
    }

    /**
     *
     * @param name
     * @param isOn
     */
    ModelRectangularShape(String name, boolean isOn) {
        this();
        this.name = name;
        this.isOn = isOn;
    }

    ModelRectangularShape(ModelType modelType, String name, boolean isOn) {
        this(name, isOn);
        this.deploy(modelType);
    }

    protected abstract RectShapes setShapeObject(Color colorObject);

    protected abstract RectShapes setShapeOff(Color colorOff);

    protected abstract RectShapes setShapeFlag(Color colorFlag);

    public void setPoint(Point point) {
        this.pointObject = point;
    }

//    protected abstract void setShapeConnection(ArrayList<ShapeColorContact> shapeConnection, Color colorConnection);

    protected abstract void setPinModel(ArrayList<PinModel> pinModels, Color colorOnConnection, Color colorActiveConnection, Color colorOffConnection);

    public String getName() {
        return this.name;
    }

    public boolean isAllowInstallation() {
        return allowInstallation;
    }

    public void setAllowInstallation(boolean allowInstallation) {
        this.allowInstallation = allowInstallation;
        for (PinModel pinModel : this.pinModels) {
            pinModel.setEnableConnection(allowInstallation);
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        for (PinModel pinModel : this.pinModels) {
            pinModel.setActiveConnection(active);
        }
    }

    /**
     * Метод проверяет, если ли в точке Point ModelShape
     *
     * @param point указанная точка
     * @return true в точке point имеется объект ModelShape
     */
    public boolean isFind(Point point) {
        if (point != null) {
            // TODO корректировка
            if (this.shapeObject != null) {
                if (this.shapeObject.isFind(point))
                    return true;
            }

            if (this.shapeOff != null) {
                if (this.shapeOff.isFind(point))
                    return true;
            }

            if (this.shapeFlag != null) {
                if (this.shapeFlag.isFind(point))
                    return true;
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
        this.paint(shapeObject, graphics2D, colorObject);


        // Отрисовка объекта при отключенном положении - не включен
        if (!this.isOn)
            this.paint(shapeOff, graphics2D, colorOff);

        // Отрисовка места установки флажка
        this.paint(shapeFlag, graphics2D, colorFlag);

        // Отрисовка точек подключения
        for (PinModel pinModel : pinModels)
            pinModel.paint(graphics2D, colorObject, colorActiveConnection, colorOff);

    }

    private void paint(RectShapes shape, Graphics2D g, Color color) {
        if (shape != null) {
            shape.paint(g, color);
        }
    }

    /**
     * Метод устанавливает объект на панели рисования (стартовое положение и при перетаскивании)
     *
     * @param point точка, куда следует установить/передвинуть объект
     */
    public void setFrameDragged(Point point) {
        // Перенос объекта при включенном положении
        if (this.shapeObject != null)
            this.shapeObject.setFrameDragged(point);

        // Перенос объекта при отключенном положении - не включен
        if (this.shapeOff != null)
            this.shapeOff.setFrameDragged(point);

        // Перенос места установки флажка
        if (this.shapeFlag != null)
            this.shapeFlag.setFrameDragged(point);

        // Перенос места расположения собственного контакта подключения и контакта подключения
        for (PinModel pinModel : this.pinModels) {
            // Изменение расположения собственного контакта подключения
            pinModel.setFrameDragged(point);

//            pinModel.getMyContactNumberView().setFrameDragged(point);
//
//            // Изменение расположения контакта подключения
//            pinModel.getConnectionContactNumberView().setFrameDragged(point);


        }
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
    public boolean isCheckConnectionContacts(ModelRectangularShape shapeConnection) {
        if (shapeConnection != null)
            if (this.pinModels != null && shapeConnection.pinModels != null)
                for (PinModel pinModelThis : this.pinModels)
                    for (PinModel pinModelConnection : shapeConnection.pinModels)
                        if (pinModelThis.isConnection(pinModelConnection))
                            return true;


        // проверяем расположение точек подключения графическим способом
        // TODO ошибка - коррекция
//                if (thisPinModel.getConnectionContactNumberView().getFrame().equals(connectionPinModel.getMyContactNumberView().getFrame())) {
//                    if (thisPinModel.getMyContactNumberView().getFrame().equals(connectionPinModel.getConnectionContactNumberView().getFrame())) {
//                        return true;
//                    }
//                }


        return false;
    }

    public void setConnectionContacts(ModelRectangularShape shapeConnection) {
        this.breakConnectionContacts();

        if (shapeConnection != null)
            if (this.pinModels != null && shapeConnection.pinModels != null)
                for (PinModel pinModelThis : this.pinModels)
                    for (PinModel pinModelConnection : shapeConnection.pinModels) {
                        if (pinModelThis.isConnection(pinModelConnection))
                            pinModelThis.setConnection(pinModelConnection);
                    }
    }

    public void breakConnectionContacts() {
        for (PinModel pinModel : this.pinModels) {
            pinModel.breakConnection();
        }
    }

    @Override
    public Object clone() {
        return super.clone();
    }

    private String contactNameToString() {
        String s = "";
        for (PinModel thisModelRectangularShape : pinModels) {
            s = s + thisModelRectangularShape.contactNameToString() + '\n';
        }
        return s;
    }

    public String connectionToString() {
        return "pointObject: [" + this.pointObject.x +":" + this.pointObject.y + "] " +
                "modelType:" + this.modelType + '\n' +
                this.contactNameToString();
    }


    public void connectionContacts(ArrayList<ModelRectangularShape> modelRectangularShapes) {
        this.breakConnectionContacts();

        for (ModelRectangularShape shape : modelRectangularShapes) {
            for (PinModel pinModelThis : this.pinModels)
                for (PinModel pinModelConnection : shape.pinModels) {
                    if (pinModelThis.isConnection(pinModelConnection))
                        pinModelThis.setConnection(pinModelConnection);
                }

//            if (this.isCheckConnectionContacts(shape)) {
//                this.setConnectionContacts(shape);
//            }

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
        if (this.shapeObject != null) {
            if (this.shapeObject.intersects(modelRectangularShape.shapeObject)) {
                return true;
            }
        }

        if (this.shapeOff != null) {
            if (this.shapeOff.intersects(modelRectangularShape.shapeOff)) {
                return true;
            }
        }

        if (this.shapeFlag != null) {
            if (this.shapeFlag.intersects(modelRectangularShape.shapeFlag)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод переворачивает объект
     * @param modelToType значение поворота
     */
    public void deploy(ModelType modelToType) {
        this.breakConnectionContacts();

        if (this.shapeObject != null)
            this.shapeObject.deploy(this.modelType, modelToType, this.pointObject);

        if (this.shapeOff != null)
            this.shapeOff.deploy(this.modelType, modelToType, this.pointObject);

        if (this.shapeFlag != null)
            this.shapeFlag.deploy(this.modelType, modelToType, this.pointObject);

        if (this.pinModels != null) {
            for (PinModel pinModel : pinModels)
                pinModel.deploy(this.modelType, modelToType, this.pointObject);
        }

        this.modelType = modelToType;
    }
}