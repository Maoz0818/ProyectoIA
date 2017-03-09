package Clases;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeCostoUniforme {
    
    int contNodosExpandidosBfs = 0;
    int profundidad = 0;
    int costo = 0;
    int estadoInicial[] = new int[2];
    Nodo raiz = new Nodo(estadoInicial,null,null,0,0,0,0,0,0);
    Nodo padre = new Nodo();
    String matriz[][] = new String[10][10];
    String matrizInicial[][] = new String[10][10];
    boolean visitados[][] = new boolean[10][10];
    long tInicio = 0, tFin = 0, tTotal = 0;
    
    public void obtenerSolucion(){
        
        Recursos pruebas = new Recursos();
        try {
            pruebas.guardarMapa();
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        matriz = pruebas.getMatriz();
        
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

        Nodo solucion = Busqueda(raiz);
        if(solucion != null){
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
        mostrarRuta(rama, solucion.profundidad, contNodosExpandidosBfs, solucion.costo, tTotal, solucion.balas);
        }
        else{
            Fallo fallo = new Fallo();
            fallo.iniciarFallo();
            fallo.pintarFallo(matriz, "DE COSTO UNIFORME");
        }
    }
    
    public void mostrarRuta(ArrayList<Nodo> rama, int profundidad, int nodos, int costo, long tiempo, int balas){
        for(int i=0;i<10;i++){
            System.arraycopy(matriz[i], 0, matrizInicial[i], 0, 10);
        }
        
        Mapa mapa = new Mapa();
        for(int i=1; i<rama.size(); i++){
            String num = matriz[rama.get(i).estado[0]][rama.get(i).estado[1]];
            switch(num){
                case "3":
                    matriz[rama.get(i).estado[0]][rama.get(i).estado[1]] = "6";
                    break;
                case "4":
                    matriz[rama.get(i).estado[0]][rama.get(i).estado[1]] = "7";
                    break;
                default:
                    matriz[rama.get(i).estado[0]][rama.get(i).estado[1]] = "5";
                    break;
            }  
        }
       
        mapa.iniciarMapa();
        mapa.pintarRuta(matrizInicial, matriz, profundidad, nodos, costo, tiempo, balas, "BUSQUEDA NO INFORMADA -> DE COSTO UNIFORME");
    }
    
    public Nodo Busqueda(Nodo raiz){
        Recursos pruebas = new Recursos();
            try {
            pruebas.guardarMapa();
                } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        raiz.balas = pruebas.getBalas();    
        tInicio = System.currentTimeMillis();
        ArrayList<Nodo> frontera = new ArrayList();
        frontera.add(raiz);
        
        while(!frontera.isEmpty()){
            
            Nodo actual;
            Collections.sort(frontera);
            actual = frontera.remove(0);
                        
            if(matriz[actual.estado[0]][actual.estado[1]].equals("4")){
                tFin = System.currentTimeMillis();
                tTotal = tFin - tInicio;
                return actual;
            }
            
            visitados[actual.estado[0]][actual.estado[1]]=true;
            
            Queue<Nodo> sucesores;
            sucesores = Expandir(actual);
            contNodosExpandidosBfs++;
            
            while(!sucesores.isEmpty()){
                frontera.add(sucesores.remove());    
            } 
        }
        tFin = System.currentTimeMillis();
        tTotal = tInicio - tFin;
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
            Nodo hijo = new Nodo(estado,null,null,0,0,0,0,0,0);
            hijo.estado[0]= posX-1;
            hijo.estado[1]= posY;
            hijo.padre = nodo;
            hijo.operador = "arriba";
            if(matriz[posX-1][posY].equals("3") && nodo.balas != 0){
                hijo.costo=nodo.costo+1;
                hijo.balas=nodo.balas;
                hijo.balas-=1;
            }else{
                if(matriz[posX-1][posY].equals("3") && nodo.balas == 0){
                    hijo.costo=nodo.costo+1+4;
                    hijo.balas=nodo.balas;
                }else{
                    hijo.costo=nodo.costo+1;
                    hijo.balas=nodo.balas;
                }
            }
            hijo.profundidad=nodo.profundidad+1;
            hijos.add(hijo);
        }
        
        //Accion derecha
        if(posX >= 0 && posX < 10 && posY+1 >= 0 && posY+1 < 10 && !matriz[posX][posY+1].equals("1") && !visitados[posX][posY+1]){
            int estado[] = new int[2];
            Nodo hijo = new Nodo(estado,null,null,0,0,0,0,0,0);
            hijo.estado[0]= posX;
            hijo.estado[1]= posY+1;
            hijo.padre = nodo;
            hijo.operador = "derecha";
            if(matriz[posX][posY+1].equals("3") && nodo.balas != 0){
                hijo.costo=nodo.costo+1;
                hijo.balas=nodo.balas;
                hijo.balas-=1;
            }else{
                if(matriz[posX][posY+1].equals("3") && nodo.balas == 0){
                    hijo.costo=nodo.costo+1+4;
                    hijo.balas=nodo.balas;
                }else{
                    hijo.costo=nodo.costo+1;
                    hijo.balas=nodo.balas;
                }
            }
            hijo.profundidad=nodo.profundidad+1;
            hijos.add(hijo);
        }
        
        //Accion abajo
        if(posX+1 >= 0 && posX+1 < 10 && posY >= 0 && posY < 10 && !matriz[posX+1][posY].equals("1") && !visitados[posX+1][posY]){
            int estado[] = new int[2];
            Nodo hijo = new Nodo(estado,null,null,0,0,0,0,0,0);
            hijo.estado[0]=posX+1;
            hijo.estado[1]= posY;
            hijo.padre = nodo;
            hijo.operador = "abajo";
            if(matriz[posX+1][posY].equals("3") && nodo.balas != 0){
                hijo.costo=nodo.costo+1;
                hijo.balas=nodo.balas;
                hijo.balas-=1;
            }else{
                if(matriz[posX+1][posY].equals("3") && nodo.balas == 0){
                    hijo.costo=nodo.costo+1+4;
                    hijo.balas=nodo.balas;
                }else{
                    hijo.costo=nodo.costo+1;
                    hijo.balas=nodo.balas;
                }
            }
            hijo.profundidad=nodo.profundidad+1;
            hijos.add(hijo);
        }
        
        //Accion izquierda
        if(posX >= 0 && posX < 10 && posY-1 >= 0 && posY-1 < 10 && !matriz[posX][posY-1].equals("1") && !visitados[posX][posY-1]){
            int estado[] = new int[2];
            Nodo hijo = new Nodo(estado,null,null,0,0,0,0,0,0);
            hijo.estado[0]= posX;
            hijo.estado[1]=posY-1;
            hijo.padre = nodo;
            hijo.operador = "izquierda";
            if(matriz[posX][posY-1].equals("3") && nodo.balas != 0){
                hijo.costo=nodo.costo+1;
                hijo.balas=nodo.balas;
                hijo.balas-=1;
            }else{
                if(matriz[posX][posY-1].equals("3") && nodo.balas == 0){
                    hijo.costo=nodo.costo+1+4;
                    hijo.balas=nodo.balas;
                }else{
                    hijo.costo=nodo.costo+1;
                    hijo.balas=nodo.balas;
                }
            }
            hijo.profundidad=nodo.profundidad+1;
            hijos.add(hijo);
        }
        
        return hijos;
    }
    
}
