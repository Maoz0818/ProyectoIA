package Clases;

//import java.io.*;
import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class Mapa extends JFrame{
    
    //Se crea el contenedor del JFrame
    Container contenedor = this.getContentPane();
    
    //Se recupera la imagen del muro
    String pathMuro = "/images/muro.png";  
    URL urlMuro = this.getClass().getResource(pathMuro);  
    ImageIcon imgMuro = new ImageIcon(urlMuro);
    //Se recupera la imagen del robot
    String pathRobot = "/images/robot.png";  
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
    
    //Metodo encargado de iniciar el mapa
    public void iniciarMapa(){
        //layout del contenedor 
        contenedor.setLayout(new GridLayout(10,10));
        //color de fondo
        contenedor.setBackground(Color.decode("#b963e0"));
        //algunas caracteristicas de la ventana
        super.setTitle("Busqueda no informada");
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(Mapa.EXIT_ON_CLOSE);
    }
     
    //Metodo encargado de pintar el mapa a partir de la matriz que contiene en archivo .txt
    public void pintarMapa(String mapaMatriz[][]){
        //Se recorre la matriz para obtener el valor en cada posición
        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 10;j++){
                //Un switch case segun el valor de la matriz en cada posicion se encargara de agregar
                //al contenedor la imagen correspondiente
                switch(mapaMatriz[i][j]){
                    case "0":
                       JLabel libre = new JLabel();
                       contenedor.add(libre);
                     break;
                    case "1":
                       JLabel muro = new JLabel();
                       muro.setIcon(imgMuro);
                       contenedor.add(muro);
                     break;
                    case "2":
                       JLabel robot = new JLabel();
                       robot.setIcon(imgRobot);
                       contenedor.add(robot);
                     break;
                    case "3":
                       JLabel robotEnemigo = new JLabel();
                       robotEnemigo.setIcon(imgRobotEnemigo);
                       contenedor.add(robotEnemigo);
                     break;
                    case "4":
                       JLabel item = new JLabel();
                       item.setIcon(imgItem);
                       contenedor.add(item);
                     break;
                }
            }
        }
    }
}
