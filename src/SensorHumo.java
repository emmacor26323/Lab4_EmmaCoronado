public class SensorHumo extends Dispositivo implements MedidorGenerador {
	private int medicion;

	public SensorHumo(int c, int ofi, float potencia) {
		super(c,ofi,potencia,true);
		this.medicion = -1;
	}
	
	public void Setmedicion(int newmedicion) {
		this.medicion = newmedicion;
	}
	
	public int Getmedicion() {
		return this.medicion;
	}
	
	public Alerta generarAlerta(int c, int h) {
		if(medicion>150){ //Si la concentracion es mayor a 150
			Alerta a;
			if(medicion<300){ //Si es menor a 300
				a= new AlertaAlta(c, codigo,h, "Concentracion de humo mayor a 150 ppm."); //Se genera alerta Alta
			}else{ //Si es mayor a 300
				a = new AlertaCritica(c, codigo, h, "Concentracion de humo mayor a 300 ppm.");//Se genera alerta critica
			}
			return a; //Se retorna la alerta
		}
		return  null; //Si no se genera alerta
	}
	
	public float calcularConsumo() {
		consumoT += potencia/1000; //Se suma el consumo al consumo total
		return potencia/1000; //Se retorna el valor del consumo
	}
	
	public String toString() {
		String cadena = "Detenctor de Humo |";
		cadena += super.toString();
		cadena += " |Ultima medicion: "+this.medicion;

		return cadena; 
	}
}
