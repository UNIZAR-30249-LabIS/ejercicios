package unizar.labis.one_to_one;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MinisterioPersonaTest {
	Ministerio m;
	Persona p;

	@BeforeEach
	void setUp() {
		p = new Persona("Montoro");
		m = new Ministerio("Hacienda");
	}

	@Test
	void asignaMinistroMinisterio() {
		p.setMinisterio(m);
		assert m.tieneComoMinistroA(p) ;
		assert p.esMinistroDe(m) ;
	}

	@Test
	void asignaMinisterioMinistro() {
		m.setMinistro(p);
		assert m.tieneComoMinistroA(p) ;
		assert p.esMinistroDe(m) ;
	}

	@Test
	void cambiaMinistro() {
		p.setMinisterio(m);
		Persona s = new Persona("Soraya");
		m.setMinistro(s);

		assert m.tieneComoMinistroA(s) ;
		assert !m.tieneComoMinistroA(p) ;
		assert s.esMinistroDe(m) ;
		// En este punto p (Montoro) no está totalmente construido, porque
		// le hemos dado su Ministerio a Soraya y por tanto el no tiene
		// Ministerio.
		assert !p.totalmenteConstruido();
		// Debe fallar si tratamos de averiguar su ministerio.
		assertThrows(IllegalStateException.class,
				()->{
					boolean a = p.esMinistroDe(m);
				});
		Ministerio m1 = new Ministerio("ParaIrTirando");
		p.setMinisterio(m1);
		assert p.esMinistroDe(m1) ;
		assert m1.tieneComoMinistroA(p) ;
	}

	@Test
	void swapMinisterios() {
		Ministerio hacienda = new Ministerio("Hacienda");
		Ministerio interior = new Ministerio("Interior");
		Persona zoido = new Persona("Zoido");
		Persona montoro = new Persona("Montoro");
		hacienda.setMinistro(montoro);
		interior.setMinistro(zoido);

		// Hacemos el swap
		hacienda.setMinistro(zoido);
		interior.setMinistro(montoro);
		assert hacienda.getMinistro() == zoido;
		assert interior.getMinistro() == montoro;
		assert zoido.getMinisterio() == hacienda;
		assert montoro.getMinisterio() == interior;
	}
}