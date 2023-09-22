package utilidades;

import java.awt.Component;
import javax.swing.JOptionPane;

public class UtilSalir {
	public static int confirmarSalir(Component ventana) {
		return JOptionPane.showConfirmDialog(ventana,
				"¿Está seguro que desea salir?",
				"Salir",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE);
	}
}
