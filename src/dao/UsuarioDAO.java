package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Usuario;

public class UsuarioDAO {

	private Connection con;

	public UsuarioDAO(Connection con) {
		this.con = con;
	}

	public Usuario validarUsuario(Usuario usuario) {
		Usuario user = new Usuario();
		String sql = "SELECT id, usuario, clave, tipo_usuario FROM usuarios "
				+ "WHERE usuario = ? AND clave = ?";
		try {
			final PreparedStatement ps = con.prepareStatement(sql);
			try(ps){
				ps.setString(1, usuario.getUsuario());
				ps.setString(2, usuario.getClave());
				final ResultSet rs = ps.executeQuery();
				try(rs){
					if(rs.next()) {
						user.setId(rs.getInt("id"));
						user.setUsuario(rs.getString("usuario"));
						user.setTipoUsuario(rs.getString("tipo_usuario"));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return user;
	}

	public void guardarUsuario(Usuario usuario) {
		try {
			String sql = "INSERT INTO usuarios(usuario, clave, tipo_usuario)"
					+ "VALUES(?, ?, ?)";
			final PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			try(ps){
				ps.setString(1, usuario.getUsuario());
				ps.setString(2, usuario.getClave());
				ps.setString(3, usuario.getTipoUsuario());
				ps.execute();
				final ResultSet rs = ps.getGeneratedKeys();
				try(rs){
					if(rs.next()) {
						usuario.setId(rs.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error usuario no registrado", "Error",
					JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException(e);
		}
	}
	
	public String buscarUsuario(String usuario) {
		String user = null;
		String sql = "SELECT usuario FROM usuarios WHERE usuario = ?";
		try {
			final PreparedStatement ps = con.prepareStatement(sql);
			try(ps){
				ps.setString(1, usuario);
				final ResultSet rs = ps.executeQuery();
				try(rs){
					if(rs.next()) {
						user = rs.getString(1);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return user;
	}

	public List<Usuario> listarUsuarios() {
		List<Usuario> lista = new ArrayList<>();
		String sql = "SELECT id, usuario, clave, tipo_usuario FROM usuarios";
		try {
			final PreparedStatement ps = con.prepareStatement(sql);
			try(ps){
				final ResultSet rs = ps.executeQuery();
				try(rs){
					while(rs.next()) {
						Usuario usuario = new Usuario();
						usuario.setId(rs.getInt("id"));
						usuario.setUsuario(rs.getString("usuario"));
						usuario.setTipoUsuario(rs.getString("tipo_usuario"));
						lista.add(usuario);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return lista;
	}

	public void eliminarUsuario(Integer id) {
		String sql = "DELETE FROM usuarios WHERE id = ?";
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

	public void modificarUsuario(Usuario usuario) {
		String sql = "UPDATE usuarios SET usuario = ?, tipo_usuario = ? WHERE id = ?";
		try {
			final PreparedStatement ps = con.prepareStatement(sql);
			try(ps){
				ps.setString(1, usuario.getUsuario());
				ps.setString(2, usuario.getTipoUsuario());
				ps.setInt(3, usuario.getId());
				ps.execute();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}
	
	public void cambiarContrasena(String usuario, String contrasena) {
		String sql = "UPDATE usuarios SET clave = ? WHERE usuario = ?";
		try {
			final PreparedStatement ps = con.prepareStatement(sql);
			try(ps){
				ps.setString(1, contrasena);
				ps.setString(2, usuario);
				ps.execute();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}
}
