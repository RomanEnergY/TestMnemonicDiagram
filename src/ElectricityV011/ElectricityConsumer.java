package ElectricityV011;

import java.util.ArrayList;

/**
 * Зверик Роман Станиставович 17.04.2018.
 */
public interface ElectricityConsumer {

    /**
     * Метод описывает появление напряжение
     */
    void electricityOn(EventElectricity eventElectricity, Contact contactStartEventElectricity);

    /**
     * Метод описывает снятие напряжение
     */
    void electricityOff(EventElectricity eventElectricity);


    void getLineElectricity(ArrayList<PathElectricCurrent> arrayListPathElectricCurrents, ArrayList<Contact> contacts, EventElectricity eventElectricity, Contact contactStartEventElectricity);

}
