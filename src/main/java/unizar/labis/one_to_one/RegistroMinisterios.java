package unizar.labis.one_to_one;

import java.util.Hashtable;

public class RegistroMinisterios {
    // No es la mejor implementación del mundo, pero para el ejemplo nos vale
    // Nombre de Ministerio -> Persona
    private Hashtable<String, Persona> registroMinistros = new Hashtable<>();
    // Nombre de Persona -> Ministerio
    private Hashtable<String, Ministerio> registroMinisterios = new Hashtable<>();

    public void creaMinisterio(String nombreMinisterio, String nombrePersona) throws Exception {
        if (!registroMinistros.contains(nombreMinisterio) &&
                !registroMinistros.contains(nombrePersona)) {
            Persona p = new Persona(this, nombrePersona);
            // En este punto p todavía no es ministro

            Ministerio m = new Ministerio(this, nombreMinisterio);
            // En este punto m todavía no tiene ministro

            registroMinistros.put(nombreMinisterio, p);
            registroMinisterios.put(nombrePersona, m);
            // Aquí ya sí, tanto p como m están completos
        } else {
            throw new Exception("El ministerio ya existe o la persona ya es ministra.");
        }
    }

    // Una posible solución de mínimos para cambiar ministros (pero esto es dependiente del dominio y de las
    // necesidades de la aplicación, no hay una solución general): como todas las personas son
    // ministros, cambiar el ministro de un ministerio solo se puede hacer con un swap (es decir, uno
    // por otro)
    public void swapMinisterios(String nombreMinisterio1, String nombreMinisterio2) throws Exception {
        Persona ministro1 = registroMinistros.get(nombreMinisterio1);
        Persona ministro2 = registroMinistros.get(nombreMinisterio2);
        if (ministro1 == null || ministro2 == null) {
            throw new Exception("No se puede hacer un swap si los ministerios no existen.");
        }
        registroMinisterios.replace(ministro1.getNombre(), registroMinisterios.get(ministro2.getNombre()));
        registroMinisterios.replace(ministro2.getNombre(), registroMinisterios.get(ministro1.getNombre()));
        registroMinistros.replace(nombreMinisterio1, ministro2);
        registroMinistros.replace(nombreMinisterio2, ministro1);
    }

    public Ministerio getMinisterioPorSuNombre(String nombreMinisterio) {
        String nombrePersona = registroMinistros.get(nombreMinisterio).getNombre();
        return registroMinisterios.get(nombrePersona);
    }

    public Ministerio getMinisterioDePersona(String nombrePersona) {
        return registroMinisterios.get(nombrePersona);
    }

    public Persona getMinistroPorSuNombre(String nombrePersona) {
        String nombreMinisterio = registroMinisterios.get(nombrePersona).getNombreMinisterio();
        return registroMinistros.get(nombreMinisterio);
    }

    public Persona getMinistroDeMinisterio(String nombreMinisterio) {
        return registroMinistros.get(nombreMinisterio);
    }

}
