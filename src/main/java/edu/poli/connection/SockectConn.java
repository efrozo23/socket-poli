package edu.poli.connection;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
 
public class SockectConn {

	private final int PUERTO = 9090;
	private final String HOST = "localhost";
	protected String mensajeServidor;
	protected Socket cs;
	protected ServerSocket ss;

	protected DataOutputStream salidaServidor, salidaCliente;

	public SockectConn(String tipo) throws IOException {
		if (tipo.equalsIgnoreCase("servidor")) {
			ss = new ServerSocket(PUERTO);
			cs = new Socket();
		} else {
			cs = new Socket(HOST, PUERTO);
		}
	}

}
