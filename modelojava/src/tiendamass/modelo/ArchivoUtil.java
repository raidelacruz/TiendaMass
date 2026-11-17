package tiendamass.modelo;

import java.io.*;

/**
 *Autor: PretelPadillaEnrique/AngieNoemiSanchez/AlessandraCondeso/KarenFaustinoGiron
 * Clase ArchivoUtil
 * Permite leer y escribir archivos de texto sin usar estructuras prohibidas.
 */
public class ArchivoUtil {

    // Leer archivo línea por línea y procesar manualmente
    public static String[] leerArchivo(String ruta) {
        String[] lineas = new String[1000]; // máximo 1000 líneas
        int contador = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea;

            while ((linea = br.readLine()) != null && contador < lineas.length) {
                lineas[contador] = linea;
                contador++;
            }

            br.close();
        } catch (IOException e) {
            System.out.println(" Error al leer archivo: " + ruta);
        }

        // Crear un nuevo arreglo con solo las líneas leídas
        String[] resultado = new String[contador];
        for (int i = 0; i < contador; i++) {
            resultado[i] = lineas[i];
        }

        return resultado;
    }

    // Escribir arreglo de líneas en archivo
    public static void escribirArchivo(String ruta, String[] lineas) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ruta));
            for (int i = 0; i < lineas.length; i++) {
                bw.write(lineas[i]);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println(" Error al leer archivo: " + ruta);
            e.printStackTrace(); //  Esto mostrará el error en consola
        }

    }
}

