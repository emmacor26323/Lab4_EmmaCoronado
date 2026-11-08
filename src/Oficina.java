import java.util.ArrayList;

public class Oficina {
	private int codigo;
	private String inquilino;
	private float valleFacturados;
	private float puntaFacturados;
	private float normalFacturados;
	private ArrayList<Float> creditos;
	private ArrayList<String> facturas;

	public Oficina(int cod, String inquilino) {
		this.codigo = cod;
		this.inquilino = inquilino;
		this.valleFacturados = 0;
		this.puntaFacturados = 0;
		this.normalFacturados = 0;
		this.creditos = new ArrayList<>();
		this.facturas = new ArrayList<>();
	}
	
	public void Setcodigo(int newcodigo) {
		this.codigo = newcodigo;
	}
	
	public int Getcodigo() {
		return this.codigo;
	}
	
	public void Setinquilino(String newinquilino) {
		this.inquilino = newinquilino;
	}
	
	public String Getinquilino() {
		return this.inquilino;
	}
	
	public void SetvalleFacturados(float newvalleFacturados) {
		this.valleFacturados = newvalleFacturados;
	}
	
	public float GetvalleFacturados() {
		return this.valleFacturados;
	}
	
	public void SetpuntaFacturados(float newpuntaFacturados) {
		this.puntaFacturados = newpuntaFacturados;
	}
	
	public float GetpuntaFacturados() {
		return this.puntaFacturados;
	}
	
	public void SetnormalFacturados(float newnormalFacturados) {
		this.normalFacturados = newnormalFacturados;
	}
	
	public float GetnormalFacturados() {
		return this.normalFacturados;
	}
	
	public void Setcreditos(ArrayList<Float> newcreditos) {
		this.creditos = newcreditos;
	}
	
	public ArrayList<Float> Getcreditos() {
		return this.creditos;
	}
	
	public void Setfacturas(ArrayList<String> newfacturas) {
		this.facturas = newfacturas;
	}
	
	public ArrayList<String> Getfacturas() {
		return this.facturas;
	}
	
	public String toString() {
		String cadena;
		cadena = "Codigo: "+this.codigo+" |Inquilino: "+inquilino+" |Facturados en Valle: "+valleFacturados+" |Facturados en Punta: "+puntaFacturados+" |Facturados en Normal: "+normalFacturados;
		return cadena;
	}


	public void registrarConsumo(float consumo, int horaDia) { //Para registrar consumo por franja
		if (horaDia >= 22 || horaDia < 6) { //Si es de franja Valle
			valleFacturados += consumo;
		} else if (horaDia < 18) { //Si es de franja Normal
			normalFacturados += consumo;
		} else { //Si es de Punta
			puntaFacturados += consumo;
		}
	}

	public void cerrarDia() {//Facturacion por dia
		float subtotal = valleFacturados +  normalFacturados + puntaFacturados; //Se calcula total a cobrar

		float totalCreditos = 0; //Se instancia variable para obtener lo reembolsable por creditos
		for (float credito : creditos) { //Por credito registrado
			totalCreditos += credito; //Se suma el monto a reembolsar por creditos
		}

		float creditoAplicado = totalCreditos; //Variable para registrar cuanto credito se utilizara

		if (creditoAplicado > subtotal) { //Si se puede facturar todo por credito
			creditoAplicado = subtotal; //Se utiliza lo equivalente al subtotal
		}

		float total = subtotal - creditoAplicado; // Se calcula le resta el credito al subtotal
		total = Math.round(total * 100) / 100f; //Decimales

		//String de Factura
		String factura = "Oficina:" +codigo+" | Inquilino: "+inquilino+"\n";
		factura	+= "Valle: "+(valleFacturados/0.85)+" kWh | Q"+valleFacturados+"\n";
		factura	+= "Nomral: "+(normalFacturados/0.85)+" kWh | Q"+normalFacturados+"\n";
		factura	+= "Punta "+(puntaFacturados/0.85)+" kWh | Q"+puntaFacturados+"\n";
		factura += "Creditos aplicados: Q"+creditoAplicado+"\n";
		factura	+= "Total a pagar: Q"+total;

		facturas.add(factura); //Se registra factura

		// Reiniciar los acumulados del dia.
		valleFacturados = 0;
		normalFacturados = 0;
		puntaFacturados = 0;
		creditos.clear();
	}
}
