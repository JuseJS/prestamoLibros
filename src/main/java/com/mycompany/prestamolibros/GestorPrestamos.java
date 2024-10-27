/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prestamolibros;

/**
 *
 * @author navarro
 */
class GestorPrestamos {
    public boolean prestarLibros(Libro libro1, Libro libro2, String nombreEstudiante) {
        Libro primerLibro, segundoLibro;
        if (libro1.getId() < libro2.getId()) {
            primerLibro = libro1;
            segundoLibro = libro2;
        } else {
            primerLibro = libro2;
            segundoLibro = libro1;
        }

        synchronized (primerLibro) {
            synchronized (segundoLibro) {
                System.out.printf("%s ha obtenido los libros %s (ID %d) y %s (ID %d).%n", nombreEstudiante, primerLibro.getTitulo(), primerLibro.getId(), segundoLibro.getTitulo(), segundoLibro.getId());
                return true;
            }
        }
    }
}
