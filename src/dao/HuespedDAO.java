package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Huesped;

public class HuespedDAO {
	private Connection con;
	
	public HuespedDAO(Connection con) {
		this.con = con;
	}
	
	public void guardarHuesped(Huesped huesped) {
		String sql = "INSERT INTO huespedes(nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva) "
				+ "VALUES(?,?,?,?,?,?)";
		try {
			final PreparedStatement ps = con.prepareStatement(sql);
			try(ps){
				ps.setString(1, huesped.getNombre());
				ps.setString(2, huesped.getApellido());
				ps.setDate(3, huesped.getFechaNacimiento());
				ps.setString(4, huesped.getNacionalidad());
				ps.setString(5, huesped.getTelefono());
				ps.setInt(6, huesped.getIdReserva());
				ps.execute();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al guardar el huesped","Error",
					JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException(e);
		}
	}

	public List<Huesped> listarHuespedes() {
		List<Huesped> lista = new ArrayList<>();
		String sql = "SELECT id, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes";
		try {
			final PreparedStatement ps = con.prepareStatement(sql);
			try(ps){
				final ResultSet rs = ps.executeQuery();
				try(rs){
					while(rs.next()) {
						Huesped huesped = new Huesped();
						huesped.setId(rs.getInt("id"));
						huesped.setNombre(rs.getString("nombre"));
						huesped.setApellido(rs.getString("apellido"));
						huesped.setFechaNacimiento(rs.getDate("fecha_de_nacimiento"));
						huesped.setNacionalidad(rs.getString("nacionalidad"));
						huesped.setTelefono(rs.getString("telefono"));
						huesped.setIdReserva(rs.getInt("id_reserva"));
						lista.add(huesped);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return lista;
	}

	public void eliminarReserva(Integer id) {
		String sql = "DELETE FROM huespedes WHERE id = ?";
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

	public void modificarHuesped(Huesped huesped) {
		String sql = "UPDATE huespedes SET nombre = ?, apellido = ?, fecha_de_nacimiento = ?, nacionalidad = ?, "
				+ "telefono = ?, id_reserva = ? WHERE id = ?";
		try {
			final PreparedStatement ps = con.prepareStatement(sql);
			try(ps){
				ps.setString(1, huesped.getNombre());
				ps.setString(2, huesped.getApellido());
				ps.setDate(3, huesped.getFechaNacimiento());
				ps.setString(4, huesped.getNacionalidad());
				ps.setString(5, huesped.getTelefono());
				ps.setInt(6, huesped.getIdReserva());
				ps.setInt(7, huesped.getId());
				ps.execute();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
