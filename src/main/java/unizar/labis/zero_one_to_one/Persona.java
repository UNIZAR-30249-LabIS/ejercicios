package unizar.labis.zero_one_to_one;

import unizar.labis.AbstractEntity;
import java.util.Optional;

public class Persona extends AbstractEntity {
    private String nombre;
    // El uso de Optional es un poco forzado, pero ilustra el interés de este tipo
	// de contenedores: se ve muy fácilmente que ministerio puede no tener un valor
    private Optional<Ministerio> ministerio;

    public Persona(String nombre) {
        this.nombre = nombre;
        this.ministerio = Optional.empty();
        validaInvariante();
    }

    public boolean esMinistroDe(Ministerio m) {
    	if (ministerio.isPresent())
        	return ministerio.get() == m;
    	return false;
    }

    public void setMinisterio(Optional<Ministerio> m) {
        ministerio = m;
        validaInvariante();
    }

    public void destituir(Persona sustituto) {
    	assert sustituto != null: "No puedes destituir a un ministro sin poner un sustito.";
    	assert ministerio.isPresent(): "No puedes destituir a alguien que no es ministro";
        ministerio.get().setMinistro(sustituto);
        ministerio = Optional.empty();
        validaInvariante();
    }

    public Optional<Ministerio> getMinisterio() {return ministerio; };

    public String getNombre() {
        return nombre;
    }

    // Se comprueba al final de cada método que cambia el estado de la clase
    private void validaInvariante() {
        // O bien esta persona no es ministra, o bien es ministra de nuestro ministerio
        assert(!ministerio.isPresent() ||
               ministerio.get().tieneComoMinistroA(this));
        assert nombre != null;
    }

}
