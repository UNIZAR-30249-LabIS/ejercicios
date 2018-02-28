package unizar.labis.zero_one_to_one;

// Es mutable. Podemos reasignarle ministerio si queremos
public class Persona {
    private String nombre;
    private Ministerio ministerio;

    public Persona(String nombre) {
        this.nombre = nombre;
        this.ministerio = null;
        validaInvariante();
    }

    public void hacerMinistroDe(Ministerio ministerio) {
        // No se puede asignar un ministerio a una persona si antes
        // no hemos asignado la persona al ministerio
        assert(ministerio.tieneComoMinistroA(this));
        this.ministerio = ministerio;
        validaInvariante();
    }

    public void destituir() {
        // No se puede quitar un ministerio a una persona si antes
        // no hemos asignado otra persona a ese ministerio (ni si no
        // tenía un ministerio)
        assert(this.ministerio != null &&
                !this.ministerio.tieneComoMinistroA(this));
        this.ministerio = null;
        validaInvariante();
    }

    public boolean esMinistroDe(Ministerio ministerio) {
        if (ministerio != null) return this.ministerio == ministerio;
        return false;
    }

    public String getNombre() {
        return nombre;
    }

    // Se comprueba al final de cada método que cambia el estado de la clase
    private void validaInvariante() {
        // O bien esta persona no es ministra, o bien es ministra de nuestro ministerio
        assert(this.ministerio == null ||
                this.ministerio.tieneComoMinistroA(this));
    }

}
