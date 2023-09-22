package controlador;

import java.util.List;

import dao.UsuarioDAO;
import factory.ConnectionFactory;
import modelo.Usuario;
import utilidades.EncryptPassword;

public class UsuarioController {

	private UsuarioDAO usuarioDAO;
	private Usuario usuario;

	public UsuarioController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.usuarioDAO = new UsuarioDAO(factory.recuperaConexion());
	}

	public Usuario validarUsuario(String usuario, String clave) {
		String pass = new EncryptPassword().passwordEncrypt(clave);
		this.usuario = new Usuario(usuario,pass);
		return this.usuarioDAO.validarUsuario(this.usuario);
	}
	
	public void guardarUsuario(Usuario usuario) {
		String pass = new EncryptPassword().passwordEncrypt(usuario.getClave());
		usuario.setClave(pass);
		usuarioDAO.guardarUsuario(usuario);
	}
	
	public String buscarUsuario(String usuario) {
		return usuarioDAO.buscarUsuario(usuario);
	}

	public List<Usuario> listarUsuarios() {
		return usuarioDAO.listarUsuarios();
	}

	public void eliminarUsuario(Integer id) {
		usuarioDAO.eliminarUsuario(id);	
	}

	public void modificarUsuario(Usuario usuario) {
		usuarioDAO.modificarUsuario(usuario);
	}
	
	public void cambiarContrasena(String usuario, String contrasena) {
		String pass = new EncryptPassword().passwordEncrypt(contrasena);
		usuarioDAO.cambiarContrasena(usuario, pass);
	}
}
