package edu.poli.main;


import java.util.Scanner;

import edu.poli.client.Client;

public class ClientMain {
	
	public static void main(String[] args)  {
		
		Client client = new Client();
		
		Scanner sc = new Scanner(System.in);
		try {
			while(true) {
				System.out.println("Menú Principal");
				System.out.println("1.Depositar Dinero");
				System.out.println("2.Consultar cuenta");
				String m = sc.nextLine();
				String message = "";
				switch (m) {
				case "1":
					System.out.print("Ingrese número cuenta:");
					Integer cuenta = sc.nextInt();
					System.out.print("Ingrese valor:");
					Integer valor = sc.nextInt();
					message = "1,"+cuenta.toString() +"," +valor.toString();
					
					client.startCliente(message);
					client.readM();
					break;
				case "2":
					System.out.print("Ingrese la cuenta:");
					Integer cuentaT = sc.nextInt();
					message = "2," + cuentaT.toString();
					client.startCliente(message);
					client.readM();
					break;
				
				default:
					System.out.println("Opcion no Valida");
					break;
				}
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
		

	}

}
