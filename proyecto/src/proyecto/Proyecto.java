/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author melissaherrera
 */
public class Proyecto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final String RUTA_LIBROS = "C:\\Users\\melis\\Documents\\NetBeansProjects\\proyecto\\src\\proyecto\\Libros.txt";
        final String RUTA_LIBROS_RESERVADOS = "C:\\Users\\melis\\Documents\\NetBeansProjects\\proyecto\\src\\proyecto\\Reservados.txt";
        String[] librosExistentes = new String[1000];//app.leerArchivo(RUTA_LIBROS);
        String[] librosReservados = new String[1000];// app.leerArchivo(RUTA_LIBROS_RESERVADOS);
        String salida = "";
        String libroSolicitado;
        String libroDevuelto;
        boolean estadoReservado = false, libroExistente = false;//False cuando no esta reservado y true cuando esta reservado
        boolean estadoDevuelto;
        int numeroLibrosReservados = 0;
        JTextArea hoja = new JTextArea();
        int opcion = 1;//Inicializado en 1 para ingresar al bucle
        //obteniendo datos de los archivos de los libros existentes
        try {
            FileReader entradaLibros = new FileReader(RUTA_LIBROS);
            BufferedReader bufferLibros = new BufferedReader(entradaLibros);
            String linea = "";
            int cont = 0;
            while (linea != null) {
                linea = bufferLibros.readLine();
                if (linea != null) {
                    librosExistentes[cont] = linea;
                    cont++;
                }
            }
            entradaLibros.close();

        } catch (IOException ex) {
            System.out.println("No se ha encontrado el archivo");
        }

        //obteniendo datos de los archivos de los libros prestados
        try {
            FileReader entradaLibros = new FileReader(RUTA_LIBROS_RESERVADOS);
            BufferedReader bufferLibros = new BufferedReader(entradaLibros);
            String linea = "";
            int cont = 0;
            while (linea != null) {
                linea = bufferLibros.readLine();
                if (linea != null) {
                    librosReservados[cont] = linea;
                    cont++;
                }
            }
            entradaLibros.close();

        } catch (IOException ex) {
            System.out.println("No se ha encontrado el archivo");
        }

        //----------------
        while (opcion > 0 && opcion < 6) {

            opcion = Integer.parseInt(JOptionPane.showInputDialog("Menu:\n1.Agregar Libros\n2.Ver libros Existentes\n3.Reservar libro\n4.Ver libros prestados\n5.Devolver libros.\n6. Salir"));

            switch (opcion) {
                case 1:
                    //Bloque para agregar libros
                    String nuevoLibro = JOptionPane.showInputDialog("Ingrese el nombre del libro agregar a la biblioteca").toUpperCase();

                    //Bloque para escribir en el archivo Libros.txt
                    try {
                        FileWriter escritura = new FileWriter(RUTA_LIBROS, true);

                        for (int i = 0; i < nuevoLibro.length(); i++) {
                            escritura.write(nuevoLibro.charAt(i));
                        }
                        escritura.write("\n");
                        escritura.close();
                    } catch (IOException ex) {
                        System.out.println("No se ha podido escribir");
                    }
                    //-----

                    JOptionPane.showMessageDialog(null, "Libro Agregado exitosamente");
                    break;
                case 2:
                    //Bloque para ver libros
                    salida = "Lista de Libros\n\n";
                    librosExistentes = app.leerArchivo(RUTA_LIBROS);

                    for (int iterador = 0; iterador < librosExistentes.length; iterador++) {
                        if (librosExistentes[iterador] != null) {
                            salida += librosExistentes[iterador] + "\n";
                        }
                    }
                    hoja.setText(salida);
                    JOptionPane.showMessageDialog(null, hoja);
                    break;
                case 3:
                    //bloque para prestar libro
                    do {
                        libroSolicitado = JOptionPane.showInputDialog("Ingrese el nombre del libro que desea reservar:").toUpperCase();
                    } while (libroSolicitado == null || libroSolicitado.equals(""));

                    for (int iterador = 0; iterador < librosReservados.length; iterador++) {
                        if (librosReservados[iterador] != null) {
                            if (libroSolicitado.equals(librosReservados[iterador])) {
                                estadoReservado = true;
                            }

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

                            //Bloque para escribir en el archivo Reservados.txt
                            try {
                                FileWriter escritura = new FileWriter(RUTA_LIBROS_RESERVADOS, true);

                                for (int i = 0; i < libroSolicitado.length(); i++) {
                                    escritura.write(libroSolicitado.charAt(i));
                                }
                                escritura.write("\n");
                                escritura.close();
                            } catch (IOException ex) {
                                System.out.println("No se ha podido escribir");
                            }
                            //-----

                            JOptionPane.showMessageDialog(null, "Libros reservado satisfactoriamente");
                        } else {
                            JOptionPane.showMessageDialog(null, "Lo lamentos, libro no existente");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Lo lamentamos este libro ya ha sido reservado");
                    }

                    break;
                case 4:
                    //bloque para ver libros que han sido reservados
                    salida = "Lista de libros reservados\n\n";

                    //
                    librosReservados = new String[1000];
                    //obteniendo datos de los archivos de los libros prestados
                    try {
                        FileReader entradaLibros = new FileReader(RUTA_LIBROS_RESERVADOS);
                        BufferedReader bufferLibros = new BufferedReader(entradaLibros);
                        String linea = "";
                        int cont = 0;
                        while (linea != null) {
                            linea = bufferLibros.readLine();
                            if (linea != null) {
                                librosReservados[cont] = linea;
                                cont++;
                            }
                        }
                        entradaLibros.close();

                    } catch (IOException ex) {
                        System.out.println("No se ha encontrado el archivo");
                    }

                    for (int iterador = 0; iterador < librosReservados.length; iterador++) {
                        if (librosReservados[iterador] != null) {
                            salida += librosReservados[iterador] + "\n";
                        }
                    }
                    hoja.setText(salida);
                    JOptionPane.showMessageDialog(null, hoja);
                    break;
                case 5:
                    //Bloque para devolver libro
                    libroDevuelto = JOptionPane.showInputDialog("Ingrese el nombre del libro a devolver").toUpperCase();
                    estadoDevuelto = false;//False cuando no esta reservado y true cuando esta reservado
                    numeroLibrosReservados = 0;
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

                            //Bloque para escribir en el archivo Reservados.txt
                            try {
                                FileWriter escritura = new FileWriter(RUTA_LIBROS_RESERVADOS, false);

                                escritura.write("");

                                escritura.close();
                            } catch (IOException ex) {
                                System.out.println("No se ha podido escribir");
                            }
                            //-----

                        }
                        try {
                            FileWriter escritura = new FileWriter(RUTA_LIBROS_RESERVADOS, false);
                            for (int iterador = 0; iterador < librosReservados.length; iterador++) {
                                if (librosReservados[iterador] != null) {
                                    if (!librosReservados[iterador].equals(libroDevuelto)) {
                                        System.out.println(librosReservados[iterador]);
                                        // app.escribir(RUTA_LIBROS_RESERVADOS, librosReservados[iterador] + "\n", false);

                                        for (int i = 0; i < librosReservados[iterador].length(); i++) {
                                            escritura.write(librosReservados[iterador].charAt(i));
                                        }
                                        escritura.write("\n");

                                        //-----
                                        JOptionPane.showMessageDialog(null, "El libro " + libroDevuelto + " a sido devuelto.");
                                    }
                                }
                            }
                            escritura.close();
                        } catch (IOException ex) {
                            System.out.println("No se ha podido escribir");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Este libro no ha sido reservado");
                    }
                    break;
            }
        }
    }
}
