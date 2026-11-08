public class AlertaCritica extends Alerta {

	public AlertaCritica(int c, int generador, int hora, String mensaje) {
		super(c, generador, hora, mensaje,1,5);
	}
	
	
	public String toString() {
		String cadena = "Alerta Critica |";
		cadena += super.toString();
		return cadena;
	}

	public int Getprioridad() { // Obtiene la prioridad de una alerta critica.
    	return 3; // Es la mayor prioridad.
	}
}
