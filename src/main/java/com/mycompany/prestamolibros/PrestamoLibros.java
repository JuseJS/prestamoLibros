/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prestamolibros;

/**
 *
 * @author navarro
 */
public class PrestamoLibros {
    public static void main(String[] args) {
        final int LIMITE_LIBROS = 4;
        
        Libro[] libros = new Libro[9];
        for (int i = 0; i < libros.length; i++) {
            libros[i] = new Libro(i + 1, "Libro " + (i + 1));
        }

        GestorPrestamos gestor = new GestorPrestamos();

        Thread[] estudiantes = new Thread[4];
        estudiantes[0] = new Thread(new Estudiante("Estudiante 1", libros, gestor, LIMITE_LIBROS));
        estudiantes[1] = new Thread(new Estudiante("Estudiante 2", libros, gestor, LIMITE_LIBROS));
        estudiantes[2] = new Thread(new Estudiante("Estudiante 3", libros, gestor, LIMITE_LIBROS));
        estudiantes[3] = new Thread(new Estudiante("Estudiante 4", libros, gestor, LIMITE_LIBROS));

        for (Thread estudiante : estudiantes) {
            estudiante.start();
        }
    }
}
