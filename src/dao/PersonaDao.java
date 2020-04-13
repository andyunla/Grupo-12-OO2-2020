package dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Cliente;
import datos.Empleado;
import datos.Persona;

public class PersonaDao {
    private static Session session;
    private Transaction tx;
    private static PersonaDao instancia = null; // Patrón Singleton

    protected PersonaDao() {}

    public static PersonaDao getInstance() {
        if (instancia == null)
            instancia = new PersonaDao();
        return instancia;
    }

    /**************************************************************/
    /** INICIO DE session **/
    protected void iniciaOperacion() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    /** MANEJO DE EXCEPCION DE HIBERNATE **/
    protected void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("ERROR en la capa de acceso a datos", he);
    }
    /******************************************************************************/

    /** Traer Persona **/
    public Persona traer(long idPersona) {
        Persona objeto = null;
        try {
            iniciaOperacion();
            objeto = (Persona) session.createQuery("from Persona p where p.idPersona = " + idPersona).uniqueResult();
        } finally {
            session.close();
        }
        return objeto;
    }

    /** Traer Persona **/
    public Persona traer(int dni) {
        Persona objeto = null;
        try {
            iniciaOperacion();
            objeto = (Persona) session.createQuery("from Persona p where p.dni = " + dni).uniqueResult();
        } finally {
            session.close();
        }
        return objeto;
    }

    /** Traer Cliente **/
    public Persona traerCliente(int nroCliente) {
        Persona objeto = null;
        try {
            iniciaOperacion();
            Query query = session.createQuery("from Cliente c where c.nroCliente = :nroCliente");
            query.setParameter("nroCliente", nroCliente);
            objeto = (Persona) query.uniqueResult();
        } finally {
            session.close();
        }
        return objeto;
    }

    /** Traer Empleado **/
    public Persona traerEmpleado(int legajo) {
        Persona objeto = null;
        try {
            iniciaOperacion();
            objeto = (Persona) session.createQuery("from Empleado e where e.legajo = " + legajo).uniqueResult();
        } finally {
            session.close();
        }
        return objeto;
    }

    /** Traer lista de Personas **/
    @SuppressWarnings("unchecked")
    public List<Persona> traer() throws HibernateException {
        List<Persona> lista = null;
        try {
            iniciaOperacion();
            lista = session.createQuery("from Persona").list();
        } finally {
            session.close();
        }
        return lista;
    }

    /** Traer lista de Clientes **/
    @SuppressWarnings("unchecked")
    public List<Cliente> traerClientes() throws HibernateException {
        List<Cliente> lista = null;
        try {
            iniciaOperacion();
            lista = session.createQuery("from Cliente").list();
        } finally {
            session.close();
        }
        return lista;
    }

    /** Traer lista de Empleados **/
    @SuppressWarnings("unchecked")
    public List<Empleado> traerEmpleados() throws HibernateException {
        List<Empleado> lista = null;
        try {
            iniciaOperacion();
            lista = session.createQuery("from Empleado").list();
        } finally {
            session.close();
        }
        return lista;
    }

    /** AGREGAR **/
    public int agregar(Persona objeto) {
        int id = 0;
        try {
            iniciaOperacion();
            id = Integer.parseInt(session.save(objeto).toString());
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return id;
    }

    /** ACTUALIZAR **/
    public void actualizar(Persona objeto) throws HibernateException {
        try {
            iniciaOperacion();
            session.update(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
    }

    /** ELIMINAR **/
    public void eliminar(Persona objeto) throws HibernateException {
        try {
            iniciaOperacion();
            session.delete(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
    }
}
