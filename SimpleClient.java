package secondyear;
import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) {
        String serverAddress = "DESKTOP-KDELJ4I"; // Replace with the server's IP address or hostname
        int port = 19000; // Must match the server's port

        try (
            Socket socket = new Socket(serverAddress, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ) {
            System.out.println("Connected to server at " + serverAddress + ":" + port);
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput); // Send user input to server
                System.out.println("Server response: " + in.readLine()); // Read server response
                if (userInput.equalsIgnoreCase("bye")) {
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + serverAddress);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + serverAddress);
            System.err.println(e.getMessage());
        }
    }
}