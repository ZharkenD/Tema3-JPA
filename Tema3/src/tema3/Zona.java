package tema3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "TZONA")
public class Zona implements Serializable {
    
    // Atributos
    @Id
    @Column(length = 30)
    private String id;
    @Column(name = "description", length = 100)
    private String descripcion;
    @OneToOne
    private Comercial comercial = null;
    @OneToMany(fetch = FetchType.LAZY)
    @OrderBy(value = "nombre")
    private List<Cliente> clientes = new ArrayList<Cliente>();
    private static final long serialVersionUID = 1L;

    protected Zona() {

    }

    /**
     * @param id
     */
    public Zona(String id) {
        this.id = id;
    }

    /**
     * @param id
     * @param descripcion
     */
    public Zona(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    /**
     * Obté el valor identificador de la zona
     *
     * @return valor identificador
     */
    public String getId() {
        return id;
    }

    /**
     * Assigna una cadena identificativa a la zona
     *
     * @param id valor a assignar
     */
    protected void setId(String id) {
        this.id = id;
    }

    /**
     * Obté la descripció de la zona
     *
     * @return the descripcio
     */
    public String getDescripcio() {
        return descripcion;
    }

    /**
     * Assigna una descripció a la zona
     *
     * @param descripcio la descripció a assignar
     */
    public void setDescripcio(String descripcio) {
        this.descripcion = descripcio;
    }

    /**
     * Obté el comercial assignat a la zona
     *
     * @return el comercial
     */
    public Comercial getComercial() {
        return comercial;
    }

    /**
     * Assigna qui serà el comercial de la zona.
     *
     * @param comercial el comercial a assignar
     */
    public void setComercial(Comercial comercial) {
        if (this.comercial != null) {
            this.comercial.setZona(null);
        }
        this.comercial = comercial;
        if (comercial.getZona().equals(this)) {
            this.comercial.setZona(this);
        }
    }

    /**
     * Obté la llista de clients assignats a la zona
     *
     * @return els clients assignats
     */
    public List<Cliente> getClients() {
        return clientes;
    }

    /**
     * Indica si l'objecte on es fa la crida i el que es passa per paràmetre són
     * iguals
     *
     * @param object a comparar
     * @return cert si són iguals. Fals en cas contrari.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Zona)) {
            return false;
        }
        Zona other = (Zona) object;
        if ((this.id == null && other.id != null)
                || (this.id != null
                && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Cadena de caràcters representativa de l'objecte on es fa la crida.
     *
     * @return Cadena de caràcters representativa de l'objecte
     */
    @Override
    public String toString() {
        return "[zona=" + id + "]";
    }
}
