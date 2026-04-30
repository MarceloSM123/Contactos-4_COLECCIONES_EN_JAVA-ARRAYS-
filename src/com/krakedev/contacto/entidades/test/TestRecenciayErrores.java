package com.krakedev.contacto.entidades.test;

import com.krakedev.contacto.entidades.Contacto;
import com.krakedev.contacto.entidades.Directorio;

public class TestRecenciayErrores {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Directorio dir = new Directorio();

		Contacto c1 = new Contacto();
		c1.setNombre("Maria");

		dir.agregarContacto(c1);

		dir.agregarContacto(new Contacto());

		Contacto c = dir.obtenerContacto(4);

		System.out.println("Nombre: " + c.getNombre());
	}

}
