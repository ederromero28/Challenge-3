package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Reserva;

public class ReservaDAO {
	private Connection con;
	
	public ReservaDAO(Connection con) {
		this.con = con;
	}
	public void guardarReserva(Reserva reserva) {
		String sql = "INSERT INTO reservas(fecha_entrada, fecha_salida, valor, forma_de_pago) "
				+ "VALUES(?,?,?,?)";
		try {
			final PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			try(ps){
				ps.setDate(1, reserva.getFechaEntrada());
				ps.setDate(2, reserva.getFechaSalida());
				ps.setFloat(3, reserva.getValor());
				ps.setString(4, reserva.getFormaDePago());
				ps.execute();
				final ResultSet rs = ps.getGeneratedKeys();
				try(rs){
					if(rs.next()) {
						reserva.setId(rs.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al guardar la reserva","Error",
					JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException(e);
		}
	}
	public List<Reserva> listarReservas() {
		List<Reserva> lista = new ArrayList<>();
		String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reservas";
		try {
			final PreparedStatement ps = con.prepareStatement(sql);
			try(ps){
				final ResultSet rs = ps.executeQuery();
				try(rs){
					while(rs.next()) {
						Reserva reserva = new Reserva();
						reserva.setId(rs.getInt("id"));
						reserva.setFechaEntrada(rs.getDate("fecha_entrada"));
						reserva.setFechaSalida(rs.getDate("fecha_salida"));
						reserva.setValor(rs.getFloat("valor"));
						reserva.setFormaDePago(rs.getString("forma_de_pago"));
						lista.add(reserva);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return lista;
	}
	public void eliminarReserva(Integer id) {
		String sql = "DELETE FROM reservas WHERE id = ?";
		try {
			final PreparedStatement ps = con.prepareStatement(sql);
			try(ps){
				ps.setInt(1, id);
				ps.execute();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}
	public void modificarReserva(Reserva reserva) {
		String sql = "UPDATE reservas SET fecha_entrada = ?, fecha_salida = ?, valor = ?, forma_de_pago = ? "
				+ "WHERE id = ?";
		try {
			final PreparedStatement ps = con.prepareStatement(sql);
			try(ps){
				ps.setDate(1, reserva.getFechaEntrada());
				ps.setDate(2, reserva.getFechaSalida());
				ps.setFloat(3, reserva.getValor());
				ps.setString(4, reserva.getFormaDePago());
				ps.setInt(5, reserva.getId());
				ps.execute();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}
}
