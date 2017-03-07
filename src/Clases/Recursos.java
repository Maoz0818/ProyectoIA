package Clases;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Recursos {
    
    String aux[][] = new String[10][10];
    String matriz[][] = new String[10][10];
    int balas;
    
    public Recursos(){}
    
    public Recursos(String[][] matriz, int balas){
        this.matriz=matriz;
        this.balas=balas;
    }
    
    public String[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(String[][] matriz) {
        this.matriz = matriz;
    }

    public int getBalas() {
        return balas;
    }

    public void setBalas(int balas) {
        this.balas = balas;
    }
    
    public void guardarMapa() throws FileNotFoundException, IOException {
        String linea;
        String delimitador = " ";
        int numlinea = 0;
 
        //FileReader file = new FileReader("/home/mauricio/Descargas/Prueba1.txt");
        //FileReader file = new FileReader("C:\\Users\\temp.DESKTOP-IB18RSF\\Downloads\\Prueba1.txt");
        FileReader file = new FileReader("C:\\Users\\Admin\\Downloads\\Prueba1.txt");
        try (BufferedReader buffer = new BufferedReader(file)) {
            while((linea = buffer.readLine())!=null) {
                if(numlinea != 0){
                    String a[] = linea.split(delimitador);
                    System.arraycopy(a, 0, aux[numlinea-1], 0, a.length);
                }else{
                    this.setBalas(Integer.parseInt(linea));
                }
                numlinea++;
            }
            buffer.close();           
        }
        this.setMatriz(aux);
    } 
    
}
