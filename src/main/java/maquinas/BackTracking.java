package maquinas;

import java.util.ArrayList;
import java.util.List;

public class BackTracking {
    private List<Maquina> maquinas;
    private List<Maquina> mejorSolucion;
    private int costo = 0;

    public BackTracking(List<Maquina> maquinas) {
        this.maquinas = maquinas;
        this.mejorSolucion = new ArrayList<>();
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public void asignarMaquinas(int piezasTotales){
        setCosto(0);
        List<Maquina> solucionActual = new ArrayList<>();
        maquinas.sort((maquina1, maquina2) -> maquina2.getPiezas() - maquina1.getPiezas());
        asignarMaquinas(solucionActual,piezasTotales, 0, 0);
        if(!mejorSolucion.isEmpty()){
            mostrarMejorSolucion(piezasTotales);
        }else{
            System.out.println("No se encontro solucion.");
        }
    }
    private void asignarMaquinas(List<Maquina> solucionActual,int piezasTotales, int piezasHechas, int indice){
        costo ++;
        if(esSolucion(piezasTotales,piezasHechas)){
            compararSolucion(solucionActual);
        }
        else{
            for(int i = indice; i < maquinas.size(); i++){
                Maquina maquina = maquinas.get(i);
                if(piezasHechas + maquina.getPiezas() <= piezasTotales){
                    solucionActual.add(maquina);
                    piezasHechas += maquina.getPiezas();
                    if (!poda(solucionActual)){
                        asignarMaquinas(solucionActual,piezasTotales, piezasHechas, i);
                    }
                    solucionActual.removeLast();
                    piezasHechas -= maquina.getPiezas();
                }
            }
        }
    }
    private boolean esSolucion(int piezasTotales, int piezasHechas){
        return piezasHechas == piezasTotales;
    }

    private void compararSolucion(List<Maquina> solucionActual){
        if(solucionActual.size() < mejorSolucion.size() || mejorSolucion.isEmpty()){
            mejorSolucion.clear();
            mejorSolucion.addAll(solucionActual);
        }
    }
    private boolean poda(List<Maquina> solucionActual){
        return !mejorSolucion.isEmpty() && solucionActual.size() >= mejorSolucion.size();
    }

    private void mostrarMejorSolucion(int piezasTotales) {
        System.out.println("- Secuencia de máquinas:");
        for (Maquina m : mejorSolucion) {
            System.out.print(m.getNombre() + " ");
        }

        System.out.println("\n- Piezas producidas:");
        System.out.println(piezasTotales);

        System.out.println("- Puestas en funcionamiento:");
        System.out.println(mejorSolucion.size());

        System.out.println("- Costo:");
        System.out.println(costo);
    }
}

