package unizar.labis.one_to_one;

// Es inmutable
public final class Ministerio {
    private final String nombreMinisterio;
    private final RegistroMinisterios registro;

    /**
     * No usar este constructor. Construir siempre desde RegistroMinisterios.
     */
    Ministerio(RegistroMinisterios registro, String nombreMinisterio) {
        // El constructor es "package-private". Eso dificulta que se use "sin querer"
        // pero no lo impide (podemos hacerlo en una clase en el mismo paquete)
        this.registro = registro;
        this.nombreMinisterio = nombreMinisterio;
        // El ministerio aquí no es correcto, y no lo será hasta que se le asigne
        // un ministro en el RegistroMinisterios.
    }

    public String getNombreMinisterio() {
        return nombreMinisterio;
    }

    public Persona getMinistro() {
        Persona p = registro.getMinistroDeMinisterio(nombreMinisterio);
        assert p != null : "Este ministerio no tiene ministro. Fue creado incorrectamente.";
        return p;
    }
}
