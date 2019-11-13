package JDK11_java.net.http;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse.BodySubscribers;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

/*
 * JDK 11 java.net.http.* Package
 */
public class Client {

	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

		// Creating A Client Object
		HttpClient client = HttpClient.newBuilder().build();
	//	.proxy(ProxySelector.of(new InetSocketAddress(proxyHost, proxyPort)))

		// GET Request
		HttpRequest reqGet = HttpRequest.newBuilder().uri(new URI("https://reqres.in/api/users?page=2")).GET().build();

		// GET Request
//		HttpRequest reqGet2 = HttpRequest.newBuilder().uri(new URI(scheme, userInfo, host, port, path, query, fragment)).GET()
//				.build();

		// POST Request
		HttpRequest reqPost = HttpRequest.newBuilder().uri(URI.create("https://reqres.in/api/users?page=2"))
				.header("Content-Type", "application/json")
				.POST(BodyPublishers.ofString("{\"name\": \"morpheus\", \"job\": \"leader\"}")).build();

		// 1nd Way Getting Response Using BodyHandlers
		HttpResponse<String> response = client.send(reqGet, BodyHandlers.ofString());
		HttpHeaders headers = response.headers();

		// Print Headers Of Response
		for (Map.Entry<String, List<String>> m : headers.map().entrySet()) {
			System.out.println(m.getKey() + " ::: " + m.getValue());
		}

		// System.out.println(response.body());
		// Converting To Pretty JSON
		System.out.println(
				new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(response.body())));

		// 2nd Way Of Getting Response Using BodySubscribers
		HttpResponse<byte[]> response2 = client.send(reqPost, a -> BodySubscribers.ofByteArray());
		for (byte b : response2.body()) {
			System.out.print((char) b);

		
		//	Incorrect Below. To Be Checked.
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://www.google.com")).build();

		BodyHandler<Path> bodyHandler = (rspInfo) -> rspInfo.statusCode() == 200
					? BodySubscribers.ofFile(Paths.get("/tmp/f")) : BodySubscribers.replacing(Paths.get("/NULL"));

		client.sendAsync(request, bodyHandler).thenApply(HttpResponse::body).thenAccept(System.out::println);
		
		}
	}
}
