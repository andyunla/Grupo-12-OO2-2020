package dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Cliente;
import datos.Factura;

public class FacturaDao {
    private static Session session;
    private Transaction tx;
    private static FacturaDao instancia = null; // Patrón Singleton

    protected FacturaDao() {}

    public static FacturaDao getInstance() {
        if (instancia == null)
            instancia = new FacturaDao();
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

    /** Traer Factura **/
    public Factura traer(long idFactura) {
        Factura objeto = null;
        try {
            iniciaOperacion();
            objeto = (Factura) session.createQuery("from Factura f where f.idFactura = " + idFactura).uniqueResult();
        } finally {
            session.close();
        }
        return objeto;
    }

    /** Traer lista de Facturas **/
    @SuppressWarnings("unchecked")
    public List<Factura> traer() throws HibernateException {
        List<Factura> lista = null;
        try {
            iniciaOperacion();
            lista = session.createQuery("from Factura").list();
        } finally {
            session.close();
        }
        return lista;
    }

    /** Traer lista de Facturas de un Cliente **/
    public List<Factura> traer(Cliente cliente) {
        List<Factura> lista = null;
        try {
            iniciaOperacion();
            String hql = "from Factura f inner join fetch f.cliente c where c.idCliente = :idCliente";
            lista = session.createQuery(hql).setLong("idCliente", cliente.getIdCliente()).list();
        } finally {
            session.close();
        }
        return lista;
    }

    /** Traer lista de Facturas de un Empleado **/
    public List<Factura> traer(Empleado empleado) {
        List<Factura> lista = null;
        try {
            iniciaOperacion();
            String hql = "from Factura f inner join fetch f.empleado e where e.idEmplado = :idEmplado";
            lista = session.createQuery(hql).setLong("idEmplado", empleado.getIdEmpleado()).list();
        } finally {
            session.close();
        }
        return lista;
    }

    /** AGREGAR **/
    public int agregar(Factura objeto) throws HibernateException {
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
    public void actualizar(Factura objeto) throws HibernateException {
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
    public void eliminar(Factura objeto) throws HibernateException {
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
