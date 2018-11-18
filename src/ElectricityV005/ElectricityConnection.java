package ElectricityV005;

/**
 * Created by user on 19.04.2018.
 */
public interface ElectricityConnection {

    /**
     *
     */
    void checkStatusObject(String name, String numberContact, EventElectricity eventElectricity);
    void connect(Contact contact);
}
