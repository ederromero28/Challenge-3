package utilidades;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ValidarNumero extends DefaultCellEditor {
    JTextField textField;    
    public ValidarNumero() {
        super(new JTextField());
        textField = (JTextField) getComponent();
        textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
			    int key = e.getKeyChar();

			    boolean numeros = key >= 48 && key <= 57;
			        
			    if (!numeros)
			    {
			        e.consume();
			    }

			    if (textField.getText().trim().length() == 10) {
			        e.consume();
			    }
            }
        });
    }
}
