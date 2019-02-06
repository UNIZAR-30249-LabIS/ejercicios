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

	public boolean totalmenteConstruido() {
		return nombre != null && ministerio != null;
	}

	private void validaInvariante() {
		if (this.totalmenteConstruido() && ministerio.totalmenteConstruido()) {
			assert ministerio.tieneComoMinistroA(this);
		}
	}
}

