package maquinas;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Lector lector = new Lector();
        lector.leerArchivo("config.txt");
        List<Maquina> maquinas = lector.getMaquinas();
        int piezasTotales = lector.getPiezasTotales();

        BackTracking backTracking = new BackTracking(maquinas);
        backTracking.asignarMaquinas(piezasTotales);
        System.out.println("---------------------------------------");
        Greedy greedy = new Greedy(maquinas);
        greedy.ProducirPiezas(piezasTotales);
    }
}