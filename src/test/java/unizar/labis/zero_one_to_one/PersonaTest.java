package unizar.labis.zero_one_to_one;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        s.hacerMinistroDe(m);
        assert(s.esMinistroDe(m));
        assert(m.tieneComoMinistroA(s));
        assert(!m.tieneComoMinistroA(p));
    }

    @Test
    void destituir() {
        p.destituir();
        assert(!p.esMinistroDe(m));
        assert(!m.tieneComoMinistroA(p));

        p.hacerMinistroDe(m);
        assert(p.esMinistroDe(m));
        assert(m.tieneComoMinistroA(p));
    }
}