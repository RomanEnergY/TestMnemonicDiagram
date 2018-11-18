package ElectricityV020.AboutDialog;

import ElectricityV020.ModelShape.ModelRectangularShape;

import javax.swing.*;

/**
 * Created by user on 14.08.2018.
 */
public class AboutModelRectangularShapeDialog extends JDialog {
    private JFrame owner;
    private ModelRectangularShape findModelRectangularShape;
    private static AboutModelRectangularShapeDialog aboutModelRectangularShapeDialog;
    JPanelContent jPanelContent;

    private AboutModelRectangularShapeDialog(JFrame owner, ModelRectangularShape findModelRectangularShape)
    {
        super(owner, "Выбран объект:", true);
        this.owner = owner;
        this.findModelRectangularShape = findModelRectangularShape;
        jPanelContent = new JPanelContent(findModelRectangularShape);
        this.add(jPanelContent);

        this.setSize(400, 300);
        this.setLocationRelativeTo(null); // Вызвать jFrame по центру экрана
        this.setResizable(false); // Запретить изменение размеров формы
    }

    public static AboutModelRectangularShapeDialog getAboutModelRectangularShapeDialog(JFrame owner, ModelRectangularShape findModelRectangularShape){
        if (aboutModelRectangularShapeDialog == null) {
            aboutModelRectangularShapeDialog = new AboutModelRectangularShapeDialog(owner, findModelRectangularShape);
            aboutModelRectangularShapeDialog.jPanelContent.setModelShape(findModelRectangularShape);
            aboutModelRectangularShapeDialog.setVisible(true);
        } else {
            aboutModelRectangularShapeDialog.owner = owner;
            aboutModelRectangularShapeDialog.findModelRectangularShape = findModelRectangularShape;
            aboutModelRectangularShapeDialog.jPanelContent.setModelShape(findModelRectangularShape);
            aboutModelRectangularShapeDialog.setVisible(true);
        }

        return aboutModelRectangularShapeDialog;
    }
}
