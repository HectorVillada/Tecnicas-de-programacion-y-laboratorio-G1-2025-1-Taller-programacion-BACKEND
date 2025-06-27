package festivos.api.presentacion.controladores;

import festivos.api.core.dominio.entidades.Festivo;
import festivos.api.core.interfaces.servicios.IFestivoServicio;
import festivos.api.infraestructura.repositorios.IFestivoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/festivos")
public class FestivoControlador {

    private final IFestivoServicio servicio;

    @Autowired
    public FestivoControlador(IFestivoServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public ResponseEntity<List<Festivo>> obtenerTodos() {
        return ResponseEntity.ok(servicio.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Festivo> obtenerPorId(@PathVariable Long id) {
        Festivo festivo = servicio.obtenerPorId(id);
        if (festivo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(festivo);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Festivo>> buscar(@RequestParam String dato) {
        return ResponseEntity.ok(servicio.buscar(dato));
    }

    @PostMapping
    public ResponseEntity<Festivo> agregar(@RequestBody Festivo festivo) {
        return ResponseEntity.ok(servicio.agregar(festivo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Festivo> modificar(@PathVariable Long id, @RequestBody Festivo festivo) {
        festivo.setId(id);
        Festivo actualizado = servicio.modificar(festivo);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = servicio.eliminar(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/anio/{anio}")
    public ResponseEntity<List<Festivo>> obtenerPorAnio(@PathVariable int anio) {
        return ResponseEntity.ok(servicio.obtenerPorAño(anio));
    }

    @GetMapping("/verificar")
    public ResponseEntity<String> esFestivoQueryParam(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        String resultado = servicio.verificarFestivo(fecha);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/verificar/{anio}/{mes}/{dia}")
    public ResponseEntity<String> verificarFestivoPath(
            @PathVariable int anio,
            @PathVariable int mes,
            @PathVariable int dia) {
        try {
            LocalDate fecha = LocalDate.of(anio, mes, dia);
            String resultado = servicio.verificarFestivo(fecha);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.ok("Fecha no válida");
        }
    }
}
