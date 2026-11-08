import java.util.ArrayList;

public class Sistema {
	private int hora;
	private ArrayList<Oficina> oficinas;
	private ArrayList<Dispositivo> dispositivos;
	private ArrayList<Alerta> bandejaAlertas;
	private ArrayList<Alerta> alertasConRetraso; // Guarda las alertas atendidas tarde.
	
	public Sistema() {
		hora = 0; //Se empieza en la hora 0.
		oficinas = new ArrayList<>();
		dispositivos =new  ArrayList<>();
		bandejaAlertas = new  ArrayList<>();
		alertasConRetraso = new  ArrayList<>();
	}
	
	public void Setoficinas(ArrayList<Oficina> newoficinas) {
		this.oficinas = newoficinas;
	}
	
	public ArrayList<Oficina> Getoficinas() {
		return this.oficinas;
	}
	
	public void Sethora(int newhora) {
		this.hora = newhora;
	}
	
	public int Gethora() {
		return this.hora;
	}
	
	public void Setdispositivos(ArrayList<Dispositivo> newdispositivos) {
		this.dispositivos = newdispositivos;
	}
	
	public ArrayList<Dispositivo> Getdispositivos() {
		return this.dispositivos;
	}
	
	public void SetbandejaAlertas(ArrayList<Alerta> newbandejaAlertas) {
		this.bandejaAlertas = newbandejaAlertas;
	}
	
	public ArrayList<Alerta> GetbandejaAlertas() {
		return this.bandejaAlertas;
	}
	
	public String registrarOficina(int c, String inquilino) {
		boolean existe = false; //Variable para validar existencia de oficina con el mismo codigo
		for(Oficina o:oficinas){ //Por cada oficina registrado
			if(o.Getcodigo() == c){ //Si su codigo es igual al ingresado
				existe = true;} //Se registra que existe
		}
		if(existe){//Si existe
			return "No se puede ingresar una oficina con este codigo (codigo repetido)."; //Se retorna mensaje de error
		}else{//Si no existe
			Oficina o = new Oficina(c, inquilino); //Se crea la oficina.
			oficinas.add(o); //Se agrega al registro
			return "Oficina registrada."; //Se retorna mensaje de exito
		}
	}
	
	public String registrarDispositivo(int codigo, int oficina, float potencia, int tipo) {
		boolean existe = false; //Variable para validar existencia de dispositivo con el mismo codigo
		for(Dispositivo d:dispositivos){ //Por cada dispositivo registrado
			if(d.Getcodigo() == codigo){ //Si su codigo es igual al ingresado
				existe = true;} //Se registra que existe
		}

		if(existe){//Si existe
			return "No se puede ingresar una dispositvo con este codigo (codigo repetido)."; //Se retorna mensaje de error
		}else{//Si no existe
			Dispositivo d; //Se instancia variable dispositivo
			switch (tipo) { //Segun el tipo solicitado
				case 1: //Luminaria
					d = new Luminaria(codigo, oficina, potencia); //Se crea y se asigna a la variable
					break;
			
				case 2: //Climatizador
					d = new Climatizador(codigo, oficina, potencia); //Se crea y se asigna a la variable
					break;
			
				case 3: //Climatizador inverter
					d = new ClimatizadorI(codigo, oficina, potencia); //Se crea y se asigna a la variable
					break;
			
				case 4: //Sensor de Humo
					d = new SensorHumo(codigo, oficina, potencia); //Se crea y se asigna a la variable
					break;
			
				case 5: //Camara de seguridad
					d = new Camara(codigo, oficina, potencia); //Se crea y se asigna a la variable
					break;

				default:
					return "Se ha ingresado un tipo no aceptado."; //Si el tipo no es permitido
			}
			dispositivos.add(d);//Se agrega dispositivo al registro
			return "Dispositivo registrado.";//Mensaje de exito
		}
	}
	
	public String encender(int n) {
		for(Dispositivo d:dispositivos){ //Por cada dispositivo registrado
			if(d.Getcodigo() == n){ //Si su codigo es igual al ingresado
				if(d instanceof Apagable){ //Si se puede apagar y encender
					((Apagable)d).encender(); //Se enciende
					return "Dispositivo encendido."; //Mensaje de exito
				}else{return "No se puede cambiar el estado del dispositivo";} //Si no se puede apagar y encender
			}
		}
		return "No se encontro el dispositivo."; //Si no se encuentra el dispositivo
	}
	
	public String apagar(int n) {
		for(Dispositivo d:dispositivos){ //Por cada dispositivo registrado
			if(d.Getcodigo() == n){ //Si su codigo es igual al ingresado
				if(d instanceof Apagable){ //Si se puede apagar
					((Apagable)d).apagar(); //Se apaga
					return "Dispositivo apagado."; //Mensaje de exito
				}else{return "El dispositivo no se puede apagar";} //Si no se puede apagar
			}
		}
		return "No se encontro el dispositivo."; //Si no se encuentra el dispositivo
	}
	
	public String registrarLectura(int codigo, int dato) {
		for(Dispositivo d:dispositivos){ //Por cada dispositivo registrado
			if(d.Getcodigo() == codigo){ //Si su codigo es igual al ingresado
				if(d instanceof MedidorGenerador){//Si el objeto recibe medidas y genera alertas
					((MedidorGenerador)d).Setmedicion(dato); //Se asigna el dato como medido.
					Alerta a = ((MedidorGenerador)d).generarAlerta(bandejaAlertas.size()+1, hora); //Se comprueba que no se generen alertas
					if(a!=null){//Si se genera alerta
						bandejaAlertas.add(a); //Se agrega alerta a la bandeja de alertas.
					}
					return "Lectura registrada";//Mensaje de exito
				}else{
					return "El dispositivo no puede recibir lecturas.";
				}
			}
		}
		return "No se encontro el dispositivo."; //Ni no se encuentra el codigo en los dispositivos registrados.
	}
	
	public String registrarMovimineto(int codigo, String movimiento) {
		for(Dispositivo d:dispositivos){ //Por cada dispositivo registrado
			if(d.Getcodigo() == codigo){ //Si su codigo es igual al ingresado
				if(d instanceof Camara){//Si el objeto es una camara de seguridad
					((Camara)d).Getregistro().add(movimiento); //Se registra el movimiento.
					return "Movimiento registrado.";//Mensaje de exito
				}else{
					return "El dispositivo no puede registrar movimientos."; //Si el dispositivo no es una camara
				}
			}
		}
		return "No se encontro el dispositivo."; //Ni no se encuentra el codigo en los dispositivos registrados.		
	}
	
	public void avanzarHora() {
		int horaDia = hora % 24; //Se obtine hora

		for (Dispositivo d : dispositivos) { //Por cada dispositivo
			float consumo = d.calcularConsumo(); //Se calcula su consumo

			for (Oficina oficina : oficinas) { //Para cada oficina
				if (oficina.Getcodigo() == d.Getoficina()) { //Si es la oficina del dispositivo
					oficina.registrarConsumo(consumo, horaDia); //Se registra su consumo
				}
			}
		}

		hora++; //Se avanza una hora, luego de calcular los consumos

		if (hora % 24 == 0) { //Si se termina un dia, cada 24 h
			for (Oficina oficina : oficinas) {
				oficina.cerrarDia(); //Se genera factura del dia y reincian acumulados
			}

			for (Dispositivo dispositivo : dispositivos) { //Para cada dispositivo
				dispositivo.SetconsumoT(0); //Su consumo se resetea
			}
		}

	}
	
	public String atenderAlerta() {
		Alerta urgente = null; // Para tratar alerta alerta.

		for (Alerta alerta : bandejaAlertas) { // Recorre las alertas de la bandeja.
			if (!alerta.Getestado()) { // Verifica que la alerta este pendiente.
				if (urgente == null) { // Si no hay una alerta seleccionada.
					urgente = alerta; // Selecciona la primera pendiente.
				} else if (alerta.Getprioridad() > urgente.Getprioridad()) { // Compara la severidad.
					urgente = alerta; // Selecciona la de mayor severidad.
				} else if (alerta.Getprioridad() == urgente.Getprioridad()) { // Si tienen igual severidad.
					if (alerta.Gethora() < urgente.Gethora()) { // Compara cual se genero primero.
						urgente = alerta; // Selecciona la mas antigua.
					} else if (alerta.Gethora() == urgente.Gethora()
							&& alerta.Getcodigo() < urgente.Getcodigo()) { // Si empatan en hora, compara los codigos.
						urgente = alerta; // Selecciona la de menor codigo.
					} 
				}
			} 
		} 

		if (urgente == null) { // Si no encontro alertas pendientes.
			return "No hay alertas pendientes."; // Informa que no hay ninguna para atender.
		} 

		Oficina oficinaAlerta = null; // Guarda la oficina que recibira el credito.

		for (Dispositivo dispositivo : dispositivos) { // Para cada dispositivo
			if (dispositivo.Getcodigo() == urgente.Getgenerador()) { // Busca el dispositivo que genero la alerta.
				for (Oficina oficina : oficinas) { // Recorre las oficinas.
					if (oficina.Getcodigo() == dispositivo.Getoficina()) { // Busca la oficina del dispositivo.
						oficinaAlerta = oficina; // Guarda la oficina encontrada.
					} 
				} 
				break; 
			} 
		} 

		if (oficinaAlerta == null) { //Si no encontro la oficina.
			return "No se encontro la oficina de la alerta."; // Retorna el error sin atender la alerta.
		} 

		int retraso = hora - urgente.Gethora() - urgente.Getplazo(); // Calcula las horas fuera del plazo.

		if (retraso < 0) { // Si todavia estaba dentro del plazo.
			retraso = 0; // Establece que no hubo retraso.
		}

		float credito = retraso * urgente.Getcredito(); // Calcula el credito por las horas de retraso.

		if (retraso > 0) { // Verifica si la atencion fue tardia.
			oficinaAlerta.Getcreditos().add(credito); // Agrega el credito a la oficina.
			alertasConRetraso.add(urgente); // Guarda la alerta para el reporte de retrasos.
		} 

		urgente.Setestado(true); // Marca la alerta como atendida.
		bandejaAlertas.remove(urgente); // Retira la alerta de la bandeja de pendientes.

		return "Alerta atendida: " + urgente.Getcodigo()+ "\nHoras de retraso: " + retraso+ "\nCredito generado: Q" + credito; //Retorna infor de alerta atendida
	} 
	
	public String consultarOficina(int cod) {
		for(Oficina o:oficinas){ //Por cada oficina registrado
			if(o.Getcodigo() == cod){ //Si su codigo es igual al ingresado
				return o.toString(); //se retorna un String con su informacion	
			}
		}
		return "Oficina no encontrada"; //Si no se encuentra oficina
	}
	
	public String consultarDispositivo(int cod) {
		for(Dispositivo d:dispositivos){ //Por cada dispositivo registrado
			if(d.Getcodigo() == cod){ //Si su codigo es igual al ingresado
				return d.toString(); //se retorna un String con su informacion	
			}
		}
		return "Dispositvo no encontrada"; //Si no se encuentra dispositvo
	}
	
	public String consultarAlerta(int cod) {	
		for(Alerta a:bandejaAlertas){ //Por cada Alerta registrada
			if(a.Getcodigo() == cod){ //Si su codigo es igual al ingresado
				return a.toString(); //se retorna un String con su informacion	
			}
		}
		return "Alerta no encontrada"; //Si no se encuentra alerta
	}
	
	
	public String generarReporte(int op) {
		String reporte = ""; // Para almacenar reporte.

		if (op == 1) { // Reporte de dispositivos por oficina.
			reporte = "Dispositivos por Ofincina \n------------------------\n"; //Encabezado

			ArrayList<Dispositivo> ordenados = new ArrayList<>(dispositivos); // Copia la lista para ordenarla.

			for (int i = 0; i < ordenados.size(); i++) { // Recorre cada posicion.
				for (int j = i + 1; j < ordenados.size(); j++) { // Recorre las posiciones siguientes.
					if (ordenados.get(i).Getcodigo() > ordenados.get(j).Getcodigo()) { // Compara los codigos.
						Dispositivo temporal = ordenados.get(i); // Guarda temporalmente el primer dispositivo.
						ordenados.set(i, ordenados.get(j)); // Coloca el de menor codigo primero.
						ordenados.set(j, temporal); // Coloca el otro dispositivo despues.
					} 
				} 
			} 

			for (Oficina oficina : oficinas) { // Recorre las oficinas.
				reporte += "\nOficina: " + oficina.Getcodigo() + "\n"; // Agrega el codigo de oficina.
				boolean encontrado = false; // Indica si la oficina tiene dispositivos.

				for (Dispositivo dispositivo : ordenados) { // Recorre los dispositivos ordenados.
					if (dispositivo.Getoficina() == oficina.Getcodigo()) { // Verifica que pertenezca a la oficina.
						reporte += dispositivo + "\n"; // Agrega su informacion usando toString().
						encontrado = true; // Indica que encontro un dispositivo.
					} 
				} 

				if (encontrado==false) { // Si no tiene dispositivos.
					reporte += "Sin dispositivos registrados.\n"; // Se indica en el reporte
				} 
			} 

			if (oficinas.isEmpty()) { // Verifica si no hay oficinas.
				reporte += "No hay oficinas registradas.\n"; // Agrega el mensaje.
			} 

		} else if (op == 2) { // Reporte de alertas pendientes.
			reporte = "Alertas Pendientes\n-------------------------------------\n"; // Agrega el titulo.

			Boolean pendiente  = false; //Validar que hay pendientes
			for (Alerta alerta : bandejaAlertas) { // Recorre la bandeja.
				if (alerta.Getestado()==false) { // Verifica que la alerta este pendiente.
					reporte += alerta.toString()+"\n"; //La agrega al reporte
					pendiente = true; //Para validadcion
				}
			}
			if(pendiente==false){//Si no hay pendientes
				reporte+="No hay alertas pendientes";//Se agrega en el reporte
			}

		} else if (op == 3) { // Reporte de alertas atendidas tarde.
			reporte = "Alertas atendidas con Retraso\n-----------------------------\n"; // Agrega el titulo.

			for (Alerta alerta : alertasConRetraso) { // Recorre el historial de retrasos.
				reporte += alerta + "\n"; // Agrega la informacion de cada alerta.
			} 

			if (alertasConRetraso.isEmpty()) { // Verifica si no hay alertas registradas.
				reporte += "No hay alertas atendidas con retraso.\n"; // Agrega el mensaje.
			} // Termina la verificacion.

		} else if (op == 4) { // Reporte de facturas.
			reporte = "Facturas por Oficina\n-----------------------------------\n"; // Agrega el titulo.

			for (Oficina oficina : oficinas) { // Recorre las oficinas.
				reporte += "\nOficina: " + oficina.Getcodigo() + "\n"; // Identifica la oficina.

				for (String factura : oficina.Getfacturas()) { // Recorre sus facturas guardadas.
					reporte += factura + "\n"; // Agrega la factura con su desglose.
				}

				if (oficina.Getfacturas().isEmpty()) { // Verifica si no tiene facturas.
					reporte += "Sin facturas generadas.\n"; // Agrega el mensaje.
				} 
			} 

			if (oficinas.isEmpty()) { // Verifica si no hay oficinas.
				reporte += "No hay oficinas registradas.\n"; // Agrega el mensaje.
			} // Termina la verificacion.

		} else { // Si la opcion no existe.
			reporte = "Opcion de reporte invalida."; // Guarda el mensaje de error.
		} 

		return reporte; // Retrona String con reporte
	}
}
