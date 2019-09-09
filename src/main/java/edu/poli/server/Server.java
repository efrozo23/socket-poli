package edu.poli.server;

import java.io.BufferedReader;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.util.List;

public class Server {

	private static Socket socket;

	public void startServer() throws IOException {
		ServerSocket serverSocket = new ServerSocket(9090);
		try {
			System.out.println("Esperando....");
			while (true) {
				socket = serverSocket.accept();
				System.out.println("Cliente en línea");
				BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String message = entrada.readLine();
				String[] meStrings = message.split(",");
				String[] getCuenta = { "", "" };
				if (meStrings[0].equals("1")) {

					this.writeFile(meStrings[1].concat(",").concat(meStrings[2]));
					this.messageResponse("OK");

				}
				if (meStrings[0].equals("2")) {

					getCuenta = searchCuenta(meStrings[1]);

					System.out.println("Cuenta :" + getCuenta[0] + " - Valor:" + getCuenta[1]);
					this.messageResponse("Cuenta :" + getCuenta[0] + " -Valor:" + getCuenta[1]);
				}
				entrada.close();

				socket.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			this.messageResponse("NO-OK");
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	public void writeFile(String text) throws UnknownHostException, IOException {
		try {
			File file = new File("cuentas.txt");
			if (file.exists())
				Files.write(Paths.get(file.getAbsolutePath()), text.concat("\n").getBytes(), StandardOpenOption.APPEND);
			List<String> data = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			data.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
			this.messageResponse("NO-OK");
		}

	}

	public String[] searchCuenta(String cuenta) {
		File file = new File("cuentas.txt");
		List<String> data;
		try {
			System.out.println("Cuenta a buscar: " + cuenta);
			data = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			String[] response = { "CUENTA NO ENCONTRADA", "" };
			data.forEach(x -> {
				if (x.split(",")[0].equals(cuenta)) {
					response[0] = x.split(",")[0] + ",";
					response[1] = x.split(",")[1];
				}
			});

			return response;
		} catch (IOException e) {

			return new String[] { e.getMessage(), "" };
		}
	}

	public void messageResponse(String message) throws UnknownHostException, IOException {
		Socket socketT = new Socket("192.168.0.8", 9090);
		DataOutputStream doDataOutputStream = new DataOutputStream(socketT.getOutputStream());
		doDataOutputStream.write(message.getBytes());
		doDataOutputStream.close();
		socketT.close();
	}

}
