package unizar.labis.one_to_one;

class RegistroMinisteriosTest {
    private RegistroMinisterios reg;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        reg = new RegistroMinisterios();
    }

    @org.junit.jupiter.api.Test
    void creaMinisterio() {
        try {
            reg.creaMinisterio("Hacienda", "Montoro");
        } catch (Exception e) {
            assert(false); // Si ejecuta esto, algo ha fallado
            e.printStackTrace();
        }
        Ministerio hacienda = reg.getMinisterioPorSuNombre("Hacienda");
        assert(hacienda.getNombreMinisterio().equals("Montoro"));
        assert(hacienda.getNombreMinisterio().equals("Hacienda"));
        assert(hacienda.getMinistro().esMinistroDe(hacienda));
    }

    @org.junit.jupiter.api.Test
    void swapMinisterios() {
        try {
            reg.creaMinisterio("Hacienda", "Montoro");
            reg.creaMinisterio("Interior", "Zoido");
            reg.swapMinisterios("Hacienda", "Interior");
        } catch (Exception e) {
            assert(false); // Si ejecuta esto, algo ha fallado
            e.printStackTrace();
        }

        Ministerio hacienda = reg.getMinisterioPorSuNombre("Hacienda");
        Ministerio interior = reg.getMinisterioPorSuNombre("Interior");
        assert(hacienda.getMinistro().getNombre().equals("Zoido"));
        assert(interior.getMinistro().getNombre().equals("Montoro"));
        Persona zoido = reg.getMinistroPorSuNombre("Zoido");
        Persona montoro = reg.getMinistroPorSuNombre("Montoro");
        assert(zoido.esMinistroDe(hacienda));
        assert(montoro.esMinistroDe(interior));
    }
}