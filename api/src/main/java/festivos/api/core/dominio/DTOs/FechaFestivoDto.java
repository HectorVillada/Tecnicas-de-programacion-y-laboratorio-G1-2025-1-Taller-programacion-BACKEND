package festivos.api.core.dominio.DTOs;

import java.time.LocalDate;

public class FechaFestivoDto {

    private LocalDate fecha;
    private String nombre;

    public FechaFestivoDto() {
    }

    public FechaFestivoDto(LocalDate fecha, String nombre) {
        this.fecha = fecha;
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
