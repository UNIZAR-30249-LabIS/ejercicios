package unizar.labis.zero_one_to_one;

import unizar.labis.AbstractEntity;
import java.util.Optional;

public class Ministerio extends AbstractEntity {
	private String nombreMinisterio;
	private Persona ministro;

	public Ministerio(Persona persona, String nombreMinisterio) {
		this.nombreMinisterio = nombreMinisterio;
		ministro = persona;
		ministro.setMinisterio(Optional.of(this));
		validaInvariante();
	}

	public boolean tieneComoMinistroA(Persona persona) {
		return this.ministro == persona;
	}

	public void setMinistro(Persona persona) {
		assert persona != null : "Un ministerio debe tener ministro.";

		// Nuestro antiguo ministro se quedará sin ministerio porque
		// se puede ser ministro de máximo 1 ministerio
		ministro.setMinisterio(Optional.empty());
		ministro = persona;
		ministro.setMinisterio(Optional.of(this));
		validaInvariante();
	}

	public Persona getMinistro() {
		return ministro;
	}

	public String getNombreMinisterio() {
		return nombreMinisterio;
	}

	// Se comprueba al final de cada método que cambia el estado de la clase
	private void validaInvariante() {
		assert nombreMinisterio != null: "Un ministerio siempre debe tener nombre";
		assert ministro != null: "Un ministerio siempre debe tener ministro";
		// Nuestro ministro es efectivamente el ministro de este ministerio
		assert(ministro.esMinistroDe(this));
	}
}
