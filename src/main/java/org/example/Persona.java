package org.example;
import java.time.LocalDate;
public class Persona {
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaNacimiento;
    private float altura;
    private float peso;

    private Persona(String nombre, String apellido, LocalDate fechaNacimiento, String dni, float altura, float peso) {
        this.nombre = normalizarTexto(nombre);
        this.apellido = normalizarTexto(apellido);
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
        this.altura = altura;
        this.peso = peso;
    }

    public static Persona create(String nombre, String apellido, LocalDate fechaNacimiento, String dni, float altura, float peso) {
        return new Persona(nombre, apellido, fechaNacimiento, dni, altura, peso);
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDni() { return dni; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public float getAltura() { return altura; }
    public float getPeso() { return peso; }

    // Normaliza un texto: " jUAn   carLOS " -> "Juan Carlos"
    private static String normalizarTexto(String texto) {
        if (texto == null || texto.trim().isEmpty()) return "";

        String[] palabras = texto.trim().split("\\s+");
        StringBuilder resultado = new StringBuilder();

        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                // Primera letra mayúscula
                resultado.append(palabra.substring(0, 1).toUpperCase());
                // El resto minúsculas (solo si tiene más de 1 letra)
                if (palabra.length() > 1) {
                    resultado.append(palabra.substring(1).toLowerCase());
                }
                resultado.append(" ");
            }
        }

        return resultado.toString().trim();
    }

}
