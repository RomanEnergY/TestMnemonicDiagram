package ElectricityV020;

import ElectricityV020.ModelShape.ModelRectangularShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * Created by user on 05.07.2018.
 */
public class PantPanel extends Component implements MouseListener, MouseMotionListener, KeyListener, FocusListener {
    private JFrame jFrameOwnerJDialog; // Фрейм который перекрывается модальным окном

    private ArrayList<ModelRectangularShape> modelRectangularShapes = new ArrayList<>();
    private Point startPointModel = new Point(0, 0);
    private ModelRectangularShape activeModelRectangular;
    private PanelObject panelObject;
    private double modelRectShapeLength = ModelRectangularShape.ZOOM * 10;
    private boolean moveActiveModelRectangular;

    public PantPanel(JFrame jFrameOwnerJDialog, PanelObject panelObject) {
        this.jFrameOwnerJDialog = jFrameOwnerJDialog;
        this.panelObject = panelObject;
        this.moveActiveModelRectangular = false;
        this.setFocusable(true);

        this.addMouseListener(this); // Регистрируем слушателя, адаптер
        this.addMouseMotionListener(this); // Регистрируем слушателя, интерфейс (перетаскивание и перемещение)
        this.addFocusListener(this);
        this.addKeyListener(this);
    }

    /**
     * Метод возвращиет ModelRectangularShape объекта, если хотя бы одна точка поподает на этот объект
     *
     * @param point Point point точка
     * @return ModelRectangularShape
     */
    private ModelRectangularShape find(Point point) {
        if (point != null) {
            for (int count = modelRectangularShapes.size() - 1; count >= 0; count--) {
                if (modelRectangularShapes.get(count).isFind(point)) { // Проверяем есть ли в указанном месте точке наличие фигуры
                    return modelRectangularShapes.get(count);
                }
            }
        }
        return null;
    }

    /**
     * Метод возвращиет Point point вводя в координаты точки
     *
     * @param point
     * @return
     */
    private Point setPointModelRectangularShape(Point point) {
        point = new Point(
                (int) ((int) (point.getX() / modelRectShapeLength) * modelRectShapeLength + (int) modelRectShapeLength / 2),
                (int) ((int) (point.getY() / modelRectShapeLength) * modelRectShapeLength + (int) modelRectShapeLength / 2)
        );

        if (startPointModel.getX() != point.getX() || startPointModel.getY() != point.getY()) {
            startPointModel = new Point(point.x, point.y);
//            System.out.println(startPointModel);
        }

        return startPointModel;
    }

    private boolean changePointModelRectangularShape(Point point) {
        point = new Point((int) ((int) (point.getX() / modelRectShapeLength) * modelRectShapeLength + (int) modelRectShapeLength / 2), (int) ((int) (point.getY() / modelRectShapeLength) * modelRectShapeLength + (int) modelRectShapeLength / 2));

        if (startPointModel.getX() != point.getX() || startPointModel.getY() != point.getY())
            return true;

        return false;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        // отрисовка координатной сетки
        for (int i = (int) modelRectShapeLength / 2; i < this.getWidth(); i = (int) (i + modelRectShapeLength)) {
            for (int j = (int) modelRectShapeLength / 2; j < this.getWidth(); j = (int) (j + modelRectShapeLength)) {
                graphics2D.setColor(new Color(155, 155, 155)); // получаем цвет фигуры
                graphics2D.fill(new Ellipse2D.Double(i - 1, j - 1, 3, 3)); // отресовываем переданную фигуру
            }
        }

        // отрисовка всех объектов на панели
        for (ModelRectangularShape modelRectangularShape : modelRectangularShapes) {
            if (this.activeModelRectangular == null)
                if (modelRectangularShape.equals(this.activeModelRectangular))
                    continue;

            modelRectangularShape.paint(g);
        }

        if (this.panelObject.isSelection()) {
            this.panelObject.getActiveModelRectangular().paint(g);
        }

        if (this.activeModelRectangular != null) {
            this.activeModelRectangular.paint(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
            this.activeModelRectangular = this.find(e.getPoint());
            if (this.activeModelRectangular != null) {
                // TODO вывести модальное оано на экран вернуть дата, время и состояние
//            AboutModelRectangularShapeDialog dialog = AboutModelRectangularShapeDialog.getAboutModelRectangularShapeDialog(jFrameOwnerJDialog, findModelRectangularShape);
                this.activeModelRectangular.setOn(!this.activeModelRectangular.isOn());
//                this.activeModelRectangular.deploy(ModelType.BOTTOM);
//                System.out.println(selectionModelRectangular);
                repaint();
            }
        }

        if (e.getButton() == MouseEvent.BUTTON3) { // Если нажата правая кнопка мыши
            // Если выбран объект установки из панели объектов, его требутся удалить и сбросить выбранный объект
            if (this.panelObject.isSelection()) {
                this.panelObject.getActiveModelRectangular().breakConnectionContacts();
//                selectionModelRectangular = null;
                panelObject.clearSelection();
            } else {
                this.activeModelRectangular = this.find(e.getPoint());
                if (this.activeModelRectangular != null && !this.activeModelRectangular.isActive()) {
                    this.activeModelRectangular.breakConnectionContacts();
                    modelRectangularShapes.remove(this.activeModelRectangular);
                    this.activeModelRectangular = null;
                }
            }
            repaint();
        }
        repaint();
    }

    @Override // Событие возникает при первом нажатии на кнопку мыши
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1: // нажата левая кнопка мыши
                this.activeModelRectangular = this.find(e.getPoint());
                if (this.activeModelRectangular == null) { // Если не попали по объектам, добавляем новый
                    if (this.panelObject.isSelection()) {
                        this.activeModelRectangular = this.panelObject.getActiveModelRectangular();
                        this.activeModelRectangular.setFrameDragged(this.setPointModelRectangularShape(e.getPoint()));

                        // определение подключения при выбраной модели
                        if (this.activeModelRectangular.returnIntersectsModelRectangularShape(this.modelRectangularShapes) == null) {
                            this.activeModelRectangular.breakConnectionContacts();
                            this.activeModelRectangular.connectionContacts(this.modelRectangularShapes);
                            this.activeModelRectangular.setPoint(this.startPointModel);
                            this.activeModelRectangular.setActive(false);
                            this.modelRectangularShapes.add(this.activeModelRectangular);
                            this.panelObject.clearSelection();
                            this.activeModelRectangular = null;
                        }


//                        this.activeModelRectangular = (ModelRectangularShape) this.panelObject.getActiveModelRectangular().clone();
//                        this.activeModelRectangular.setFrameDragged(this.setPointModelRectangularShape(e.getPoint()));
//
//                        // определение подключения при выбраной модели
//                        if (this.activeModelRectangular.returnIntersectsModelRectangularShape(this.modelRectangularShapes) == null) {
//                            this.activeModelRectangular.connectionContacts(this.modelRectangularShapes);
//                            this.activeModelRectangular.setPoint(this.startPointModel);
//                            this.modelRectangularShapes.add(this.activeModelRectangular);
//                            this.panelObject.clearSelection();
//                            this.activeModelRectangular = null;
//                        }
                        repaint();
                    } else {
                        repaint();
                    }
                } else {
                    this.activeModelRectangular.setActive(!this.activeModelRectangular.isActive());
                    repaint();
                }
                break;
            case MouseEvent.BUTTON2: // нажата средняя кнопка мыши
//                System.out.println("case MouseEvent.BUTTON2");
                break;
            case MouseEvent.BUTTON3: // нажата правая кнопка мыши
//                System.out.println("case MouseEvent.BUTTON3");
                break;
        }
    }

    @Override // при отжатии кнопки мыши
    public void mouseReleased(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1: // нажата левая кнопка мыши
                // Проверяем если при отжатии кнопки мыши объект находится под мышкой
                this.activeModelRectangular = this.find(e.getPoint());
                if (this.activeModelRectangular != null) {
                    if (this.moveActiveModelRectangular) {
                        this.moveActiveModelRectangular = false;
                        this.activeModelRectangular.setActive(false); // снимаем ативность объекта
                    }

                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // установка курсора рука с пальцем
                } else {
                    for (ModelRectangularShape modelRectangularShape : modelRectangularShapes) {
                        modelRectangularShape.setActive(false);
                    }
                    setCursor(Cursor.getDefaultCursor()); // получение ссылки на курсор по умочанию
                }

                repaint();

                break;
            case MouseEvent.BUTTON2: // нажата средняя кнопка мыши
//                System.out.println("case MouseEvent.BUTTON2");
                break;
            case MouseEvent.BUTTON3: // нажата правая кнопка мыши
                repaint();
//                System.out.println("case MouseEvent.BUTTON3");
                break;
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    // движение мыши по объекту, любая кнопка мыши нажата
    public void mouseDragged(MouseEvent e) {
        switch (e.getModifiers()) {
            case 16: //Нажата левая кнопка мыши + Ctrl
                if (this.activeModelRectangular != null) {
                    if (this.changePointModelRectangularShape(e.getPoint())) {
                        this.activeModelRectangular.setFrameDragged(this.setPointModelRectangularShape(e.getPoint()));
//                        this.activeModelRectangular.setActive(true);

                        // определение подключения при выбраной модели
                        if (this.activeModelRectangular.returnIntersectsModelRectangularShape(this.modelRectangularShapes) != null) {
                            this.activeModelRectangular.setAllowInstallation(false);
                            this.activeModelRectangular.breakConnectionContacts();
                            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); // установка курсора рука с пальцем
                        } else {
                            this.activeModelRectangular.setAllowInstallation(true);
                            this.activeModelRectangular.connectionContacts(this.modelRectangularShapes);
                            setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR)); // установка курсора движение
                        }

                        this.activeModelRectangular.setPoint(this.startPointModel);
                        this.moveActiveModelRectangular = true;

//                        System.out.println(selectionModelRectangular.connectionToString());

                        repaint();
                    }
                }
                break;
        }
    }

    @Override
    // движение мыши по объекту, кнопка мыши отжата
    public void mouseMoved(MouseEvent e) {
        this.requestFocusInWindow();

        if (this.panelObject.isSelection()) {
            this.panelObject.getActiveModelRectangular().setActive(true);

            if (this.changePointModelRectangularShape(e.getPoint())) {
                this.panelObject.getActiveModelRectangular().setFrameDragged(this.setPointModelRectangularShape(e.getPoint()));
                // определение подключения при выбраной модели
                if (this.panelObject.getActiveModelRectangular().returnIntersectsModelRectangularShape(this.modelRectangularShapes) != null) {
                    this.panelObject.getActiveModelRectangular().setAllowInstallation(false);
                    this.panelObject.getActiveModelRectangular().breakConnectionContacts();
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); // установка курсора рука с пальцем

                } else {
                    this.panelObject.getActiveModelRectangular().setAllowInstallation(true);
                    this.panelObject.getActiveModelRectangular().connectionContacts(this.modelRectangularShapes);
                    setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR)); // установка курсора движение
                }

                this.panelObject.getActiveModelRectangular().setPoint(this.startPointModel);
//                System.out.println(selectionModelRectangular.connectionToString());


                repaint();
            }
        } else {
            this.activeModelRectangular = this.find(e.getPoint());

            if (this.activeModelRectangular != null) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // установка курсора рука с пальцем

            } else {
                setCursor(Cursor.getDefaultCursor()); // получение ссылки на курсор по умочанию
            }
        }


    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) { // нажата кнопка Ctrl
//            this.moveActiveModelRectangular = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) { // отжата кнопка Ctrl
//            this.moveActiveModelRectangular = false;
        }
    }


    @Override
    public void focusGained(FocusEvent e) {
        System.out.println("public void focusGained(FocusEvent e)");
    }

    @Override
    public void focusLost(FocusEvent e) {
        System.out.println("public void focusLost(FocusEvent e)");
        if (this.modelRectangularShapes != null) {
            for (ModelRectangularShape modelRectangularShape : modelRectangularShapes) {
                modelRectangularShape.setActive(false);
            }
        }

        repaint();
    }
}
