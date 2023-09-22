package utilidades;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ValidarTexto extends DefaultCellEditor {
    JTextField textField;    
    public ValidarTexto() {
        super(new JTextField());
        textField = (JTextField) getComponent();
        textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
			    int key = e.getKeyChar();

			    boolean mayusculas = key >= 65 && key <= 90;
			    boolean minusculas = key >= 97 && key <= 122;
			    boolean espacio = key == 32;
			            
			     if (!(minusculas || mayusculas || espacio))
			    {
			        e.consume();
			    }
            }
        });
    }
}
