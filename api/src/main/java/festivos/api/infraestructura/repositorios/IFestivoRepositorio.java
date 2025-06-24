package festivos.api.infraestructura.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import festivos.api.core.dominio.entidades.Festivo;

@Repository
public interface IFestivoRepositorio extends JpaRepository<Festivo, Integer> {
    @Query("SELECT f FROM Festivo f WHERE f.nombre LIKE '%'||:dato||'%'") // JPQL
    public List<Festivo> buscar(String dato);

    @Query("SELECT f FROM Festivo f WHERE f.dia = :dia AND f.mes = :mes")
    public List<Festivo> findByDiaAndMes(@Param("dia") int dia, @Param("mes") int mes);

    @Query("SELECT f FROM Festivo f WHERE f.diasPascua <> :diasPascua")
    public List<Festivo> findByDiasPascuaNot(@Param("diasPascua") int diasPascua);

}