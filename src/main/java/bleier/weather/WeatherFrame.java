package bleier.weather;

import com.andrewoid.apikeys.ApiKey;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.swing.*;
import java.awt.*;

public class WeatherFrame extends JFrame {
    private JTextField place = new JTextField();
    private JLabel temp = new JLabel();
    private JLabel feels_like = new JLabel();
    private JLabel temp_min = new JLabel();
    private JLabel temp_max = new JLabel();
    private WeatherService service;
    private JLabel description = new JLabel();
    private WeatherController controller;

    public WeatherFrame() {
        setTitle("Weather Now");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        service = new WeatherServiceFactory().getService();

        setLayout(new GridLayout(6, 1));
        ApiKey apiKey = new ApiKey();

        controller = new WeatherController(service, place, temp, feels_like, temp_min, temp_max, description, apiKey);
        //controller.display();

        add(place);
        add(temp);
        add(feels_like);
        add(temp_min);
        add(temp_max);
        add(description);
    }



    public static void main(String[] args) {
        WeatherFrame frame = new WeatherFrame();
        frame.setVisible(true);
    }

}
