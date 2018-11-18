package ElectricityV020;

import javax.swing.*;
import java.awt.*;

/**
 * Created by user on 19.07.2018.
 */
public class PanelCENTER extends JPanel {
    PantPanel pantPanel;
    PanelObject panelObject;

    public PanelCENTER(JFrame jFrameOwnerJDialog){
        panelObject = new PanelObject();
        pantPanel = new PantPanel(jFrameOwnerJDialog, panelObject);
        panelObject.setJPanelRepaint(pantPanel);

        setLayout(new BorderLayout());

        add(pantPanel, BorderLayout.CENTER);
        add(panelObject, BorderLayout.NORTH);
    }

}
