package ElectricityV005;

/**
 * Created by user on 17.04.2018.
 */
public class Contact implements ElectricityConsumer {
    private String name;
    private String numberContact;
    private ElectricityConnection electricityConnection;

    @Override
    public void electricityOn(EventElectricity eventElectricity) {
//        System.out.println("void electricityOn " + this.name + " " + this.numberContact + " " + eventElectricity.toString());
        this.electricityConnection.checkStatusObject(this.name, this.numberContact, eventElectricity);
    }

    @Override
    public void electricityOff(EventElectricity eventElectricity) {
//        System.out.println("void electricityOff " + this.name + " " + this.numberContact + " " + eventElectricity.toString());
        this.electricityConnection.checkStatusObject(this.name, this.numberContact, eventElectricity);
    }

    static Contact Factory(String nameContact, String numberContact, ElectricityConnection electricityConnection) {
        Contact contact = new Contact();
        contact.name = nameContact;
        contact.numberContact = numberContact;
        contact.electricityConnection = electricityConnection;
        return contact;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + "'," +
                "numberContact='" + numberContact  + "'"+
                '}';
    }

    public ElectricityConnection getElectricityConnection() {
        return electricityConnection;
    }

}
