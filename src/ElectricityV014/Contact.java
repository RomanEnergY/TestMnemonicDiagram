package ElectricityV014;

import java.util.ArrayList;

/**
 * Created by user on 17.04.2018.
 */
public class Contact {
    private String name;
    private ContactNumber contactNumber;
    private ElectricityConnection electricityConnection;
    private ElectricityConsumer electricityConsumer;
    private ElectricityObject electricityObject;

    private ArrayList<PathElectricCurrent> arrayListPathElectricCurrents;

    static Contact Factory(String nameContact,
                           ContactNumber contactNumber,
                           ElectricityConnection electricityConnection,
                           ElectricityConsumer electricityConsumer,
                           ElectricityObject electricityObject) {
        Contact contact = new Contact();
        contact.name = nameContact;
        contact.contactNumber = contactNumber;
        contact.electricityConnection = electricityConnection;
        contact.electricityConsumer = electricityConsumer;
        contact.electricityObject = electricityObject;
        contact.arrayListPathElectricCurrents = new ArrayList<>();

        return contact;
    }

    void connection(Contact contactConnection){
        if (electricityConnection != null) {
            this.electricityConnection.connect(this, contactConnection);
        }
    }

    void disConnection(Contact contactConnection){
        if (electricityConnection != null) {
            this.electricityConnection.disConnect(this, contactConnection);
        }
    }

    @Override
    public String toString() {
        return "{" +
//                "name='" + name + "'," +
//                "numbCont='" + contactNumber  + "'"+
                '\'' + name + "'," +
                '\'' + contactNumber  + "'"+
                '}';
    }

    boolean isElectricityConnection() {
        if (electricityConnection != null)
            return true;
        else
            return false;
    }

    public String getName() {
        return name;
    }

    public ContactNumber getContactNumber() {
        return contactNumber;
    }

    ArrayList<Contact> getElectricityConnectionContactArrayList() {
        if (electricityConnection != null)
            return this.electricityConnection.getContactArrayList();
        else
            return null;
    }

    public ElectricityConsumer getElectricityConsumer() {
        return electricityConsumer;
    }

    void addConnectionContactArrayList(ArrayList<Contact> contactArrayList) {
        if (electricityConnection != null)
            this.electricityConnection.addContactArrayList(contactArrayList);
    }

    void removeConnectionContactArrayList(Contact contactDisConnect) {
        if (electricityConnection != null)
            this.electricityConnection.removeContactArrayList(contactDisConnect);
    }

    public void electricityOn(PathElectricCurrent pathElectricCurrent) {
//        PathElectricCurrent pathElectricCurrentClone = pathElectricCurrent.getClone();
//        pathElectricCurrentClone.addContactToStart(this);
//        this.addPathElectricCurrents(pathElectricCurrentClone);
        this.electricityConsumer.electricityOn(pathElectricCurrent);
    }

    public void electricityOff(PathElectricCurrent pathElectricCurrent) {
//        System.out.println(this.getName() + " pathElectricCurrent:" + pathElectricCurrent);

        int count = this.getArrayListPathElectricCurrents().size();
        for (int j = 0; j < this.arrayListPathElectricCurrents.size(); j++) {
            if (count != this.getArrayListPathElectricCurrents().size()) {
                this.electricityOff(pathElectricCurrent);
                break;
            }

            if (pathElectricCurrent.getEventElectricity().getName().equals(this.arrayListPathElectricCurrents.get(j).getEventElectricity().getName())) {
                for (int i = 0; i < pathElectricCurrent.getContactsArrayList().size(); i++) {
                    if (this.arrayListPathElectricCurrents.get(j).getContactsArrayList().size() - 1 < i) {
                        continue;
                    }
                    if (!this.arrayListPathElectricCurrents.get(j).getContactsArrayList().get(i).equals(pathElectricCurrent.getContactsArrayList().get(i)))
                        break;

                    if (i == pathElectricCurrent.getContactsArrayList().size() - 1) {
                        this.removePathElectricCurrents(pathElectricCurrent);
                        this.electricityConsumer.electricityOff(pathElectricCurrent);
                        break;
                    }
                }
            }
        }
    }

    public ElectricityObject getElectricityObject() {
        return electricityObject;
    }

    public ArrayList<PathElectricCurrent> getArrayListPathElectricCurrents() {
        return this.arrayListPathElectricCurrents;
    }

    String toStringArrayListPathElectricCurrents(){
        String s = "";
        for (PathElectricCurrent pathElectricCurrent: this.arrayListPathElectricCurrents) {
            s = s + pathElectricCurrent + "\n\r\t\t";
        }

        return this.name + ":" + this.contactNumber + " " + s;
    }

    public VoltageTerminal getVoltageTerminal(EventElectricity eventElectricity) {
        return this.electricityObject.getVoltageTerminal(eventElectricity, this);
    }


    public void addPathElectricCurrents(PathElectricCurrent pathElectricCurrent) {
        if (this.getArrayListPathElectricCurrents().size() == 0) {
            this.electricityObject.voltageTerminalAppeared(this);
        }

        this.getArrayListPathElectricCurrents().add(pathElectricCurrent);

    }

    public void removePathElectricCurrents(PathElectricCurrent pathElectricCurrent) {
        int count = this.getArrayListPathElectricCurrents().size();


        for (int i = 0; i < this.getArrayListPathElectricCurrents().size(); i++) {
            if (count != this.getArrayListPathElectricCurrents().size()) {
                break;
            }

            if (pathElectricCurrent.getEventElectricity().getName().equals(this.getArrayListPathElectricCurrents().get(i).getEventElectricity().getName())) {
                if (pathElectricCurrent.getContactsArrayList().size() <= this.getArrayListPathElectricCurrents().get(i).getContactsArrayList().size()) {
                    if (isContactBegin(pathElectricCurrent.getContactsArrayList(), this.getArrayListPathElectricCurrents().get(i).getContactsArrayList())) {
                        this.arrayListPathElectricCurrents.remove(this.getArrayListPathElectricCurrents().get(i));

                        if (this.arrayListPathElectricCurrents.size() == 0) {
                            this.electricityObject.voltageTerminalDisAppeared(this);
                        }

                        break;
                    }
                }
            }
        }
    }

    private boolean isContactBegin(ArrayList<Contact> contacts_1, ArrayList<Contact> contacts_2) {
        for (int i = 0; i < contacts_1.size(); i++) {
            if (!contacts_1.get(i).equals(contacts_2.get(i))) {
                return false;
            }

            if (i == contacts_1.size() - 1) {
                return true;
            }
        }

        return false;
    }
}

