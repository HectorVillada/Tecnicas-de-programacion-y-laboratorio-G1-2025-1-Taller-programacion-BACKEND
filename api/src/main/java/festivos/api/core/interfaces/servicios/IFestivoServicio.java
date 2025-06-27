package festivos.api.core.interfaces.servicios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import festivos.api.core.dominio.entidades.Festivo;
import festivos.api.core.dominio.DTOs.FechaFestivoDto;

@Service
public interface IFestivoServicio {

    List<Festivo> obtenerTodos();

    Festivo obtenerPorId(Long id);

    List<Festivo> buscar(String dato);

    Festivo agregar(Festivo festivo);

    Festivo modificar(Festivo festivo);

    boolean eliminar(Long id);

    // Consultas especiales
     
    List<Festivo> obtenerPorAño(int anio);

    boolean esFestivo(LocalDate fecha);

    String verificarFestivo(LocalDate fecha);
 
    List<FechaFestivoDto> obtenerFechasFestivasDto(int anio);
}

