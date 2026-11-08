public abstract class Alerta {
	protected int codigo;
	protected int generador;
	protected int hora;
	protected String mensaje;
	protected boolean estado;
	protected int plazo;
	protected float credito;

	public Alerta(int c, int generador, int hora, String mensaje, int plazo, float credito) {
		this.codigo = c;
		this.generador = generador;
		this.hora = hora;
		this.mensaje = mensaje;
		this.plazo = plazo;
		this.credito = credito;
		this.estado = false;
	}
	
	public void Setcodigo(int newcodigo) {
		this.codigo = newcodigo;
	}
	
	public int Getcodigo() {
		return this.codigo;
	}
	
	public void Setgenerador(int newgenerador) {
		this.generador = newgenerador;
	}
	
	public int Getgenerador() {
		return this.generador;
	}
	
	public void Sethora(int newhora) {
		this.hora = newhora;
	}
	
	public int Gethora() {
		return this.hora;
	}
	
	public void Setmensaje(String newmensaje) {
		this.mensaje = newmensaje;
	}
	
	public String Getmensaje() {
		return this.mensaje;
	}
	
	public void Setestado(boolean newestado) {
		this.estado = newestado;
	}
	
	public boolean Getestado() {
		return this.estado;
	}
	
	public void Setplazo(int newplazo) {
		this.plazo = newplazo;
	}
	
	public int Getplazo() {
		return this.plazo;
	}
	
	public void Setcredito(float newcredito) {
		this.credito = newcredito;
	}
	
	public float Getcredito() {
		return this.credito;
	}
	
	public String toString(){
		String cadenana;
		cadenana = "Codigo: "+codigo+" |Genreador: "+generador+" |Hora: "+hora+" |Mensaje: "+mensaje+" |Plazo: "+plazo+" |Credito: "+credito;
		return cadenana;
	}

	public abstract int Getprioridad(); // Cada tipo de alerta define su prioridad.
}
