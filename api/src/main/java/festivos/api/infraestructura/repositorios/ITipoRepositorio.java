package festivos.api.infraestructura.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import festivos.api.core.dominio.entidades.Tipo;

@Repository
public interface ITipoRepositorio extends JpaRepository<Tipo, Integer> {
    @Query("SELECT t FROM Tipo t WHERE t.nombre LIKE '%'||:dato||'%'") // JPQL
    public List<Tipo> buscarTipo(String dato);

    @Query("SELECT t FROM Tipo t WHERE t.id = :id")
    public List<Tipo> buscarId(@Param("id") Integer id);

}