package Clases;

import java.io.IOException;
import javax.swing.JFrame;

public class Main extends JFrame{

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        PreferentePorAmplitud BNI = new PreferentePorAmplitud();
        BNI.guardarMapa();
        BNI.obtenerSolucion();
    }  
}
