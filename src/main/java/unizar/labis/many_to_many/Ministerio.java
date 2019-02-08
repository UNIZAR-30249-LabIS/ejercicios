package unizar.labis.many_to_many;

import unizar.labis.AbstractEntity;

import java.util.HashSet;
import java.util.Set;

public class Ministerio extends AbstractEntity {
	private String nombreMinisterio;
	private Set<Persona> ministros = new HashSet<Persona>();

	public Ministerio(String nombreMinisterio) {
		this.nombreMinisterio = nombreMinisterio;
		validaInvariante();
	}

	public boolean tieneComoMinistroA(Persona persona) {
		return ministros.contains(persona);
	}

	public void addMinistro(Persona persona) {
		assert persona != null;
		ministros.add(persona);
		// Si no comprobamos primero que la asociación inversa no existe antes de añadirla
		// acabaremos por entrar en un bucle infinito entre addMinistro en esta clase y
		// addMinistrio en la clase Persona. Esto es así para todos los métodos que alteran
		// la relación M a N en ambas clases
		if (!persona.esMinistroDe(this))
			persona.addMinisterio(this); // Esto para mantener la coherencia (relación M a N)
		validaInvariante();
	}

	public void removeMinistro(Persona persona) {
		assert persona != null;
		ministros.remove(persona);
		if (persona.esMinistroDe(this))
			persona.removeMinisterio(this); // Esto para mantener la coherencia (relación M a N)
		validaInvariante();
	}

	public Set<Persona> getMinistros() {
		// Devolvemos una copia defensiva, o nadie nos garantiza que el objeto que nos
		// llama no modifique el conjunto de ministros destrozando la coherencia de la relación M a N
		// A partir de Java 9 una alternativa a las copias defensivas son las colecciones inmutables,
		// que se pueden compartir con libertad
		return new HashSet<Persona>(ministros);
	}

	public String getNombreMinisterio() {
		return nombreMinisterio;
	}


	private void validaInvariante() {
		assert nombreMinisterio != null: "Un ministerio siempre debe tener nombre";
		// Nuestros ministros han sido efectivamente ministros de este ministerio
		for (Persona m: ministros) {
			assert m.esMinistroDe(this);
		}
	}
}
