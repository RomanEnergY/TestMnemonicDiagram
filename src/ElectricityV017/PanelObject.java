package ElectricityV017;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by user on 19.07.2018.
 */
public class PanelObject extends JPanel implements ActionListener {
    static final String VV_10kV = "ВВ-10кВ";
    static final String RV_10kV = "РВ-10кВ";
    static final String LR_10kV = "ЛР-10кВ";
    static final String VN_10kV = "ВН-10кВ";

    private ArrayList<JTB> jtbs;
    private String selection;
    private ButtonGroup buttonGroup;

    public PanelObject(){
        jtbs = new ArrayList<>();
        buttonGroup = new ButtonGroup();
        selection = null;

        jtbs.add(new JTB(VV_10kV));
        jtbs.add(new JTB(RV_10kV));
        jtbs.add(new JTB(LR_10kV));
        jtbs.add(new JTB(VN_10kV));


        setLayout(new GridLayout());

        for (JTB jtb: jtbs) {
            jtb.addActionListener(this);
            buttonGroup.add(jtb);
            add(jtb);
        }
    }

    public String getSelection() {
        return selection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTB jtb = (JTB) e.getSource();
        if (jtb.getText().equals(this.selection)) {
            this.selection = null;
            buttonGroup.clearSelection();

        }
        else {
            this.selection = jtb.getText();
        }

        System.out.println("PanelObject this.selection=" + this.selection);
    }
}
