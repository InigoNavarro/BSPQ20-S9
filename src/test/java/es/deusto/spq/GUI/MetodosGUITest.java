package es.deusto.spq.GUI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;


import org.junit.Before;
import org.junit.Test;
import es.deusto.spq.GUI.MetodosGUI.Resp;
import es.deusto.spq.data.Piso;
import es.deusto.spq.data.Post;

public class MetodosGUITest {

	MetodosGUI mGUI;
	Resp r;	
	
	
	@Before
	public void setUp() {
		mGUI = new MetodosGUI();
		
	}
	
	@Test
	public void validarNombreTest() { 
		assertTrue(mGUI.validarNombre("Eneko"));
		assertFalse(mGUI.validarNombre(""));
	}
	
	@Test
	public void validarUsuarioTest() {
		assertTrue(mGUI.validarUsuario("eneko98"));
		assertFalse(mGUI.validarUsuario(""));
		assertFalse(mGUI.validarUsuario("ene"));
		
	}
	
	@Test
	public void validarContrasenyaTest() { 
		assertTrue(mGUI.validarContrasenya("12345678"));
		assertFalse(mGUI.validarContrasenya("a123dg"));
		assertFalse(mGUI.validarContrasenya(""));
	}
	
	@Test 
	public void validarConfContrasenyaTest() {
		assertTrue(mGUI.validarConfContrasenya("12345678", "12345678"));
		assertFalse(mGUI.validarConfContrasenya("asd12334", "12345678"));
		
	}
	//Test Funcional: saca un popup cuando se ejecuta en terminal
	@Test
	public void validarEmailTest() {
		assertTrue(mGUI.validarEmail("enekovalero@gmail.com"));
		assertFalse(mGUI.validarEmail("eneko"));
	}

	@Test
	public void validate(){
		r = MetodosGUI.validate("12345", "123456");
		assertEquals(Resp.MISSMATCH, r);
		r = MetodosGUI.validate("p1", "p1");
		assertEquals(Resp.LENGTH, r);
		r = MetodosGUI.validate("123456", "123456");
		assertEquals(Resp.VALID, r);
	}
	//Test Funcional: saca un popup cuando se ejecuta en terminal
//	@Test
//	public void mensajeErrorTest() {
//		JTextField tF = new JTextField("Prueba Test");
//		String mensaje = "Esto es una prueba de test";
//		assertFalse(mGUI.mensajeError(tF, mensaje));
//	}
	
//	@Test
//	public void comprobarVacioTest() {
//		JTextField tF1 = new JTextField();
//		tF1.setText("Prueba True");
//		JTextField tF2 = new JTextField();
//		tF2.setText("");
//		assertTrue(mGUI.comprobarVacio(tF1));
//		assertFalse(mGUI.comprobarVacio(tF2));
//	}
	
	@Test
	public void validarBuscarPisos() {
		String texto = "Bilbao";
		String texto2 = "Sevilla";
		String texto3 = "Barcelona";
		
		Piso p1 = new Piso("AAA", 2.0);
		Piso p2 = new Piso("BBB", 3.0);
		Piso p3 = new Piso("CCC", 4.0);
		p1.setLocalizacion("Bilbao");
		p2.setLocalizacion("Sevilla");
		p3.setLocalizacion("Bilbao");
		List<Piso> pisos = new ArrayList<Piso>();
		pisos.add(p1);
		pisos.add(p2);
		pisos.add(p3);
		
		assertTrue(mGUI.buscarPisos(texto, pisos).size() == 2);
		assertTrue(mGUI.buscarPisos(texto2, pisos).size() == 1);
		assertTrue(mGUI.buscarPisos(texto3, pisos).size() == 0);
		
	}
	
	@Test
	public void validarBuscarPosts() {
		
		String texto = "Aitor";
		String texto2 = "Ander";
		String texto3 = "Iñigo";
		
		Post p1 = new Post("AAA", "Aitor", "AAAAA");
		Post p2 = new Post("CCC", "Aitor", "CCCCC");
		Post p3 = new Post("DDD", "Ander", "DDDDD");

		List<Post> posts = new ArrayList<Post>();
		posts.add(p1);
		posts.add(p2);
		posts.add(p3);
		
		assertTrue(mGUI.buscarPosts(texto, posts).size() == 2);
		assertTrue(mGUI.buscarPosts(texto2, posts).size() == 1);
		assertTrue(mGUI.buscarPosts(texto3, posts).size() == 0);
	}
	
	@Test
	public void validarObtenerPiso() { 
		
		Piso p1 = new Piso("AAA", 2.0);
		Piso p2 = new Piso("BBB", 3.0);
		Piso p3 = new Piso("CCC", 4.0);
		
		p1.setLocalizacion("Bilbao");
		p2.setLocalizacion("Sevilla");
		p3.setLocalizacion("Bilbao");
		List<Piso> pisos = new ArrayList<Piso>();
		pisos.add(p1);
		pisos.add(p2);
		pisos.add(p3);
		
		assertTrue(mGUI.obtenerPiso("piso:AAA", pisos).getNombre() == "AAA");
		assertTrue(mGUI.obtenerPiso("Precio:AAA", pisos).getNombre() == "AAA");
		assertTrue(mGUI.obtenerPiso("Valora:AAA", pisos).getNombre() == "AAA");
	}
	
	@Test
	public void validarObtenerPost() {
		Post p1 = new Post("AAA", "BBB", "CCC");
		Post p2 = new Post ("EEE", "DDD", "FFF");
		List<Post> posts = new ArrayList<Post>();
		posts.add(p1);
		posts.add(p2);
		
		assertTrue(mGUI.obtenerPost("Titulo: AAA", posts).getTitulo() == "AAA" );
		assertTrue(mGUI.obtenerPost("Descripción: AAA", posts).getTitulo() == "AAA" );
		assertTrue(mGUI.obtenerPost("Dutor: AAA", posts).getTitulo() == "AAA" );
		assertTrue(mGUI.obtenerPost("Likes: AAA", posts).getTitulo() == "AAA" );
	}
	
	
}
