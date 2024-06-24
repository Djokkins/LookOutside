package better.weather;

import java.util.Calendar;
import java.util.Date;

public class WeatherData {
    private Date _time = new Date();
    private WInstant _instant;

    public WeatherData(String _time, WInstant _instant) {
        this._time = new Date(_time);
        this._instant = _instant;
    }


    public class WInstant {
        private double _airPressureAtSeaLevel = 0.0;
        private double _airTemperature = 0.0;
        private double _relativeHudmidity = 0.0;
        private double _windspeed = 0.0;

        public WInstant(double _airPressureAtSeaLevel, double _airTemperature, double _relativeHudmidity, double _windspeed) {
            this._airPressureAtSeaLevel = _airPressureAtSeaLevel;
            this._airTemperature = _airTemperature;
            this._relativeHudmidity = _relativeHudmidity;
            this._windspeed = _windspeed;
        }
        public WInstant(){}
    }
}
