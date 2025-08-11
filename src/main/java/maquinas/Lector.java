package maquinas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lector {

    private List<Maquina> maquinas;
    private int piezasTotales;

    public Lector(){
        this.maquinas = new ArrayList<>();
        this.piezasTotales = 0;
    }

    public void leerArchivo(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea = br.readLine();
            piezasTotales = Integer.parseInt(linea.trim());

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    String nombre = partes[0].trim();
                    int piezas = Integer.parseInt(partes[1].trim());
                    maquinas.add(new Maquina(nombre, piezas));
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    public List<Maquina> getMaquinas() {
        return maquinas;
    }

    public int getPiezasTotales() {
        return piezasTotales;
    }
}
