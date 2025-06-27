package festivos.api.presentacion.controladores;

import festivos.api.aplicacion.servicios.TipoServicio;
import festivos.api.core.dominio.entidades.Tipo;
import festivos.api.core.interfaces.servicios.IFestivoServicio;
import festivos.api.core.interfaces.servicios.ITipoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
public class TipoControlador {

    private ITipoServicio servicio;

    public TipoServicio(ITipoServicio servicio){
        this.servicio = servicio;
    }

    @Autowired
    private ITipoServicio tipoServicio;

    @GetMapping
    public ResponseEntity<List<Tipo>> obtenerTodos() {
        List<Tipo> tipos = tipoServicio.obtenerTodos();
        return ResponseEntity.ok(tipos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tipo> obtenerPorId(@PathVariable int id) {
        Tipo tipo = tipoServicio.obtenerPorId(id);
        if (tipo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tipo);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Tipo>> buscar(@RequestParam String dato) {
        List<Tipo> resultados = tipoServicio.buscar(dato);
        return ResponseEntity.ok(resultados);
    }

    @PostMapping
    public ResponseEntity<Tipo> agregar(@RequestBody Tipo tipo) {
        Tipo creado = tipoServicio.agregar(tipo);
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tipo> modificar(@PathVariable int id, @RequestBody Tipo tipo) {
        tipo.setId(id);
        Tipo actualizado = tipoServicio.modificar(tipo);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        boolean eliminado = tipoServicio.eliminar(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
