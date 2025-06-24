package festivos.api.core.interfaces.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import festivos.api.core.dominio.entidades.Tipo;

@Service
public interface ITipoServicio {

    List<Tipo> obtenerTodos();

    Tipo obtenerPorId(int id);

    List<Tipo> buscar(String dato);

    Tipo agregar(Tipo tipo);

    Tipo modificar(Tipo tipo);

    boolean eliminar(int id);
}
