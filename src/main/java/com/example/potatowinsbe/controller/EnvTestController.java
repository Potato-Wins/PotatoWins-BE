import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvTestController {

    @Value("${ELASTICSEARCH_URI:default}")
    private String elasticsearchUri;

    @Value("${ELASTICSEARCH_USERNAME:default}")
    private String elasticsearchUsername;

    @Value("${ELASTICSEARCH_PASSWORD:default}")
    private String elasticsearchPassword;

    @GetMapping("/env-test")
    public String testEnvVariables() {
        return String.format(
                "ELASTICSEARCH_URI: %s, ELASTICSEARCH_USERNAME: %s, ELASTICSEARCH_PASSWORD: %s",
                elasticsearchUri, elasticsearchUsername, elasticsearchPassword
        );
    }
}
