public class Luminaria extends Dispositivo implements Apagable, Regulable{

	private int nivel;
	private int ri;
	private int rs;

	public Luminaria(int c, int ofi, float potencia) {
		super(c, ofi, potencia, false);
		nivel = 100;
		ri = 10;
		rs = 100;
	}
	
	public void Setnivel(int newnivel) {
		this.nivel = newnivel;
	}
	
	public int Getnivel() {
		return this.nivel;
	}
	
	public void Setri(int newri) {
		this.ri = newri;
	}
	
	public int Getri() {
		return this.ri;
	}
	
	public void Setrs(int newrs) {
		this.rs = newrs;
	}
	
	public int Getrs() {
		return rs;
	}
	
	public void apagar() {
		estado = false;
	}
	
	public void encender() {
		estado = true;
	}
	
	public float calcularConsumo() {
		float c;
		if(estado){ //Si esta encendido 
			c = potencia*nivel/100; //Se calcula su consumo
		}else{c=0;} //Si no su consumo es 0

		consumoT += c/1000; //Se suma el consumo al consumo total
		return c/1000; //Se retorna el valor del consumo
	}
	
	public String toString() {
		String cadena = "Luminaria |";
		cadena += super.toString();
		cadena += " |Nivel: "+this.nivel;

		return cadena; 
	
	}
}
