package festivos.api.core.dominio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo")
public class tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuenciador_tipo")
    @SequenceGenerator(name = "secuenciador_tipo", sequenceName = "secuenciador_tipo", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "tipo", unique = true, nullable = false)
    private String nombre;

    public tipo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public tipo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
