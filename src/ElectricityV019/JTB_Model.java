package ElectricityV019;

import ElectricityV019.ModelShape.ModelRectangularShape;

import javax.swing.*;

/**
 * Created by user on 22.07.2018.
 */
public class JTB_Model extends JToggleButton {
    private ModelRectangularShape modelRectangularShape;

    public JTB_Model(ModelRectangularShape modelRectangularShape) {
        this.setText(modelRectangularShape.getName());
        this.modelRectangularShape = modelRectangularShape;
    }

    public ModelRectangularShape getModelRectangularShape() {
        return modelRectangularShape;
    }
}
