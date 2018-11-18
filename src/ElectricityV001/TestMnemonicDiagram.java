package ElectricityV001;

/** 479633
 * Created by user on 02.04.2018.
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

    private TestMnemonicDiagram(){
        Test1();
    }

    private void Test1() {
        System.out.println("ElectricityV001 TEST\n");

        Switcher switcher = new Switcher("АВ_1");
        Lamp lamp = new Lamp("Лампа_1");
        Radio radio = new Radio("Радио_1");

        switcher.addElectricityListener(lamp);
        switcher.addElectricityListener(radio);

        switcher.switchOff();
        switcher.switchOn();

        System.out.println(switcher.toString());
        System.out.println(radio.toString());
        System.out.println(lamp.toString());


    }
}
