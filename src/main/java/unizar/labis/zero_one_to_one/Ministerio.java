package unizar.labis.zero_one_to_one;

// Es mutable. Podemos reasignarle ministra si queremos
public class Ministerio {
    private String nombreMinisterio;
    private Persona ministro;

    public Ministerio(Persona persona, String nombreMinisterio) {
        this.nombreMinisterio = nombreMinisterio;
        // Un ministerio siempre tiene un ministro
        this.ministro = persona;
        this.ministro.hacerMinistroDe(this);
        validaInvariante();
    }
    public boolean tieneComoMinistroA(Persona persona) {
        return this.ministro == persona;
    }

    public void cambiaMinistro(Persona persona) {
        this.ministro.destituir();
        this.ministro = persona;
        this.ministro.hacerMinistroDe(this);
        validaInvariante();
    }

    public String getNombreMinisterio() {
        return nombreMinisterio;
    }

    // Se comprueba al final de cada método que cambia el estado de la clase
    private void validaInvariante() {
        // Nuestro ministro es efectivamente el ministro de este ministerio
        assert(this.ministro.esMinistroDe(this));
    }
}
