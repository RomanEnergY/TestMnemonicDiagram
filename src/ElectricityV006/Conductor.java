package ElectricityV006;

import java.util.ArrayList;

/** Класс описывающий ПРОВОДНИК
 *
 * Зверик Роман Станиславович 23.04.2018.
 */
public class Conductor implements ElectricityConnection {

    private String name;
//    private Boolean aElectricityOn;
    private ObjectContact objectContact_p1;
    private ObjectContact objectContact_p2;

//    private ArrayList<EventElectricity> eventElectricity_p1_ArrayList = new ArrayList<>();
//    private ArrayList<EventElectricity> eventElectricity_p2_ArrayList = new ArrayList<>();



    Conductor(String name){
        this.name = name;
//        this.aElectricityOn = false;
        this.objectContact_p1 = ObjectContact.Factory(this.name, ContactNumber.p_1, this);
        this.objectContact_p2 = ObjectContact.Factory(this.name, ContactNumber.p_2, this);
    }



    /**
     * Метод реализующий добавление в список соединений ArrayList<Contact> contact_p1_ArrayList
     * объекта, который в дальнейшем при появлении напряжения на контакте будет опрашивать объект
     * со стороны, с которой появилось напряжение
     *
     * @param contactConnect Contact contactConnection
     */
    public void addConnectionContact_p1(Contact contactConnect) {
        this.addConnection(contactConnect, this.objectContact_p1);
    }

    public void addConnectionContact_p2(Contact contactConnect) {
        this.addConnection(contactConnect, this.objectContact_p2);
    }

    private void addConnection(Contact contactConnect, ObjectContact objectContact) {
        System.out.println("Conductor " + name + ":--> " + objectContact.getContact() + " подключаем к " + contactConnect);

        // Проверка, если передаваемый объект равен пустате
        if (contactConnect == null) {
            System.out.println("ERROR: Conductor " + name + ":contactConnect == null");
            return;
        }

        // Если передаваемый объект не имеет ссылку на интерфейс ElectricityConnection
        if (!contactConnect.isElectricityConnection()) {
            System.out.println("ERROR: Conductor " + name + ":!contactConnect.isElectricityConnection()");
            return;
        }

        ArrayList<Contact> contactConnectAllArrayList = contactConnect.getConnectContactArrayList(contactConnect);
        contactConnectAllArrayList.addAll(objectContact.getContactArrayList());
        contactConnectAllArrayList.add(objectContact.getContact());
        contactConnectAllArrayList.add(contactConnect);

        for (Contact consumer : contactConnectAllArrayList) {
            consumer.connectThis(contactConnectAllArrayList);
        }
    }

//    /**
//     * Метод реализующий удаление из списока слушателей ArrayList<ElectricityConsumer> listeners
//     * объекта ElectricityConsumer listener
//     *
//     * @param listener ElectricityConsumer listener
//     */
//    void removeContact_P1(Contact listener){
//        contact_p1_ArrayList.remove(listener);
//    }

    private void connect(ArrayList<Contact> contactArrayList, ArrayList<Contact> contactConnectAllArrayList){
        // Добавляем передаваемый объект в список слушателей (подключаем)
        for (Contact contact: contactConnectAllArrayList){
            // Проверяем, если передоваемый контакт в списке равняется contact_p1, его не добавляем
            if (contact.equals(objectContact_p1.getContact()) || contact.equals(objectContact_p2.getContact()))
                continue;

            // Проверяем, если передоваемый контакт в списке добавлен ранее, запрещие добавление
            boolean b = true;
            for (Contact contact1: contactArrayList) {
                if (contact1.equals(contact)) {
                    b = false;
                    break;
                }
            }

            // Проверяем, если есть запрет на добавление, тогда данный контак уже был ранее добавлен
            if (b){
                contactArrayList.add(contact);
            }
        }
    }

    @Override
    public void connect(Contact contactDeterminant, ArrayList<Contact> contactConnectAllArrayList) {
        // Проверка, если передаваемый объект равен пустате
        if (contactDeterminant == null)
            return;

        // Методом сравнения передаваемого contactDeterminant определяем к какому контакту требуется добавление данных
        if (contactDeterminant.equals(objectContact_p1.getContact())) {
            this.connect(objectContact_p1.getContactArrayList(), contactConnectAllArrayList);
        }
        else if (contactDeterminant.equals(objectContact_p2.getContact())) {
            this.connect(objectContact_p2.getContactArrayList(), contactConnectAllArrayList);
        }
    }

    @Override
    public void connect(Contact contact_This, Contact contact_connect) {
        // Проверка, если передаваемый объект равен пустате
        if (contact_This == null || contact_connect == null)
            return;

        if (contact_This.equals(objectContact_p1.getContact())){
            this.addConnection(contact_connect, this.objectContact_p1);
        }
        else if (contact_This.equals(objectContact_p2.getContact())){
            this.addConnection(contact_connect, this.objectContact_p2);
        }
    }

    @Override
    public ArrayList<Contact> getConnectContactArrayList(Contact contact) {
        if (contact.equals(objectContact_p1.getContact()))
            return objectContact_p1.getContactArrayList();
        else if (contact.equals(objectContact_p2.getContact()))
            return objectContact_p2.getContactArrayList();

        return null;
    }

    Contact getContact_p1() {
        return objectContact_p1.getContact();
    }
    Contact getContact_p2() {
        return objectContact_p2.getContact();
    }

    String toStringContactAll() {
        return "Conductor{" +
                "name='" + name + '\'' +
                ",contact_p1_ArrayList=" + objectContact_p1.getContactArrayList() +
                ",contact_p2_ArrayList=" + objectContact_p2.getContactArrayList() +
                '}';
    }

    String toStringContactP1() {
        return "Conductor:toStringContactP1" +
                '{' + objectContact_p1 + '}';
    }

    String toStringContactP2() {
        return "Conductor:toStringContactP2" +
                '{' + objectContact_p2 + '}';
    }
}
