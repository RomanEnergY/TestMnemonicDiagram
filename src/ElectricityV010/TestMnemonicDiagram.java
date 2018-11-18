package ElectricityV010;

/** package ElectricityV009
 * Реализована подача/снчтие напряжения и согранение пути от генератора на контактах
 * 1. БАГ при закольцовке с одного и того же генератора, НЕопределяет точный путь от генератора
 * 2. ИСКЛЮЧЕНИЕ: ПЕРЕПОЛНЕНИЕ СТЕКА от постоянного клонирования пути при снятии напряжения при закольцовке
 * // решение данной проблемы package ElectricityV011
 *
 *
 * Зверик Роман Станиславович 17.04.2018.
 */
public class TestMnemonicDiagram {
    Generator generator1 = new Generator("Г-1");
    Generator generator2 = new Generator("Г-2");

    Conductor conductor1 = new Conductor("Л-1");
    Conductor conductor2 = new Conductor("Л-2");
    Conductor conductor3 = new Conductor("Л-3");

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

//        Test1();
        Test2();
    }

    private void Test1() throws CloneNotSupportedException {
        generator1.getContact_p1().connection(conductor1.getContact_p1());
        generator1.getContact_p1().connection(conductor3.getContact_p1());
        conductor1.getContact_p2().connection(conductor2.getContact_p1());
        conductor2.getContact_p2().connection(generator2.getContact_p1());
//        conductor2.switchOff();

        System.out.println();
        System.out.println(generator1.toStringConnectContactAll());
        System.out.println(conductor1.toStringConnectContactAll());
        System.out.println(conductor2.toStringConnectContactAll());

        System.out.println();
        generator1.run();
        generator2.run();

        printAllElectricityObject();
        conductor2.switchOff();

        System.out.println();
        generator1.stop();
        generator2.stop();

        printAllElectricityObject();
        conductor2.switchOff();

        generator1.run();
        generator2.run();
        printAllElectricityObject();

    }

    private void Test2() throws CloneNotSupportedException {
        generator1.getContact_p1().connection(conductor1.getContact_p1());
        generator1.getContact_p1().connection(conductor3.getContact_p1());
        conductor1.getContact_p2().connection(conductor2.getContact_p1());
        conductor2.getContact_p2().connection(generator2.getContact_p1());

        generator1.run();
        printAllElectricityObject();

    }

    private void printAllElectricityObject() {
        System.out.println();
        System.out.println(generator1.toStringArrayListPathElectricCurrents());
        System.out.println(conductor1.toStringArrayListPathElectricCurrents());
        System.out.println(conductor2.toStringArrayListPathElectricCurrents());
        System.out.println(generator2.toStringArrayListPathElectricCurrents());

        System.out.println(conductor3.toStringArrayListPathElectricCurrents());
    }

}
