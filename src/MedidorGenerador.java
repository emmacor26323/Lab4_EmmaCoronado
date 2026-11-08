public interface MedidorGenerador {
	public void Setmedicion(int newmedicion);
	public int Getmedicion();
	public Alerta generarAlerta(int c, int h);
}
