package rest_apis.access_tokens;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.time.Instant;

public class AdminTokenPool extends BaseTokenPool {
    private static final String AUTH_SERVER_URL = "https://your-auth-server.com/token";
    private static final String REFRESH_URL = "https://your-auth-server.com/refresh";
    private static final String CLIENT_ID = "your-admin-client-id";
    private static final String CLIENT_SECRET = "your-admin-client-secret";

    public AdminTokenPool(int poolSize) {
        super("admin", poolSize);
    }

    @Override
    protected TokenInfo fetchNewToken() {
        try {
            Response response = RestAssured.given()
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("grant_type", "client_credentials")
                    .formParam("client_id", CLIENT_ID)
                    .formParam("client_secret", CLIENT_SECRET)
                    .post(AUTH_SERVER_URL);

            if (response.getStatusCode() != 200) {
                throw new RuntimeException("Failed to fetch token. Status code: " + response.getStatusCode());
            }

            JSONObject jsonResponse = new JSONObject(response.getBody().asString());
            String accessToken = jsonResponse.getString("access_token");
            long expiresIn = jsonResponse.getLong("expires_in");

            return new TokenInfo(accessToken, Instant.now().plusSeconds(expiresIn));
        } catch (Exception e) {
            throw new RuntimeException("Error fetching new token", e);
        }
    }

    @Override
    protected TokenInfo refreshToken(TokenInfo tokenInfo) {
        try {
            Response response = RestAssured.given()
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("grant_type", "refresh_token")
                    .formParam("refresh_token", tokenInfo.getRefreshToken())
                    .formParam("client_id", CLIENT_ID)
                    .formParam("client_secret", CLIENT_SECRET)
                    .post(REFRESH_URL);

            if (response.getStatusCode() != 200) {
                // If refresh fails, fetch a new token instead
                return fetchNewToken();
            }

            JSONObject jsonResponse = new JSONObject(response.getBody().asString());
            String newAccessToken = jsonResponse.getString("access_token");
            long expiresIn = jsonResponse.getLong("expires_in");
            String newRefreshToken = jsonResponse.optString("refresh_token", tokenInfo.getRefreshToken());

            return new TokenInfo(newAccessToken, Instant.now().plusSeconds(expiresIn), newRefreshToken);
        } catch (Exception e) {
            // If refresh fails for any reason, fall back to fetching a new token
            return fetchNewToken();
        }
    }
}

class TokenInfo {
    private final String token;
    private final Instant expiresAt;
    private final String refreshToken;

    public TokenInfo(String token, Instant expiresAt) {
        this(token, expiresAt, null);
    }

    public TokenInfo(String token, Instant expiresAt, String refreshToken) {
        this.token = token;
        this.expiresAt = expiresAt;
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public boolean isValid() {
        return Instant.now().isBefore(expiresAt);
    }

    public boolean needsRefresh() {
        // Refresh if token expires in less than 5 minutes
        return Instant.now().plusSeconds(300).isAfter(expiresAt);
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
