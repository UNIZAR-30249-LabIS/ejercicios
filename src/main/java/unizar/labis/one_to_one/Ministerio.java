package unizar.labis.one_to_one;

import unizar.labis.AbstractEntity;

public class Ministerio extends AbstractEntity {
	private String nombreMinisterio = null;
	private Persona ministro = null;

	public Ministerio(String nombreMinisterio) {
		this.nombreMinisterio = nombreMinisterio;
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
		// ¿Qué pasa con nuestro antiguo ministro, que sí o sí tiene
		// que estar asociado a un Ministerio, pero ya no a este?
		// Este es un problema que no tiene una solución única o
		// trivial e ilustra la problemática de las multiplicidades
		// que no pueden ser 0

		// Vamos a resolverlo haciéndolo null, con lo que el método
		// totalmenteConstruido de ese ministro dará false avisando de
		// que no se puede usar tal cual está
		if (persona == null)
			this.ministro = null; // Si solo nos nulifican, no hacemos nada más
		else {
			if (totalmenteConstruido()) {
				ministro.setMinisterio(null);
			}
			// Y luego ya lo podemos sustituir
			ministro = persona;
			if (!ministro.totalmenteConstruido() ||
					ministro.totalmenteConstruido() && !ministro.getMinisterio().equals(this))
				ministro.setMinisterio(this);
		}
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
			return ministro.equals(persona);
		else
			throw new IllegalStateException("Ministerio no totalmenteConstruido");
	}

	// Este método nos permite saber si el objeto se ha terminado de construir o no
	// Se usa porque dado que la asociación Persona-Minsterio es 1 a 1 siempre habrá
	// algún momento en que no se validará el invariante porque alguno de los dos
	// lados está a medio construir
	public boolean totalmenteConstruido() {
		return nombreMinisterio != null && ministro != null;
	}

	private void validaInvariante() {
		if (this.totalmenteConstruido() && ministro.totalmenteConstruido()) {
			assert ministro.esMinistroDe(this);
		}
	}
}
