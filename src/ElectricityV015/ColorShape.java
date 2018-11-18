package ElectricityV015;

import java.awt.*;
import java.util.Random;

/**
 * Created by user on 09.07.2018.
 */
public class ColorShape {
    Color color;
    Shape shape;

    public ColorShape(Color color, Shape shape) {
        this.color = color;
        this.shape = shape;
    }

    static Color setColor(){
        Random random = new Random();

        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);


        return new Color(r, g, b);
    }
}
