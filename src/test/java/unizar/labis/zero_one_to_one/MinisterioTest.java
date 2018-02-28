package unizar.labis.zero_one_to_one;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinisterioTest {
    Ministerio m;
    Persona p;

    @BeforeEach
    void setUp() {
        p = new Persona("Montoro");
        m = new Ministerio(p, "Hacienda");
    }

    @Test
    void tieneComoMinistroA() {
        assert(m.tieneComoMinistroA(p));
    }

    @Test
    void cambiaMinistro() {
        Persona s = new Persona("Soraya");
        m.cambiaMinistro(s);
        assert(m.tieneComoMinistroA(s));
        assert(!m.tieneComoMinistroA(p));
        assert(s.esMinistroDe(m));
        assert(!p.esMinistroDe(m));
    }
}