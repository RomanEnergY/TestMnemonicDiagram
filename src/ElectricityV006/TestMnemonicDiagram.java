package ElectricityV006;

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
        System.out.println("ElectricityV006 void Test1() -->");

        Generator generator1 = new Generator("Г-1", Voltage.V0_4);
        Conductor conductor1 = new Conductor("ВЛ-1");
        Conductor conductor2 = new Conductor("ВЛ-2");
        Conductor conductor3 = new Conductor("ВЛ-3");
        Conductor conductor4 = new Conductor("ВЛ-4");
        Conductor conductor5 = new Conductor("ВЛ-5");
        Conductor conductor6 = new Conductor("ВЛ-6");

        conductor1.addConnectionContact_p1(conductor2.getContact_p2());
        conductor1.addConnectionContact_p1(conductor3.getContact_p2());
//        conductor1.addConnectionContact_p2(conductor2.getContact_p2());
//        conductor3.addConnectionContact_p1(conductor4.getContact_p1());
//        conductor5.addConnectionContact_p2(conductor6.getContact_p2());


        System.out.println(conductor1.toStringContactAll());
        System.out.println(conductor2.toStringContactAll());
        System.out.println(conductor3.toStringContactAll());
        System.out.println(conductor4.toStringContactAll());
        System.out.println(conductor5.toStringContactAll());
        System.out.println(conductor6.toStringContactAll());
//        System.out.println();
//
//        System.out.println(conductor3.toStringContactP2());


    }

    private void Test2() {

    }

}
