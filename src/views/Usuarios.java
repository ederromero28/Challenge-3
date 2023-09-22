package views;

import java.awt.Color;
import java.awt.Cursor;
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

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controlador.UsuarioController;
import modelo.Usuario;
import utilidades.UtilSalir;

@SuppressWarnings("serial")
public class Usuarios extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtContrasenia;
	int xMouse, yMouse;
	private JLabel lblSalir;
	//private JLabel lblAtras;
	private JComboBox<String> cbTipoUsuario;
	private UsuarioController usuarioController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios usuarios = new Usuarios();
					usuarios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Usuarios() {
		usuarioController = new UsuarioController();
		setMinimumSize(new Dimension(910, 480));
		setMaximumSize(new Dimension(910, 480));
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/imagenes/aH-40px.png")));
		setUndecorated(true);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 910, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 480);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(12, 138, 199));
		panel_1.setBounds(0, 0, 480, 480);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(Usuarios.class.getResource("/imagenes/Ha-100px.png")));
		logo.setBounds(197, 40, 104, 107);
		panel_1.add(logo);

//		JPanel btnAtras = new JPanel();
//		btnAtras.setBounds(0, 0, 53, 36);
//		panel_1.add(btnAtras);
//		btnAtras.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				MenuUsuario menuUsuario = new MenuUsuario();
//				menuUsuario.setVisible(true);
//				dispose();
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				btnAtras.setBackground(Color.red);
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				btnAtras.setBackground(new Color(12, 138, 199));
//			}
//		});
//		btnAtras.setBackground(new Color(12, 138, 199));
//		btnAtras.setLayout(null);
//
//		lblAtras = new JLabel("<");
//		lblAtras.setForeground(Color.white);
//		lblAtras.setBounds(0, 0, 53, 36);
//		btnAtras.add(lblAtras);
//		lblAtras.setHorizontalAlignment(SwingConstants.CENTER);
//		lblAtras.setFont(new Font("Roboto", Font.PLAIN, 23));

		JLabel imagenFondo = new JLabel("");
		imagenFondo.setIcon(new ImageIcon(Usuarios.class.getResource("/imagenes/reservas-img-3.png")));
		imagenFondo.setBounds(0, 158, 470, 300);
		panel_1.add(imagenFondo);

		JLabel lblTitulo = new JLabel("REGISTRO USUARIOS");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
		lblTitulo.setBounds(606, 55, 234, 42);
		panel.add(lblTitulo);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBackground(SystemColor.textHighlight);
		separator_1.setBounds(560, 178, 289, 2);
		panel.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(SystemColor.textHighlight);
		separator_2.setBackground(SystemColor.textHighlight);
		separator_2.setBounds(560, 259, 289, 2);
		panel.add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(SystemColor.textHighlight);
		separator_3.setBackground(SystemColor.textHighlight);
		separator_3.setBounds(560, 338, 289, 2);
		panel.add(separator_3);

		JLabel lblUsuario = new JLabel("USUARIO");
		lblUsuario.setForeground(SystemColor.textInactiveText);
		lblUsuario.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		lblUsuario.setBounds(560, 119, 253, 18);
		panel.add(lblUsuario);

		JLabel lblContrasea = new JLabel("CONTRASEÑA");
		lblContrasea.setForeground(SystemColor.textInactiveText);
		lblContrasea.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		lblContrasea.setBounds(560, 200, 253, 18);
		panel.add(lblContrasea);

		JLabel lblTipoUsuario = new JLabel("TIPO USUARIO");
		lblTipoUsuario.setForeground(SystemColor.textInactiveText);
		lblTipoUsuario.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		lblTipoUsuario.setBounds(560, 279, 169, 18);
		panel.add(lblTipoUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtUsuario.setBounds(560, 143, 289, 35);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);

		txtContrasenia = new JPasswordField();
		txtContrasenia.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtContrasenia.setBounds(560, 224, 289, 35);
		panel.add(txtContrasenia);

		cbTipoUsuario = new JComboBox<String>();
		cbTipoUsuario.setBackground(Color.WHITE);
		cbTipoUsuario.setModel(new DefaultComboBoxModel<String>(new String[] { "Empleado", "Administrador" }));
		cbTipoUsuario.setFont(new Font("Roboto", Font.PLAIN, 16));
		cbTipoUsuario.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		cbTipoUsuario.setBounds(560, 303, 289, 35);
		panel.add(cbTipoUsuario);

		JPanel btnGuardar = new JPanel();
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardar.setBackground(SystemColor.textHighlight);
		btnGuardar.setBounds(727, 358, 122, 35);
		panel.add(btnGuardar);
		btnGuardar.setLayout(null);

		JLabel lblGuardar = new JLabel("GUARDAR");
		lblGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				guardar();
				limpiar();
			}
		});
		lblGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuardar.setForeground(Color.WHITE);
		lblGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblGuardar.setBounds(0, 0, 122, 35);
		btnGuardar.add(lblGuardar);

		JPanel btnSalir = new JPanel();
		btnSalir.setBounds(857, 0, 53, 36);
		panel.add(btnSalir);
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (UtilSalir.confirmarSalir(contentPane) == 0) {
					MenuUsuario usuario = new MenuUsuario();
					usuario.setVisible(true);
					dispose();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnSalir.setBackground(Color.red);
				lblSalir.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSalir.setBackground(Color.white);
				lblSalir.setForeground(Color.black);
			}
		});
		btnSalir.setBackground(Color.WHITE);

		lblSalir = new JLabel("X");
		lblSalir.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalir.setForeground(Color.BLACK);
		lblSalir.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnSalir.add(lblSalir);

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
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		panel.add(header);
		header.setLayout(null);
	}

	private void guardar() {
		if (txtUsuario.getText().isEmpty() || txtContrasenia.getPassword().length == 0) {
			JOptionPane.showMessageDialog(this, "Los campos de usuario y contraseña son obligatorios");
			return;
		}
		if(txtUsuario.getText().equals(usuarioController.buscarUsuario(txtUsuario.getText()))) {
			JOptionPane.showMessageDialog(contentPane, "El usuario ya existe", "Registro usuario",JOptionPane.WARNING_MESSAGE);
			return;
		}
		Usuario usuario = new Usuario(txtUsuario.getText(), 
				String.valueOf(txtContrasenia.getPassword()),
				cbTipoUsuario.getSelectedItem().toString());
		usuarioController.guardarUsuario(usuario);
		JOptionPane.showMessageDialog(this, "Usuario registrado correctamente con ID: " + usuario.getId(), "Registro usuario",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		Point punto = MouseInfo.getPointerInfo().getLocation();
		this.setLocation(punto.x - xMouse, punto.y - yMouse);
	}

	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void limpiar() {
		txtUsuario.setText("");
		txtContrasenia.setText("");
		cbTipoUsuario.setSelectedIndex(0);
	}
}
