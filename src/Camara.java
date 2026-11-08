import java.util.ArrayList;

public class Camara extends Dispositivo implements Apagable{
	private ArrayList<String> registro;
	
	public Camara(int c, int ofi, float potencia) {
		super(c, ofi, potencia, false);
		this.registro = new ArrayList<>();
	}
	
	public void Setregistro(ArrayList<String> newregistro) {
		this.registro = newregistro;
	}
	
	public ArrayList<String> Getregistro() {
		return this.registro;
	}
	
	public void apagar() {
		this.estado = false;
	}
	
	public void encender() {
		this.estado = true;
	}
	
	public float calcularConsumo() {
		if (estado){ //Si esta encendido
			consumoT += potencia/1000; //Se suma el consumo al consumo total.
			return potencia/1000; //Se retonra el consumo.
		}else{return 0;}//Si no retorna 0
	}
	
	public String toString() {
		String cadena = "Camara de vigilancia |";
		cadena += super.toString();
		if(registro.size()==0){
			cadena += "| No movimientos registrados";
		}else{cadena += " |Ultimo movimiento detectado: "+registro.get(registro.size()-1);}

		return cadena; 
		
	}
}
