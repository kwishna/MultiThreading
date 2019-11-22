package Networking.TestOne;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class TestOne { // https://www.baeldung.com/java-http-request

	public static void main(String[] args) throws Exception {
		 
		URI uri = new URI("https://reqres.in/api/users?page=2");
		URL url = uri.toURL();
		
		
		//GET
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		
		url.openConnection().connect(); // Establizing Connection. NOT REQUIRED
		
		Map<String, List<String>> header = con.getHeaderFields();

		// Ways To Iterate A Map Object
//		header.entrySet().forEach(entry->System.out.println(entry.getKey()+"-------"+entry.getValue()));
//		header.keySet().forEach(key->System.out.println(header.get(key)));
//		header.forEach((K,V)->System.out.println(K+"--"+V));
		
		for(Map.Entry<String, List<String>> entry : header.entrySet()) {
			
			System.out.println(entry.getKey() +" ::: "+entry.getValue());
			
		}
		
		System.out.println("Response Code ::: "+con.getResponseCode()+" Response Message ::: "+con.getResponseMessage());

		//Response GET
		BufferedInputStream buf = new BufferedInputStream(con.getInputStream());
		
		byte[] b = buf.readAllBytes();
		for(byte by : b) {
			System.out.print((char)by);
		}
		System.out.println();
		System.out.println("\n*****************************************************************************\n");
		
		
		
		//POST REQUEST		
		URL url2 = new URL("https://reqres.in/api/users");
	    HttpURLConnection connection = (HttpURLConnection) url2.openConnection();
		
	    connection.setDoInput(true);
	    connection.setDoOutput(true);
	    connection.setRequestMethod("POST");
	    connection.setRequestProperty("Accept", "application/json");
	    connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	    connection.setRequestProperty("Content-Language", "en-US"); 
		
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        
        String payload="{\"jsonrpc\":\"2.0\",\"method\":\"changeDetail\",\"params\":[{\"id\":11376}],\"id\":2}";
        payload ="{ \"name\": \"morpheus\", \"job\": \"leader\" }";
     
        writer.write(payload); // Or,  byte[] outputBytes = payload.getBytes("UTF-8"); os.write(outputBytes);
        
        writer.close();
        
        
// 		Reading Response 1st Way  :-
        
//      BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//      StringBuffer jsonString = new StringBuffer();
//      String line;
//      while ((line = br.readLine()) != null) {
//             jsonString.append(line);
//      } 
//      System.out.println(jsonString);
//      br.close();
       
        
//		Reading Response 2nd Way :-
        InputStream in = new BufferedInputStream(connection.getInputStream());
        String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");    

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(result);
        String prettyJsonString = gson.toJson(je);
        System.out.println(prettyJsonString);
        
        in.close();
        
        connection.disconnect();
	}
}
