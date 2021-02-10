package UnderscroreJavaApi;

import com.github.underscore.lodash.U;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PlayXML {

	public static void main(String[] args) throws IOException {
		String xmlStr = Files.readString(Path.of("sample.xml"));
		java.util.Map<String, Object> object = U.fromXmlMap(xmlStr);
		Object obj = U.get(object, "catalog.product.catalog_item[0]");
//		System.out.println(obj);
		// This Is Awesome
		U.set(object, "catalog.product.catalog_item[0].sizes", "Krishna");
		System.out.println(U.toXml(object));
	}
}
