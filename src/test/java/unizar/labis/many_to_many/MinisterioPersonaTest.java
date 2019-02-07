package unizar.labis.many_to_many;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

class MinisterioPersonaTest {
	Ministerio m1, m2, m3;
	Persona p1, p2, p3;

	@BeforeEach
	void setUp() {
		p1 = new Persona("Montoro");
		p2 = new Persona("Salgado");
		p3 = new Persona("Solbes");
		m1 = new Ministerio("Hacienda");
		m2 = new Ministerio("Interior");
		m3 = new Ministerio("Economía");
	}

	@Test
	void añadeMinistros() {
		m1.addMinistro(p1);
		m1.addMinistro(p2);
		m1.addMinistro(p3);
		m2.addMinistro(p1);
		m2.addMinistro(p3);

		assert p1.esMinistroDe(m1);
		assert p2.esMinistroDe(m1);
		assert p3.esMinistroDe(m1);
		assert p1.esMinistroDe(m2);
		assert p3.esMinistroDe(m2);
		assert !p2.esMinistroDe(m2);
		assert !p1.esMinistroDe(m3);
		assert !p2.esMinistroDe(m3);
		assert !p3.esMinistroDe(m3);
		assert m1.tieneComoMinistroA(p1);
		assert m2.tieneComoMinistroA(p3);
	}

	@Test
	void añadeMinisterios() {
		p1.addMinisterio(m1);
		p1.addMinisterio(m2);
		p2.addMinisterio(m1);
		p3.addMinisterio(m1);
		p3.addMinisterio(m2);

		assert p1.esMinistroDe(m1);
		assert p2.esMinistroDe(m1);
		assert p3.esMinistroDe(m1);
		assert p1.esMinistroDe(m2);
		assert p3.esMinistroDe(m2);
		assert !p2.esMinistroDe(m2);
		assert !p1.esMinistroDe(m3);
		assert !p2.esMinistroDe(m3);
		assert !p3.esMinistroDe(m3);
		assert m1.tieneComoMinistroA(p1);
		assert m2.tieneComoMinistroA(p3);
	}

	@Test
	void removeMinistros() {
		m1.addMinistro(p1);
		m1.addMinistro(p2);
		m1.addMinistro(p3);
		m2.addMinistro(p1);
		m3.addMinistro(p1);
		m3.addMinistro(p3);

		m1.removeMinistro(p1);
		assert !p1.esMinistroDe(m1);
		assert p1.esMinistroDe(m2);

		m2.removeMinistro(p1);
		assert !p1.esMinistroDe(m2);
		assert !m2.tieneComoMinistroA(p1);
		assert p1.esMinistroDe(m3);
		assert m3.tieneComoMinistroA(p1);

	}

	@Test
	void removeMinisterios() {
		p1.addMinisterio(m1);
		p1.addMinisterio(m2);
		p2.addMinisterio(m1);
		p3.addMinisterio(m1);
		p3.addMinisterio(m2);

		p1.removeMinisterio(m2);
		assert !p1.esMinistroDe(m2);
		assert p1.esMinistroDe(m1);
		p3.removeMinisterio(m1);
		assert !p3.esMinistroDe(m1);
		assert !m1.tieneComoMinistroA(p3);

		p1.removeMinisterio(m2); // No falla al eliminar un ministro que ya no estaba asociado
		assert !p1.esMinistroDe(m2);
		assert !m2.tieneComoMinistroA(p1);
	}

	@Test
	void copiaDefensivaMinistros() {
		m1.addMinistro(p1);
		m1.addMinistro(p2);
		m1.addMinistro(p3);
		Set<Persona> ministros = m1.getMinistros();
		ministros.remove(p1);
		// Compruebo que m1 no ha sido modificado a pesar de haber alterado el conjunto de ministros
		// que me ha devuelto
		assert m1.tieneComoMinistroA(p1);
		assert p1.esMinistroDe(m1);
	}

	@Test
	void copiaDefensivaMinisterios() {
		p1.addMinisterio(m1);
		p1.addMinisterio(m2);
		p1.addMinisterio(m3);

		Set<Ministerio> ministerios = p1.getMinisterios();
		ministerios.remove(m1);
		// Compruebo que p1 no ha sido modificado a pesar de haber alterado el conjunto de ministerios
		// que me ha devuelto
		assert m1.tieneComoMinistroA(p1);
		assert p1.esMinistroDe(m1);
	}

}