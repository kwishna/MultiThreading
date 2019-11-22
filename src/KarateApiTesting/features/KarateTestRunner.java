package KarateApiTesting.features;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;


@KarateOptions(
	features = "classpath:KarateApiTesting\\features\\karateFirst.feature"
//	tags = {"@apple"}
)
@RunWith(Karate.class)
public class KarateTestRunner {
}
