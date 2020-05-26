package mlforecast;

import com.github.signaflo.timeseries.TimePeriod;
import com.github.signaflo.timeseries.TimeSeries;
import com.github.signaflo.timeseries.forecast.Forecast;
import com.github.signaflo.timeseries.model.arima.Arima;
import com.github.signaflo.timeseries.model.arima.ArimaOrder;

import java.util.List;

public class MonthToMonth implements IForecast{


    public MonthToMonth(List<Double> dayExpenseList){

        _dayExpenseList=dayExpenseList;

    }
    private List<Double> _dayExpenseList;


    //kaç ay var?
    public int GetArraySize(){
        int count=0;
        for (int i = 0;i<_dayExpenseList.size();i++){
            if (i%30==0)
                count++;

        }
        return count;


    }
    //gün karşılık yeni 1 ay oluştur
    public double [] CreateArray(){
        int count = GetArraySize();
        double [] monthArray = new double[count];

        double total=0;

        for (int i=0;i<count;i++){
            for (int j=i*30;j<(i+1)*30;j++){
                total+=_dayExpenseList.get(j);
            }
            monthArray[i]=total;
            total=0;
        }

        return monthArray;

    }
    private List<Double> ForecastMonthToMonth(){



        double [] monthArray= CreateArray();



        TimePeriod monthtomonth = TimePeriod.oneMonth();
        TimeSeries series = TimeSeries.from(monthtomonth, monthArray);


        ArimaOrder order = ArimaOrder.order(1,0,0,0,0,0);


        Arima model = Arima.model(series, order);
        Forecast forecast = model.forecast(1);
        for (int i=0;i<monthArray.length;i++){
            System.out.println(monthArray[i]);
        }

        return forecast.pointEstimates().asList();


    }

    @Override
    public List<Double> DoForecast() {
        _dayExpenseList= ForecastMonthToMonth();
        return _dayExpenseList;
    }
}
