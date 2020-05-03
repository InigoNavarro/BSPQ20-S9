package es.deusto.spq.data;

import java.util.ArrayList;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Usuario {

	@PrimaryKey
	private String nickname;  		//La usamos como primaryKey
	
	private String nombre;	
	private String apellidos;		//Apellido1 + " " + Apellido2
	
	@PrimaryKey
	private String email;
	
	
	private String pw1;				//TODO encriptar pw.
	private Piso piso;				//Si el usuario solo puede tener un piso alquilado.
	private boolean tienePiso;		
	
	private ArrayList<MensajePrivado> mensajes;
	
	
	
	public Piso getPiso() {
		return piso;
	}



	public void setPiso(Piso piso) {
		this.piso = piso;
	}



	public boolean isTienePiso() {
		return tienePiso;
	}



	public void setTienePiso(boolean tienePiso) {
		this.tienePiso = tienePiso;
	}



	/** Constructor de la clase Usuario
	 * @param nickname 
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param pw1
	 */
	public Usuario(String nickname, String nombre, String apellidos, String email, String pw1) {
		this.setNickname(nickname);
		this.setNombre(nombre);
		this.setApellidos(apellidos);
		this.setEmail(email);
		this.setPw1(pw1);
	}

	public Usuario(String nickname,String pw1) {
		this.setNickname(nickname);
		this.setPw1(pw1);
	}

	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellidos() {
		return apellidos;
	}



	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPw1() {
		return pw1;
	}



	public void setPw1(String pw1) {
		this.pw1 = pw1;
	}



	public ArrayList<MensajePrivado> getMensajes() {
		return mensajes;
	}



	public void setMensajes(ArrayList<MensajePrivado> mensajes) {
		this.mensajes = mensajes;
	}

	
}
