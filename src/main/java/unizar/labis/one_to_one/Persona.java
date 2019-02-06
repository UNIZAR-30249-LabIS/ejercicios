package unizar.labis.one_to_one;

import unizar.labis.Entity;

public class Persona implements Entity {
	private String nombre;
	private Ministerio ministerio;

	public Persona() {
		validaInvariante();
	}

	public Persona(String nombre, Ministerio ministerio) {
		this.nombre = nombre;
		this.ministerio = ministerio;
		validaInvariante();
	}

	public void setNombre(String nombre) {
		assert nombre != null:"Una Persona debe tener nombre";
		this.nombre = nombre;
		validaInvariante();
	}

	public void setMinisterio(Ministerio ministerio) {
		assert ministerio != null:"Una Persona debe tener ministerio";
		this.ministerio = ministerio;
		validaInvariante();
	}

	public Ministerio getMinisterio() {
		if (ministerio != null)
			return ministerio;
		else throw new IllegalStateException("Persona no totalmenteConstruido");
	}

	public boolean esMinistroDe(Ministerio m) {
		if (ministerio != null)
			return ministerio == m;
		else throw new IllegalStateException("Persona no totalmenteConstruido");
	}

	public String getNombre() {
		if (nombre != null)
			return nombre;
		else throw new IllegalStateException("Persona no totalmenteConstruido");
	}

	// Este método nos permite saber si el objeto se ha terminado de construir o no
	// Se usa porque dado que la asociación Persona-Minsterio es 1 a 1 siempre habrá
	// algún momento en que no se validará el invariante porque alguno de los dos
	// lados está a medio construir
	public boolean totalmenteConstruido() {
		return nombre != null && ministerio != null;
	}

	private void validaInvariante() {
		if (this.totalmenteConstruido() && ministerio.totalmenteConstruido()) {
			assert ministerio.tieneComoMinistroA(this);
		}
	}
}

