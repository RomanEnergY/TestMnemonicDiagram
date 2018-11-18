package ElectricityV011;

/** package ElectricityV011
 *
 * Зверик Роман Станиславович 21.04.2018.
 */
public class TestMnemonicDiagram {
    Generator generator1 = new Generator("Г-1");
    Generator generator2 = new Generator("Г-2");

    Conductor conductor1 = new Conductor("Л-1");
    Conductor conductor2 = new Conductor("Л-2");
    Conductor conductor3 = new Conductor("Л-3");
    Conductor conductor4 = new Conductor("Л-4");

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
        generator1.getContact_p1().connection(conductor1.getContact_p1());
        generator2.getContact_p1().connection(conductor4.getContact_p2());
        conductor1.getContact_p2().connection(conductor2.getContact_p1());
        generator1.getContact_p1().connection(conductor3.getContact_p1());
        conductor3.getContact_p2().connection(conductor4.getContact_p1());
        conductor2.getContact_p2().connection(conductor4.getContact_p2());
        generator1.run();
//        generator2.run();

        printAllElectricityObject();
        System.out.println();
//        conductor2.getContact_p1().getLineElectricity(generator1.getEventElectricity());
//        conductor2.getContact_p2().getLineElectricity(generator1.getEventElectricity());
//        System.out.println(conductor2.toStringArrayListPathElectricCurrents());


    }

    private void printAllElectricityObject() {
        System.out.println();
        System.out.println(generator1.toStringArrayListPathElectricCurrents());
//        System.out.println(generator2.toStringArrayListPathElectricCurrents());
        System.out.println(conductor1.toStringArrayListPathElectricCurrents());
        System.out.println(conductor2.toStringArrayListPathElectricCurrents());
        System.out.println(conductor3.toStringArrayListPathElectricCurrents());
        System.out.println(conductor4.toStringArrayListPathElectricCurrents());

    }

}
