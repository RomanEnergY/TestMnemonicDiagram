package ElectricityV019.AboutDialog;

import ElectricityV019.ModelShape.ModelRectangularShape;

import javax.swing.*;

/**
 * Created by user on 22.08.2018.
 */
public class JPanelContent extends JPanel {
    ModelRectangularShape findModelRectangularShape;
    JLabel jLabel;

    public JPanelContent(ModelRectangularShape findModelRectangularShape) {
        this.findModelRectangularShape = findModelRectangularShape;
        this.jLabel = new JLabel(findModelRectangularShape.getName() + " " + findModelRectangularShape.isOn());
        this.add(jLabel);

    }

    public void setModelShape(ModelRectangularShape findModelRectangularShape) {
        this.findModelRectangularShape = findModelRectangularShape;
        this.jLabel.setText(findModelRectangularShape.getName() + " " + findModelRectangularShape.isOn());
    }
}
