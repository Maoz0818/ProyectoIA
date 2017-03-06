package Clases;

//import java.io.*;
import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class Mapa extends JFrame{
    
    //Se crea los elementos para agregar al JFrame
    JPanel ruta = new JPanel();
    JPanel inicio = new JPanel();
    JPanel fondo = new JPanel();
    JLabel lProfundidad = new JLabel();
    JLabel lNodosExpandidos = new JLabel();
    JLabel lCosto = new JLabel();
    JLabel lTiempo = new JLabel();
    JLabel lBalas = new JLabel();
    
    //Se recupera la imagen del muro
    String pathMuro = "/images/muro.png";  
    URL urlMuro = this.getClass().getResource(pathMuro);  
    ImageIcon imgMuro = new ImageIcon(urlMuro);
    //Se recupera la imagen del robot
    String pathRobot = "/images/robot.gif";  
    URL urlRobot = this.getClass().getResource(pathRobot);  
    ImageIcon imgRobot = new ImageIcon(urlRobot);
    //Se recupera la imagen del robot enemigo
    String pathRobotEnemigo = "/images/robot_enemigo.gif";  
    URL urlRobotEnemigo = this.getClass().getResource(pathRobotEnemigo);  
    ImageIcon imgRobotEnemigo = new ImageIcon(urlRobotEnemigo);
    //Se recupera la imagen del item
    String pathItem = "/images/item.gif";  
    URL urlItem = this.getClass().getResource(pathItem);  
    ImageIcon imgItem = new ImageIcon(urlItem);
    //Se recupera la imagen camino
    String pathCamino = "/images/camino.gif";  
    URL urlCamino = this.getClass().getResource(pathCamino);  
    ImageIcon imgCamino = new ImageIcon(urlCamino);
    //Se recupera la imagen camino enemigo
    String pathCaminoEnemigo = "/images/camino_enemigo.gif";  
    URL urlCaminoEnemigo = this.getClass().getResource(pathCaminoEnemigo);  
    ImageIcon imgCaminoEnemigo = new ImageIcon(urlCaminoEnemigo);
    
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
    public void iniciarMapa(){
        
        //algunas caracteristicas de la ventana
        this.setSize(872,640);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        
        fondo.setBackground(Color.WHITE);
        fondo.setLayout(null);
        
        ruta.setLayout(new GridLayout(10,10));
        ruta.setBackground(Color.decode("#a1a1a1"));
        ruta.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        
        inicio.setLayout(new GridLayout(10,10));
        inicio.setBackground(Color.decode("#a1a1a1"));
        inicio.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        
        lProfundidad.setFont(new java.awt.Font("Arial", 0, 20));
        lNodosExpandidos.setFont(new java.awt.Font("Arial", 0, 20));
        lCosto.setFont(new java.awt.Font("Arial", 0, 20));
        lTiempo.setFont(new java.awt.Font("Arial", 0, 20));
        lBalas.setFont(new java.awt.Font("Arial", 0, 20));
                
        fondo.add(inicio);
        fondo.add(ruta);
        fondo.add(lNodosExpandidos);
        fondo.add(lProfundidad);
        fondo.add(lCosto);
        fondo.add(lTiempo);
        fondo.add(lBalas);
        
        this.add(fondo);
        
        fondo.setBounds(0, 0, 872, 640);
        ruta.setBounds(260, 5, 600, 600);
        inicio.setBounds(5, 5, 250, 250);
        
        lProfundidad.setBounds(10, 340, 250, 30);
        lNodosExpandidos.setBounds(10, 380, 250, 30);
        lCosto.setBounds(10, 420, 250, 30); 
        lTiempo.setBounds(10, 460, 250, 30);
        lBalas.setBounds(10, 500, 250, 30);
        
        //this.setDefaultCloseOperation(Mapa.EXIT_ON_CLOSE);
    }
    
    //Metodo encargado de pintar el mapa a partir de la matriz que contiene en archivo .txt
    public void pintarRuta(String matrizInicial[][], String matrizSolucion[][], int profundidad, int nodos, int costo, long tiempo, int balas, String titulo ){
        super.setTitle(titulo);
        lNodosExpandidos.setText("Nodos expandidos: "+nodos);
        lProfundidad.setText("Profundidad: "+profundidad);
        lCosto.setText("Costo: "+costo);
        lTiempo.setText("Tiempo: "+tiempo+" milisegundos");
        lBalas.setText("Balas: "+balas);
        
        //Se recorre la matriz para obtener el valor en cada posición
        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 10;j++){
                //Un switch case segun el valor de la matriz en cada posicion se encargara de agregar
                //al contenedor la imagen correspondiente
                switch(matrizSolucion[i][j]){
                    case "0":
                       JLabel libre = new JLabel();
                       ruta.add(libre);
                     break;
                    case "1":
                       JLabel muro = new JLabel();
                       muro.setIcon(imgMuro);
                       ruta.add(muro);
                     break;
                    case "2":
                       JLabel robot = new JLabel();
                       robot.setIcon(imgRobot);
                       ruta.add(robot);
                     break;
                    case "3":
                       JLabel robotEnemigo = new JLabel();
                       robotEnemigo.setIcon(imgRobotEnemigo);
                       ruta.add(robotEnemigo);
                     break;
                    case "4":
                       JLabel item = new JLabel();
                       item.setIcon(imgItem);
                       ruta.add(item);
                     break;
                    case "5":
                       JLabel camino = new JLabel();
                       camino.setIcon(imgCamino);
                       ruta.add(camino);
                     break;
                    case "6":
                       JLabel camino_enemigo = new JLabel();
                       camino_enemigo.setIcon(imgCaminoEnemigo);
                       ruta.add(camino_enemigo);
                     break;
                    default:
                       JLabel defecto = new JLabel();
                       ruta.add(defecto);
                }
                
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


