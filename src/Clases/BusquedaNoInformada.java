package Clases;

import java.io.*;
import java.util.*;

public class BusquedaNoInformada {
    
    int cont = 0;
    int balas;
    int profundidad = 0;
    int costo = 0;
    int posSol = 0;
    int estadoInicial[] = new int[2];
    Nodo raiz = new Nodo(estadoInicial,null,null,0,0,0);
    Nodo padre = new Nodo();
    String matriz[][] = new String[10][10];
    boolean visitados[][] = new boolean[10][10];
    
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
                    System.arraycopy(a, 0, matriz[numlinea-1], 0, a.length);

                }else{
                    balas = Integer.parseInt(linea);
                }

                numlinea++;
            }

            buffer.close();           
        }
        
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(matriz[i][j].equals("2")){
                    raiz.estado[0]=i;
                    raiz.estado[1]=j;
                }
            }
        }
        
        raiz.padre = null;
        raiz.operador = null;
        raiz.costo = 0;
        raiz.profundidad = 0;
        raiz.balas = balas;
        
    }
    
    public void mostrarRuta(){
        Nodo solucion = BFS(raiz);
        for(int i=0; i<solucion.profundidad; i++){
            
        }
        Mapa mapa = new Mapa();
        mapa.iniciarMapa();
        mapa.pintarMapa(matriz);
    }
    
    public Nodo BFS(Nodo raiz){
           
        Queue<Nodo> frontera;
        frontera=new LinkedList();
        frontera.add(raiz);
        
        while(!frontera.isEmpty()){
            
            Nodo actual;
            actual = frontera.remove();
            
            if(matriz[actual.estado[0]][actual.estado[1]].equals("4")){
                System.out.println("Solución");
                return actual;
            }
            
            visitados[actual.estado[0]][actual.estado[1]]=true;
            
            Queue<Nodo> sucesores;
            sucesores = Expandir(actual);
            
            while(!sucesores.isEmpty()){
                frontera.add(sucesores.remove());
            } 
        }
        System.out.println("fallo");
        return null;
    }
    
    public Queue<Nodo> Expandir(Nodo nodo){
        
        int posX = nodo.estado[0];
        int posY = nodo.estado[1];
        Queue<Nodo> hijos;
        hijos=new LinkedList();
        
        //Acción arriba
        if(posX-1 >= 0 && posX-1 < 10 && posY >= 0 && posY < 10 && !matriz[posX-1][posY].equals("1") && !visitados[posX-1][posY]){
            int estado[] = new int[2];
            Nodo hijo = new Nodo(estado,null,null,0,0,0);
            hijo.estado[0]= posX-1;
            hijo.estado[1]= posY;
            hijo.padre = nodo;
            hijo.operador = "arriba";
            hijo.costo=nodo.costo+1;
            hijo.profundidad=nodo.profundidad+1;
            hijos.add(hijo);
        }
        
        //Accion derecha
        if(posX >= 0 && posX < 10 && posY+1 >= 0 && posY+1 < 10 && !matriz[posX][posY+1].equals("1") && !visitados[posX][posY+1]){
            int estado[] = new int[2];
            Nodo hijo = new Nodo(estado,null,null,0,0,0);
            hijo.estado[0]= posX;
            hijo.estado[1]= posY+1;
            hijo.padre = nodo;
            hijo.operador = "arriba";
            hijo.costo=nodo.costo+1;
            hijo.profundidad=nodo.profundidad+1;
            hijos.add(hijo);
        }
        
        //Accion abajo
        if(posX+1 >= 0 && posX+1 < 10 && posY >= 0 && posY < 10 && !matriz[posX+1][posY].equals("1") && !visitados[posX+1][posY]){
            int estado[] = new int[2];
            Nodo hijo = new Nodo(estado,null,null,0,0,0);
            hijo.estado[0]=posX+1;
            hijo.estado[1]= posY;
            hijo.padre = nodo;
            hijo.operador = "arriba";
            hijo.costo=nodo.costo+1;
            hijo.profundidad=nodo.profundidad+1;
            hijos.add(hijo);
        }
        
        //Accion izquierda
        if(posX >= 0 && posX < 10 && posY-1 >= 0 && posY-1 < 10 && !matriz[posX][posY-1].equals("1") && !visitados[posX][posY-1]){
            int estado[] = new int[2];
            Nodo hijo = new Nodo(estado,null,null,0,0,0);
            hijo.estado[0]= posX;
            hijo.estado[1]=posY-1;
            hijo.padre = nodo;
            hijo.operador = "arriba";
            hijo.costo=nodo.costo+1;
            hijo.profundidad=nodo.profundidad+1;
            hijos.add(hijo);
        }
        //hijos.forEach(x->System.out.print(Arrays.toString(x.estado)));
        //System.out.println("\n");
        return hijos;
    }
}
