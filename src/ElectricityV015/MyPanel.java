package ElectricityV015;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by user on 05.07.2018.
 */
public class MyPanel extends Component {
    private ArrayList<ColorShape> colorShapes;
    boolean isShape; // переключение фигур

    public MyPanel(){
        colorShapes = new ArrayList();
        isShape = true;
        this.addMouseListener(new MyMouse()); // Регистрируем слушателя, адаптер
        this.addMouseMotionListener(new MyMove()); // Регистрируем слушателя, интерфейс (перетаскивание и перемещение)
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D)g;
        for (ColorShape colorShape: colorShapes){
            Shape shape = colorShape.shape; // Формируем фигуру
            graphics2D.setColor(colorShape.color); // получаем цвет фигуры
            graphics2D.fill(shape); // отресовываем переданную фигуру

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
        @Override
        // Событие возникает при первом нажатии на кнопку мыши
        public void mousePressed(MouseEvent e) {
            System.out.println("mousePressed");
            super.mousePressed(e);
            if (e.getButton() == MouseEvent.BUTTON1) { // Если нажата левая кнопка мыши
                if (find(e.getPoint()) == null) {
                    add(e.getPoint());
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("mouseClicked");
//            if (e.getClickCount() == 2) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                ColorShape colorShape = find(e.getPoint());
                if (colorShape != null) {
                    remove(colorShape);
                }
            }
        }
    }

    private class MyMove implements MouseMotionListener {
        @Override
        // перетаскивание при нажатой любой кнопки мыши
        public void mouseDragged(MouseEvent e) {
            ColorShape colorShape = find(e.getPoint());
            if (colorShape != null) {
                if (colorShape.shape instanceof Ellipse2D) {
                    Ellipse2D ellipse2D = (Ellipse2D) colorShape.shape;
                    ellipse2D.setFrame(e.getX() - 20, e.getY() - 20, 40, 40);
                    repaint();
                }

                if (colorShape.shape instanceof Rectangle2D) {
                    Rectangle2D rectangle2D = (Rectangle2D) colorShape.shape;
                    rectangle2D.setFrame(e.getX() - 20, e.getY() - 20, 40, 40);
                    repaint();
                }
            }




            System.out.println("mouseDragged");
        }

        @Override
        // перемещение мыши по объекту (кнопка мыши отжата)
        public void mouseMoved(MouseEvent e) {
            if (find(e.getPoint()) == null) {
                setCursor(Cursor.getDefaultCursor()); // получение ссылки на курсор по умочанию
            }
            else
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // установка курсора рука с пальцем
        }
    }

    private void remove(ColorShape colorShape) {
        if (colorShape != null) {
            colorShapes.remove(colorShape);
            repaint();
        }
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

    private void add(Point point) {
        ColorShape colorShape = null;
        if (isShape) {
            Ellipse2D ellipse2D = new Ellipse2D.Double(point.getX() - 20, point.getY() - 20, 40, 40);

            colorShape = new ColorShape(ColorShape.setColor(), ellipse2D);
            colorShapes.add(colorShape);
            isShape = !isShape;
        }
        else {
            Rectangle2D rectangle2D = new Rectangle2D.Double(point.getX() - 20, point.getY() - 20, 40, 40);

            colorShape = new ColorShape(ColorShape.setColor(), rectangle2D);
            colorShapes.add(colorShape);
            isShape = !isShape;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // установка курсора рука с пальцем
        repaint();
    }


}
