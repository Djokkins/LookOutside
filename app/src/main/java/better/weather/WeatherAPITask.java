package better.weather;

import static androidx.fragment.app.FragmentManager.TAG;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class WeatherAPITask extends AsyncTask<String, Void, String> {

    private String endpoint = "https://api.met.no/weatherapi/locationforecast/2.0/compact?"; //API endpoint fra YR som giver 9 dages forecast
    private int numberOffHourlyForecasts = 92; // løbene dag(worst case 24) time for time + 2 hele døgn time for time + 4 tidspunkter for de resterende 5 dag
    private WeatherData[] weatherDataByHour = new WeatherData[numberOffHourlyForecasts];


    public void RequestWeatherDataFromAPI(double latitude, double longitude){
        //String weatherData = doInBackground(endpoint+"lat="+latitude+"&lon="+longitude);
        //ParseWeatherDataJSON(weatherData);
    }

    protected void ParseWeatherDataJSON(String weatherdata){
        try {

            JSONObject jsonWeatherData = new JSONObject(weatherdata);
            // Assuming the JSON has an array named "timeseriesarray"
            JSONArray timeseriesArray = jsonWeatherData.getJSONArray("timeseries");

            for (int i = 0; i < timeseriesArray.length() && i < numberOffHourlyForecasts; i++) {
                JSONObject item = timeseriesArray.getJSONObject(i);
                String datetime = item.getString("time"); // Assuming the JSON objects have a field named "name"
                JSONArray instant = item.optJSONArray("instant");

                //WeatherData _weatherdata = new WeatherData(datetime, instant);
                //weatherDataByHour[i] = _weatherdata;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... params) {
        String urlString = params[0]; // URL to call
        String weatherdataraw = "";
        InputStream in = null;

        // HTTP Get
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(10000); // Set timeout for the connection
            urlConnection.setReadTimeout(10000); // Set timeout for reading data
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                weatherdataraw = result.toString();
            } else {
                weatherdataraw = "Error: " + statusCode;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return weatherdataraw;
    }
    @Override
    protected void onPostExecute(String result) {
        //int d = Log.d("Result: " + result);
        // Do something with the result
        // For example, update UI
        System.out.println(result);
    }
}
