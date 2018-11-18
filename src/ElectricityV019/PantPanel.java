package ElectricityV019;

import ElectricityV019.ModelShape.ModelRectangularShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * Created by user on 05.07.2018.
 */
public class PantPanel extends Component implements MouseListener, MouseMotionListener {
    private JFrame jFrameOwnerJDialog; // Фрейм который перекрывается модальным окном

    private ArrayList<ModelRectangularShape> modelRectangularShapes = new ArrayList<>();
    private Point startPointModel = new Point(0, 0);
    private ModelRectangularShape selectionModelRectangular;
    private ModelRectangularShape tempSelectionModelRectangular;
    private PanelObject panelObject;
    private double modelRectShapeLength = ModelRectangularShape.ZOOM * 10;

    public PantPanel(JFrame jFrameOwnerJDialog, PanelObject panelObject) {
        this.jFrameOwnerJDialog = jFrameOwnerJDialog;
        this.panelObject = panelObject;

        this.addMouseListener(this); // Регистрируем слушателя, адаптер
        this.addMouseMotionListener(this); // Регистрируем слушателя, интерфейс (перетаскивание и перемещение)

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
        point = new Point((int) ((int) (point.getX() / modelRectShapeLength) * modelRectShapeLength + (int) modelRectShapeLength / 2), (int) ((int) (point.getY() / modelRectShapeLength) * modelRectShapeLength + (int) modelRectShapeLength / 2));

        if (startPointModel.getX() != point.getX() || startPointModel.getY() != point.getY()) {
            startPointModel = new Point(point.x, point.y);
        }

        return startPointModel;
    }

    private boolean changePointModelRectangularShape(Point point) {
        point = new Point((int) ((int) (point.getX() / modelRectShapeLength) * modelRectShapeLength + (int) modelRectShapeLength / 2), (int) ((int) (point.getY() / modelRectShapeLength) * modelRectShapeLength + (int) modelRectShapeLength / 2));

        if (startPointModel.getX() != point.getX() || startPointModel.getY() != point.getY()) {
//            System.out.println("changePointModelRectangularShape: " + true);
            return true;
        }

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
            if (this.selectionModelRectangular != null && this.selectionModelRectangular.equals(modelRectangularShape)) {
                continue;
            }
            modelRectangularShape.paint(g);
        }

        if (this.selectionModelRectangular != null) {
            this.selectionModelRectangular.paint(g);
            this.selectionModelRectangular.setAllowInstallation(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
            ModelRectangularShape findModelRectangularShape = find(e.getPoint());

            // TODO вывести модальное оано на экран вернуть дата, время и состояние
//            AboutModelRectangularShapeDialog dialog = AboutModelRectangularShapeDialog.getAboutModelRectangularShapeDialog(jFrameOwnerJDialog, findModelRectangularShape);
            findModelRectangularShape.setOn(!findModelRectangularShape.isOn());
            System.out.println(findModelRectangularShape);
            repaint();

        }

        if (e.getButton() == MouseEvent.BUTTON3) { // Если нажата правая кнопка мыши
            // Если выбран объект установки из панели объектов, его требутся удалить и сбросить выбранный объект
            if (panelObject.isSelection() && selectionModelRectangular != null) {
                selectionModelRectangular.breakConnectionContacts();
                selectionModelRectangular = null;
                panelObject.clearSelection();
            } else {
                selectionModelRectangular = find(e.getPoint());
                if (selectionModelRectangular != null) {
                    selectionModelRectangular.breakConnectionContacts();
                    modelRectangularShapes.remove(selectionModelRectangular);
                    selectionModelRectangular = null;
                }
            }

            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1: // нажата левая кнопка мыши
                selectionModelRectangular = find(e.getPoint());
                if (selectionModelRectangular == null) { // Если не попали по объектам, добавляем новый
                    if (panelObject.getModelRectangularShapeSelection() != null) {

                        selectionModelRectangular = (ModelRectangularShape) panelObject.getModelRectangularShapeSelection().clone();
                        selectionModelRectangular.setFrame(this.setPointModelRectangularShape(e.getPoint()));

                        // определение подключения при выбраной модели
                        selectionModelRectangular.connectionContacts(this.modelRectangularShapes);
                        modelRectangularShapes.add(selectionModelRectangular);

//                        selectionModelRectangular = null;
                        panelObject.clearSelection();

                        repaint();
                    }
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

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1: // нажата левая кнопка мыши
                // Проверяем если при отжатии кнопки мыши выбранный объект есть
                if (selectionModelRectangular != null) {
                    selectionModelRectangular.active = false; // снимаем ативность объекта

                    selectionModelRectangular = find(e.getPoint());
                    if (selectionModelRectangular != null) {
                        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // установка курсора рука с пальцем
                    } else
                        setCursor(Cursor.getDefaultCursor()); // получение ссылки на курсор по умочанию
                } else {
                    setCursor(Cursor.getDefaultCursor()); // получение ссылки на курсор по умочанию
                }

                repaint();

                break;
            case MouseEvent.BUTTON2: // нажата средняя кнопка мыши
//                System.out.println("case MouseEvent.BUTTON2");
                break;
            case MouseEvent.BUTTON3: // нажата правая кнопка мыши
                setCursor(Cursor.getDefaultCursor()); // получение ссылки на курсор по умочанию
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
            case 16: //Нажата левая кнопка мыши
                if (selectionModelRectangular != null) {
                    selectionModelRectangular.active = true;
                    if (this.changePointModelRectangularShape(e.getPoint())) {
                        selectionModelRectangular.setFrame(this.setPointModelRectangularShape(e.getPoint()));

                        // определение подключения при выбраной модели
                        if (selectionModelRectangular.returnIntersectsModelRectangularShape(this.modelRectangularShapes) != null) {
                            selectionModelRectangular.setAllowInstallation(false);
                            selectionModelRectangular.breakConnectionContacts();
                        } else
                            selectionModelRectangular.connectionContacts(this.modelRectangularShapes);


//                        if (shape != null) {
//                            if (!shape.equals(findModelRectangularShape))
//                                shape.setSet(false);
//                            else {
//                                shape.setSet(true);
//                                this.connectionContacts(shape);
//                            }
//                        }

//                        this.connectionContacts(shape);
//                        System.out.println(shape.getName());
//                        if (!shape.equals(findModelRectangularShape))

//                            this.connectionContacts(findModelRectangularShape);

                        repaint();
                    }
                }
                break;

            case 8: //Нажата средняя кнопка мыши

                break;

            case 4: //Нажата правая кнопка мыши

                break;
        }
    }

    @Override
    // движение мыши по объекту, кнопка мыши отжата
    public void mouseMoved(MouseEvent e) {
        if (panelObject.isSelection()) {
            this.selectionModelRectangular = panelObject.getModelRectangularShapeSelection();
            this.selectionModelRectangular.active = true;

            if (this.changePointModelRectangularShape(e.getPoint())) {
                selectionModelRectangular.setFrame(this.setPointModelRectangularShape(e.getPoint()));
                // определение подключения при выбраной модели
                if (selectionModelRectangular.returnIntersectsModelRectangularShape(this.modelRectangularShapes) != null) {
                    selectionModelRectangular.breakConnectionContacts();
                    selectionModelRectangular.setAllowInstallation(false);
                } else {
                    selectionModelRectangular.connectionContacts(this.modelRectangularShapes);
                }

                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // установка курсора рука с пальцем
                repaint();
            }


        } else {
            selectionModelRectangular = find(e.getPoint());

            if (selectionModelRectangular == null) {
                setCursor(Cursor.getDefaultCursor()); // получение ссылки на курсор по умочанию

            } else {
                System.out.println(selectionModelRectangular.connectionToString());
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // установка курсора рука с пальцем
            }
        }
    }
}
