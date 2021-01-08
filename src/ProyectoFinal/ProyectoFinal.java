package ProyectoFinal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author melissaherrera
 */
public class ProyectoFinal {

    public String[] leerArchivo(String ruta_libros) {

        String[] libros = new String[1000];
        try {
            FileReader entrada = new FileReader(ruta_libros);
            BufferedReader buffer = new BufferedReader(entrada);
            String linea = "";
            int cont = 0;
            while (linea != null) {

                linea = buffer.readLine();
                if (linea != null) {
                    libros[cont] = linea;
                    cont++;
                }
            }
            entrada.close();

        } catch (IOException ex) {
            System.out.println("No se ha encontrado el archivo");
            Logger.getLogger(ProyectoFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return libros;
    }

    public String[] leer(String reservas) {
        String[] letras = new String[1000];
        try {
            FileReader entrada = new FileReader(reservas);
            int c = 0, cont = 0;
            String cadena = "";
            while (c != -1) {
                c = entrada.read();
                char letra = (char) c;
                if (c != -1) {
                    letras[cont] = Character.toString(letra);
                    cont++;
                }
            }
            entrada.close();

        } catch (IOException ex) {
            System.out.println("No se ha encontrado el archivo");
            Logger.getLogger(ProyectoFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return letras;
    }

    public void escribir(String reservas, String libroReservado) {
        try {
            FileWriter escritura = new FileWriter(reservas, true);

            for (int i = 0; i < libroReservado.length(); i++) {
                escritura.write(libroReservado.charAt(i));
            }

            escritura.close();
        } catch (IOException ex) {
            Logger.getLogger(ProyectoFinal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        final String RUTA_LIBROS = "/home/melissaherrera/NetBeansProjects/JavaApplication1/src/ProyectoFinal/Libros.txt";
        final String RUTA_LIBROS_RESERVADOS = "/home/melissaherrera/NetBeansProjects/JavaApplication1/src/ProyectoFinal/Reservados.txt";
        ProyectoFinal app = new ProyectoFinal();
        String[] letras = app.leerArchivo(RUTA_LIBROS);
        for (int i = 0; i < letras.length; i++) {
            if (letras[i] != null) {
                System.out.println(letras[i]);
            }

        }

        app.escribir(RUTA_LIBROS_RESERVADOS, "\n50");
        String[] reservas = app.leerArchivo(RUTA_LIBROS_RESERVADOS);
        for (int i = 0; i < reservas.length; i++) {
            if (reservas[i] != null) {
                System.out.println(reservas[i]);
            }

        }
    }

}
