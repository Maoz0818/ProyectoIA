package Clases;

public class Nodo implements Comparable<Nodo> {
    
    int estado[]=new int[2];
    Nodo padre;
    String operador;
    int profundidad;
    int costo;
    int balas;
    
    public Nodo(){}
    
    public Nodo(int[] estado, Nodo padre, String operador, int profundidad, int costo, int balas){
        this.estado=estado;
        this.padre=padre;
        this.operador=operador;
        this.profundidad=profundidad;
        this.costo=costo;
        this.balas=balas;
    }

    @Override
    public int compareTo(Nodo o) {
            if (costo < o.costo) {
                return -1;
            }
            if (costo > o.costo) {
                return 1;
            }
            return 0;
    }  
}
