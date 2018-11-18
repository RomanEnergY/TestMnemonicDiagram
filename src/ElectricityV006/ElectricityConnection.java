package ElectricityV006;

import java.util.ArrayList;

/**
 * Created by user on 19.04.2018.
 */
public interface ElectricityConnection {
    /**
     *
     * @param contact_connect Контакт, к которому нужно присоедениться
     * @param contactArrayList Передаваемый список контактов
     */
    void connect(Contact contact_connect, ArrayList<Contact> contactArrayList);
    void connect(Contact contact_This, Contact contact_connect);
    ArrayList<Contact> getConnectContactArrayList(Contact contact);
}
