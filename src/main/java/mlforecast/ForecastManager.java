package mlforecast;


import java.util.List;

public class ForecastManager {

    private IForecast _forecast;
    public ForecastManager(IForecast forecast){


        _forecast= forecast;

    }

    public List<Double> DoForecast(){


        return _forecast.DoForecast();

    }


}
