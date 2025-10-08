package secondyear;

// Program to get system IP Address using InetAddress
	import java.net.*;

	public class GetIPAdress {
	    public static void main(String[] args) {
	        try {
	            // Get the local host information
	            InetAddress localHost = InetAddress.getLocalHost();
	            
	            // Print host name and IP address
	            System.out.println("Host Name: " + localHost.getHostName());
	            System.out.println("IP Address: " + localHost.getHostAddress());
	        } catch (UnknownHostException e) {
	            System.out.println("Unable to retrieve IP address.");
	            e.printStackTrace();
	        }
	    }
	}



