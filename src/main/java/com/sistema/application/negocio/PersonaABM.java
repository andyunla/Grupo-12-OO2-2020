package negocio;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

import dao.PersonaDao;
import datos.Cliente;
import datos.Empleado;
import datos.Local;
import datos.Persona;


public class PersonaABM {
    PersonaDao dao = PersonaDao.getInstance();
    private static PersonaABM instancia = null;

    protected PersonaABM() {}

    public static PersonaABM getInstance() {
        if (instancia == null) {
            instancia = new PersonaABM();
        }
        return instancia;
    }

    /****   Traer Persona   ****/
    public Persona traer(long idPersona) {
        return dao.traer(idPersona);
    }

    /****   Traer Persona   ****/
    public Persona traer(int dni) {
        return dao.traer(dni);
    }

    /****   Traer Cliente   ****/
    public Cliente traerCliente(int nroCliente) {
        return (Cliente) dao.traerCliente(nroCliente);
    }
    
    /****   Traer Empleado  ****/
    public Empleado traerEmpleado(int legajo){
        return (Empleado) dao.traerEmpleado(legajo);
    }

    public List<Persona> traer(){
        return dao.traer();
    }

    public List<Cliente> traerClientes(){
        return dao.traerClientes();
    }

    public List<Empleado> traerEmpleados() {
        return dao.traerEmpleados();
    }
    
    /****   Agregar Persona   ****/
    public long agregar(long idPersona) throws Exception {
        if(traer(idPersona) != null) {
            throw new Exception("Ya existe una persona con este ID");
        }
        Persona obj = new Persona(idPersona);
        return dao.agregar(obj);
    }
    
    /****   Agregar Cliente   ****/
    public int agregar(long idPersona, String nombre, String apellido, int dni, LocalDate fechaNacimiento, 
                       String email, int nroCliente) throws Exception {
        if(traerCliente(nroCliente) != null) {
            throw new Exception("Ya existe un cliente con este número de cliente: " + nroCliente);
        }
        if(traer(idPersona) != null){
            throw new Exception("Ya existe una persona con este ID: " + idPersona);
        }
        if(traer(dni) != null){
            throw new Exception("Ya existe una persona con este DNI: " + dni);
        }
        Cliente obj = new Cliente(idPersona, nombre, apellido, dni, fechaNacimiento, email, nroCliente);
        return dao.agregar(obj);
    }
    
    /****   Agregar Empleado   ****/
    public int agregar(long idPersona, String nombre, String apellido, int dni, LocalDate fechaNacimiento, 
                       int legajo, LocalTime horaDesde, LocalTime horaHasta, double sueldoBasico, Local local) throws Exception {
        if(traerEmpleado(legajo) != null) {
            throw new Exception("Ya existe un empleado con este número de legajo: " + legajo);
        }
        if(traer(idPersona) != null){
            throw new Exception("Ya existe una persona con este ID: " + idPersona);
        }
        if(traer(dni) != null){
            throw new Exception("Ya existe una persona con este DNI: " + dni);
        }
        Empleado obj = new Empleado(idPersona, nombre, apellido, dni, fechaNacimiento, legajo, horaDesde, horaHasta, sueldoBasico, local);
        return dao.agregar(obj);
    }
    
    /****   MODIFICAR   ****/
    public void modificar(Persona obj) throws Exception {
        if(traer(obj.getIdPersona()) == null) {
            throw new Exception("No se puede modificar a la persona porque no existe.");
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
