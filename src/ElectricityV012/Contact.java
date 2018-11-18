package ElectricityV012;

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
        return "Contact{" +
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

    void addConnectionContactArrayList(ArrayList<Contact> contactArrayList) {
        if (electricityConnection != null)
            this.electricityConnection.addContactArrayList(contactArrayList);
    }

    void removeConnectionContactArrayList(Contact contactDisConnect) {
        if (electricityConnection != null)
            this.electricityConnection.removeContactArrayList(contactDisConnect);
    }

    public void electricityStartOn(EventElectricity eventElectricity) {
//        System.out.println(this.getClass() + " contact:" + this);

        PathElectricCurrent pathElectricCurrent = new PathElectricCurrent(eventElectricity);
        pathElectricCurrent.addContactToStart(this);
        this.arrayListPathElectricCurrents.add(pathElectricCurrent);
        this.electricityConsumer.electricityOn(pathElectricCurrent, this);
    }

    public void electricityOn(PathElectricCurrent pathElectricCurrent, Contact contactStartEventElectricity) {
//        System.out.println(this.getClass() + " contact:" + this + " pathElectricCurrent:" + pathElectricCurrent);

        PathElectricCurrent pathElectricCurrentClone = pathElectricCurrent.getClone();
        pathElectricCurrentClone.addContactToStart(this);
        this.arrayListPathElectricCurrents.add(pathElectricCurrentClone);

        this.electricityConsumer.electricityOn(pathElectricCurrentClone, contactStartEventElectricity);
    }

    public void electricityStartOff(EventElectricity eventElectricity) {
//        System.out.println(this.getClass() + " contact:" + this);

        int pathElectricCurrentSize;

        do {
            pathElectricCurrentSize = this.arrayListPathElectricCurrents.size();
            for (PathElectricCurrent pathElectricCurrent : this.arrayListPathElectricCurrents) {
                if (pathElectricCurrent.getEventElectricity().equals(eventElectricity)) {
                    this.arrayListPathElectricCurrents.remove(pathElectricCurrent);
                    break;
                }
            }
        } while (pathElectricCurrentSize == this.arrayListPathElectricCurrents.size());

        this.electricityConsumer.electricityOff(eventElectricity, this);

    }

    public void electricityOff(EventElectricity eventElectricity, Contact contactStartEventElectricity) {
//        System.out.println(this.getClass() + " contact:" + this + " eventElectricity:" + eventElectricity);

        int pathElectricCurrentSize;

        do {
            pathElectricCurrentSize = this.arrayListPathElectricCurrents.size();
            for (PathElectricCurrent pathElectricCurrent : this.arrayListPathElectricCurrents) {
                if (pathElectricCurrent.getEventElectricity().equals(eventElectricity)) {
                    this.arrayListPathElectricCurrents.remove(pathElectricCurrent);
                    break;
                }
            }
        } while (pathElectricCurrentSize == this.arrayListPathElectricCurrents.size());

        this.electricityConsumer.electricityOff(eventElectricity, contactStartEventElectricity);

    }

    public ElectricityObject getElectricityObject() {
        return electricityObject;
    }

    public void addPathElectricCurrent(PathElectricCurrent eventPathElectricCurrent) {

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


}

