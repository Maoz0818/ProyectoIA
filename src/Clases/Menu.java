package Clases;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Menu extends JFrame implements ActionListener{
    
    //Se recupera la imagen del fondo
    String pathFondo = "/images/fondo.jpg";  
    URL urlFondo = this.getClass().getResource(pathFondo);  
    ImageIcon imgFondo = new ImageIcon(urlFondo);
    
    JLabel lFondo = new JLabel();
    
    JPanel fondo = new JPanel();
    JLabel noInfo = new JLabel("BUSQUEDA NO INFORMADA");
    JLabel info = new JLabel("BUSQUEDA INFORMADA");
    JButton amplitud = new JButton("Preferente por amplitud");
    JButton costo = new JButton("De costo uniforme");
    JButton profundidad = new JButton("Preferente por profundidad");
    JButton voraz = new JButton("Voraz");
    JButton a_estrella = new JButton("A*");
    
    JButton prueba = new JButton("Prueba");
    
    //Metodo encargado de iniciar el mapa
    public void iniciarMenu(){
        
        //algunas caracteristicas de la ventana
        this.setSize(400,450);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        super.setTitle("METODOS DE BUSQUEDA");
        
        lFondo.setIcon(imgFondo);
        
        fondo.setBackground(Color.WHITE);
        fondo.setLayout(null);
        
        info.setFont(new java.awt.Font("Arial", 0, 18));
        info.setForeground(Color.decode("#ecedf1"));
        noInfo.setFont(new java.awt.Font("Arial", 0, 18));
        noInfo.setForeground(Color.decode("#ecedf1"));
        
        amplitud.addActionListener(this);
        amplitud.setBackground(Color.decode("#ecedf1"));
        amplitud.setFocusPainted(false);
        costo.addActionListener(this);
        costo.setBackground(Color.decode("#ecedf1"));
        profundidad.addActionListener(this);
        profundidad.setBackground(Color.decode("#ecedf1"));
        voraz.addActionListener(this);
        voraz.setBackground(Color.decode("#ecedf1"));
        a_estrella.addActionListener(this);
        a_estrella.setBackground(Color.decode("#ecedf1"));
        
        prueba.addActionListener(this);
        
        fondo.add(noInfo);
        fondo.add(info);
        fondo.add(amplitud);
        fondo.add(costo);
        fondo.add(profundidad);
        fondo.add(voraz);
        fondo.add(a_estrella);
        
        fondo.add(prueba);
        
        fondo.add(lFondo);
        
        this.add(fondo);
        
        fondo.setBounds(0, 0, 400, 500);
        noInfo.setBounds(80, 60, 250, 30);
        amplitud.setBounds(110, 100, 190, 30);
        costo.setBounds(110, 140, 190, 30);
        profundidad.setBounds(110, 180, 190, 30);
        info.setBounds(100, 240, 250, 30);
        voraz.setBounds(110, 280, 190, 30);
        a_estrella.setBounds(110, 320, 190, 30);
        
        prueba.setBounds(110, 360, 190, 30);
        
        lFondo.setBounds(0, 0, 400, 500);
        
        this.setDefaultCloseOperation(Mapa.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Accion por aplitud
        if(e.getSource()==amplitud){
            PreferentePorAmplitud PPA = new PreferentePorAmplitud();
            PPA.obtenerSolucion();
        }
        
        //Accion costo
        if(e.getSource()==costo){
            DeCostoUniforme DCU = new DeCostoUniforme();
            DCU.obtenerSolucion();
        }
        
        //Accion profundidad
        if(e.getSource()==profundidad){
            PreferentePorProfundidad PPP = new PreferentePorProfundidad();
            PPP.obtenerSolucion();
        }
        
        //Accion voraz
        if(e.getSource()==voraz){
            Voraz V = new Voraz();
            V.obtenerSolucion();
        }
        
        //Accion A*
        if(e.getSource()==a_estrella){
            AEstrella AE = new AEstrella();
            AE.obtenerSolucion();
        }
        
        //Accion para pruebas
        if(e.getSource()==prueba){
            Recursos pruebas = new Recursos();
            try {
                pruebas.guardarMapa();
            } catch (IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            String aux[][];
            aux = pruebas.getMatriz();
            for(int i=0; i<10; i++){
                for(int j=0; j<10; j++){
                    System.out.print(aux[i][j]+" ");
                }
                System.out.print("\n");
            }
            System.out.println(pruebas.getBalas());
            System.out.println(Arrays.toString(pruebas.getEstado()));
        }
    }  
}
