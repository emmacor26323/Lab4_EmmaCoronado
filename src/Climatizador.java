public class Climatizador extends Dispositivo implements Apagable,MedidorGenerador,Regulable{
	protected int nivel;
	protected int ri;
	protected int rs;
	protected int medicion;

	public Climatizador(int c, int ofi, float potencia) {
		super(c, ofi, potencia, false);
		this.nivel = -1;
		this.nivel = 50;
		this.ri = 20;
		this.rs = 100;
	}
	
	public void Setnivel(int newnivel) {
		this.nivel = newnivel;
	}
	
	public int Getnivel() {
		return nivel;
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
		return this.rs;
	}
	
	public void Setmedicion(int newmedicion) {
		this.medicion = newmedicion;
	}
	
	public int Getmedicion() {
		return this.medicion;
	}
	
	public Alerta generarAlerta(int c,int h) {
		if(medicion>=28){//Si se miden 28 grados o mas
			Alerta a;
			if(medicion<32){ //Si se miden menos de 32 grados
				a =new AlertaBaja(c, codigo,h, "Temperatura mayor o igual a 28 grados."); //Se genera alerta baja
			}else{
				if(medicion<40){//Si se miden mas de 32 pero menos que 40
					a = new AlertaAlta(c, codigo, h, "Temperatura mayor a 32 grados.");//Se genera aleta Alta
				}else{//Si la temperatura es mayor a 40 grados
					a = new AlertaCritica(c, codigo,h, "Temperatura mayor o igual a 40 grados.");//Se genera alerta critica
				}
			}
			return a;//Se retorna alerta
		}
		return null;//Si la temperatura es menor a 28 grados no se genera alerta.
	}
	
	public void apagar() {
		this.estado = false;
	}
	
	public void encender() {
		this.estado = true;
	}
	
	public float calcularConsumo() {
		float c;
		if(estado){ //Si esta encendido 
			c = potencia*(0.30f+0.70f*nivel/100); //Se calcula su consumo
		}else{c=0;} //Si no su consumo es 0

		consumoT += c/1000; //Se suma el consumo al consumo total
		return c/1000; //Se retorna el valor del consumo
	}
	
	public String toString() {
		String cadena= "Climatizador: ";
		cadena += super.toString();
		cadena += " |Ultima medicion: "+this.medicion;
		cadena += " |Nivel: "+this.nivel;

		return cadena;
	}

	
	public String toString(boolean I) {
		String cadena= "Climatizador inverter: ";
		cadena += super.toString();
		cadena += " |Ultima medicion: "+this.medicion;
		cadena += " |Nivel: "+this.nivel;

		return cadena;
	}
}
