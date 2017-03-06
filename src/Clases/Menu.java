package Clases;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Menu extends JFrame implements ActionListener{
    
    JPanel fondo = new JPanel();
    JLabel noInfo = new JLabel("BUSQUEDA NO INFORMADA");
    JLabel info = new JLabel("BUSQUEDA INFORMADA");
    JButton amplitud = new JButton("Preferente por amplitud");
    JButton costo = new JButton("De costo uniforme");
    JButton profundidad = new JButton("Preferente por profundidad");
    
    //Metodo encargado de iniciar el mapa
    public void iniciarMenu(){
        
        //algunas caracteristicas de la ventana
        this.setSize(400,500);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setBackground(Color.yellow);
        super.setTitle("METODOS DE BUSQUEDA");
        
        fondo.setBackground(Color.WHITE);
        fondo.setLayout(null);
        
        info.setFont(new java.awt.Font("Arial", 0, 18));
        noInfo.setFont(new java.awt.Font("Arial", 0, 18));
        amplitud.addActionListener(this);
        costo.addActionListener(this);
        profundidad.addActionListener(this);
        
        fondo.add(noInfo);
        fondo.add(info);
        fondo.add(amplitud);
        fondo.add(costo);
        fondo.add(profundidad);
        
        this.add(fondo);
        
        fondo.setBounds(0, 0, 400, 500);
        noInfo.setBounds(80, 60, 250, 30);
        amplitud.setBounds(110, 100, 190, 30);
        costo.setBounds(110, 140, 190, 30);
        profundidad.setBounds(110, 180, 190, 30);
        //info.setBounds(125, 280, 250, 30);
        
        this.setDefaultCloseOperation(Mapa.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Accion por aplitud
        if(e.getSource()==amplitud){
            PreferentePorAmplitud PPA = new PreferentePorAmplitud();
            try {
                PPA.guardarMapa();
            } catch (IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            PPA.obtenerSolucion();
        }
        
        //Accion costo
        if(e.getSource()==costo){
            DeCostoUniforme DCU = new DeCostoUniforme();
            try {
                DCU.guardarMapa();
            } catch (IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            DCU.obtenerSolucion();
        }
    }  
}
