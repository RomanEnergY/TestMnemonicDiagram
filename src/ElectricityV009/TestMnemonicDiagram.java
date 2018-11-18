package ElectricityV009;

/** package ElectricityV009
 * Реализован
 *
 * Зверик Роман Станиславович 17.04.2018.
 */
public class TestMnemonicDiagram {
    public static void main(String[] args) {
        // Создаем отдельный поток и запускаем проект
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new TestMnemonicDiagram();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private TestMnemonicDiagram() throws CloneNotSupportedException {
        System.out.println(this.getClass());
        Test1();
    }
    private void Test1() throws CloneNotSupportedException {
        Generator generator1 = new Generator("Г-1");
        Conductor conductor1 = new Conductor("Л-1");
        Conductor conductor2 = new Conductor("Л-2");
        Conductor conductor3 = new Conductor("Л-3");
        Conductor conductor4 = new Conductor("Л-4");
        Conductor conductor5 = new Conductor("Л-5");

        conductor2.switchOff();

        generator1.getContact_p1().connection(conductor3.getContact_p2());
        conductor1.getContact_p1().connection(conductor3.getContact_p1());

        generator1.getContact_p1().connection(conductor2.getContact_p1());
        conductor2.getContact_p2().connection(conductor4.getContact_p1());

        generator1.getContact_p1().connection(conductor5.getContact_p1());
        conductor4.getContact_p2().connection(conductor5.getContact_p2());

        System.out.println();
        System.out.println(generator1.toStringContactAll());
        System.out.println(conductor1.toStringContactAll());
        System.out.println(conductor2.toStringContactAll());




//        generator1.getContact_p1().connection(conductor2.getContact_p1());
//        conductor2.getContact_p2().connection(conductor3.getContact_p1());
//        conductor2.getContact_p2().connection(conductor4.getContact_p2());
//        conductor4.getContact_p2().connection(conductor5.getContact_p1());
//        conductor1.switchOff();
//        generator1.getContact_p1().disConnection(conductor2.getContact_p1());

        System.out.println();
        generator1.run();

        System.out.println();
        System.out.println("Л-1 р_1:" + conductor1.getContact_p1().getPathElectricCurrent());
        System.out.println("Л-1 р_2:" + conductor1.getContact_p2().getPathElectricCurrent());
        System.out.println();

        System.out.println("Л-2 р_1:" + conductor2.getContact_p1().getPathElectricCurrent());
        System.out.println("Л-2 р_2:" + conductor2.getContact_p2().getPathElectricCurrent());
        System.out.println();

        System.out.println("Л-3 р_1:" + conductor3.getContact_p1().getPathElectricCurrent());
        System.out.println("Л-3 р_2:" + conductor3.getContact_p2().getPathElectricCurrent());
        System.out.println();

        System.out.println("Л-4 р_1:" + conductor4.getContact_p1().getPathElectricCurrent());
        System.out.println("Л-4 р_2:" + conductor4.getContact_p2().getPathElectricCurrent());
        System.out.println();

        System.out.println("Л-5 р_1:" + conductor5.getContact_p1().getPathElectricCurrent());
        System.out.println("Л-5 р_2:" + conductor5.getContact_p2().getPathElectricCurrent());
        System.out.println();

    }

}
