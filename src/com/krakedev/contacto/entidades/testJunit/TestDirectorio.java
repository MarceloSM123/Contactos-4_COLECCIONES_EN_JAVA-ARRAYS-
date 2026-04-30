package com.krakedev.contacto.entidades.testJunit;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.contacto.entidades.Contacto;
import com.krakedev.contacto.entidades.Directorio;

import java.util.ArrayList;

public class TestDirectorio {
	private Directorio directorio;
    private Contacto contactoNuevo;
    private Contacto contactoConMismoNumero;
    private Contacto contacto1;
    private Contacto contacto2;
    private Contacto contacto3;
    private Contacto contacto4;

    @BeforeEach
    void setUp() {
        directorio = new Directorio();

        contactoNuevo = new Contacto();
        contactoNuevo.setNombre("Ana");
        contactoNuevo.setApellido("Garcia");
        contactoNuevo.setEdad(25);
        contactoNuevo.setCelular("999888777");

        contactoConMismoNumero = new Contacto();
        contactoConMismoNumero.setNombre("Pedro");
        contactoConMismoNumero.setApellido("Lopez");
        contactoConMismoNumero.setEdad(30);
        contactoConMismoNumero.setCelular("999888777"); // Mismo número que contactoNuevo
        
        // Contactos adicionales para pruebas de búsqueda y eliminación
        contacto1 = new Contacto();
        contacto1.setNombre("Maria");
        contacto1.setApellido("Garcia");
        contacto1.setCelular("123456789");
        contacto1.setEdad(25);
        contacto1.setPeso(60.5);

        contacto2 = new Contacto();
        contacto2.setNombre("Mario");
        contacto2.setApellido("Lopez");
        contacto2.setCelular("987654321");
        contacto2.setEdad(30);
        contacto2.setPeso(75.0);

        contacto3 = new Contacto();
        contacto3.setNombre("Carlos");
        contacto3.setApellido("Rodriguez");
        contacto3.setCelular("555555555");
        contacto3.setEdad(28);
        contacto3.setPeso(80.2);

        contacto4 = new Contacto();
        contacto4.setNombre("Ana");
        contacto4.setApellido("Martinez");
        contacto4.setCelular("111222333");
        contacto4.setEdad(22);
        contacto4.setPeso(55.0);
        
        // Agregar contactos para las pruebas
        directorio.agregarContacto(contacto1);
        directorio.agregarContacto(contacto2);
        directorio.agregarContacto(contacto3);
        directorio.agregarContacto(contacto4);
    }

    // ==================== TESTS PARA agregarContacto ====================

    @Test
    void testAgregarContacto_CuandoNoExiste_DeberiaAgregarYRetornarTrue() {
        Directorio nuevoDirectorio = new Directorio();
        boolean resultado = nuevoDirectorio.agregarContacto(contactoNuevo);

        assertTrue(resultado);
        assertEquals(1, nuevoDirectorio.obtenerCantidadContactos());
        
        Contacto contactoCreado = nuevoDirectorio.obtenerContacto(0);
        assertNotNull(contactoCreado);
        assertEquals("Ana", contactoCreado.getNombre());
        assertEquals("999888777", contactoCreado.getCelular());
    }

    @Test
    void testAgregarContacto_CuandoYaExisteConMismoNumero_DeberiaNoAgregarYRetornarFalse() {
        Directorio nuevoDirectorio = new Directorio();
        nuevoDirectorio.agregarContacto(contactoNuevo);
        
        assertEquals(1, nuevoDirectorio.obtenerCantidadContactos());
        
        boolean resultado = nuevoDirectorio.agregarContacto(contactoConMismoNumero);
        
        assertFalse(resultado);
        assertEquals(1, nuevoDirectorio.obtenerCantidadContactos());
        
        Contacto existente = nuevoDirectorio.obtenerContacto(0);
        assertEquals("Ana", existente.getNombre());
        assertNotEquals("Pedro", existente.getNombre());
    }

    @Test
    void testAgregarContacto_CuandoSeAgreganMultiplesContactosConDistintosNumeros_DeberiaAgregarTodos() {
        Directorio nuevoDirectorio = new Directorio();
        
        Contacto c1 = new Contacto();
        c1.setNombre("Luis");
        c1.setCelular("111111111");
        
        Contacto c2 = new Contacto();
        c2.setNombre("Marta");
        c2.setCelular("222222222");
        
        Contacto c3 = new Contacto();
        c3.setNombre("Jose");
        c3.setCelular("333333333");
        
        assertTrue(nuevoDirectorio.agregarContacto(c1));
        assertTrue(nuevoDirectorio.agregarContacto(c2));
        assertTrue(nuevoDirectorio.agregarContacto(c3));
        
        assertEquals(3, nuevoDirectorio.obtenerCantidadContactos());
        
        assertNotNull(nuevoDirectorio.buscarContacto("111111111"));
        assertNotNull(nuevoDirectorio.buscarContacto("222222222"));
        assertNotNull(nuevoDirectorio.buscarContacto("333333333"));
    }

    @Test
    void testAgregarContacto_CuandoSeIntentaAgregarElMismoContactoDosVeces_DeberiaAgregarSoloLaPrimera() {
        Directorio nuevoDirectorio = new Directorio();
        
        boolean primerIntento = nuevoDirectorio.agregarContacto(contactoNuevo);
        boolean segundoIntento = nuevoDirectorio.agregarContacto(contactoNuevo);
        
        assertTrue(primerIntento);
        assertFalse(segundoIntento);
        assertEquals(1, nuevoDirectorio.obtenerCantidadContactos());
    }

    @Test
    void testAgregarContacto_CuandoDirectorioVacio_DeberiaAgregarCorrectamente() {
        Directorio nuevoDirectorio = new Directorio();
        
        assertEquals(0, nuevoDirectorio.obtenerCantidadContactos());
        
        boolean resultado = nuevoDirectorio.agregarContacto(contactoNuevo);
        
        assertTrue(resultado);
        assertEquals(1, nuevoDirectorio.obtenerCantidadContactos());
    }

    @Test
    void testAgregarContacto_CuandoContactoTieneDatosCompletos_DeberiaGuardarTodosLosDatos() {
        Directorio nuevoDirectorio = new Directorio();
        
        Contacto contactoCompleto = new Contacto();
        contactoCompleto.setNombre("Roberto");
        contactoCompleto.setApellido("Fernandez");
        contactoCompleto.setEdad(28);
        contactoCompleto.setCelular("444555666");
        contactoCompleto.setPeso(75.5);
        
        boolean resultado = nuevoDirectorio.agregarContacto(contactoCompleto);
        
        assertTrue(resultado);
        Contacto recuperado = nuevoDirectorio.buscarContacto("444555666");
        assertNotNull(recuperado);
        assertEquals("Roberto", recuperado.getNombre());
        assertEquals("Fernandez", recuperado.getApellido());
        assertEquals(28, recuperado.getEdad());
        assertEquals(75.5, recuperado.getPeso());
    }

    // ==================== TESTS PARA buscarContacto ====================

    @Test
    void testBuscarContacto_CuandoExiste_DeberiaRetornarElContacto() {
        Contacto resultado = directorio.buscarContacto("123456789");
        
        assertNotNull(resultado);
        assertEquals("Maria", resultado.getNombre());
        assertEquals("123456789", resultado.getCelular());
    }

    @Test
    void testBuscarContacto_CuandoNoExiste_DeberiaRetornarNull() {
        Contacto resultado = directorio.buscarContacto("999999999");
        
        assertNull(resultado);
    }

    @Test
    void testBuscarContacto_CuandoDirectorioVacio_DeberiaRetornarNull() {
        Directorio directorioVacio = new Directorio();
        Contacto resultado = directorioVacio.buscarContacto("123456789");
        
        assertNull(resultado);
    }


    // ==================== TESTS PARA eliminarContacto ====================

    @Test
    void testEliminarContacto_CuandoExiste_DeberiaEliminarYRetornarTrue() {
        int cantidadInicial = directorio.obtenerCantidadContactos();
        
        boolean resultado = directorio.eliminarContacto("123456789");
        
        assertTrue(resultado);
        assertEquals(cantidadInicial - 1, directorio.obtenerCantidadContactos());
        assertNull(directorio.buscarContacto("123456789"));
    }

    @Test
    void testEliminarContacto_CuandoNoExiste_DeberiaRetornarFalse() {
        int cantidadInicial = directorio.obtenerCantidadContactos();
        
        boolean resultado = directorio.eliminarContacto("999999999");
        
        assertFalse(resultado);
        assertEquals(cantidadInicial, directorio.obtenerCantidadContactos());
    }

    @Test
    void testEliminarContacto_CuandoDirectorioVacio_DeberiaRetornarFalse() {
        Directorio directorioVacio = new Directorio();
        
        boolean resultado = directorioVacio.eliminarContacto("123456789");
        
        assertFalse(resultado);
        assertEquals(0, directorioVacio.obtenerCantidadContactos());
    }

   

    @Test
    void testEliminarContacto_CuandoHayMultiplesContactos_DeberiaEliminarSoloElIndicado() {
        assertNotNull(directorio.buscarContacto("123456789"));
        assertNotNull(directorio.buscarContacto("987654321"));
        assertNotNull(directorio.buscarContacto("555555555"));
        
        boolean resultado = directorio.eliminarContacto("987654321");
        
        assertTrue(resultado);
        assertEquals(3, directorio.obtenerCantidadContactos());
        
        assertNotNull(directorio.buscarContacto("123456789"));
        assertNull(directorio.buscarContacto("987654321"));
        assertNotNull(directorio.buscarContacto("555555555"));
    }

    @Test
    void testEliminarContacto_EliminarYMismoNumeroNoSePuedeEliminarDosVeces() {
        boolean primerIntento = directorio.eliminarContacto("123456789");
        assertTrue(primerIntento);
        assertEquals(3, directorio.obtenerCantidadContactos());
        
        boolean segundoIntento = directorio.eliminarContacto("123456789");
        assertFalse(segundoIntento);
        assertEquals(3, directorio.obtenerCantidadContactos());
    }

    // ==================== TESTS PARA buscarContactosCoincidencia ====================

    @Test
    void testBuscarContactosCoincidencia_CuandoHayCoincidencias_DeberiaRetornarListaConContactos() {
        ArrayList<Contacto> encontrados = directorio.buscarContactosCoincidencia("Ma");
        
        assertNotNull(encontrados);
        assertEquals(2, encontrados.size()); // Maria y Mario
        
        assertTrue(encontrados.stream().anyMatch(c -> c.getNombre().equals("Maria")));
        assertTrue(encontrados.stream().anyMatch(c -> c.getNombre().equals("Mario")));
    }

    @Test
    void testBuscarContactosCoincidencia_CuandoNoHayCoincidencias_DeberiaRetornarListaVacia() {
        ArrayList<Contacto> encontrados = directorio.buscarContactosCoincidencia("Xyz");
        
        assertNotNull(encontrados);
        assertEquals(0, encontrados.size());
    }

    @Test
    void testBuscarContactosCoincidencia_CuandoSubcadenaEsVacia_DeberiaRetornarTodosLosContactos() {
        ArrayList<Contacto> encontrados = directorio.buscarContactosCoincidencia("");
        
        assertNotNull(encontrados);
        assertEquals(4, encontrados.size());
    }

    @Test
    void testBuscarContactosCoincidencia_CuandoSubcadenaEsNull_DeberiaLanzarExcepcion() {
        assertThrows(NullPointerException.class, () -> {
            directorio.buscarContactosCoincidencia(null);
        });
    }

    @Test
    void testBuscarContactosCoincidencia_CuandoDirectorioVacio_DeberiaRetornarListaVacia() {
        Directorio directorioVacio = new Directorio();
        ArrayList<Contacto> encontrados = directorioVacio.buscarContactosCoincidencia("Ma");
        
        assertNotNull(encontrados);
        assertEquals(0, encontrados.size());
    }

    @Test
    void testBuscarContactosCoincidencia_DistintasCoincidencias() {
        ArrayList<Contacto> encontradosMa = directorio.buscarContactosCoincidencia("Ma");
        assertEquals(2, encontradosMa.size());
        
        ArrayList<Contacto> encontradosCa = directorio.buscarContactosCoincidencia("Ca");
        assertEquals(1, encontradosCa.size());
        assertEquals("Carlos", encontradosCa.get(0).getNombre());
        
        ArrayList<Contacto> encontradosAn = directorio.buscarContactosCoincidencia("An");
        assertEquals(1, encontradosAn.size());
        assertEquals("Ana", encontradosAn.get(0).getNombre());
    }

    @Test
    void testBuscarContactosCoincidencia_DebeCoincidirConElInicioDelNombre() {
        ArrayList<Contacto> encontrados = directorio.buscarContactosCoincidencia("ri");
        assertEquals(0, encontrados.size());
    }

    @Test
    void testBuscarContactosCoincidencia_CaseSensitive_DeberiaRespetarMayusculasMinusculas() {
        ArrayList<Contacto> encontradosMinuscula = directorio.buscarContactosCoincidencia("ma");
        assertEquals(0, encontradosMinuscula.size());
        
        ArrayList<Contacto> encontradosMayuscula = directorio.buscarContactosCoincidencia("Ma");
        assertEquals(2, encontradosMayuscula.size());
    }
	

}
