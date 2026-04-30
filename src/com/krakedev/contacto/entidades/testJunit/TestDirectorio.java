package com.krakedev.contacto.entidades.testJunit;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.contacto.entidades.Contacto;
import com.krakedev.contacto.entidades.Directorio;

public class TestDirectorio {
	private Directorio directorio;
    private Contacto contactoNuevo;
    private Contacto contactoConMismoNumero;

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
    }

    @Test
    void testAgregarContacto_CuandoNoExiste_DeberiaAgregarYRetornarTrue() {
        // Ejecutar
        boolean resultado = directorio.agregarContacto(contactoNuevo);

        // Verificar
        assertTrue(resultado);
        assertEquals(1, directorio.obtenerCantidadContactos());
        
        // Verificar que el contacto fue agregado correctamente
        Contacto contactoCreado = directorio.obtenerContacto(0);
        assertNotNull(contactoCreado);
        assertEquals("Ana", contactoCreado.getNombre());
        assertEquals("999888777", contactoCreado.getCelular());
    }

    @Test
    void testAgregarContacto_CuandoYaExisteConMismoNumero_DeberiaNoAgregarYRetornarFalse() {
        // Primero agregar el primer contacto
        directorio.agregarContacto(contactoNuevo);
        
        // Verificar que solo hay un contacto
        assertEquals(1, directorio.obtenerCantidadContactos());
        
        // Intentar agregar contacto con mismo número
        boolean resultado = directorio.agregarContacto(contactoConMismoNumero);
        
        // Verificar
        assertFalse(resultado);
        assertEquals(1, directorio.obtenerCantidadContactos()); // La cantidad no debe aumentar
        
        // Verificar que el contacto original sigue siendo el mismo
        Contacto existente = directorio.obtenerContacto(0);
        assertEquals("Ana", existente.getNombre());
        assertNotEquals("Pedro", existente.getNombre());
    }

    @Test
    void testAgregarContacto_CuandoSeAgreganMultiplesContactosConDistintosNumeros_DeberiaAgregarTodos() {
        // Crear contactos con diferentes números
        Contacto c1 = new Contacto();
        c1.setNombre("Luis");
        c1.setCelular("111111111");
        
        Contacto c2 = new Contacto();
        c2.setNombre("Marta");
        c2.setCelular("222222222");
        
        Contacto c3 = new Contacto();
        c3.setNombre("Jose");
        c3.setCelular("333333333");
        
        // Agregar contactos
        assertTrue(directorio.agregarContacto(c1));
        assertTrue(directorio.agregarContacto(c2));
        assertTrue(directorio.agregarContacto(c3));
        
        // Verificar cantidad
        assertEquals(3, directorio.obtenerCantidadContactos());
        
        // Verificar que todos están presentes
        assertNotNull(directorio.buscarContacto("111111111"));
        assertNotNull(directorio.buscarContacto("222222222"));
        assertNotNull(directorio.buscarContacto("333333333"));
    }

    @Test
    void testAgregarContacto_CuandoSeIntentaAgregarElMismoContactoDosVeces_DeberiaAgregarSoloLaPrimera() {
        // Intentar agregar el mismo contacto dos veces
        boolean primerIntento = directorio.agregarContacto(contactoNuevo);
        boolean segundoIntento = directorio.agregarContacto(contactoNuevo);
        
        // Verificar
        assertTrue(primerIntento);
        assertFalse(segundoIntento);
        assertEquals(1, directorio.obtenerCantidadContactos());
    }

    @Test
    void testAgregarContacto_CuandoDirectorioVacio_DeberiaAgregarCorrectamente() {
        // Verificar que el directorio comienza vacío
        assertEquals(0, directorio.obtenerCantidadContactos());
        
        // Agregar contacto
        boolean resultado = directorio.agregarContacto(contactoNuevo);
        
        // Verificar
        assertTrue(resultado);
        assertEquals(1, directorio.obtenerCantidadContactos());
    }

    @Test
    void testAgregarContacto_CuandoContactoTieneDatosCompletos_DeberiaGuardarTodosLosDatos() {
        // Configurar contacto con todos los datos
        Contacto contactoCompleto = new Contacto();
        contactoCompleto.setNombre("Roberto");
        contactoCompleto.setApellido("Fernandez");
        contactoCompleto.setEdad(28);
        contactoCompleto.setCelular("444555666");
        contactoCompleto.setPeso(75.5);
        
        // Agregar contacto
        boolean resultado = directorio.agregarContacto(contactoCompleto);
        
        // Verificar
        assertTrue(resultado);
        Contacto recuperado = directorio.buscarContacto("444555666");
        assertNotNull(recuperado);
        assertEquals("Roberto", recuperado.getNombre());
        assertEquals("Fernandez", recuperado.getApellido());
        assertEquals(28, recuperado.getEdad());
        assertEquals(75.5, recuperado.getPeso());
    }
	

}
