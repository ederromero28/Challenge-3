package utilidades;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class UtilAjustarImagen {
	
	public static void AjustarImagenLabel(JLabel label, String ruta) {
		ImageIcon imagen = new ImageIcon(ruta);
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
		label.setIcon(icono);
	}
}