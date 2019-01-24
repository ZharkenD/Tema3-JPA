package tema3;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Gestor {

    // Atributos
    private EntityManagerFactory emf;

    public Gestor() {

    }

    // Methods
    private void insertarCliente(Cliente cliente) {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            System.out.println("El cliente con ID : " + cliente.getId() + " y NIF : " + cliente.getNif() + " ha sido añadido a la base de datos correctamente.");
        } catch (Exception e) {
            System.out.println("El cliente con ID : " + cliente.getId() + " y NIF : " + cliente.getNif() + " no ha podido ser añadido a la base de datos.");
        } finally {
            em.close();
        }

    }

    private void modificarCliente(Cliente cliente) {
        
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
            System.out.println("El cliente con ID : " 
                    + cliente.getId() 
                    + " y NIF : " 
                    + cliente.getNif() 
                    + " ha sido modificado correctamente.");
        } catch (Exception e) {
            System.out.println("El cliente con ID : " 
                    + cliente.getId() 
                    + " y NIF : " 
                    + cliente.getNif() 
                    + " no ha podido ser modificado.");
        } finally {
            em.close();
        }
        
    }

    private void eliminarCliente(int id) {
        
        EntityManager em = emf.createEntityManager();

        try {
            // Crear cliente con id deseado
            Cliente cliente = em.getReference(Cliente.class, id);
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
            System.out.println("El cliente con ID : " 
                    + cliente.getId() 
                    + " y NIF : " 
                    + cliente.getNif() 
                    + " ha sido eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("El cliente con ID : " 
                    + id  
                    + " no ha podido ser eliminado.");
        } finally {
            em.close();
        }
        
    }

    private Cliente obtenerClientPorId(int id) {
        
        EntityManager em = emf.createEntityManager();
        
        Cliente cliente = em.find(Cliente.class, id);
        
        if (cliente != null) {
            System.out.println("Se ha encontrado el cliente con ID : "
                    + id
                    + " y NIf : " 
                    + cliente.getNif()
                    + " en la base de datos.");
        } else {
            System.out.println("No se ha encontrado el cliente con ID : "
                    + id
                    + " en la base de datos.");
        }
        
        em.close();
        
        return cliente;
        
    }

    private Cliente obtenerClientPorNif(String nif) {
        
        EntityManager em = emf.createEntityManager();
        Cliente cliente = null;
        
        try {
            
            Query query = em.createNamedQuery("Cliente.clientePorNif", Cliente.class);
            query.setParameter("nif", nif);
            cliente = (Cliente) query.getSingleResult();
            System.out.println("Se ha encontrado el cliente con ID : "
                    + cliente.getId()
                    + " y NIf : " 
                    + cliente.getNif()
                    + " en la base de datos.");
             em.close();

        } catch (Exception e) {
            
            System.out.println("No se ha encontrado el cliente con NIF : "
                    + nif
                    + " en la base de datos.");
            
        } 
        
        return cliente;
        
    }

    private List<Cliente> obtenerClientPorNombre(String nombre) {
        
        EntityManager em = emf.createEntityManager();
        List<Cliente> clientes = null;
        
        try {
            Query query = em.createNamedQuery("Cliente.clientePorNombre", Cliente.class);
            query.setParameter("nombre", "%" + nombre + "%");
            clientes = query.getResultList();
            if (!clientes.isEmpty()) {
                System.out.println("Se han encontrado " 
                    + clientes.size()
                    + " clientes con un nombre parecido a : "
                    + nombre
                    + " en la base de datos.");
            } else {
                System.out.println("No se han encontrado clientes con nombre parecido a :"
                    + nombre
                    + " en la base de datos debido a que no hay conincidencias.");
            }
            em.close();
        } catch (Exception e) {
            System.out.println("No se han encontrado clientes con nombre parecido a : "
                    + nombre
                    + " en la base de datos debido a un error.");
        } 
        
        return clientes;
        
    }

    private List<Cliente> obtenerClientPorSector(Sector sector) {
        
        EntityManager em = emf.createEntityManager();
        List<Cliente> clientes = null;
        
        try {
            Query query = em.createNamedQuery("Cliente.clientePorSector", Cliente.class);
            query.setParameter("idSector", sector.getId());
            clientes = query.getResultList();
            if (!clientes.isEmpty()) {
                System.out.println("Se han encontrado " 
                    + clientes.size()
                    + " clientes que pertenecen al sector : "
                    + sector.getId()
                    + " en la base de datos.");
            } else {
                System.out.println("No se han encontrado clientes que pertenezcan al sector : "
                    + sector.getId()
                    + " en la base de datos debido a que no hay conincidencias.");
            }
            em.close();
        } catch (Exception e) {
            System.out.println("No se han encontrado clientes que pertenezcan al sector : "
                    + sector.getId()
                    + " en la base de datos debido a un error.");
        } 
        
        return clientes;
        
    }
    
    // Main 
    public static void main(String[] args) {
        
        //Crear objeto gestor
        Gestor gestor = new Gestor();

        // Creacion de gestor de entidades.
        gestor.emf = Persistence.createEntityManagerFactory("Tema3PU");
        
        // Prueba insertar clientes 
        // Crear objetos de tipo cliente
        Cliente cA = new Cliente(1, "43221163T", "Angel");
        Cliente cB = new Cliente(2, "43481820K", "Christian");
        Cliente cC = new Cliente(3, "66666666T", "Pardal");
        // Insertar clientes en la base de datos
        gestor.insertarCliente(cA);
        gestor.insertarCliente(cB);
        gestor.insertarCliente(cC);
        //*/
        
        /*/ Prueba de excepciones al insertar clientes
        // Cliente con nif existente
        Cliente cA2 = new Cliente("43221163T", "Angel2");
        gestor.insertarCliente(cA2);
        // Cliente con nif invalido
        Cliente cB2 = new Cliente("43481820K43481820K3", "Eustaquio");
        gestor.insertarCliente(cB2); 
        //*/
        
        // Prueba modificar clientes
        // Para modificar clientes debemos indicar el ID del
        // cliente a modificar cuando lo creemos.
        Cliente cA3 = new Cliente(1, "123456789A", "Tobi");
        gestor.modificarCliente(cA3);
        // Modificar un cliente que no existe pero con campos validos.
        // Esto insertara un nuevo cliente con los datos que le hemos dado.
        Cliente cB3 = new Cliente(4, "987654321A", "Perry");
        gestor.modificarCliente(cB3);
        //*/
        
        /*/ Prueba de excepciones al modificar clientes
        // Cliente con nif existente
        Cliente cA4 = new Cliente(1, "43481820K", "Alvaro");
        gestor.modificarCliente(cA4);
        // Cliente con nif invalido
        Cliente cB4 = new Cliente(4, "43481820K43481820K", "Christian");
        gestor.modificarCliente(cB4);
        //*/
        
        // Prueba eliminar clientes por id
        gestor.eliminarCliente(3);
        //*/
        
        /*/ Prueba de excepciones al eliminar clientes por id
        // Cliente con id inexistente
        gestor.eliminarCliente(777);
        //*/
        
        // Prueba buscar clientes por id
        Cliente cBuscadoPorId = gestor.obtenerClientPorId(1);
        //*/
        
        /*/ Prueba de excepciones al buscar clientes por id
        // Cliente con id inexistente
        Cliente cBuscadoPorId2 = gestor.obtenerClientPorId(777);
        //*/
        
        // Prueba buscar clientes por nif
        Cliente cBuscadoPorNif = gestor.obtenerClientPorNif("123456789A");
        //*/
        
        /*/ Prueba de excepciones al buscar clientes por nif
        // Cliente con nif inexistente
        Cliente cBuscadoPorNif2 = gestor.obtenerClientPorNif("77777777A");
        //*/
        
        // Prueba buscar clientes por nombre
        List<Cliente> cBuscadosPorNombre = gestor.obtenerClientPorNombre("Christian");
        //*/
        
        /*/ Prueba de excepciones al buscar clientes por nombre
        // Clientes con nombre inexistente
        List<Cliente> cBuscadosPorNombre2 = gestor.obtenerClientPorNombre("Ramon");
        //*/
        
        // Prueba buscar clientes por sector
        Sector sector = new Sector("1");
        Cliente clienteSector1 = new Cliente(1, "123456789A", "Tobi");
        clienteSector1.setSector(sector);
        gestor.modificarCliente(clienteSector1);
        List<Cliente> cBuscadosPorSector = gestor.obtenerClientPorSector(sector);
        //*/
        
        /*/ Prueba de excepciones al buscar clientes por sector
        // Clientes con sector inexistente
        Sector sector2 = new Sector("2");
        List<Cliente> cBuscadosPorSector2 = gestor.obtenerClientPorSector(sector2);
        //*/
        
    }

}
