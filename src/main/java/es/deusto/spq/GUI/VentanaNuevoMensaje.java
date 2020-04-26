package es.deusto.spq.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.ws.rs.client.Client;

import es.deusto.spq.data.Mensaje;

public class VentanaNuevoMensaje extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Client client;
	private MetodosGUI m = new MetodosGUI();
	
	private int ancho = 620;
	private int alto = 480;
	
	public VentanaNuevoMensaje(String hostname, String port) {
		
		setSize(ancho, alto);
		setTitle("Lista de mensajes");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);
		
		 
		JLabel titulo = new JLabel();
		titulo.setText("Titulo");
        titulo.setBounds(23, 20, 200, 20);
        getContentPane().add(titulo);
        
        
        JTextArea tAtitulo = new JTextArea("Introduce el titulo");
        tAtitulo.setBounds(223, 20, 300 , 20);
	    getContentPane().add(tAtitulo);
	    
	    
		JLabel cuerpo = new JLabel();
	    cuerpo.setText("Mensaje");
	    cuerpo.setBounds(23,  100, 200 , 20);
	    getContentPane().add(cuerpo);
	    
	   
		JTextArea tCuerpo = new JTextArea("Introduce el mensaje");
		tCuerpo.setBounds(10, 150, ancho-10, 200);
		getContentPane().add(tCuerpo);
        
        JButton botonEnviar = new JButton("Enviar");
        botonEnviar.setBounds(10,360, 100, 20);
        getContentPane().add(botonEnviar);
        
        setVisible(true);
		
		
        botonEnviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Mensaje m = new Mensaje("id", tAtitulo.getText(), tCuerpo.getText());
				System.out.println("Mensaje enviado");
			}
		});
		
		
	}
	
	public static void main(String[] args) {
		new VentanaNuevoMensaje(null, null);
	}

}
