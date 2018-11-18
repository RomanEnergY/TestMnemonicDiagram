package ElectricityV018;

import javax.swing.*;
import java.awt.*;

/**
 * Created by user on 19.07.2018.
 */
public class PanelCENTER extends JPanel {
    PantPanel pantPanel;
    PanelObject panelObject;

    public PanelCENTER(){
        panelObject = new PanelObject();
        pantPanel = new PantPanel(panelObject);

        setLayout(new BorderLayout());

        add(pantPanel, BorderLayout.CENTER);
        add(panelObject, BorderLayout.NORTH);
    }

}
