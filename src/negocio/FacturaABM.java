package negocio;

import java.util.List;
import dao.FacturaDao;
import datos.Cliente;
import datos.Empleado;

public class FacturaABM {
    FacturaDao dao = FacturaDao.getInstance();
    private static FacturaABM instancia = null;

    protected FacturaABM() {}

    public static FacturaABM getInstance() {
        if (instancia == null) {
            instancia = new FacturaABM();
        }
        return instancia;
    }

    /****   Traer Factura   ****/
    public Factura traer(long idFactura) {
        return dao.traer(idFactura);
    }

    /****   Traer Facturas de un cliente   ****/
    public List<Factura> traer() {
        return dao.traer();
    }

    /****   Traer Facturas de un cliente   ****/
    public List<Factura> traer(Cliente objeto) {
        return dao.traer(objeto);
    }

    /****   Traer Facturas de un empleado   ****/
    public List<Factura> traer(Empleado objeto) {
        return dao.traer(objeto);
    }

    /****   Agregar Factura   ****/
    public long agregar(Cliente cliente, Chango chango, LocalDate fechaFactura, double costeTotal, Empleado empleado, Local local) {
        Factura obj = new Factura(cliente, chango, fechaFactura, costeTotal, empleado, local);
        return dao.agregar(obj);
    }

    /****   MODIFICAR   ****/
    public void modificar(Factura obj) throws Exception {
        if(traer(obj.getIdFactura()) == null) {
            throw new Exception("No se puede modificar a la factura porque no existe.");
        }
        dao.actualizar(obj);
    }
    
    /****   ELIMINAR   ****/
    public void eliminar(long idPersona) throws Exception {
        Persona obj = dao.traer(idPersona);
        if(obj == null) {
            throw new Exception("No existe el cliente a eliminar");
        }
        dao.eliminar(obj);
    }
}
