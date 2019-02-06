package unizar.labis.one_to_one;

import unizar.labis.Entity;

public class Ministerio implements Entity {
	private String nombreMinisterio = null;
	private Persona ministro = null;

	public Ministerio() {
		validaInvariante();
	}

	public Ministerio(Persona ministro, String nombreMinisterio) {
		this.nombreMinisterio = nombreMinisterio;
		this.ministro = ministro;
		validaInvariante();
	}

	public void setNombreMinisterio(String nombreMinisterio) {
		this.nombreMinisterio = nombreMinisterio;
		validaInvariante();
	}

	public void setMinistro(Persona persona) {
		assert persona != null : "Un ministerio debe tener ministro.";

		// ¿Qué pasa con nuestro antiguo ministro, que sí o sí tiene
		// que estar asociado a un Ministerio, pero ya no a este?
		// Este es un problema que no tiene una solución única o
		// trivial e ilustra la problemática de las multiplicidades
		// que no pueden ser 0

		if (ministro != null) {
			// Una solución puede ser crear un Ministerio Dummy para
			// nuestro actual ministro
			Ministerio dummy = new Ministerio();
			ministro.setMinisterio(dummy);
			dummy.setNombreMinisterio("Dummy");
			dummy.setMinistro(ministro);
		}

		// Y luego ya lo podemos sustituir
		ministro = persona;
		ministro.setMinisterio(this);
		validaInvariante();
	}

	public String getNombreMinisterio() {
		if (nombreMinisterio != null)
			return nombreMinisterio;
		 else
			throw new IllegalStateException("Ministerio no totalmenteConstruido");
	}

	public Persona getMinistro() {
		if (ministro != null)
			return ministro;
		else
			throw new IllegalStateException("Ministerio no totalmenteConstruido");
	}

	public boolean tieneComoMinistroA(Persona persona) {
		if (ministro != null)
			return ministro == persona;
		else
			throw new IllegalStateException("Ministerio no totalmenteConstruido");
	}

	public boolean totalmenteConstruido() {
		return nombreMinisterio != null && ministro != null;
	}

	private void validaInvariante() {
		if (this.totalmenteConstruido() && ministro.totalmenteConstruido()) {
			assert ministro.esMinistroDe(this);
		}
	}
}
