package ElectricityV002;

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
//        Test1();
        Test2();
    }

    private void Test1() {
        System.out.println("ElectricityV002 void Test1() -->\n");

        Generator generator = new Generator("ТЕЦ-16");
        Switcher switcher = new Switcher("АВ_2");
        Lamp lamp = new Lamp("Лампа_2");
        Radio radio = new Radio("Радио_2");

        // Собираем схему
        generator.addElectricityListener(switcher);
        switcher.addElectricityListener(lamp);
        switcher.addElectricityListener(radio);

        // Включем генератор, АВ отключен
        generator.switchOn();
        switcher.switchOn();

        System.out.println(switcher.toString());
    }

    private void Test2() {
        System.out.println("ElectricityV002 void Test2() -->\n");

        Generator generator = new Generator("ТЕЦ-16");
        Switcher switcher = new Switcher("АВ_2");
        Lamp lamp = new Lamp("Лампа_2");
        Radio radio = new Radio("Радио_2");

        // Собираем схему
        generator.addElectricityListener(switcher);
        switcher.addElectricityListener(lamp);
        switcher.addElectricityListener(radio);

        // (БАГ) Генератор отключен, при включении АВ без напряжения опрашиваются потребителя
        switcher.switchOn();

        System.out.println(switcher.toString());
    }

}
