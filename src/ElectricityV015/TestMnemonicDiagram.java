package ElectricityV015;


import javax.swing.*;
import java.awt.*;

/** package ElectricityV015
 *
 * Зверик Роман Станиславович 05.07.2018
 * Обработка событий
 */
public class TestMnemonicDiagram {
    public static void main(String[] args) {
        // Создаем отдельный поток и запускаем проект
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestMnemonicDiagram();

            }
        });
    }

    private TestMnemonicDiagram() {
        System.out.println(this.getClass());

        Test1();
    }

    private void Test1() {
        MyFrame myFrame = new MyFrame();

    }

    class MyFrame extends JFrame {

        public MyFrame(){
            this.setTitle("#Java Урок 107 Событие \"щелчок мыши\" в приложении. Отрисовка эллипса при щелчке");
            this.setSize(800, 600);
//            this.setLayout(new FlowLayout());
            MyPanel myPanel = new MyPanel();
            this.add(myPanel);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
        }
    }
}
