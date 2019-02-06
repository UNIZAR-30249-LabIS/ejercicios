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
		p = new Persona();
		m = new Ministerio();
		p.setNombre("Montoro");
		p.setMinisterio(m);
		m.setNombreMinisterio("Hacienda");
		m.setMinistro(p);
	}

	@Test
	void tieneComoMinistroA() {
		assert(m.tieneComoMinistroA(p));
	}

	@Test
	void cambiaMinistro() {
		Persona s = new Persona();
		m.setMinistro(s);
		s.setNombre("Soraya");
		s.setMinisterio(m);

		assert(m.tieneComoMinistroA(s));
		assert(!m.tieneComoMinistroA(p));
		assert(s.esMinistroDe(m));
		assert(!p.esMinistroDe(m));
	}

	@Test
	void swapMinisterios() {
		Ministerio hacienda = new Ministerio();
		Ministerio interior = new Ministerio();
		Persona zoido = new Persona();
		Persona montoro = new Persona();
		hacienda.setNombreMinisterio("Hacienda");
		hacienda.setMinistro(montoro);
		interior.setNombreMinisterio("Interior");
		interior.setMinistro(zoido);
		zoido.setNombre("Zoido");
		zoido.setMinisterio(interior);
		montoro.setNombre("Montoro");
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