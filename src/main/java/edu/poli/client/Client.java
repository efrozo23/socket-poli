package edu.poli.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

	private static Socket socket;
	

	public void startCliente(String message) {
		try {
			socket = new Socket("192.168.0.7", 9090);
			DataOutputStream data = new DataOutputStream(socket.getOutputStream());
			data.write(message.getBytes());
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readM() throws IOException {
		ServerSocket serverSocket = new ServerSocket(9090);
		try {

			socket = serverSocket.accept();

			BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String message = entrada.readLine();
			System.out.println(message);
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
