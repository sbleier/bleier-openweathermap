package bleier.weather;

import com.andrewoid.apikeys.ApiKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherServiceTest {

    @Test
    public void weatherNow() {
        WeatherService service = new WeatherServiceFactory().getService();
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();

        WeatherResponse response = service.weatherNow("Edison", keyString, "imperial").blockingGet();

        assertTrue(response.main.temp > 0);

    }

}