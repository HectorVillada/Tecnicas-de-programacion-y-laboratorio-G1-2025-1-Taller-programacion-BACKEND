package festivos.api.aplicacion.servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import festivos.api.core.dominio.entidades.Festivo;
import festivos.api.core.interfaces.servicios.IFestivoServicio;
import festivos.api.infraestructura.repositorios.IFestivoRepositorio;

@Service
public class FestivoServicio implements IFestivoServicio {

    private final IFestivoRepositorio festivoRepositorio;

    @Autowired
    public FestivoServicio(IFestivoRepositorio festivoRepositorio) {
        this.festivoRepositorio = festivoRepositorio;
    }

    @Override
    public List<Festivo> obtenerTodos() {
        return festivoRepositorio.findAll();
    }

    @Override
    public Festivo obtenerPorId(Long id) {
        return festivoRepositorio.findById(id.intValue()).orElse(null);
    }

    @Override
    public List<Festivo> buscar(String dato) {
        return festivoRepositorio.buscar(dato);
    }

    @Override
    public Festivo agregar(Festivo festivo) {
        return festivoRepositorio.save(festivo);
    }

    @Override
    public Festivo modificar(Festivo festivo) {
        if (festivoRepositorio.existsById(festivo.getId())) {
            return festivoRepositorio.save(festivo);
        }
        return null;
    }

    @Override
    public boolean eliminar(Long id) {
        int intId = id.intValue();
        if (festivoRepositorio.existsById(intId)) {
            festivoRepositorio.deleteById(intId);
            return true;
        }
        return false;
    }

    @Override
    public List<Festivo> obtenerPorAño(int anio) {
        List<Festivo> festivos = festivoRepositorio.findAll();
        LocalDate pascua = calcularPascua(anio);
        List<Festivo> result = new ArrayList<>();

        for (Festivo f : festivos) {
            LocalDate fechaFestivo = calcularFechaFestivo(f, anio, pascua);
            if (fechaFestivo != null && fechaFestivo.getYear() == anio) {
                result.add(f);
            }
        }

        return result;
    }

    @Override
    public boolean esFestivo(LocalDate fecha) {
        return verificarFestivo(fecha).startsWith("Es festivo");
    }

    public String verificarFestivo(LocalDate fecha) {
        if (fecha == null) {
            return "Fecha no válida";
        }

        int anio = fecha.getYear();
        LocalDate pascua = calcularPascua(anio);
        List<Festivo> festivos = festivoRepositorio.findAll();

        for (Festivo f : festivos) {
            LocalDate festivoCalculado = calcularFechaFestivo(f, anio, pascua);
            if (festivoCalculado != null && festivoCalculado.equals(fecha)) {
                return "Es festivo: " + f.getNombre();
            }
        }

        return "No es festivo";
    }

    private LocalDate calcularFechaFestivo(Festivo f, int anio, LocalDate pascua) {
        try {
            switch (f.getTipo().getId()) {
                case 1: // Fijo
                    return LocalDate.of(anio, f.getMes(), f.getDia());

                case 2: // Puente
                    return siguienteLunes(LocalDate.of(anio, f.getMes(), f.getDia()));

                case 3: // Pascua
                    return pascua.plusDays(f.getDiasPascua());

                case 4: // Pascua + lunes
                    return siguienteLunes(pascua.plusDays(f.getDiasPascua()));

                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    private LocalDate siguienteLunes(LocalDate fecha) {
        int diaSemana = fecha.getDayOfWeek().getValue(); // Lunes = 1
        return fecha.plusDays((8 - diaSemana) % 7);
    }

    private LocalDate calcularPascua(int anio) {
        int a = anio % 19;
        int b = anio % 4;
        int c = anio % 7;
        int d = (19 * a + 24) % 30;
        int e = (2 * b + 4 * c + 6 * d + 5) % 7;
        int dias = d + e;
        return LocalDate.of(anio, 3, 15).plusDays(dias + 7);
    }
}
