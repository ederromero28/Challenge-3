package modelo;

public class Usuario {
	private int id;
	private String usuario;
	private String clave;
	private String tipoUsuario;

	public Usuario() {}

	public Usuario(String usuario, String clave) {
		this.usuario = usuario;
		this.clave = clave;
	}

	public Usuario(String usuario, String clave, String tipoUsuario) {
		this.usuario = usuario;
		this.clave = clave;
		this.tipoUsuario = tipoUsuario;
	}
	
	public Usuario(int id, String usuario, String clave, String tipoUsuario) {
		this.id = id;
		this.usuario = usuario;
		this.clave = clave;
		this.tipoUsuario = tipoUsuario;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
