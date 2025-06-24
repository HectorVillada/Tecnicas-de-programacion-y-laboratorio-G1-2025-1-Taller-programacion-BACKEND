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

    private IFestivoServicio servicio;

    public FestivoServicio(IFestivoServicio servicio){
        this.servicio = servicio;
    }

    @Autowired
    private IFestivoServicio festivoServicio;

    @GetMapping
    public ResponseEntity<List<Festivo>> obtenerTodos() {
        return ResponseEntity.ok(festivoServicio.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Festivo> obtenerPorId(@PathVariable Long id) {
        Festivo festivo = festivoServicio.obtenerPorId(id);
        if (festivo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(festivo);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Festivo>> buscar(@RequestParam String dato) {
        return ResponseEntity.ok(festivoServicio.buscar(dato));
    }

    @PostMapping
    public ResponseEntity<Festivo> agregar(@RequestBody Festivo festivo) {
        return ResponseEntity.ok(festivoServicio.agregar(festivo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Festivo> modificar(@PathVariable Long id, @RequestBody Festivo festivo) {
        festivo.setId(id);
        Festivo actualizado = festivoServicio.modificar(festivo);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = festivoServicio.eliminar(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/anio/{anio}")
    public ResponseEntity<List<Festivo>> obtenerPorAnio(@PathVariable int anio) {
        return ResponseEntity.ok(festivoServicio.obtenerPorAño(anio));
    }

    @GetMapping("/verificar")
    public ResponseEntity<String> esFestivo(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {

        boolean esFestivo = festivoServicio.esFestivo(fecha);
        String mensaje = esFestivo ? "Es festivo" : "No es festivo";
        return ResponseEntity.ok(mensaje);
    }
}
