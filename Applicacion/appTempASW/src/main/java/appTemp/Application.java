package appTemp;

import org.jfree.ui.RefineryUtilities;

public class Application {
    
    public static void main(String[] args) {
        Grafica demo = Grafica.getInstance();
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
        
        new AccesoADatos();
    }    
}
