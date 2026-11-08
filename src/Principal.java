import java.util.Scanner;

public class Principal {
	public void main(String[] args) {		
		Scanner teclado = new Scanner(System.in);
		Sistema s = new Sistema();

		System.out.println("¡Bienvenido!");
		int op = 0; // Guarda la opcion del menu principal.

        System.out.println("¡Bienvenido a TorreGT!"); // Muestra el saludo inicial.

        while (op != 14) { // Repite el menu hasta que el usuario decida salir.
            try { // Intenta leer la opcion del menu.
                System.out.println("-----------------------Menu------------------------------"); 
                System.out.println("Hora de simulacion: " + s.Gethora()); // Muestra las horas transcurridas.
                System.out.println("Hora del dia: " + s.Gethora() % 24 + ":00"); // Muestra la hora dentro del dia.
                System.out.println("1. Registrar oficina\n2. Registrar dispositivo\n3. Encender dispositivo\n4. Apagar dispositivo\n5. Ajustar nivel\n6. Registrar lectura\n7. Registrar movimiento\n8. Avanzar una hora\n9. Atender alerta\n10. Consultar oficina\n11. Consultar dispositivo\n12. Consultar alerta\n13. Generar reporte\n14. Salir"); // Muestra las opciones.
                System.out.println("---------------------------------------------------------"); 
                System.out.println("Ingrese la opcion que desee:"); // Solicita una opcion.
                op = teclado.nextInt(); // Lee la opcion como entero.
                teclado.nextLine(); // Consume el salto de linea pendiente.

            } catch (java.util.InputMismatchException e) { // Detecta una opcion que no sea un entero.
                teclado.nextLine(); // Descarta la entrada incorrecta.
                System.out.println("Ha ingresado una opcion en un formato no permitido."); // Explica el error.
            }

            boolean error = true; // Controla si deben repetirse los datos de la opcion.

            while (error) { // Repite la solicitud si ocurre un error
                try {
                    switch (op) { //Segun la opcion
                        case 1: // Registra una oficina.
                            System.out.println("Ingrese los datos de la nueva oficina"); // Presenta la operacion.
                            System.out.println("Codigo:"); // Solicita el codigo.
                            int codigo = teclado.nextInt(); // Lee el codigo de oficina.
                            teclado.nextLine(); // Consume el salto de linea.
                            System.out.println("Inquilino:"); // Solicita el nombre del inquilino.
                            String inquilino = teclado.nextLine(); // Lee el nombre completo.
                            System.out.println(s.registrarOficina(codigo, inquilino)); // Muestra el resultado real del registro.
						break;

                        case 2: // Registra un dispositivo.
                            System.out.println("Ingrese los datos del nuevo dispositivo"); // Presenta la operacion.
                            System.out.println("Codigo del dispositivo:"); // Solicita su codigo.
                            int c = teclado.nextInt(); // Lee el codigo del dispositivo.
                            teclado.nextLine(); // Consume el salto de linea.
                            System.out.println("Codigo de la oficina:"); // Solicita la oficina donde se instalara.
                            int oficina = teclado.nextInt(); // Lee el codigo de oficina.
                            teclado.nextLine(); // Consume el salto de linea.
                            System.out.println("Potencia nominal en watts:"); // Solicita la potencia.
                            float potencia = teclado.nextFloat(); // Lee una potencia que puede tener decimales.
                            teclado.nextLine(); // Consume el salto de linea.
                            System.out.println("Tipo: 1. Luminaria / 2. Climatizador / 3. Climatizador inverter / 4. Sensor de humo / 5. Camara"); // Muestra los tipos disponibles.
                            int tipo = teclado.nextInt(); // Lee el tipo seleccionado.
                            teclado.nextLine(); // Consume el salto de linea.

                            if (tipo<1 || tipo>5) { // Verifica si selecciono un tipo inexistente.
                                System.out.println("Tipo de dispositivo no permitido."); // Informa el error sin registrar nada.
                            } else { // Si selecciono un tipo valido.
                                System.out.println(s.registrarDispositivo(c, oficina, potencia, tipo)); // Solicita el registro y muestra su resultado.
                            } 
                        break;

                        case 3: // Enciende un dispositivo.
                            System.out.println("Identificador del dispositivo (codigo):"); // Solicita el identificador que utiliza tu metodo encender().
                            int n = teclado.nextInt();teclado.nextLine();
							System.out.println(s.encender(n));
						break;

                        case 4: { // Apaga un dispositivo.
                            System.out.println("Identificador del dispositivo:"); // Solicita el identificador que utiliza tu metodo apagar().
                            int n1 = teclado.nextInt(); teclado.nextLine(); // Conserva el parametro String de tu Sistema.
                            System.out.println(s.apagar(n1)); // Muestra el resultado del intento de apagar.
                            break; }

						case 5: { // Ajusta el nivel de un dispositivo.
							System.out.println("Codigo del dispositivo:"); // Solicita el codigo.
							int codigo1 = teclado.nextInt(); // Lee el codigo ingresado.
							teclado.nextLine(); // Consume el salto de linea.

							System.out.println("Nuevo nivel en porcentaje:"); // Solicita el nivel deseado.
							int nivel = teclado.nextInt(); // Lee el nivel ingresado.
							teclado.nextLine(); // Consume el salto de linea.

							boolean encontrado = false; // Indica si encontro el dispositivo.

							for (Dispositivo d : s.Getdispositivos()) { // Recorre los dispositivos.
								if (d.Getcodigo() == codigo1) { // Busca el codigo solicitado.
									encontrado = true; // Indica que encontro el dispositivo.

									if (d instanceof Regulable) { // Verifica que permita ajustar su nivel.
										Regulable r = (Regulable) d; // Permite utilizar los metodos de Regulable.

										if (!d.Getestado()) { // Verifica si esta apagado.
											System.out.println("Debe encender el dispositivo primero."); // Rechaza el cambio.
										} else if (nivel < r.Getri() || nivel > r.Getrs()) { // Verifica los limites permitidos.
											System.out.println("El nivel debe estar entre "
													+ r.Getri() + " y " + r.Getrs() + "."); // Muestra el rango permitido.
										} else { // Si esta encendido y el nivel es valido.
											r.Setnivel(nivel); // Actualiza el nivel.
											System.out.println("Nivel actualizado a " + nivel + "%."); // Confirma el cambio.
										}
									} else { // Si no implementa Regulable.
										System.out.println("El dispositivo no es regulable."); // Explica por que no puede cambiarse.
									} 
								}
							} 

							if (!encontrado) { // Verifica si ningun codigo coincidio.
								System.out.println("No se encontro el dispositivo."); // Informa que no existe.
							} 

						break;}

                        case 6: { // Registra una lectura de un dispositivo que mide.
                            System.out.println("Identificador del dispositivo:"); // Solicita el identificador usado por registrarLectura().
                            int num = teclado.nextInt(); // Lee el identificador.
                            System.out.println("Lectura: temperatura en grados Celsius o humo en ppm:"); // Solicita la lectura segun el dispositivo.
                            int dato = teclado.nextInt(); // Lee el valor medido.
                            teclado.nextLine(); // Consume el salto de linea.
                            System.out.println(s.registrarLectura(num, dato)); // Solicita registrar la lectura y generar la alerta que corresponda.
                            break; 
                        } 

                        case 7: { // Registra un movimiento de una camara.
                            System.out.println("Identificador de la camara:"); // Solicita el identificador de la camara.
                            int num = teclado.nextInt(); // Lee el identificador.
                            System.out.println("Descripcion del movimiento:"); // Solicita describir el movimiento.
                            String movimiento = teclado.nextLine(); // Lee la descripcion completa.
                            System.out.println(s.registrarMovimineto(num, movimiento)); // Usa la escritura del metodo en el Sistema que compartiste.
                            break; 
                        } 

                        case 8: { // Avanza una hora de simulacion.
                            s.avanzarHora(); // Calcula consumos y realiza el cierre diario cuando corresponde.
                            System.out.println("Hora avanzada. Hora de simulacion: " + s.Gethora()); // Muestra la nueva hora acumulada.
                            System.out.println("Hora del dia: " + s.Gethora() % 24 + ":00"); // Muestra la nueva hora del dia.
                            break; }

                        case 9: { // Atiende la alerta pendiente mas urgente.
                            System.out.println(s.atenderAlerta()); // Muestra la alerta atendida o el mensaje de error.
                            break; }

                        case 10: { // Consulta una oficina.
                            System.out.println("Codigo de la oficina:"); // Solicita el codigo de oficina.
                            int codi = teclado.nextInt(); // Lee el codigo.
                            teclado.nextLine(); // Consume el salto de linea.
                            System.out.println(s.consultarOficina(codi)); // Muestra la informacion o el mensaje de error.
                            break; }

                        case 11: { // Consulta un dispositivo.
                            System.out.println("Codigo del dispositivo:"); // Solicita el codigo del dispositivo.
                            int codi= teclado.nextInt(); // Lee el codigo.
                            teclado.nextLine(); // Consume el salto de linea.
                            System.out.println(s.consultarDispositivo(codi)); // Muestra la informacion o el mensaje de error.
                            break;}

                        case 12: { // Consulta una alerta.
                            System.out.println("Codigo de la alerta:"); // Solicita el codigo de alerta.
                            int codi = teclado.nextInt(); // Lee el codigo.
                            teclado.nextLine(); // Consume el salto de linea.
                            System.out.println(s.consultarAlerta(codi)); // Muestra la informacion o el mensaje de error.
                            break; 
                        } 

                        case 13: { // Genera uno de los reportes del programa.
                            System.out.println("Tipo de reporte:"); // Presenta el submenu de reportes.
                            System.out.println("1. Dispositivos por oficina\n2. Alertas pendientes\n3. Alertas atendidas con retraso\n4. Facturas por oficina"); // Muestra las opciones que trabajamos.
                            int t = teclado.nextInt(); // Lee el reporte solicitado.
                            teclado.nextLine(); // Consume el salto de linea.
                            System.out.println(s.generarReporte(t)); // Genera el reporte y muestra el resultado.
                            break; 
                        } 

                        case 14: { // Finaliza el programa.
                            System.out.println("¡Hasta pronto!"); // Muestra el mensaje de despedida.
                            break;
                        } 
                        default: { // Si la opcion no pertenece al menu.
                            System.out.println("Ha ingresado una opcion no permitida."); // Informa que no existe la opcion.
                            break; 
                        } 
                    } 
                    error = false; // Termina la solicitud de datos y regresa al menu.
                } catch (java.util.InputMismatchException e) { // Detecta datos numericos con formato incorrecto.
                    teclado.nextLine(); // Descarta el dato incorrecto para poder volver a leer.
                    System.out.println("Ha ingresado el dato en un formato no permitido. Ingrese nuevamente los datos de esta opcion."); // Explica que se repetira la operacion.
                }
            } 
        } 
	}
}
