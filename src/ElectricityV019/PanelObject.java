package ElectricityV019;

import ElectricityV019.ModelShape.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Панель объектов расположенная сверху
 * <p>
 * Created by user on 19.07.2018.
 */
public class PanelObject extends JPanel implements ActionListener {
    private ArrayList<JTB_Model> jtb_models = new ArrayList<>();
    private ModelRectangularShape selectionModelRectangularShapeSelection = null;
    private ButtonGroup buttonGroup = new ButtonGroup();

    public PanelObject() {
        buttonGroup = new ButtonGroup();

        setLayout(new GridLayout());
        addJtb_models(new ModelVV10kV("ВВ-10кВ", true));
        addJtb_models(new ModelVV10kvVertic("ВВ-10кВ вертик", true));
        addJtb_models(new ModelVN10kV("ВН-10кВ", true));
        addJtb_models(new ModelLR10kV("ЛР-10кВ", true));
        addJtb_models(new ModelShina10kV("Шины 10кВv2", true));

        for (JTB_Model jtb_model : jtb_models) {
            this.add(jtb_model);
        }
    }

    private void addJtb_models(ModelRectangularShape modelRectangularShape) {
        JTB_Model jtb_model = new JTB_Model(modelRectangularShape);
        buttonGroup.add(jtb_model);
        jtb_model.addActionListener(this);
        jtb_models.add(jtb_model);
    }

    public ModelRectangularShape getModelRectangularShapeSelection() {
        return selectionModelRectangularShapeSelection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTB_Model jtb = (JTB_Model) e.getSource();
        if (jtb.getModelRectangularShape().equals(this.selectionModelRectangularShapeSelection)) {
            this.selectionModelRectangularShapeSelection = null;
            buttonGroup.clearSelection();

        } else {
            this.selectionModelRectangularShapeSelection = jtb.getModelRectangularShape();
        }
    }

    public void clearSelection() {
        this.selectionModelRectangularShapeSelection = null;
        buttonGroup.clearSelection();
    }

    public boolean isSelection() {
        if (selectionModelRectangularShapeSelection != null)
            return true;
        return false;
    }
}
