package mlforecast;

import com.github.signaflo.timeseries.TimePeriod;
import com.github.signaflo.timeseries.TimeSeries;
import com.github.signaflo.timeseries.forecast.Forecast;
import com.github.signaflo.timeseries.model.arima.Arima;
import com.github.signaflo.timeseries.model.arima.ArimaOrder;

import java.util.List;

/*
* 90 günlük veriyi alır ve 30 günlük veri döndürür.
*
* */


public class DayToMonth implements IForecast{

    private List<Double> _dayExpenseList;

    public DayToMonth(List<Double> dayExpenseList) {

        _dayExpenseList= dayExpenseList;
    }



    @Override
    public List<Double> DoForecast() {
        _dayExpenseList= ForecastDayToMonth();
        return _dayExpenseList;
    }



    private List<Double> ForecastDayToMonth(){

        double [] expenseDay= new double[_dayExpenseList.size()];

        for (int i=0; i<_dayExpenseList.size();i++)
        {
            expenseDay[i]= _dayExpenseList.get(i);
        }



        TimePeriod day = TimePeriod.oneDay();
        TimeSeries series = TimeSeries.from(day, expenseDay);

        //90 günlük veri 30 günlük günlük tahmin (Pazartesi-Salı...)
        //Hocanın verdiği sezonsallık parametreleri :
        ArimaOrder order = ArimaOrder.order(1,0,1,1,1,0);

        TimePeriod month = TimePeriod.oneMonth();
        Arima model = Arima.model(series, order, month);
        Forecast forecast = model.forecast(30);

        return forecast.pointEstimates().asList();


    }



}