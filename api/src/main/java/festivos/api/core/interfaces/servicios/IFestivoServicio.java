package festivos.api.core.interfaces.servicios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import festivos.api.core.dominio.entidades.Festivo;

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
}
/*{

    @Autowired
    private IFestivoRepositorio festivoRepositorio;

    @Override
    public String verificarFestivo(LocalDate fecha) {
        if (fecha == null || !esFechaValida(fecha)) {
            return "Fecha no válida";
        }

        int dia = fecha.getDayOfMonth();
        int mes = fecha.getMonthValue();
        int anio = fecha.getYear();

        // Festivos fijos o puente (tipo 1 y 2)
        List<Festivo> festivosFijos = festivoRepositorio.findByDiaAndMes(dia, mes);
        for (Festivo f : festivosFijos) {
            LocalDate festivoCalculado = calcularFechaFestivo(f, anio);
            if (festivoCalculado != null && festivoCalculado.equals(fecha)) {
                return "Es festivo: " + f.getNombre();
            }
        }

        // Festivos pascuales
        List<Festivo> pascuaFestivos = festivoRepositorio.findByDiasPascuaNot(0);
        for (Festivo f : pascuaFestivos) {
            LocalDate festivoCalculado = calcularFechaFestivo(f, anio);
            if (festivoCalculado != null && festivoCalculado.equals(fecha)) {
                return "Es festivo: " + f.getNombre();
            }
        }

        return "No es festivo";
    }

    private LocalDate calcularFechaFestivo(Festivo f, int year) {
        try {
            switch (f.getTipo().getId().intValue()) {
                case 1: // Fijo
                    return LocalDate.of(year, f.getMes(), f.getDia());

                case 2: // Puente festivo
                    return siguienteLunes(LocalDate.of(year, f.getMes(), f.getDia()));

                case 3: // Basado en Pascua
                    return calcularPascua(year).plusDays(f.getDiasPascua());

                case 4: // Pascua + lunes
                    return siguienteLunes(calcularPascua(year).plusDays(f.getDiasPascua()));

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

        LocalDate base = LocalDate.of(anio, 3, 15);
        return base.plusDays(dias + 7);
    }

    private boolean esFechaValida(LocalDate fecha) {
        return fecha != null;
    }
}
*/