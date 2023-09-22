package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controlador.*;
import modelo.Huesped;
import modelo.Reserva;
import modelo.Usuario;
import utilidades.CalcularValorReserva;
import utilidades.CalendarCellEditor;
import utilidades.UtilSalir;
import utilidades.ValidarNumero;
import utilidades.ValidarTexto;

import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private JTable tbUsuarios;
	private DefaultTableModel modeloReserva;
	private DefaultTableModel modeloHuesped;
	private DefaultTableModel modeloUsuario;
	//private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private UsuarioController usuarioController;
	private ReservaController reservaController; 
	private HuespedController huespedController;
	private JTabbedPane panel; 
	private CalcularValorReserva calcularValorReserva;
	private boolean cargarDatos = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda() {
		usuarioController = new UsuarioController();
		reservaController = new ReservaController();
		huespedController = new HuespedController();
		calcularValorReserva = new CalcularValorReserva();
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int pestaña = panel.getSelectedIndex();
				switch (pestaña) {
					case 0:
						actualizarFiltro(tbReservas);
						break;
					case 1:
						actualizarFiltro(tbHuespedes);
						break;
					case 2:
						actualizarFiltro(tbUsuarios);
						break;
				}
			}
		});
//		txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
//			@Override
//			public void insertUpdate(DocumentEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void removeUpdate(DocumentEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void changedUpdate(DocumentEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		});

		txtBuscar.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtBuscar.setForeground(SystemColor.activeCaptionBorder);
		txtBuscar.setBounds(617, 127, 268, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 320, 42);
		contentPane.add(lblNewLabel_4);

		panel = new JTabbedPane(SwingConstants.TOP);
		panel.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				txtBuscar.setText("");
		        JTable tablaAnterior = (JTable) ((JScrollPane) panel.getSelectedComponent()).getViewport().getView();
		        tablaAnterior.clearSelection();
				int pestaña = panel.getSelectedIndex();
				switch (pestaña) {
				case 0:						
					listarReservas();
					break;
				case 1:	
					listarHuespedes();
					break;
				case 2:		
					listarUsuarios();
					break;
				}
			}
		});
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		tbReservas.setRowHeight(20);
		modeloReserva = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return !(column == 0 || column == 3);
			}
		};
		modeloReserva.addColumn("Numero de reserva");
		modeloReserva.addColumn("Fecha entrada");
		modeloReserva.addColumn("Fecha salida");
		modeloReserva.addColumn("Valor");
		modeloReserva.addColumn("Forma de pago");
		tbReservas.setModel(modeloReserva);
		tbReservas.getColumnModel().getColumn(1).setCellEditor(new CalendarCellEditor());
		tbReservas.getColumnModel().getColumn(2).setCellEditor(new CalendarCellEditor());
		tbReservas.getColumnModel().getColumn(3).setCellEditor(new ValidarNumero());
		tbReservas.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(formaDePago()));
		tbReservas.getModel().addTableModelListener(new TableModelListener() {
		    public void tableChanged(TableModelEvent e) {
		    	if(cargarDatos) {
		    		return;
		    	}
		        int col = e.getColumn();
		        if (col == 1 || col == 2) {
		        	calcularValor();
		        }
		    }
		});
		JScrollPane scroll_tableReservas = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), 
				scroll_tableReservas,null);
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		tbHuespedes.setRowHeight(20);
		modeloHuesped = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int fila, int columna) {
		        return columna != 0;
		    }
		};
		modeloHuesped.addColumn("Número de huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Teléfono");
		modeloHuesped.addColumn("Número de reserva");
		tbHuespedes.setModel(modeloHuesped);
		tbHuespedes.getColumnModel().getColumn(1).setCellEditor(new ValidarTexto());
		tbHuespedes.getColumnModel().getColumn(2).setCellEditor(new ValidarTexto());
		tbHuespedes.getColumnModel().getColumn(3).setCellEditor(new CalendarCellEditor());
		tbHuespedes.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(nacionalidad()));
		tbHuespedes.getColumnModel().getColumn(5).setCellEditor(new ValidarNumero());
		tbHuespedes.getColumnModel().getColumn(6).setCellEditor(new ValidarNumero());
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		
		if("Administrador".equals(Login.tipoUsuario)) {
			tbUsuarios = new JTable();
			tbUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tbUsuarios.setFont(new Font("Roboto", Font.PLAIN, 16));
			tbUsuarios.setRowHeight(20);
			modeloUsuario = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					return column != 0;
				}
			};
			modeloUsuario.addColumn("Numero de Usuario");
			modeloUsuario.addColumn("Usuario");
			modeloUsuario.addColumn("Tipo de Usuario");
			tbUsuarios.setModel(modeloUsuario);
			tbUsuarios.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(tipoUsuario()));
			JScrollPane scroll_tableUsuarios = new JScrollPane(tbUsuarios);
			panel.addTab("Usuarios", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), 
					scroll_tableUsuarios,null);			
		}
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

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
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

//		JPanel btnAtras = new JPanel();
//		btnAtras.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				MenuUsuario usuario = new MenuUsuario();
//				usuario.setVisible(true);
//				dispose();
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				btnAtras.setBackground(new Color(12, 138, 199));
//				labelAtras.setForeground(Color.white);
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				btnAtras.setBackground(Color.white);
//				labelAtras.setForeground(Color.black);
//			}
//		});
//		btnAtras.setLayout(null);
//		btnAtras.setBackground(Color.WHITE);
//		btnAtras.setBounds(0, 0, 53, 36);
//		header.add(btnAtras);
//
//		labelAtras = new JLabel("<");
//		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
//		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
//		labelAtras.setBounds(0, 0, 53, 36);
//		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(UtilSalir.confirmarSalir(contentPane) == 0) {
					MenuUsuario usuario = new MenuUsuario();
					usuario.setVisible(true);
					dispose();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(620, 159, 265, 2);
		contentPane.add(separator_1_2);

//		JPanel btnbuscar = new JPanel();
//		btnbuscar.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//
//			}
//		});
//		btnbuscar.setLayout(null);
//		btnbuscar.setBackground(new Color(12, 138, 199));
//		btnbuscar.setBounds(503, 508, 122, 35);
//		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
//		contentPane.add(btnbuscar);

		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int pestaña = panel.getSelectedIndex();
				switch (pestaña) {
					case 0:
						modificarReserva();
						break;
					case 1:
						modificarHuesped();
						break;
					case 2:
						modificarUsuario();
						break;
				}
			}
		});
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int pestaña = panel.getSelectedIndex();
				switch (pestaña) {
					case 0:
						eliminarReserva();
						break;
					case 1:
						eliminarHuesped();
						break;
					case 2:
						eliminarUsuario();
						break;
				}
			}
		});
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(485, 127, 122, 35);
		contentPane.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(new Color(12, 138, 199));
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		setResizable(false);
	}


//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		// int x = evt.getXOnScreen();
		// int y = evt.getYOnScreen();
		// this.setLocation(x - xMouse, y - yMouse);

		Point punto = MouseInfo.getPointerInfo().getLocation();
		this.setLocation(punto.x - xMouse, punto.y - yMouse);
	}
	
	private void listarUsuarios() {
		modeloUsuario.getDataVector().clear();
		tbUsuarios.setRowSorter(null);
		List<Usuario> lista = usuarioController.listarUsuarios();
		for (Usuario usuario : lista) {
			modeloUsuario.addRow(new Object[] {
					usuario.getId(),
					usuario.getUsuario(),
					usuario.getTipoUsuario()
			});
		}
	}
	
	private void listarReservas() {
		cargarDatos = true;
		modeloReserva.getDataVector().clear();
		tbReservas.setRowSorter(null);
		List<Reserva> lista = reservaController.listarReservas();
		for (Reserva reserva : lista) {
			modeloReserva.addRow(new Object[] {
					reserva.getId(),
					reserva.getFechaEntrada(),
					reserva.getFechaSalida(),
					reserva.getValor(),
					reserva.getFormaDePago()
			});
		}
		cargarDatos = false;
	}
	
	private void listarHuespedes() {
		modeloHuesped.getDataVector().clear();
		tbHuespedes.setRowSorter(null);
		List<Huesped> lista = huespedController.listarHuespedes();
		for (Huesped huesped : lista) {
			modeloHuesped.addRow(new Object[] {
					huesped.getId(),
					huesped.getNombre(),
					huesped.getApellido(),
					huesped.getFechaNacimiento(),
					huesped.getNacionalidad(),
					huesped.getTelefono(),
					huesped.getIdReserva()
			});
		}
	}
	
	private void eliminarUsuario() {
		if(tbUsuarios.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione el registro que desea eliminar");
            return;
		}
        Optional.ofNullable(modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), tbUsuarios.getSelectedColumn()))
        .ifPresentOrElse(fila -> {
            Integer id = Integer.valueOf(modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), 0).toString());
			this.usuarioController.eliminarUsuario(id);
			modeloUsuario.removeRow(tbUsuarios.getSelectedRow());
            JOptionPane.showMessageDialog(this, "Registro eliminado con éxito!");
        }, null);
	}
	
	private void eliminarReserva() {
		if(tbReservas.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione el registro que desea eliminar");
            return;
		}
        Optional.ofNullable(modeloReserva.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
        .ifPresentOrElse(fila -> {
            Integer id = Integer.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 0).toString());
			this.reservaController.eliminarReserva(id);
			modeloReserva.removeRow(tbReservas.getSelectedRow());
            JOptionPane.showMessageDialog(this, "Registro eliminado con éxito!");
        }, null);
	}
	
	private void eliminarHuesped() {
		if(tbHuespedes.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione el registro que desea eliminar");
            return;
		}
        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
        .ifPresentOrElse(fila -> {
            Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
			this.huespedController.eliminarReserva(id);
			modeloHuesped.removeRow(tbHuespedes.getSelectedRow());
            JOptionPane.showMessageDialog(this, "Registro eliminado con éxito!");
        }, null);
	}
	
	private void modificarUsuario() {
		if(tbUsuarios.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione el registro que desea actualizar");
            return;
		}
        Optional.ofNullable(modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), tbUsuarios.getSelectedColumn()))
        .ifPresentOrElse(fila -> {
            Integer id = Integer.valueOf(modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), 0).toString());
            String user = (String) modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), 1);
            String tipoUsuario = (String)modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), 2);                   
            Usuario usuario = new Usuario(); 
            usuario.setId(id);
            usuario.setUsuario(user);
            usuario.setTipoUsuario(tipoUsuario);
            this.usuarioController.modificarUsuario(usuario);
            JOptionPane.showMessageDialog(this, "Registro actualizado con éxito!");
            listarUsuarios();
        },null);
	}
	
	private void modificarHuesped() {
		if(tbHuespedes.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione el registro que desea actualizar");
            return;
		}
        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
        .ifPresentOrElse(fila -> {
            Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
            String nombre = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1);
            String apellido = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2);
            String fechaNacimiento = String.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3));                   
            String nacionalidad = (String)modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4);                   
            String telefono = (String)modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5);                   
            Integer idReserva = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());                   
            Huesped huesped = new Huesped(id, nombre, apellido, java.sql.Date.valueOf(fechaNacimiento), nacionalidad, telefono, idReserva); 
            this.huespedController.modificarHuesped(huesped);
            JOptionPane.showMessageDialog(this, "Registro actualizado con éxito!");
            listarHuespedes();
        },null);
	}
	
	private void modificarReserva() {
		if(tbReservas.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione el registro que desea actualizar");
            return;
		}
        Optional.ofNullable(modeloReserva.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
        .ifPresentOrElse(fila -> {
            Integer id = Integer.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 0).toString());
            String fechaEntrada = String.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 1));
            String fechaSalida = String.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 2));
            String valor = (String) modeloReserva.getValueAt(tbReservas.getSelectedRow(), 3);                  
            String formaDePago = (String)modeloReserva.getValueAt(tbReservas.getSelectedRow(), 4);                                     
            Reserva reserva = new Reserva(id, java.sql.Date.valueOf(fechaEntrada), java.sql.Date.valueOf(fechaSalida), Float.parseFloat(valor), formaDePago);
            this.reservaController.modificarReserva(reserva);
            JOptionPane.showMessageDialog(this, "Registro actualizado con éxito!");
            listarReservas();
        },null);
	}
	
    private void actualizarFiltro(JTable tabla) {
        String filtro = txtBuscar.getText();
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tabla.getModel());
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filtro, 
                IntStream.range(0, tabla.getModel().getColumnCount()).toArray()));
        tabla.setRowSorter(sorter);
    }
    
    private JComboBox<String> tipoUsuario() {
    	JComboBox<String> cbTipoUsuario = new JComboBox<String>();
		cbTipoUsuario.setBackground(Color.WHITE);
		cbTipoUsuario.setModel(new DefaultComboBoxModel<String>(new String[] { "Empleado", "Administrador" }));
		cbTipoUsuario.setFont(new Font("Roboto", Font.PLAIN, 14));
		cbTipoUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		return cbTipoUsuario;
    }
    
    private JComboBox<String> formaDePago(){
    	JComboBox<String> cbFormaPago = new JComboBox<String>();
    	cbFormaPago.setBackground(Color.WHITE);
    	cbFormaPago.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    	cbFormaPago.setFont(new Font("Roboto", Font.PLAIN, 14));
    	cbFormaPago.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo" }));
		return cbFormaPago;
    }
    
    private JComboBox<String> nacionalidad(){
    	JComboBox<String> cbNacionalidad = new JComboBox<String>();
    	cbNacionalidad.setBackground(Color.WHITE);
    	cbNacionalidad.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    	cbNacionalidad.setFont(new Font("Roboto", Font.PLAIN, 14));
    	cbNacionalidad.setModel(new DefaultComboBoxModel<String>(new String[] { "afgano-afgana", "alemán-", "alemana",
				"árabe-árabe", "argentino-argentina", "australiano-australiana", "belga-belga", "boliviano-boliviana",
				"brasileño-brasileña", "camboyano-camboyana", "canadiense-canadiense", "chileno-chilena", "chino-china",
				"colombiano-colombiana", "coreano-coreana", "costarricense-costarricense", "cubano-cubana",
				"danés-danesa", "ecuatoriano-ecuatoriana", "egipcio-egipcia", "salvadoreño-salvadoreña",
				"escocés-escocesa", "español-española", "estadounidense-estadounidense", "estonio-estonia",
				"etiope-etiope", "filipino-filipina", "finlandés-finlandesa", "francés-francesa", "galés-galesa",
				"griego-griega", "guatemalteco-guatemalteca", "haitiano-haitiana", "holandés-holandesa",
				"hondureño-hondureña", "indonés-indonesa", "inglés-inglesa", "iraquí-iraquí", "iraní-iraní",
				"irlandés-irlandesa", "israelí-israelí", "italiano-italiana", "japonés-japonesa", "jordano-jordana",
				"laosiano-laosiana", "letón-letona", "letonés-letonesa", "malayo-malaya", "marroquí-marroquí",
				"mexicano-mexicana", "nicaragüense-nicaragüense", "noruego-noruega", "neozelandés-neozelandesa",
				"panameño-panameña", "paraguayo-paraguaya", "peruano-peruana", "polaco-polaca", "portugués-portuguesa",
				"puertorriqueño-puertorriqueño", "dominicano-dominicana", "rumano-rumana", "ruso-rusa", "sueco-sueca",
				"suizo-suiza", "tailandés-tailandesa", "taiwanes-taiwanesa", "turco-turca", "ucraniano-ucraniana",
				"uruguayo-uruguaya", "venezolano-venezolana", "vietnamita-vietnamita" }));
    	return cbNacionalidad;
    }
    
	private void calcularValor() {
		int filaSeleccionada = tbReservas.getSelectedRow();
		if(filaSeleccionada != -1) {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	        String fechaEntrada = String.valueOf(modeloReserva.getValueAt(filaSeleccionada, 1));
	        String fechaSalida = String.valueOf(modeloReserva.getValueAt(filaSeleccionada, 2));
	        Date FechaE = null;
	        Date FechaS = null;
	        try {
		        FechaE = formato.parse(fechaEntrada);
		        FechaS = formato.parse(fechaSalida);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			if(FechaE != null && FechaS != null) {
				String valor = calcularValorReserva.calcularValor(FechaE, FechaS);
				modeloReserva.setValueAt(valor, filaSeleccionada, 3);
			}
		}
	}
}
