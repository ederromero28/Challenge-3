package utilidades;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class CalendarCellEditor extends AbstractCellEditor implements TableCellEditor {
    private JDateChooser dateChooser;
    private SimpleDateFormat dateFormat;

    public CalendarCellEditor() {
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public Object getCellEditorValue() {
        Date selectedDate = dateChooser.getDate();
        String formattedDate = dateFormat.format(selectedDate);
        return formattedDate;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value instanceof Date) {
            dateChooser.setDate((Date) value);
        }
        return dateChooser;
    }
}




