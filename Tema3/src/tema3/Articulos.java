package tema3;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "TARTICULOS")
public class Articulos implements Serializable {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "description", length = 150)
    private String descripcion;

    public Articulos() {
    }

    /**
     * @param descripcion 
     */
    public Articulos(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @param id 
     * @param descripcio 
     */
    public Articulos(Integer id, String descripcio) {
        this.id = id;
        this.descripcion = descripcio;
    }

    /**
     * @return 
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param object 
     * @return 
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Articulos)) {
            return false;
        }
        Articulos other = (Articulos) object;
        if ((this.id == null && other.id != null)
                || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * @return 
     */
    @Override
    public String toString() {
        return descripcion + " (id=" + id + ")";
    }

    /**
     * @return 
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
