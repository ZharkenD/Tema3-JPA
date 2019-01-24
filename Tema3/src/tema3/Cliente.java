package tema3;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "TCLIENTE")
@NamedQueries ({
    @NamedQuery (
            name = "Cliente.clientePorNif",
            query = "SELECT c "
                  + "FROM Cliente c "
                  + "WHERE c.nif=:nif"
    ),
        @NamedQuery (
            name = "Cliente.clientePorNombre",
            query = "SELECT c "
                  + "FROM Cliente c "
                  + "WHERE c.nombre like :nombre"
    ),
        @NamedQuery (
            name = "Cliente.clientePorSector",
            query = "SELECT c "
                  + "FROM Cliente c "
                  + "WHERE c.sector.id=:idSector"
    )
})
public class Cliente implements Serializable {

    // Atributos
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id = null;
    @Column(name = "nif", length = 15, unique = true, nullable = false)
    private String nif;
    @Column(name = "denominacion", length = 120)
    private String nombre;
    @ManyToOne
    private Zona zona = null;
    @OneToOne
    private Sector sector = null;

    public Cliente() {
    }

    /**
     * @param nif
     * @param nombre
     */
    public Cliente(String nif, String nombre) {
        this.nif = nif;
        this.nombre = nombre;
    }
    
    /**
     * @param id
     * @param nif
     * @param nombre
     */
    public Cliente(int id, String nif, String nombre) {
        this.id = id;
        this.nif = nif;
        this.nombre = nombre;
    }

    /**
     * Obté el codi de client
     *
     * @return el codi de client
     */
    public Integer getId() {
        return id;
    }

    /**
     * Assigna el codi de client
     *
     * @param id codi de client a assignar
     */
    protected void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obté el nif del client
     *
     * @return the nif
     */
    public String getNif() {
        return nif;
    }

    /**
     * Assigna el nif al client
     *
     * @param nif a assignar
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Obté el nom del client.
     *
     * @return nom del client
     */
    public String getNom() {
        return nombre;
    }

    /**
     * Assigna el nom al client
     *
     * @param nom el nom del client a assignar.
     */
    public void setNom(String nom) {
        this.nombre = nom;
    }

    /**
     * Obte la zona assignada al client
     *
     * @return the zona
     */
    public Zona getZona() {
        return zona;
    }

    /**
     * Assigna el client a la zona passada per paràmetre
     *
     * @param zona la zona on assignar el client
     */
    public void setZona(Zona zona) {
        if (this.zona != null) {
            this.zona.getClients().remove(this);
        }
        this.zona = zona;
        this.zona.getClients().add(this);
    }

    /**
     * Obté el sector de producció del client
     *
     * @return el sector productiu
     */
    public Sector getSector() {
        return sector;
    }

    /**
     * Assigna el sector productiu del client
     *
     * @param sector el serctor productiu a assignar
     */
    public void setSector(Sector sector) {
        this.sector = sector;
    }

    /**
     * Indica si l'objecte on es fa la crida i el que es passa per paràmetre són
     * iguals
     *
     * @param object a comparar
     * @return cert si són iguals. Fals en cas contrari.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (this.id == null && other.id != null) {
            return false;
        }
        if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
}
