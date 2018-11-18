package ElectricityV004;

/**
 * Зверик Роман Станиславович 17.04.2018.
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
        System.out.println("ElectricityV004 void Test1() -->");

        Generator generator1 = new Generator("Генератор-1", Voltage.V0_4);
        Generator generator2 = new Generator("Генератор-2", Voltage.V0_4);
        Consumer consumer1 = new Consumer("Абонент-1");
        Consumer consumer2 = new Consumer("Абонент-2");

        generator1.addContact_P1(consumer1.getContact_P1());
        generator2.addContact_P1(consumer1.getContact_P1());

        generator1.run();
        System.out.println();
        generator2.run();
        System.out.println();
        generator1.stop();
        System.out.println();
        generator2.stop();

    }

    private void Test2() {

    }

}
