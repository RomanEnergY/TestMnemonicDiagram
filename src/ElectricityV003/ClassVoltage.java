package ElectricityV003;

/** Перечисление классов напряжения
 *
 * Зверик Роман Станиславович 08.04.2018.
 */
public enum ClassVoltage {
    EARTH, // потенциал "Земли"
    NO,    // потенциал отсутствует
    V0_4,  // потенциал 0,4кВ
    V6,    // потенциал 6кВ
    V10,   // потенциал 10кВ
    V35,   // потенциал 35кВ
    V110;  // потенциал 110кВ

    public static String toString(ClassVoltage classVoltage){

        switch (classVoltage){
            case EARTH: return "Земли";
            case NO: return "NULL";
            case V0_4: return "0,4кВ";
            case V6:   return "6кВ";
            case V10:  return "10кВ";
            case V35:  return "35кВ";
            case V110: return "110кВ";
            default:   return "default";
        }
    }
}
