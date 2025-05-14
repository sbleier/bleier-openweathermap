package bleier.weather;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.swing.*;
import java.awt.*;

public class WeatherFrame extends JFrame {
    private JLabel place = new JLabel();
    private JLabel temp = new JLabel();
    private WeatherService service;

    public WeatherFrame() {
        setTitle("Products");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        service = new WeatherServiceFactory().getService();

        setLayout(new GridLayout(2, 1));
        display();


        add(place);
        add(temp);
    }

    public void display() {
        WeatherResponse response = service.weatherNow("Edison", "0ba6a8451c8dbe36a1a0919a5810a457", "imperial").blockingGet();
        this.place.setText("Place: " + response.name);
        this.temp.setText("Temp (in imperial units): " + response.main.temp);


    }

    public static void main(String[] args) {
        WeatherFrame frame = new WeatherFrame();
        frame.setVisible(true);
    }

}
