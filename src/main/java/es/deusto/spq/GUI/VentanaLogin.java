package es.deusto.spq.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import es.deusto.spq.GUI.MetodosGUI;
import es.deusto.spq.data.Piso;
import es.deusto.spq.data.Usuario;
import es.deusto.spq.server.DAOFactory;
import es.deusto.spq.server.SendEmail;
import javax.swing.JPasswordField;

public class VentanaLogin {

	private JFrame frame;
	private JTextField tFLogin;
	private JPasswordField tFCont;
	private JTextField tFCorreo;
	private Client client;
	MetodosGUI mGUI = new MetodosGUI();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		String hostname = args[0];
		String port = args[1];
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin window = new VentanaLogin(hostname, port);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaLogin(String hostname, String port) {
		initialize(hostname, port);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String hostname, String port) {
		
		DAOFactory.getInstance().createPisoDAO().crearAlgunosDatos();
        DAOFactory.getInstance().createPostDAO().crearPosts();

        client = ClientBuilder.newClient();
        
		frame = new JFrame();
		frame.setBounds(100, 100, 637, 456);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelPrincipal = new JPanel();
		frame.getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);
		
		JLabel lblLoginTit = new JLabel("Usuario:");
		lblLoginTit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLoginTit.setBounds(124, 136, 90, 30);
		panelPrincipal.add(lblLoginTit);
		
		tFLogin = new JTextField();
		tFLogin.setBounds(293, 141, 140, 20);
		panelPrincipal.add(tFLogin);
		tFLogin.setColumns(10);
		
		JLabel lblContrasenya = new JLabel("Contraseña:");
		lblContrasenya.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblContrasenya.setBounds(123, 199, 90, 30);
		panelPrincipal.add(lblContrasenya);
		
		tFCont = new JPasswordField();
		tFCont.setColumns(10);
		tFCont.setBounds(293, 205, 140, 20);
		panelPrincipal.add(tFCont);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFocusPainted(false);
		btnLogin.setBounds(356, 262, 89, 23);
		panelPrincipal.add(btnLogin);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setFocusPainted(false);
		btnRegistrarse.setBounds(107, 262, 107, 23);
		panelPrincipal.add(btnRegistrarse);
		
		JLabel lblRecContr = new JLabel("¿No recuerdas tu contraseña?");
		lblRecContr.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRecContr.setBounds(10, 353, 204, 20);
		panelPrincipal.add(lblRecContr);
		
		JLabel lblCorreoTit = new JLabel("Introduce tu correo:");
		lblCorreoTit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCorreoTit.setBounds(10, 383, 122, 20);
		panelPrincipal.add(lblCorreoTit);
		
		tFCorreo = new JTextField();
		tFCorreo.setColumns(10);
		tFCorreo.setBounds(142, 381, 140, 20);
		panelPrincipal.add(tFCorreo);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setFocusPainted(false);
		btnEnviar.setBounds(309, 380, 89, 23);
		panelPrincipal.add(btnEnviar);

		btnEnviar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String generatedCode = SendEmail.enviarMail(tFCorreo.getText());
				String inputCode = JOptionPane.showInputDialog(frame, "Introduce el código recibido: ");

				if(generatedCode.equals(inputCode)){
					new VentanaResetPassword();
				}else{
					JOptionPane.showMessageDialog(null, "Wrong code", "oops", JOptionPane.ERROR_MESSAGE);
				}

			}

		
		});
		
		btnRegistrarse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new VentanaRegistro(hostname, port);
				
			}
		});
		
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!mGUI.validarUsuario(tFLogin.getText())) {
					mGUI.mensajeError(tFLogin, "Introduce un usuario valido");
				}
				String password1 = new String(tFCont.getPassword());
				if(!mGUI.validarContrasenya(password1)){
					mGUI.mensajeError(tFCont, "Introduce una contraseña valida (8 o más caracteres)");
				}
				if(mGUI.validarUsuario(tFLogin.getText()) && mGUI.validarContrasenya(password1)) {
					List<Piso> pisos = new ArrayList<Piso>();
					pisos = mGUI.getPisos(client.target(String.format("http://%s:%s/rest", hostname, port)));
					String password = new String(tFCont.getPassword());
					Usuario u1 = new Usuario(tFLogin.getText(), password);
					frame.dispose();
					new VentanaListaPisos(pisos, pisos, hostname, port, u1);
				}
				
				
			}
		});
	}
}
