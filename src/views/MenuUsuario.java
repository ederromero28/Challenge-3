package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import controlador.UsuarioController;

import javax.swing.JPasswordField;
import utilidades.UtilSalir;

@SuppressWarnings("serial")
public class MenuUsuario extends JFrame {

	private JPanel contentPane;
	int xMouse, yMouse;
	private JLabel labelExit;
	private JLabel lblCambiarContrasena;
	private UsuarioController usuarioController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MenuUsuario frame = new MenuUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuUsuario() {
		usuarioController = new UsuarioController();
		setMinimumSize(new Dimension(944, 609));
		setMaximumSize(new Dimension(944, 609));
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuUsuario.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 944, 609);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});

		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(12, 138, 199));
		panelMenu.setBounds(0, 0, 257, 609);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(50, 58, 150, 150);
		panelMenu.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/aH-150px.png")));
		
		JPanel btnRegistro = new JPanel();
		btnRegistro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRegistro.setBackground(new Color(118, 187, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnRegistro.setBackground(new Color(12, 138, 199));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				ReservasView reservas = new ReservasView();
				reservas.setVisible(true);
				dispose();
			}
		});
		btnRegistro.setBounds(0, 255, 257, 56);
		btnRegistro.setBackground(new Color(12, 138, 199));
		panelMenu.add(btnRegistro);
		btnRegistro.setLayout(null);

		JLabel labelRegistro = new JLabel("Registro de reservas");
		labelRegistro.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/reservado.png")));
		labelRegistro.setForeground(SystemColor.text);
		labelRegistro.setBounds(25, 11, 205, 34);
		labelRegistro.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegistro.add(labelRegistro);

		JPanel btnBusqueda = new JPanel();
		btnBusqueda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBusqueda.setBackground(new Color(118, 187, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnBusqueda.setBackground(new Color(12, 138, 199));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Busqueda busqueda = new Busqueda();
				busqueda.setVisible(true);
				dispose();
			}
		});
		btnBusqueda.setBounds(0, 312, 257, 56);
		btnBusqueda.setBackground(new Color(12, 138, 199));
		panelMenu.add(btnBusqueda);
		btnBusqueda.setLayout(null);

		JLabel lblBusquedaDeReservas = new JLabel("Búsqueda");
		lblBusquedaDeReservas.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/pessoas.png")));
		lblBusquedaDeReservas.setBounds(27, 11, 200, 34);
		lblBusquedaDeReservas.setHorizontalAlignment(SwingConstants.LEFT);
		lblBusquedaDeReservas.setForeground(Color.WHITE);
		lblBusquedaDeReservas.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnBusqueda.add(lblBusquedaDeReservas);

		JSeparator separator = new JSeparator();
		separator.setBounds(26, 219, 201, 2);
		panelMenu.add(separator);
		
		if("Administrador".equals(Login.tipoUsuario)) {
			JPanel btnUsuario = new JPanel();
			btnUsuario.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Usuarios usuarios = new Usuarios();
					usuarios.setVisible(true);
					dispose();
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					btnUsuario.setBackground(new Color(118, 187, 223));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					btnUsuario.setBackground(new Color(12, 138, 199));
				}
			});
			btnUsuario.setBackground(new Color(12, 138, 199));
			btnUsuario.setBounds(0, 369, 257, 56);
			panelMenu.add(btnUsuario);
			btnUsuario.setLayout(null);

			JLabel lblUsuario = new JLabel("Usuarios");
			lblUsuario.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/usuario31x31.png")));
			lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
			lblUsuario.setForeground(Color.WHITE);
			lblUsuario.setFont(new Font("Roboto", Font.PLAIN, 18));
			lblUsuario.setBounds(27, 11, 200, 34);
			btnUsuario.add(lblUsuario);

		}
		
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 944, 36);
		contentPane.add(header);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (UtilSalir.confirmarSalir(contentPane) == 0) {
					Login login = new Login();
					login.setVisible(true);
					dispose();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});

		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(891, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel panelFecha = new JPanel();
		panelFecha.setBackground(new Color(118, 187, 223));
		panelFecha.setBounds(256, 84, 688, 121);
		contentPane.add(panelFecha);
		panelFecha.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Sistema de reservas Hotel Alura");
		lblNewLabel_1.setBounds(180, 11, 356, 42);
		panelFecha.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Roboto", Font.PLAIN, 24));

		JLabel labelFecha = new JLabel("New label");
		labelFecha.setBounds(35, 64, 294, 36);
		panelFecha.add(labelFecha);
		labelFecha.setForeground(Color.WHITE);
		labelFecha.setFont(new Font("Roboto", Font.PLAIN, 33));
		Date fechaActual = new Date(); // fecha y hora actual
		String fecha = new SimpleDateFormat("dd/MM/yyyy").format(fechaActual); // formatear la fecha en una cadena
		labelFecha.setText("Hoy es " + fecha); // setear la representacion en cadena de la fecha

		JLabel lblNewLabel = new JLabel("Bienvenido");
		lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 24));
		lblNewLabel.setBounds(302, 234, 147, 46);
		contentPane.add(lblNewLabel);

		String textoDescripcion = "<html><body>Sistema de reserva de hotel. Controle y administre de forma óptima y fácil <br> el flujo de reservas y de huespédes del hotel   </body></html>";
		JLabel labelDescripcion = new JLabel(textoDescripcion);
		labelDescripcion.setFont(new Font("Roboto", Font.PLAIN, 17));

		labelDescripcion.setBounds(312, 291, 598, 66);
		contentPane.add(labelDescripcion);

		String textoDescripcion1 = "<html><body> Esta herramienta le permitirá llevar un control completo y detallado de sus reservas y huéspedes, tendrá acceso a heramientas especiales para tareas específicas como lo son:</body></html>";
		JLabel labelDescripcion_1 = new JLabel(textoDescripcion1);
		labelDescripcion_1.setFont(new Font("Roboto", Font.PLAIN, 17));
		labelDescripcion_1.setBounds(311, 345, 569, 88);
		contentPane.add(labelDescripcion_1);

		JLabel lblNewLabel_3 = new JLabel("- Registro de Reservas y Huéspedes");
		lblNewLabel_3.setFont(new Font("Roboto", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(312, 444, 295, 27);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("- Edición de Reservas y Huéspedes existentes");
		lblNewLabel_3_1.setFont(new Font("Roboto", Font.PLAIN, 17));
		lblNewLabel_3_1.setBounds(312, 482, 355, 27);
		contentPane.add(lblNewLabel_3_1);

		JLabel lblNewLabel_3_2 = new JLabel("- Eliminar todo tipo de registros");
		lblNewLabel_3_2.setFont(new Font("Roboto", Font.PLAIN, 17));
		lblNewLabel_3_2.setBounds(312, 520, 295, 27);
		contentPane.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_4 = new JLabel("Usuario: " + Login.user);
		lblNewLabel_4.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblNewLabel_4.setBounds(267, 47, 355, 26);
		contentPane.add(lblNewLabel_4);
		
		JPanel cambiarContrasena = new JPanel();
		cambiarContrasena.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cambiarContrasena();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				cambiarContrasena.setBackground(Color.gray);
				lblCambiarContrasena.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cambiarContrasena.setBackground(Color.white);
				lblCambiarContrasena.setForeground(Color.black);
			}
		});
		cambiarContrasena.setLayout(null);
		cambiarContrasena.setBackground(Color.WHITE);
		cambiarContrasena.setBounds(784, 45, 160, 30);
		contentPane.add(cambiarContrasena);
		
		lblCambiarContrasena = new JLabel("Cambiar Contraseña");
		lblCambiarContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblCambiarContrasena.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblCambiarContrasena.setBounds(0, 0, 160, 30);
		cambiarContrasena.add(lblCambiarContrasena);
	}

	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}// GEN-LAST:event_headerMousePressed

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		// int x = evt.getXOnScreen();
		// int y = evt.getYOnScreen();
		// this.setLocation(x - xMouse, y - yMouse);

		Point punto = MouseInfo.getPointerInfo().getLocation();
		this.setLocation(punto.x - xMouse, punto.y - yMouse);
	}
	
	private void cambiarContrasena() {
	    JPanel panel = new JPanel();
	    JLabel label = new JLabel("Contraseña:");
	    JPasswordField passwordField = new JPasswordField(20);
	    panel.add(label);
	    panel.add(passwordField);
	    String[] options = new String[]{"Cambiar", "Cancelar"};

	    int option = JOptionPane.showOptionDialog(null, panel, "Cambio de Contraseña",
	            JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
	            null, options, options[1]);
	    if(option == 0){
	    	usuarioController.cambiarContrasena(Login.user, String.valueOf(passwordField.getPassword()));
			JOptionPane.showMessageDialog(this, "Contraseña actualizada correctamente", "Cambio de Contraseña",
					JOptionPane.INFORMATION_MESSAGE);
	    }
	}
}
