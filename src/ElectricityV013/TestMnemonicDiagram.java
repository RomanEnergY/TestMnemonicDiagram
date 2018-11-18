package ElectricityV013;

/** package ElectricityV012
 *
 * Зверик Роман Станиславович 28.05.2018.
 */
public class TestMnemonicDiagram {
    Generator g1 = new Generator("Г-1");
    Generator g2 = new Generator("Г-2");
    Generator g3 = new Generator("Г-3");

    Conductor l1 = new Conductor("Л-1");
    Conductor l2 = new Conductor("Л-2");

    SwitchObject v1 = new SwitchObject("В-1", false);
    SwitchObject v2 = new SwitchObject("В-2", false);
    SwitchObject v3 = new SwitchObject("В-3", false);
    SwitchObject v4 = new SwitchObject("В-4", false);

    ReceiverElectricCurrent t1 = new ReceiverElectricCurrent("П-1");
    ReceiverElectricCurrent t2 = new ReceiverElectricCurrent("П-2");

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

//        Test1();
        Test2();

    }

    private void Test1() {
        g1.getContact_p1().connection(v1.getContact_p1());
        v1.getContact_p2().connection(l1.getContact_p1());
        l1.getContact_p2().connection(v3.getContact_p1());
        v3.getContact_p1().connection(t1.getContact_p1());
        v3.getContact_p2().connection(l2.getContact_p1());
        l2.getContact_p1().connection(t2.getContact_p1());
        l2.getContact_p2().connection(v2.getContact_p2());
        v2.getContact_p1().connection(g2.getContact_p1());
        g3.getContact_p1().connection(v4.getContact_p2());
        v4.getContact_p1().connection(l1.getContact_p1());
        l2.getContact_p2().connection(v4.getContact_p1());

        l2.getContact_p2().connection(l1.getContact_p1());

        g1.run();
        g2.run();
        g3.run();
//        g1.stop();
//        g2.run();
//        g1.stop();
//        g2.stop();
//        switchObject2.switchOff();

        printAllElectricityObject();

    }

    private void Test2() {
        g1.getContact_p1().connection(v1.getContact_p1());
        v1.getContact_p2().connection(l1.getContact_p1());
        l1.getContact_p2().connection(v2.getContact_p1());
        v2.getContact_p2().connection(l2.getContact_p2());
        l2.getContact_p1().connection(v3.getContact_p2());
        v3.getContact_p1().connection(g2.getContact_p1());
        t1.getContact_p1().connection(l1.getContact_p2());
        t2.getContact_p1().connection(l2.getContact_p2());

        g1.getContact_p1().connection(g2.getContact_p1());
        g2.getContact_p1().disConnection(g1.getContact_p1());

        g1.run();
//        g2.run();
        g1.stop();
//        g2.stop();

        this.printAllElectricityObject();

    }

    private void printAllElectricityObject() {
        System.out.println();
        System.out.println(g1.toStringArrayListPathElectricCurrents());
        System.out.println(g2.toStringArrayListPathElectricCurrents());
//        System.out.println(g3.toStringArrayListPathElectricCurrents());

        System.out.println(l1.toStringArrayListPathElectricCurrents());
        System.out.println(l2.toStringArrayListPathElectricCurrents());

        System.out.println(v1.toStringArrayListPathElectricCurrents());
        System.out.println(v2.toStringArrayListPathElectricCurrents());
        System.out.println(v3.toStringArrayListPathElectricCurrents());
//        System.out.println(v4.toStringArrayListPathElectricCurrents());

        System.out.println(t1.toStringArrayListPathElectricCurrents());
        System.out.println(t2.toStringArrayListPathElectricCurrents());

    }

}
