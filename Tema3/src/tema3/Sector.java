package tema3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "TSECTOR")
public class Sector implements Serializable {

    // Atributos
    @Id
    @Column(length = 30)
    private String id;
    @Column(length = 100)
    private String descripcion;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Articulos> articulos = new ArrayList<Articulos>();

    protected Sector() {
    }

    /**
     * @param id
     */
    public Sector(String id) {
        this.id = id;
    }

    /**
     * @param id
     * @param descripcion
     */
    public Sector(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    /**
     * @param object 
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Sector)) {
            return false;
        }
        Sector other = (Sector) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "Sector[ id=" + id + " ]";
    }

    /**
     * @return
     */
    public String getId() {
        return id;
    }

    /**

     * @param id valor a assignar
     */
    protected void setId(String id) {
        this.id = id;
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

    /**
     * @return
     */
    public List<Articulos> getArticulos() {
        return articulos;
    }

}
