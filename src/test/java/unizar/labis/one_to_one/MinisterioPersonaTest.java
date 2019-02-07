package unizar.labis.one_to_one;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MinisterioPersonaTest {
	Ministerio m;
	Persona p;

	@BeforeEach
	void setUp() {
		// La construcción es pesada porque hay que hacer las cosas en orden
		// y es fácil no acordarse. Esto sería una buenísima razón para implementar
		// una Factory que instanciara apropiadamente parejas de objetos Persona-Ministerio
		// ocultando esta complejidad.
		p = new Persona("Montoro");
		m = new Ministerio("Hacienda");
		p.setMinisterio(m);
		m.setMinistro(p);
	}

	@Test
	void tieneComoMinistroA() {
		assert(m.tieneComoMinistroA(p));
	}

	@Test
	void cambiaMinistro() {
		Persona s = new Persona("Soraya");
		m.setMinistro(s);
		s.setMinisterio(m);

		assert(m.tieneComoMinistroA(s));
		assert(!m.tieneComoMinistroA(p));
		assert(s.esMinistroDe(m));
		assert(!p.esMinistroDe(m));
	}

	@Test
	void swapMinisterios() {
		Ministerio hacienda = new Ministerio("Hacienda");
		Ministerio interior = new Ministerio("Interior");
		Persona zoido = new Persona("Zoido");
		Persona montoro = new Persona("Montoro");
		hacienda.setMinistro(montoro);
		interior.setMinistro(zoido);
		zoido.setMinisterio(interior);
		montoro.setMinisterio(hacienda);

		// Hacemos el swap
		hacienda.setMinistro(zoido);
		interior.setMinistro(montoro);
		zoido.setMinisterio(hacienda);
		montoro.setMinisterio(interior);
		assert hacienda.getMinistro() == zoido;
		assert interior.getMinistro() == montoro;
		assert zoido.getMinisterio() == hacienda;
		assert montoro.getMinisterio() == interior;
	}
}