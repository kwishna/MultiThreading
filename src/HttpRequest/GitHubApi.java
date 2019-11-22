package HttpRequest;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

// https://hc.apache.org/httpcomponents-client-4.2.x/examples.html
public class GitHubApi {

	public static void main(String[] args) throws IOException {

		// Rest Assured! : Working Fine!
		String host = "https://api.github.com";
		String path = "/repos/kwishna/GitPractice";

		RestAssured.baseURI = host;

		ValidatableResponse r = RestAssured
				.given()
				.header("Accept", "application/vnd.github.v3+json")
				.auth()
				.preemptive()
				.basic("kwishna", "ggcgvhbjcgvgfh")
				.when()
				.get(path).then().assertThat().statusCode(200);
		System.out.println(r.extract().response().asString());
//		System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(s1));

		// Using Apache HttpClient Fluent API :-
//		HttpResponse res = Request.Get("https://api.github.com/user")
//				.execute().returnResponse();
//		byte[] s = res.getEntity().getContent().readAllBytes();
//		String s1 = new String(s, StandardCharsets.UTF_8);
//		System.out.println(s1);

		// HttpClient Preemptive : Not Working
//		CredentialsProvider provider = new BasicCredentialsProvider();
//		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("kricna", "r4ghup4t1_R4GH4");
//
//		AuthCache authCache = new BasicAuthCache();
//		authCache.put(new HttpHost("https://api.github.com"), new BasicScheme());
//
//		provider.setCredentials(AuthScope.ANY, credentials);
//
//		HttpClientContext context = HttpClientContext.create();
//		context.setCredentialsProvider(provider);
//		context.setAuthCache(authCache);
//
//		HttpClient client = HttpClientBuilder.create().build();
//		HttpResponse response = client.execute(
//				new HttpGet("https://api.github.com/user"), context);
//
//		int statusCode = response.getStatusLine().getStatusCode();
//		System.out.println(statusCode);

		// Completed : Not Working
//		CredentialsProvider provider1 = new BasicCredentialsProvider();
//		UsernamePasswordCredentials credentials1 = new UsernamePasswordCredentials("kricna", "r4ghup4t1_R4GH4");
//		provider1.setCredentials(AuthScope.ANY, credentials1);
//		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(provider).build();
//		CloseableHttpResponse resp = client.execute(new HttpGet("https://api.github.com/user"));
//		System.out.println(new String(resp.getEntity().getContent().readAllBytes()));

	}
}
