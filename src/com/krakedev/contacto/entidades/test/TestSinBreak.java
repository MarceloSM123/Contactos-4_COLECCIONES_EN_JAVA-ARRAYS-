package com.krakedev.contacto.entidades.test;

import com.krakedev.contacto.entidades.Contacto;
import com.krakedev.contacto.entidades.Directorio;

public class TestSinBreak {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Directorio dir = new Directorio();

		Contacto c1 = new Contacto();
		c1.setNombre("Maria");
		c1.setCelular("0910");

		Contacto c2 = new Contacto();
		c2.setNombre("Juan");
		c2.setCelular("0911");

		Contacto c3 = new Contacto();
		c3.setNombre("Carlos");
		c3.setCelular("0912");

		dir.agregarContacto(c1);
		dir.agregarContacto(c2);
		dir.agregarContacto(c3);
		
		Contacto contactoEncontrado = dir.buscarContacto("0911");
		System.out.println("Nombre: " + contactoEncontrado.getNombre());
	}

}
