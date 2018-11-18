package ElectricityV005;

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
        System.out.println("ElectricityV005 void Test1() -->");

        Generator generator1 = new Generator("Генератор-1", Voltage.V0_4);
        Generator generator2 = new Generator("Генератор-2", Voltage.V0_4);
        Consumer consumer1 = new Consumer("Абонент-1");
        Consumer consumer2 = new Consumer("Абонент-2");
        Consumer consumer3 = new Consumer("Абонент-3");

        generator1.addContact_P1(generator2.getContact_P1());
        generator1.addContact_P1(consumer1.getContact_P1());
        generator1.addContact_P1(consumer2.getContact_P1());
        generator1.addContact_P1(consumer3.getContact_P1());

        generator1.run();
        System.out.println();
        generator2.run();
        System.out.println();
        generator1.stop();
        System.out.println();



        System.out.println(generator1.getContact_p1_toString());
        System.out.println(generator2.getContact_p1_toString());
        System.out.println(consumer1.getContact_p1_toString());
        System.out.println(consumer2.getContact_p1_toString());
        System.out.println(consumer3.getContact_p1_toString());

    }

    private void Test2() {

    }

}
