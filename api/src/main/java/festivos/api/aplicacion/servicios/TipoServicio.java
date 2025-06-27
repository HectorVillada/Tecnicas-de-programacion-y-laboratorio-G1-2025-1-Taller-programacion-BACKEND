package festivos.api.aplicacion.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import festivos.api.core.dominio.entidades.Tipo;
import festivos.api.core.interfaces.servicios.ITipoServicio;
import festivos.api.infraestructura.repositorios.ITipoRepositorio;

@Service
public class TipoServicio implements ITipoServicio {

    private final ITipoRepositorio repositorio;

    @Autowired
    public TipoServicio(ITipoRepositorio repositorio){
        this.repositorio = repositorio;
    }

    @Override
    public List<Tipo> obtenerTodos() {
        return repositorio.findAll();
    }

    @Override
    public Tipo obtenerPorId(int id) {
        Optional<Tipo> optional = repositorio.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<Tipo> buscar(String dato) {
        return repositorio.buscarTipo(dato);
    }

    @Override
    public Tipo agregar(Tipo tipo) {
        return repositorio.save(tipo);
    }

    @Override
    public Tipo modificar(Tipo tipo) {
        if (repositorio.existsById(tipo.getId())) {
            return repositorio.save(tipo);
        }
        return null;
    }

    @Override
    public boolean eliminar(int id) {
        if (repositorio.existsById(id)) {
            repositorio.deleteById(id);
            return true;
        }
        return false;
    }

}