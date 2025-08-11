package maquinas;

import java.util.ArrayList;
import java.util.List;

public class Greedy {
private List<Maquina> maquinas;
private int costo;

    public Greedy(List<Maquina> maquinas) {
        this.maquinas = maquinas;
        this.costo = 0;
    }
    public void ProducirPiezas(int piezasTotales){
        setCosto(0);
        List<Maquina> candidatos = new ArrayList<>(this.maquinas);
        List<Maquina> solucion = new ArrayList<>();
        candidatos.sort((maquina1, maquina2) -> maquina2.getPiezas() - maquina1.getPiezas());
        int piezasHechas = 0;
        while (!esSolucion(piezasTotales, piezasHechas) && !candidatos.isEmpty()){
           costo++;
            Maquina candidato = candidatos.get(0);
            while(esFactible(candidato, piezasHechas, piezasTotales)){
                solucion.add(candidato);
                piezasHechas += candidato.getPiezas();
            }
            candidatos.remove(candidato);
        }
        if (piezasHechas == piezasTotales){
            mostrarMejorSolucion(piezasTotales, solucion);
        } else {
            System.out.println("No se encontro solucion.");
        }

    }
    private boolean esFactible(Maquina candidato, int piezasHechas, int piezasTotales){
        return piezasHechas + candidato.getPiezas() <= piezasTotales;
    }
    private boolean esSolucion(int piezasTotales, int piezasHechas){
        return piezasHechas == piezasTotales;
    }
    private void mostrarMejorSolucion(int piezasTotales, List<Maquina> mejorSolucion) {
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

    public void setCosto(int costo) {
        this.costo = costo;
    }
}
