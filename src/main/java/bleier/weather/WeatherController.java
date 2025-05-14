package bleier.weather;

import com.andrewoid.apikeys.ApiKey;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherController {

    private JTextField place;
    private JLabel temp = new JLabel();
    private JLabel feels_like;
    private JLabel temp_min;
    private JLabel temp_max;
    private WeatherService service;
    private ApiKey apiKey;
    private JLabel description;

    public WeatherController(WeatherService service, JTextField place, JLabel temp, JLabel feels_like, JLabel temp_min, JLabel temp_max, JLabel description, ApiKey apiKey) {
        this.service = service;
        this.place = place;
        this.temp = temp;
        this.feels_like = feels_like;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.description = description;
        this.apiKey = apiKey;

        place.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display();
            }
        });
    }



    public void display() {

        String keyString = apiKey.get();
        Disposable disposable = service.weatherNow(place.getText().trim(), keyString, "imperial")
                // tells Rx to request the data on a background Thread
                .subscribeOn(Schedulers.io())
                // tells Rx to handle the response on Swing's main Thread
                .observeOn(Schedulers.from(SwingUtilities::invokeLater))
                //.observeOn(AndroidSchedulers.mainThread()) // Instead use this on Android only
                .subscribe(
                        this::handleResponse,
                        Throwable::printStackTrace);

    }

    private void handleResponse(WeatherResponse response) {
        //this.place.setText(response.name);
        this.temp.setText("Temp (in imperial units): " + response.main.temp);
        this.feels_like.setText("It feels like " + response.main.feels_like);
        this.temp_min.setText("Lowest temperature of the day: " + response.main.temp_min);
        this.temp_max.setText("Highest temperature of the day: " + response.main.temp_max);
        this.description.setText("Condition: " + response.weather[0].main);


    }
}
