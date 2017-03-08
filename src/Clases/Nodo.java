package Clases;

public class Nodo implements Comparable<Nodo> {
    int estado[]=new int[2];
    Nodo padre;
    String operador;
    int profundidad;
    int costo;
    int heuristica;
    int g;
    int balas;
    int compare;
    
    public Nodo(){}
    
    public Nodo(int[] estado, Nodo padre, String operador, int profundidad, int costo, int heuristica, int g, int balas, int compare){
        this.estado=estado;
        this.padre=padre;
        this.operador=operador;
        this.profundidad=profundidad;
        this.costo=costo;
        this.heuristica=heuristica;
        this.g=g;
        this.balas=balas;
        this.compare=compare;
    }

    @Override
    public int compareTo(Nodo o) {
        
        switch(o.compare){
            case 0:
                //System.out.println("compareTo con costo");
                if (costo < o.costo) {
                return -1;
            }
            if (costo > o.costo) {
                return 1;
            }
                break;
            
            case 1:
                //System.out.println("compareTo con heuristica");
                if (heuristica < o.heuristica) {
                return -1;
            }
            if (heuristica > o.heuristica) {
                return 1;
            }
                break;
            
            case 2:
                //System.out.println("compareTo con g");
                if (g < o.g) {
                return -1;
            }
            if (g > o.g) {
                return 1;
            }
                break;
            
            default:
                //System.out.println("Fallo compareTo");
                break;
        }
        return 0;
    }  
}
