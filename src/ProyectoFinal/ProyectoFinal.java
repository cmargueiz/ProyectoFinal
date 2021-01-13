package ProyectoFinal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

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

    public void escribir(String reservas, String libroReservado, boolean sobreEscritura) {
        try {
            FileWriter escritura = new FileWriter(reservas, sobreEscritura);

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
        final String RUTA_CARNET = "/home/melissaherrera/NetBeansProjects/JavaApplication1/src/ProyectoFinal/ListaCanet.txt";
        ProyectoFinal app = new ProyectoFinal();
        String[] librosExistentes = app.leerArchivo(RUTA_LIBROS);
        String[] librosReservados = app.leerArchivo(RUTA_LIBROS_RESERVADOS);
        String[] carnetValidos = app.leerArchivo(RUTA_CARNET);
        String salida = "";
        String libroSolicitado;
        String libroDevuelto;
        boolean estadoReservado = false, libroExistente = false;//False cuando no esta reservado y true cuando esta reservado
        boolean estadoDevuelto;
        int numeroLibroReservados = 0;
        JTextArea hoja = new JTextArea();
        int opcion = 1;//Inicializado en 1 para ingresar al bucle
        String carneIngreso;
        boolean carneConfirmado = false;

        do {
            carneIngreso = JOptionPane.showInputDialog("Ingrese su carnet de estudiante para ingresar al sistema bibliotecario").toUpperCase();

            for (int iterador = 0; iterador < carnetValidos.length; iterador++) {
                if (carnetValidos[iterador] != null) {
                    if (carneIngreso.endsWith(carnetValidos[iterador])) {
                        carneConfirmado = true;
                    }
                }

            }

            if (!carneConfirmado) {
                JOptionPane.showMessageDialog(hoja, "Ingrese un carnet valido");
                carneConfirmado = false;
            }

        } while (carneIngreso.equals("") || carneConfirmado == false);

        while (opcion > 0 && opcion < 5) {

            opcion = Integer.parseInt(JOptionPane.showInputDialog("Menu:\n1.Ver libros Existentes\n2. Reservar libro\n3.Ver libros prestados\n4.Devolver libros.\n5. Salir"));

            switch (opcion) {
                case 1:
                    //Bloque para ver libros

                    for (int iterador = 0; iterador < librosExistentes.length; iterador++) {
                        if (librosExistentes[iterador] != null) {
                            salida += librosExistentes[iterador] + "\n";

                        }
                    }
                    hoja.setText(salida);
                    JOptionPane.showMessageDialog(null, hoja);

                    break;
                case 2:
                    //bloque para prestar libro
                    libroSolicitado = JOptionPane.showInputDialog("Ingrese el nombre del libro que desea reservar:").toUpperCase();

                    for (int iterador = 0; iterador < librosReservados.length; iterador++) {
                        if (librosReservados[iterador] != null) {
                            if (libroSolicitado.equals(librosReservados[iterador])) {
                                estadoReservado = true;
                            }
                            numeroLibroReservados++;
                        }

                    }
                    if (!estadoReservado) {
                        for (int iterador = 0; iterador < librosExistentes.length; iterador++) {
                            if (librosExistentes[iterador] != null) {
                                if (libroSolicitado.equals(librosExistentes[iterador].toUpperCase())) {
                                    libroExistente = true;
                                }

                            }
                        }

                        if (libroExistente) {
                            //librosReservados[numeroLibroReservados] = libroSolicitado;

                            app.escribir(RUTA_LIBROS_RESERVADOS, libroSolicitado + "\n", true);
                            JOptionPane.showMessageDialog(null, "Libros reservado satisfactoriamente");
                        } else {
                            JOptionPane.showMessageDialog(null, "Lo lamentos, libro no existente");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Lo lamentamos este libro ya ha sido reservado");
                    }

                    break;
                case 3:
                    //bloque para ver libros que han sido reservados
                    salida = "";
                    librosReservados = app.leerArchivo(RUTA_LIBROS_RESERVADOS);
                    for (int iterador = 0; iterador < librosReservados.length; iterador++) {
                        if (librosReservados[iterador] != null) {
                            salida += librosReservados[iterador] + "\n";
                        }
                    }

                    hoja.setText(salida);
                    JOptionPane.showMessageDialog(null, hoja);

                    break;
                case 4:

                    //Bloque para devolver libro
                    libroDevuelto = JOptionPane.showInputDialog("Ingrese el codigo del libro devuelve").toUpperCase();
                    estadoDevuelto = false;//False cuando no esta reservado y true cuando esta reservado
                    int numeroLibrosReservados = 0;
                    for (int iterador = 0; iterador < librosReservados.length; iterador++) {
                        if (librosReservados[iterador] != null) {
                            if (libroDevuelto.equals(librosReservados[iterador])) {
                                estadoDevuelto = true;
                                numeroLibrosReservados++;
                            }

                        }
                    }
                    if (estadoDevuelto) {
                        if (numeroLibrosReservados == 1) {
                            app.escribir(RUTA_LIBROS_RESERVADOS, "", false);
                        }

                        for (int iterador = 0; iterador < librosReservados.length; iterador++) {
                            if (librosReservados[iterador] != null) {
                                if (!librosReservados[iterador].equals(libroDevuelto)) {
                                    System.out.println(librosReservados[iterador]);
                                    app.escribir(RUTA_LIBROS_RESERVADOS, librosReservados[iterador] + "\n", false);
                                }

                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Este libro no ha sido reservado");
                    }
                    break;

            }

        }
    }

}
