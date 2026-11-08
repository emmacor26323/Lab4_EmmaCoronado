public class AlertaAlta extends Alerta {

	public AlertaAlta(int c, int generador, int hora, String mensaje) {
		super(c, generador, hora, mensaje,4,2);
	}
	
	public String toString() {
		String cadena = "Alerta Alta |";
		cadena += super.toString();
		return cadena;
	}

	public int Getprioridad() { // Obtiene la prioridad de una alerta alta.
    	return 2; // Es una prioridad intermedia.
	}
}
