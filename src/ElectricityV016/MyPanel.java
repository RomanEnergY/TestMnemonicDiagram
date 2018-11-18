package ElectricityV016;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by user on 05.07.2018.
 */
public class MyPanel extends Component {
    private ArrayList<ColorShape> colorShapes = new ArrayList<>();
    private ColorShape colorShape;
    private Point lastMouseDragged = new Point();

    public MyPanel(){
        ColorShape.setPaintPanel(this);
        colorShape = null;
        this.addMouseListener(new MyMouse(this)); // Регистрируем слушателя, адаптер
        this.addMouseMotionListener(new MyMove(this)); // Регистрируем слушателя, интерфейс (перетаскивание и перемещение)
    }

    private ColorShape find(Point point) {
        if (point != null) {
            for (int count = colorShapes.size() - 1; count >= 0; count--) {
                if (colorShapes.get(count).shape.contains(point)) { // Проверяем есть ли в указанном месте точке наличие фигуры
                    return colorShapes.get(count);
                }
            }
        }
        return null;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D)g;
        for (ColorShape colorShape: colorShapes){
            colorShape.pint(graphics2D);

        }







//        Point startPoint = new Point(100, 100);
//
//        g.setColor(Color.RED);
//        g.fillRect(startPoint.x, startPoint.y, 50, 50);
//        g.setColor(Color.black);
//        g.drawRect(startPoint.x, startPoint.y, 50, 50);
//
//        g.setColor(Color.white);
//        g.fillRect(startPoint.x + 11, startPoint.y + 8, 2, 10);
//        g.setColor(Color.black);
//        g.drawRect(startPoint.x + 11, startPoint.y + 8, 2, 10);

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
            lastMouseDragged = e.getPoint();

            if (e.getButton() == MouseEvent.BUTTON1) { // Если нажата левая кнопка мыши
                colorShape = find(e.getPoint());
                if (colorShape == null) {
                    colorShapes.add(ColorShape.addNewShape(e.getPoint()));
                    repaint();
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // установка курсора рука с пальцем
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) { // Если нажата правая кнопка мыши
                ColorShape colorShape = find(e.getPoint());
                if (colorShape != null) {
                    colorShapes.remove(colorShape);
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

            if (colorShape != null) {
                Point deltaPointDragged = new Point(e.getX() - lastMouseDragged.x, e.getY() - lastMouseDragged.y);
                colorShape.setFrame(deltaPointDragged);
                repaint();
            }

            lastMouseDragged = new Point(e.getX(), e.getY());
        }

        @Override
        // перемещение мыши по объекту (кнопка мыши отжата)
        public void mouseMoved(MouseEvent e) {
//            lastMouseDragged = e.getPoint();

            colorShape = find(e.getPoint());
            if (colorShape == null) {
                setCursor(Cursor.getDefaultCursor()); // получение ссылки на курсор по умочанию
            }
            else {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // установка курсора рука с пальцем
            }
        }
    }
}
