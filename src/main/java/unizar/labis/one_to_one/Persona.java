package unizar.labis.one_to_one;

// Es inmutable
public final class Persona {
    private final String nombre;
    private final RegistroMinisterios registro;

    /**
     * No usar este constructor. Construir siempre desde RegistroMinisterios.
     */
    Persona(RegistroMinisterios registro, String nombre) {
        // El constructor es "package-private". Eso dificulta que se use "sin querer"
        // pero no lo impide (podemos crear una clase para ello en el mismo paquete)
        this.registro = registro;
        this.nombre = nombre;
        // Esta persona aquí no es correcta; todavía no es ministro y no lo será
        // hasta que se le asigne ministerio en RegistroMinisterios
    }

    public Ministerio getMinisterio() {
        Ministerio m = registro.getMinisterioDePersona(nombre);
        assert m != null : "Esta persona no es ministro. Fue creada incorrectamente.";
        return m;
    }

    public boolean esMinistroDe(Ministerio ministerio) {
        // Asumo que cada ministerio tiene un nombre único (EN GENERAL PODRÍA SER MUCHO ASUMIR)
        return getMinisterio().getNombreMinisterio() == ministerio.getNombreMinisterio();
    }

    public String getNombre() {
        return nombre;
    }
}

