package controlador;

import java.util.List;

import dao.HuespedDAO;
import factory.ConnectionFactory;
import modelo.Huesped;

public class HuespedController{
	private HuespedDAO huespedDAO;
	
	public HuespedController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.huespedDAO = new HuespedDAO(factory.recuperaConexion());
	}
	
	public void guardarHuesped(Huesped huesped) {
		huespedDAO.guardarHuesped(huesped);
	}

	public List<Huesped> listarHuespedes() {
		return huespedDAO.listarHuespedes();
	}

	public void eliminarReserva(Integer id) {
		huespedDAO.eliminarReserva(id);	
	}

	public void modificarHuesped(Huesped huesped) {
		huespedDAO.modificarHuesped(huesped);
	}
}
