package edu.poli.main;

import java.io.IOException;

import edu.poli.server.Server;

public class PoliMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Server server = new Server();
		System.out.println("Iniciando servidor");
		server.startServer();
		
	}

}
