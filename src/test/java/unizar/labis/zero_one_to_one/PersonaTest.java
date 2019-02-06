package unizar.labis.zero_one_to_one;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonaTest {
	Ministerio m;
	Persona p;

	@BeforeEach
	void setUp() {
		p = new Persona("Montoro");
		m = new Ministerio(p, "Hacienda");
	}

	@Test
	void hacerMinistroDe() {
		Persona s = new Persona("Soraya");
		m.setMinistro(s);
		assert(s.esMinistroDe(m));
		assert(m.tieneComoMinistroA(s));
		assert(!m.tieneComoMinistroA(p));
	}

	@Test
	void destituir() {
		Persona sustituto = new Persona("Aceves");
		p.destituir(sustituto);
		assert(!p.esMinistroDe(m));
		assert(!m.tieneComoMinistroA(p));
	}
}