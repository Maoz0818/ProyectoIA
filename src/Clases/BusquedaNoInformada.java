package Clases;

import java.io.*;
import java.util.*;

public class BusquedaNoInformada {
    
    int contNodos = 0;
    int balas;
    int profundidad = 0;
    int costo = 0;
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
    
    public void obtenerRuta(){
        
        Nodo solucion = BFS(raiz);
        ArrayList<Nodo> rama = new ArrayList<>();
                        
        Queue<Nodo> aux;
        aux=new LinkedList();
        aux.add(solucion);
        
        while(!aux.isEmpty()){
            
            Nodo actual;
            actual = aux.remove();
            rama.add(actual);
            if(actual.padre != null){
                aux.add(actual.padre);
            }
        }
        
        Collections.reverse(rama);
        //rama.forEach(x->System.out.print(Arrays.toString(x.estado)));
        //System.out.println("\n");
        mostrarRuta(rama); 
    }
    
    public void mostrarRuta(ArrayList<Nodo> rama){
        
        Mapa mapa = new Mapa();
        for(int i=1; i<rama.size()-1; i++){
            for(int j=0;j<10;j++){
                for(int k=0;k<10;k++){
                    matriz[rama.get(i).estado[0]][rama.get(i).estado[1]] = "5";
                }
            }   
        }
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
                System.out.println(contNodos);
                System.out.println(actual.costo);
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
        contNodos++;
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
            hijo.balas=nodo.balas;
            hijos.add(hijo);
        }
        
        //Accion derecha
        if(posX >= 0 && posX < 10 && posY+1 >= 0 && posY+1 < 10 && !matriz[posX][posY+1].equals("1") && !visitados[posX][posY+1]){
            int estado[] = new int[2];
            Nodo hijo = new Nodo(estado,null,null,0,0,0);
            hijo.estado[0]= posX;
            hijo.estado[1]= posY+1;
            hijo.padre = nodo;
            hijo.operador = "derecha";
            hijo.costo=nodo.costo+1;
            hijo.profundidad=nodo.profundidad+1;
            hijo.balas=nodo.balas;
            hijos.add(hijo);
        }
        
        //Accion abajo
        if(posX+1 >= 0 && posX+1 < 10 && posY >= 0 && posY < 10 && !matriz[posX+1][posY].equals("1") && !visitados[posX+1][posY]){
            int estado[] = new int[2];
            Nodo hijo = new Nodo(estado,null,null,0,0,0);
            hijo.estado[0]=posX+1;
            hijo.estado[1]= posY;
            hijo.padre = nodo;
            hijo.operador = "abajo";
            hijo.costo=nodo.costo+1;
            hijo.profundidad=nodo.profundidad+1;
            hijo.balas=nodo.balas;
            hijos.add(hijo);
        }
        
        //Accion izquierda
        if(posX >= 0 && posX < 10 && posY-1 >= 0 && posY-1 < 10 && !matriz[posX][posY-1].equals("1") && !visitados[posX][posY-1]){
            int estado[] = new int[2];
            Nodo hijo = new Nodo(estado,null,null,0,0,0);
            hijo.estado[0]= posX;
            hijo.estado[1]=posY-1;
            hijo.padre = nodo;
            hijo.operador = "izquierda";
            hijo.costo=nodo.costo+1;
            hijo.profundidad=nodo.profundidad+1;
            hijo.balas=nodo.balas;
            hijos.add(hijo);
        }
        //hijos.forEach(x->System.out.print(Arrays.toString(x.estado)));
        //System.out.println("\n");
        return hijos;
    }
}
