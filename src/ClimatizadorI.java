public class ClimatizadorI extends Climatizador{
	
	public ClimatizadorI(int c, int ofi, float potencia) {
		super(c, ofi, potencia);
		this.ri = 10; 
	}
	
	public float calcularConsumo() {
		return super.calcularConsumo()*0.8f; //Retorna el 80% de consumo de un climatizador.
	}
	
	public String toString() {
		return (super.toString(true));
	}
}
