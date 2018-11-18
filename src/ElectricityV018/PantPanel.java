package ElectricityV018;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * Created by user on 05.07.2018.
 */
public class PantPanel extends Component {
    private ArrayList<ModelShape> modelShapes = new ArrayList<>();
    private ModelShape paintShape;
    private Point lastMouseDragged = new Point();
    private PanelObject panelObject;

    private ArrayList<ShapeAndColor> shapeAndColors = new ArrayList<>();
    private ShapeAndColor shapeAndColor;

    public PantPanel(PanelObject panelObject){
        shapeAndColor = null;
        this.panelObject = panelObject;

        this.addMouseListener(new MyMouse(this)); // Регистрируем слушателя, адаптер
        this.addMouseMotionListener(new MyMove(this)); // Регистрируем слушателя, интерфейс (перетаскивание и перемещение)
    }

    private ModelShape find(Point point) {
        if (point != null) {
            for (int count = modelShapes.size() - 1; count >= 0; count--) {
                if (modelShapes.get(count).isFind(point)){ // Проверяем есть ли в указанном месте точке наличие фигуры
//                    System.out.println("PaintShape find OK:" + paintShapes.get(count).name);
                    return modelShapes.get(count);
                }
            }
        }
        return null;
    }

    @Override
    public void paint(Graphics g) {
        for (ModelShape modelShape: modelShapes) {
            System.out.println(modelShape.toString());
            modelShape.paint(g);
        }
        System.out.println();
    }

    private class MyMouse extends MouseAdapter
    {
        PantPanel paintPanel;

        public MyMouse(PantPanel paintPanel) {
            this.paintPanel = paintPanel;
        }

        @Override
        // Событие возникает при первом нажатии на кнопку мыши
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) { // Если нажата левая кнопка мыши
                paintShape = find(e.getPoint());
                if (paintShape == null) { // Если не попали по объектам, добавляем новый
                    if (panelObject.getModelShapeSelection() != null) {
                        System.out.println("add " + panelObject.getModelShapeSelection());

                        try {
                            ModelShape modelShape = (ModelShape) panelObject.getModelShapeSelection().clone();
                            modelShape.setPoint(e.getPoint());
                            modelShapes.add(modelShape);
                            panelObject.clearSelection();
                            repaint();
                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // установка курсора рука с пальцем

                        } catch (CloneNotSupportedException e1) {
                            e1.printStackTrace();
                        }


//                        if (panelObject.getSelection().equals(PanelObject.VV_10kV)) {
//
//
//                            ObjectShape paintShape = new Model_VV_10kV(PanelObject.VV_10kV, e.getPoint());
//                            modelShapes.add(paintShape);
//                            repaint();
//                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // установка курсора рука с пальцем
//                        }
                    }
                }
                else {
                    lastMouseDragged = e.getPoint();
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) { // Если нажата правая кнопка мыши
                ModelShape paintShape = find(e.getPoint());

//                ColorShape colorShape = find(e.getPoint());
                if (paintShape != null) {
                    modelShapes.remove(paintShape);
                    repaint();
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            shapeAndColor = null;
        }
    }

    private class MyMove implements MouseMotionListener {
        PantPanel paintPanel;

        public MyMove(PantPanel paintPanel) {
            this.paintPanel = paintPanel;
        }
        @Override
        // перетаскивание при нажатой любой кнопки мыши
        public void mouseDragged(MouseEvent e) {
            if (paintShape != null) {
                Point deltaPointDragged = new Point(e.getX() - lastMouseDragged.x, e.getY() - lastMouseDragged.y);
                paintShape.setFrame(deltaPointDragged);
                repaint();
            }

            lastMouseDragged = new Point(e.getX(), e.getY());

        }

        @Override
        // перемещение мыши по объекту (кнопка мыши отжата)
        public void mouseMoved(MouseEvent e) {

            paintShape = find(e.getPoint());
//            colorShape = find(e.getPoint());
            if (paintShape == null) {
                setCursor(Cursor.getDefaultCursor()); // получение ссылки на курсор по умочанию
            }
            else {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // установка курсора рука с пальцем
            }
        }
    }
}
