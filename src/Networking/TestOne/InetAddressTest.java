package Networking.TestOne;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		
		InetAddress[] inet = InetAddress.getAllByName("www.google.com");
		
		for(InetAddress a : inet) {
				
			System.out.println(a);
		}
	}

}
