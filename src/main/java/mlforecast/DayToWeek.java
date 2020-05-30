package mlforecast;

import com.github.signaflo.timeseries.TimePeriod;
import com.github.signaflo.timeseries.TimeSeries;
import com.github.signaflo.timeseries.model.arima.Arima;
import com.github.signaflo.timeseries.model.arima.ArimaOrder;
import com.github.signaflo.timeseries.forecast.Forecast;

import java.util.List;
/*
*
* 28 günlük veriyi alır ve 7 günlük veri döndürür.
*
* */


public class DayToWeek implements IForecast {

    private List<Double> _dayExpenseList;

    public DayToWeek(List<Double> dayExpenseList) {

        _dayExpenseList= dayExpenseList;
    }




    //Tahmin yapıp .Net'e döndürecek.
    @Override
    public List<Double> DoForecast() {

            _dayExpenseList= ForecastDayToWeek();
            return _dayExpenseList;
    }

    //Günlük veri alıp, 1 haftalık tahmin yapacak.
    private List<Double> ForecastDayToWeek(){
        double [] expenseDay= new double[_dayExpenseList.size()];


        for (int i=0; i<_dayExpenseList.size();i++)
        {
            expenseDay[i]= _dayExpenseList.get(i);
        }



        TimePeriod day = TimePeriod.oneDay();
        TimeSeries series = TimeSeries.from(day, expenseDay);

        //28 gün veri, 1 haftalık günlük tahmin (Pazartesi-Salı...)
        //Hocanın verdiği sezonsallık parametreleri :
        ArimaOrder order = ArimaOrder.order(1,0,1,0,1,1);

        TimePeriod week = TimePeriod.oneWeek();
        Arima model = Arima.model(series, order, week);
        Forecast forecast = model.forecast(7);
        for (int i=0;i<expenseDay.length;i++){
            System.out.println(expenseDay[i]);
        }
        return forecast.pointEstimates().asList();


    }
}
