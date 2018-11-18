package ElectricityV003;

/**
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
//        Test2();
    }

    private void Test1() {
        System.out.println("ElectricityV003 void Test1() -->\n");

        Generator generator = new Generator("ТЕЦ-16", ClassVoltage.V0_4);
        Switcher switcher = new Switcher("АВ_2");
        Lamp lamp = new Lamp("Лампа_2");
        Radio radio = new Radio("Радио_2");

        // Собираем схему
        generator.addElectricityListener(switcher);
        switcher.addElectricityListener(lamp);
        switcher.addElectricityListener(radio);

        // Включем генератор, АВ отключен
        generator.switchOn(); // отключен switcher
        switcher.switchOn();
        generator.switchOn();
        switcher.switchOff();

        System.out.println(generator.toString());
        System.out.println(switcher.toString());
    }

    private void Test2() {

    }

}
