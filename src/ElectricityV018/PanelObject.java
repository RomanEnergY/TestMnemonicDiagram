package ElectricityV018;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by user on 19.07.2018.
 */
public class PanelObject extends JPanel implements ActionListener {
    private ArrayList<JTB_Model> jtb_models = new ArrayList<>();
    private ModelShape modelShapeSelection = null;
    private ButtonGroup buttonGroup = new ButtonGroup();

    public PanelObject(){
        buttonGroup = new ButtonGroup();

        setLayout(new GridLayout());
        addJtb_models(new Model_VV_10kV("ВВ-10кВ"));
        addJtb_models(new Model_VV_10kV("ЛР-10кВ"));


        for (JTB_Model jtb_model: jtb_models) {
            this.add(jtb_model);
        }
    }

    private void addJtb_models(Model_VV_10kV model_vv_10kV) {
        JTB_Model jtb_model = new JTB_Model(model_vv_10kV);
        buttonGroup.add(jtb_model);
        jtb_model.addActionListener(this);
        jtb_models.add(jtb_model);
    }

    public ModelShape getModelShapeSelection(){
        return modelShapeSelection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTB_Model jtb = (JTB_Model) e.getSource();
        if (jtb.getModelShape().equals(this.modelShapeSelection)) {
            this.modelShapeSelection = null;
            buttonGroup.clearSelection();

        }
        else {
            this.modelShapeSelection = jtb.getModelShape();
        }
    }

    public void clearSelection() {
        this.modelShapeSelection = null;
        buttonGroup.clearSelection();
    }
}
