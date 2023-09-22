package utilidades;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class CalcularValorReserva {
//	public String calcularValor(JDateChooser txtFechaE, JDateChooser txtFechaS) {
//		String valorTotal = null;
//		if(txtFechaE.getDate() != null && txtFechaS.getDate() != null) {
//			Calendar inicio = txtFechaE.getCalendar();
//			Calendar fin = txtFechaS.getCalendar();
//			int dias = 0;
//			float valorDia = 50000;
//			float valor;
//			
//			while(inicio.before(fin) || inicio.equals(fin)) {
//				dias++;
//				inicio.add(Calendar.DATE, 1);
//			}
//			valor = dias * valorDia;
//			valorTotal = String.valueOf(valor);			
//		}
//		return valorTotal;
//	}
	
	public String calcularValor(Date txtFechaE, Date txtFechaS) {
		String valorTotal = null;
		float valorDia = 50;
		LocalDate fechaEntrada = txtFechaE.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate fechaSalida = txtFechaS.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		long dias = ChronoUnit.DAYS.between(fechaEntrada,fechaSalida);
		if(dias == 0) {
			valorTotal = String.valueOf(valorDia);
		}else {
			valorTotal = String.valueOf((dias + 1) * valorDia);
		}
		return valorTotal;
	}
}
