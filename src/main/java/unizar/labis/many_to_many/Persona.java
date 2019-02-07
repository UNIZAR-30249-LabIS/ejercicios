package unizar.labis.many_to_many;

import unizar.labis.Entity;

import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

public class Persona implements Entity {
	private String nombre;
	private Set<Ministerio> ministerios = new HashSet<Ministerio>();

	public Persona(String nombre) {
		this.nombre = nombre;
		validaInvariante();
	}

	public boolean esMinistroDe(Ministerio m) {
		return ministerios.contains(m);
	}

	public void addMinisterio(Ministerio m) {
		assert m != null;
		ministerios.add(m);
		if (!m.tieneComoMinistroA(this))
			m.addMinistro(this); // Esto para mantener la coherencia (relación M a N)
		validaInvariante();
	}

	public void removeMinisterio(Ministerio m) {
		assert m!= null;
		ministerios.remove(m);
		if (m.tieneComoMinistroA(this))
			m.removeMinistro(this); // Esto para mantener la coherencia (relación M a N)
		validaInvariante();
	}

	public Set<Ministerio> getMinisterios() {
		// Devolvemos una copia defensiva, o nadie nos garantiza que el objeto que nos
		// llama no modifique el conjunto de ministerios destrozando la coherencia de la relación M a N
		// A partir de Java 9 una alternativa a las copias defensivas son las colecciones inmutables,
		// que se pueden compartir con libertad
		return new HashSet<Ministerio>(ministerios);
	};

	public String getNombre() { return nombre; }

	// Se comprueba al final de cada método que cambia el estado de la clase
	private void validaInvariante() {
		assert nombre != null;

		// Esta persona ha sido efectivamente ministra de todos los ministerios asociados
		for (Ministerio m: ministerios) {
			assert m.tieneComoMinistroA(this);
		}
	}
}
