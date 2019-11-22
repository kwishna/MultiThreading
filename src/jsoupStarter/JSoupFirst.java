package jsoupStarter;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * First JSoup Code!
 * @author AB D
 * @since 20 November 2019
 */
public class JSoupFirst {

	public static void main(String[] args) throws IOException {

		Connection conn = Jsoup.connect("https://www.javatpoint.com");
		Document doc = conn.get();
		System.out.println(doc.title());

		Files.writeString(Paths.get("html.html"), new String(conn.execute().bodyAsBytes()), StandardOpenOption.CREATE);

		Document d1 = Jsoup.parse(Files.readString(Paths.get("html.html")));
		System.out.println(d1.select("title").first().text());
		Elements e = d1.select("title");
		System.out.println(e.get(0));

		Element page = d1.getElementById("page");
		System.out.println(page.attr("class"));

		Jsoup.connect("https://www.javatpoint.com")
				.data("query", "Java")
				.userAgent("Mozilla")
				.cookie("auth", "token")
				.timeout(3000)
				.post();

		String unsafe =
				"<p><a href='http://example.com/' onclick='stealCookies()'>Link</a></p>";
		String safe = Jsoup.clean(unsafe, Whitelist.basic());
		// now: <p><a href="http://example.com/" rel="nofollow">Link</a></p>

	}
}
