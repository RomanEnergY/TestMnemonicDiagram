package ElectricityV002;

/**
 * Зверик Роман Станиставович 11.04.2018.
 */
public class Radio implements ElectricityConsumer {
    private String name;

    Radio(String name){
        this.name = name;
    }

    private void playMusicOn(){
        System.out.println('\'' + this.name + "' заиграло");
    }

    private void playMusicOff(){
        System.out.println('\'' + this.name + "' перестало играть");
    }

    @Override
    public void electricityOn() {
        playMusicOn();
    }

    @Override
    public void electricityOff() {
        playMusicOff();
    }
}
