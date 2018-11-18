package ElectricityV020;

import ElectricityV020.ModelShape.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Панель объектов расположенная сверху
 * <p>
 * Created by user on 19.07.2018.
 */
public class PanelObject extends JPanel implements ActionListener {
    private ArrayList<JTB_Model> jtbModels = new ArrayList<>();
    private JTB_Model activeJtbModel;
    private ButtonGroup buttonGroup = new ButtonGroup();
    private PantPanel pantPanel;

    public PanelObject() {
        this.pantPanel = null;
        buttonGroup = new ButtonGroup();
        activeJtbModel = null;

        setLayout(new GridLayout());
        addJtb_models(new ModelVV10kV(ModelType.TOP, "ВВ-10кВ", false));
        addJtb_models(new ModelShina10kV("Шины 10кВ", true));

        for (JTB_Model jtb_model : jtbModels) {
            this.add(jtb_model);
        }
    }

    private void addJtb_models(ModelRectangularShape modelRectangularShape) {
        JTB_Model jtb_model = new JTB_Model(modelRectangularShape);
        this.buttonGroup.add(jtb_model);
        jtb_model.addActionListener(this);
        this.jtbModels.add(jtb_model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTB_Model jtb = (JTB_Model) e.getSource();
        if (activeJtbModel != null) {
            if (activeJtbModel.equals(jtb)) {
                this.clearSelection();
                return;
            } else {
                activeJtbModel = jtb;
            }
        } else {
            activeJtbModel = jtb;
        }


//        if (this.buttonGroup.getSelection().equals(jtb.getModel())) {
//            this.clearSelection();
//        } else {
//            System.out.println("Ok");
//        }

//        if (jtb_action == null) {
//            jtb_action = jtb;
//            this.selectionModelRectangularShapeSelection = jtb.getModelRectangularShape();
//            return;
//        }
//
//        if (jtb.getModelRectangularShape().equals(jtb_action.getModelRectangularShape())) {
//            clearSelection();
//            jtb_action = null;
//            if (this.jPanel != null) {
//                System.out.println("jPanel.repaint();");
//                jPanel.repaint();
//            }
//        } else {
//            this.selectionModelRectangularShapeSelection = jtb.getModelRectangularShape();
//        }


//        if (jtb.getModelRectangularShape().equals(this.selectionModelRectangularShapeSelection)) {
//            this.selectionModelRectangularShapeSelection = null;
//            buttonGroup.clearSelection();
//
//        } else {
//            this.selectionModelRectangularShapeSelection = jtb.getModelRectangularShape();
//        }
    }

    public ModelRectangularShape getActiveModelRectangular() {
        if (this.activeJtbModel != null)
            return this.activeJtbModel.getModelRectangularShape();
        else
            return null;
    }

    public ModelRectangularShape getActiveCloneModelRectangular() {
        if (this.activeJtbModel != null)
            return this.activeJtbModel.getModelRectangularShapeClone();
        else
            return null;
    }

    public void clearSelection() {
        this.activeJtbModel.setModelRectangularShape((ModelRectangularShape)this.activeJtbModel.getModelRectangularShape().clone());
        this.activeJtbModel = null;
        this.pantPanel.repaint();
        this.buttonGroup.clearSelection();

    }

    public boolean isSelection() {
        if (this.activeJtbModel != null)
            return true;
        return false;
    }

    public void setJPanelRepaint(PantPanel jPanel) {
        this.pantPanel = jPanel;
    }
}
