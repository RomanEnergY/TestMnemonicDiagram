package ElectricityV017;

import javax.swing.*;
import java.awt.*;

/**
 * Created by user on 19.07.2018.
 */
public class PanelCENTER extends JPanel {
    MyPanel myPanel;
    PanelObject panelObject;

    public PanelCENTER(){
        panelObject = new PanelObject();
        myPanel = new MyPanel(panelObject);

        setLayout(new BorderLayout());

        add(myPanel, BorderLayout.CENTER);
        add(panelObject, BorderLayout.NORTH);
    }

}
