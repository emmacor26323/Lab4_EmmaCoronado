public abstract class Dispositivo {
	protected int codigo;
	protected int oficina;
	protected float potencia;
	protected boolean estado;
	protected float consumoT;

	public Dispositivo(int c, int ofi, float potencia, boolean estado) {
		this.codigo = c;
		this.oficina = ofi;
		this.potencia = potencia;
		this.estado = estado;
		this.consumoT = 0;
	}
	
	public void Setcodigo(int newcodigo) {
		this.codigo = newcodigo;
	}
	
	public int Getcodigo() {
		return this.codigo;
	}
	
	public void Setoficina(int newoficina) {
		this.oficina = newoficina;
	}
	
	public int Getoficina() {
		return this.oficina;
	}
	
	public void Setpotencia(float newpotencia) {
		this.potencia = newpotencia;
	}
	
	public float Getpotencia() {
		return this.potencia;
	}
	
	public void Setestado(boolean newestado) {
		this.estado = newestado;
	}
	
	public boolean Getestado() {
		return estado;
	}
	
	public void SetconsumoT(float newconsumoT) {
		this.consumoT = newconsumoT;
	}
	
	public float GetconsumoT() {
		return this.consumoT;
	}
	
	public abstract float calcularConsumo();
	
	public String toString(){
		String cadena;
		cadena = "Codigo: "+this.codigo+" |Cod. Oficina: "+this.oficina+" |Potencia: "+this.potencia+" |Estado: ";
		if(this.estado){
			cadena+="Encendido";
		}else{cadena+="Apagado";}
		cadena += " |Consumo del dia: "+this.consumoT;

		return cadena;
	}
}
