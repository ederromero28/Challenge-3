package controlador;

import java.util.List;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;

public class ReservaController {
	private ReservaDAO reservaDAO;

	public ReservaController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.reservaDAO = new ReservaDAO(factory.recuperaConexion());
	}
	
	public void guardarReserva(Reserva reserva) {
		reservaDAO.guardarReserva(reserva);
	}

	public List<Reserva> listarReservas() {
		return reservaDAO.listarReservas();
	}

	public void eliminarReserva(Integer id) {
		reservaDAO.eliminarReserva(id);	
	}

	public void modificarReserva(Reserva reserva) {
		reservaDAO.modificarReserva(reserva);			
	}	
}
