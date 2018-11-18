package ElectricityV018;

import javax.swing.*;

/**
 * Created by user on 22.07.2018.
 */
public class JTB_Model extends JToggleButton {
    private ModelShape modelShape;

    public JTB_Model(ModelShape modelShape) {
        this.setText(modelShape.getName());
        this.modelShape = modelShape;
    }

    public ModelShape getModelShape() {
        return modelShape;
    }
}
