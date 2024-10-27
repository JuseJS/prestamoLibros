/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prestamolibros;

import java.util.Random;

/**
 *
 * @author navarro
 */
class Estudiante implements Runnable {
    private final String nombre;
    private final Libro[] libros;
    private final GestorPrestamos gestor;
    private final Random random;
    private int librosObtenidos;
    private final int limiteLibros;
    private static int estudiantesTerminados = 0;
    private static final Object lock = new Object();

    public Estudiante(String nombre, Libro[] libros, GestorPrestamos gestor, int limiteLibros) {
        this.nombre = nombre;
        this.libros = libros;
        this.gestor = gestor;
        this.random = new Random();
        this.librosObtenidos = 0;
        this.limiteLibros = limiteLibros;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int primerLibro = random.nextInt(libros.length);
                int segundoLibro;
                do {
                    segundoLibro = random.nextInt(libros.length);
                } while (segundoLibro == primerLibro);

                Libro libro1 = libros[primerLibro];
                Libro libro2 = libros[segundoLibro];

                System.out.printf("%s está intentando obtener los libros %s (ID %d) y %s (ID %d).%n", nombre, libro1.getTitulo(), libro1.getId(), libro2.getTitulo(), libro2.getId());

                if (gestor.prestarLibros(libro1, libro2, nombre)) {
                    // Utilizar los libros por un tiempo aleatorio entre 1 y 3 horas (minutos en lugar de horas)
                    int tiempoUso = random.nextInt(3) + 1;
                    Thread.sleep(tiempoUso * 1000L);
                    System.out.printf("%s ha terminado de usar los libros %s (ID %d) y %s (ID %d) tras %d minutos.%n", nombre, libro1.getTitulo(), libro1.getId(), libro2.getTitulo(), libro2.getId(), tiempoUso);

                    librosObtenidos += 2;
                    System.out.printf("%s ha obtenido un total de %d libros.%n", nombre, librosObtenidos);

                    if (librosObtenidos >= limiteLibros) {
                        synchronized (lock) {
                            estudiantesTerminados++;
                            System.out.printf("%s ha obtenido un total de %d libros y ha terminado.%n", nombre, librosObtenidos);
                            if (estudiantesTerminados >= 4) {
                                System.out.println("Todos los estudiantes han terminado.");
                                return;
                            }
                        }
                        return;
                    }
                }

                int tiempoDescanso = random.nextInt(2) + 1;
                System.out.printf("%s está descansando durante %d minutos.%n", nombre, tiempoDescanso);
                Thread.sleep(tiempoDescanso * 1000L);
            }
        } catch (InterruptedException e) {
            System.out.printf("%s ha sido interrumpido.%n", nombre);
        }
    }
}
