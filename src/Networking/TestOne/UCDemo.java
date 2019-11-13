package Networking.TestOne;

//Demonstrate URLConnection.
import java.net.*;
import java.io.*;
import java.util.Date;

class UCDemo {
	public static void main(String args[]) throws Exception {
		int c;
		URL hp = new URL("http://www.guimp.com/");
		URLConnection hpCon = hp.openConnection();
		long d = hpCon.getDate();

		if (d == 0)
			System.out.println("No date information.");
		else
			System.out.println("Date: " + new Date(d));
		System.out.println("Content-Type: " + hpCon.getContentType());

		d = hpCon.getExpiration();

		if (d == 0)
			System.out.println("No expiration information.");
		else
			System.out.println("Expires: " + new Date(d));

		d = hpCon.getLastModified();

		if (d == 0)
			System.out.println("No last-modified information.");
		else
			System.out.println("Last-Modified: " + new Date(d));

		int len = hpCon.getContentLength();

		if (len == -1)
			System.out.println("Content length unavailable.");
		else
			System.out.println("Content-Length: " + len);

		if (len != 0) {
			System.out.println("=== Content ===");

			InputStream input = hpCon.getInputStream();

			int i = len;

			while (((c = input.read()) != -1)) {

				char k = (char) c;
				System.out.print(k);
			}

			input.close();
		}
	}
}
