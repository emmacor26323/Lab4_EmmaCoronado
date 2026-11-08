public class AlertaBaja extends Alerta {

	public AlertaBaja(int c, int generador, int hora, String mensaje) {
		super(c, generador, hora, mensaje,24,0.5f);
	}
		
	public String toString() {
		String cadena = "Alerta Baja |";
		cadena += super.toString();
		return cadena;
	}

	public int Getprioridad() { // Obtiene la prioridad de una alerta baja.
    	return 1; // Es la menor prioridad.
	}
}
