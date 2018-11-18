package ElectricityV014;

/** Перечисление классов напряжения
 *
 * Зверик Роман Станиславович 08.04.2018.
 */
public enum Voltage {
    EARTH, // потенциал "Земли"
    NO,    // потенциал отсутствует
    V0_4,  // потенциал 0,4кВ
    V6,    // потенциал 6кВ
    V10,   // потенциал 10кВ
    V35,   // потенциал 35кВ
    V110;  // потенциал 110кВ

    public static String toString(Voltage voltage){
        switch (voltage){
            case EARTH: return "'Заземлен'";
            case NO: return "'НЕТ НАПРЯЖЕНИЯ'";
            case V0_4: return "'0,4кВ'";
            case V6:   return "'6кВ'";
            case V10:  return "'10кВ'";
            case V35:  return "'35кВ'";
            case V110: return "'110кВ'";
            default:   return "'НЕ ОПРЕДЕЛЕНО'";
        }
    }

    public static boolean isElectricity(Voltage voltage) {
        switch (voltage){
            case EARTH: return false;
            case NO: return false;
            case V0_4: return true;
            case V6:   return true;
            case V10:  return true;
            case V35:  return true;
            case V110: return true;
            default:   return true;
        }
    }
}
