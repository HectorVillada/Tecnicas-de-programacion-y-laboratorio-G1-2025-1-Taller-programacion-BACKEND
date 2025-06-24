package festivos.api.aplicacion.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import festivos.api.core.dominio.entidades.Tipo;
import festivos.api.core.interfaces.servicios.ITipoServicio;
import festivos.api.infraestructura.repositorios.ITipoRepositorio;

@Service
public class TipoServicio implements ITipoServicio {

    private ITipoRepositorio repositorio;

    public TipoServicio(ITipoRepositorio repositorio){
        this.repositorio = repositorio;
    }

    @Override
    public List<Tipo> obtenerTodos() {
        return repositorio.findAll();
    }

    @Override
    public Tipo obtenerPorId(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPorId'");
    }

    @Override
    public List<Tipo> buscar(String dato) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscar'");
    }

    @Override
    public Tipo agregar(Tipo tipo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregar'");
    }

    @Override
    public Tipo modificar(Tipo tipo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public boolean eliminar(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

}
