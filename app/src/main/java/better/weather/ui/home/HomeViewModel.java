package better.weather.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import better.weather.WeatherAPITask;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mTemp;
    //private final MutableLiveData<String> mHumidity;

    public HomeViewModel() {
        mTemp = new MutableLiveData<>();
        WeatherAPITask.execute();

        mTemp.setValue("20");
    }

    public LiveData<String> getTemp() {
        return mTemp;
    }
}