package ElectricityV016;


import javax.swing.*;

/** package ElectricityV015
 *
 * Зверик Роман Станиславович 05.07.2018
 * Обработка событий
 */
public class TestMnemonicDiagram {
    public static void main(String[] args) {
        // Создаем отдельный поток и запускаем проект
        SwingUtilities.invokeLater(new Runnable() {
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
            this.setTitle(this.getClass().toString());
            this.setSize(800, 600);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            MyPanel myPanel = new MyPanel();
            this.add(myPanel);
            this.setVisible(true);
        }
    }
}
