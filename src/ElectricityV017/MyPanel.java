package ElectricityV017;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * Created by user on 05.07.2018.
 */
public class MyPanel extends Component {
    private ArrayList<PaintShape> paintShapes = new ArrayList<>();
    private PaintShape paintShape;
    private Point lastMouseDragged = new Point();
    private PanelObject panelObject;


    private ArrayList<ColorShape> colorShapes = new ArrayList<>();
    private ColorShape colorShape;

    public MyPanel(PanelObject panelObject){
        ColorShape.setPaintPanel(this);
        colorShape = null;
        this.panelObject = panelObject;

        this.addMouseListener(new MyMouse(this)); // Регистрируем слушателя, адаптер
        this.addMouseMotionListener(new MyMove(this)); // Регистрируем слушателя, интерфейс (перетаскивание и перемещение)
    }

    private PaintShape find(Point point) {
        if (point != null) {
            for (int count = paintShapes.size() - 1; count >= 0; count--) {
                if (paintShapes.get(count).isFind(point)){ // Проверяем есть ли в указанном месте точке наличие фигуры
//                    System.out.println("PaintShape find OK:" + paintShapes.get(count).name);
                    return paintShapes.get(count);
                }
            }
        }
        return null;
    }

    @Override
    public void paint(Graphics g) {
        for (PaintShape paintShape_1: paintShapes) {
            paintShape_1.paint(g);
        }
    }

    private class MyMouse extends MouseAdapter
    {
        MyPanel paintPanel;

        public MyMouse(MyPanel paintPanel) {
            this.paintPanel = paintPanel;
        }

        @Override
        // Событие возникает при первом нажатии на кнопку мыши
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) { // Если нажата левая кнопка мыши
                System.out.println(panelObject.getSelection());


                paintShape = find(e.getPoint());



                if (paintShape == null) { // Если не попали по объектам, добавляем новый
                    if (panelObject.getSelection() != null) {
                        if (panelObject.getSelection().equals(PanelObject.VV_10kV)) {
                            PaintShape paintShape = new V_10(PanelObject.VV_10kV, e.getPoint());
                            paintShapes.add(paintShape);
                            repaint();
                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // установка курсора рука с пальцем
                        }
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
                PaintShape paintShape = find(e.getPoint());

//                ColorShape colorShape = find(e.getPoint());
                if (paintShape != null) {
                    paintShapes.remove(paintShape);
                    repaint();
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            colorShape = null;
        }
    }

    private class MyMove implements MouseMotionListener {
        MyPanel paintPanel;

        public MyMove(MyPanel paintPanel) {
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
