package ElectricityV007;

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
//        Test1();
//        Test2();
//        Test3();
        Test4();
    }

    private void Test1() {
        System.out.println("ElectricityV007:Test1 void Test1() -->");

        Conductor conductor1 = new Conductor("ВЛ-1");
        Conductor conductor2 = new Conductor("ВЛ-2");
        Conductor conductor3 = new Conductor("ВЛ-3");
        Conductor conductor4 = new Conductor("ВЛ-4");
        Conductor conductor5 = new Conductor("ВЛ-5");
        Conductor conductor6 = new Conductor("ВЛ-6");

        conductor1.getContact_p2().connection(conductor3.getContact_p2());
        conductor1.getContact_p2().connection(conductor2.getContact_p2());
        conductor3.getContact_p1().connection(conductor5.getContact_p2());
        conductor5.getContact_p1().connection(conductor6.getContact_p1());
        conductor3.getContact_p1().connection(conductor4.getContact_p1());
        conductor6.getContact_p2().connection(conductor4.getContact_p2());
        conductor2.getContact_p1().connection(conductor4.getContact_p2());

        conductor3.getContact_p1().disConnection(conductor4.getContact_p1());

        System.out.println();
        System.out.println(conductor1.toStringContactAll());
        System.out.println(conductor2.toStringContactAll());
        System.out.println(conductor3.toStringContactAll());
        System.out.println(conductor4.toStringContactAll());
        System.out.println(conductor5.toStringContactAll());
        System.out.println(conductor6.toStringContactAll());
        System.out.println();
    }
    private void Test2() {
        System.out.println("ElectricityV007:Test2 void Test1() -->");

    }
    private void Test3() {
        System.out.println("ElectricityV007:Test3 void Test1() -->");

        Conductor conductor1 = new Conductor("ВЛ-1");
        Conductor conductor2 = new Conductor("ВЛ-2");
        Conductor conductor3 = new Conductor("ВЛ-3");
        Conductor conductor4 = new Conductor("ВЛ-4");
        conductor1.getContact_p1().connection(conductor2.getContact_p1());
        conductor1.getContact_p1().connection(conductor3.getContact_p2());
        conductor1.getContact_p1().connection(conductor4.getContact_p2());

        System.out.println(conductor1.toStringContactAll());
        System.out.println(conductor2.toStringContactAll());
        System.out.println(conductor3.toStringContactAll());
        System.out.println();

        conductor1.getContact_p1().disConnection(conductor3.getContact_p2());

        System.out.println(conductor1.toStringContactAll());
        System.out.println(conductor2.toStringContactAll());
        System.out.println(conductor3.toStringContactAll());
        System.out.println(conductor4.toStringContactAll());

    }

    private void Test4() {
        System.out.println("ElectricityV007:Test4 void Test1() -->");

        Generator generator1 = new Generator("Г-1");
        Conductor conductor1 = new Conductor("Л-1");
        Conductor conductor2 = new Conductor("Л-2");
        Conductor conductor3 = new Conductor("Л-3");
        Conductor conductor4 = new Conductor("Л-4");
        Conductor conductor5 = new Conductor("Л-5");

        generator1.getContact_p1().connection(conductor1.getContact_p2());
        generator1.getContact_p1().connection(conductor2.getContact_p1());
        conductor2.getContact_p2().connection(conductor3.getContact_p1());
        conductor2.getContact_p2().connection(conductor4.getContact_p2());
        conductor4.getContact_p2().connection(conductor5.getContact_p1());
        conductor1.switchOff();
//        generator1.getContact_p1().disConnection(conductor2.getContact_p1());

        System.out.println();
        generator1.run();

        System.out.println();
        generator1.stop();

    }

}
