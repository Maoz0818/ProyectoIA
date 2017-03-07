package Clases;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class Fallo extends JFrame{
 
    JPanel inicio = new JPanel();
    JPanel fondo = new JPanel();
    JLabel lFondo = new JLabel();
    JLabel lFallo = new JLabel();
    JLabel lFallo2 = new JLabel();
    JLabel lAlgoritmo = new JLabel();
    
    //Se recupera la imagen del fondo
    String pathFondo = "/images/fondoMapa.jpg";  
    URL urlFondo = this.getClass().getResource(pathFondo);  
    ImageIcon imgFondo = new ImageIcon(urlFondo);
    
    //Se recupera la imagen del itemInicio
    String pathItemInicio = "/images/item.png";  
    URL urlItemInicio = this.getClass().getResource(pathItemInicio);  
    ImageIcon imgItemInicio = new ImageIcon(urlItemInicio);
    //Se recupera la imagen del muroInicio
    String pathMuroInicio = "/images/muroInicio.png";  
    URL urlMuroInicio = this.getClass().getResource(pathMuroInicio);  
    ImageIcon imgMuroInicio = new ImageIcon(urlMuroInicio);
    //Se recupera la imagen del robotInicio
    String pathRobotInicio = "/images/robot.png";  
    URL urlRobotInicio = this.getClass().getResource(pathRobotInicio);  
    ImageIcon imgRobotInicio = new ImageIcon(urlRobotInicio);
    //Se recupera la imagen del robot enemigoInicio
    String pathRobotEnemigoInicio = "/images/robot_enemigo.png";  
    URL urlRobotEnemigoInicio = this.getClass().getResource(pathRobotEnemigoInicio);  
    ImageIcon imgRobotEnemigoInicio = new ImageIcon(urlRobotEnemigoInicio);
    
    //Metodo encargado de iniciar el mapa
    public void iniciarFallo(){
        
        //algunas caracteristicas de la ventana
        this.setSize(280,390);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        
        lFondo.setIcon(imgFondo);
        
        fondo.setLayout(null);
                
        inicio.setLayout(new GridLayout(10,10));
        inicio.setBackground(Color.decode("#a1a1a1"));
        inicio.setBorder(BorderFactory.createLineBorder(Color.decode("#ecedf1"), 2));
        
        lFallo.setFont(new java.awt.Font("Arial", 0, 14));
        lFallo.setForeground(Color.decode("#ecedf1"));
        lFallo2.setFont(new java.awt.Font("Arial", 0, 14));
        lFallo2.setForeground(Color.decode("#ecedf1"));
        lAlgoritmo.setFont(new java.awt.Font("Arial", 0, 14));
        lAlgoritmo.setForeground(Color.decode("#ecedf1"));        
                
        fondo.add(inicio);
        fondo.add(lFallo);
        fondo.add(lFallo2);
        fondo.add(lAlgoritmo);
        fondo.add(lFondo);
        
        this.add(fondo);
        
        fondo.setBounds(0, 0, 280, 390);
        inicio.setBounds(12, 100, 250, 250);
        lFallo.setBounds(8, 10, 280, 30);
        lFallo2.setBounds(25, 35, 280, 30);
        lAlgoritmo.setBounds(22, 60, 280, 30);
                
        lFondo.setBounds(0, 0, 280, 390);
        
        //this.setDefaultCloseOperation(Mapa.EXIT_ON_CLOSE);
    }
    
    //Metodo encargado de pintar el mapa a partir de la matriz que contiene en archivo .txt
    public void pintarFallo(String matrizInicial[][], String titulo ){
        super.setTitle("BUSQUEDA FALLIDA");
        lFallo.setText("No fue posible encontrar la solución para");
        lFallo2.setText("este mundo aplicando la busqueda");
        if(titulo.equals("DE COSTO UNIFORME")){
            lAlgoritmo.setBounds(60, 60, 280, 30);        
        }
        if(titulo.equals("PREFERENTE POR AMPLITUD")){
            lAlgoritmo.setBounds(40, 60, 280, 30);        
        }
        lAlgoritmo.setText(titulo);
        
        //Se recorre la matriz para obtener el valor en cada posición
        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 10;j++){
                //Un switch case segun el valor de la matriz en cada posicion se encargara de agregar
                //al contenedor la imagen correspondiente
                
                switch(matrizInicial[i][j]){
                    case "0":
                       JLabel libre = new JLabel();
                       inicio.add(libre);
                     break;
                    case "1":
                       JLabel muro = new JLabel();
                       muro.setIcon(imgMuroInicio);
                       inicio.add(muro);
                     break;
                    case "2":
                       JLabel robot = new JLabel();
                       robot.setIcon(imgRobotInicio);
                       inicio.add(robot);
                     break;
                    case "3":
                       JLabel robotEnemigo = new JLabel();
                       robotEnemigo.setIcon(imgRobotEnemigoInicio);
                       inicio.add(robotEnemigo);
                     break;
                    case "4":
                       JLabel item = new JLabel();
                       item.setIcon(imgItemInicio);
                       inicio.add(item);
                     break;
                    default:
                       JLabel defecto = new JLabel();
                       inicio.add(defecto);
                }
            }
        }
    }
    
}
