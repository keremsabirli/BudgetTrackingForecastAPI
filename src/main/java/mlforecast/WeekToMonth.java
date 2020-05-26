package mlforecast;

import com.github.signaflo.timeseries.TimePeriod;
import com.github.signaflo.timeseries.TimeSeries;
import com.github.signaflo.timeseries.forecast.Forecast;
import com.github.signaflo.timeseries.model.arima.Arima;
import com.github.signaflo.timeseries.model.arima.ArimaOrder;

import java.util.List;

public class WeekToMonth implements IForecast {


    public WeekToMonth(List<Double> dayExpenseList){

        _dayExpenseList=dayExpenseList;

    }

    private List<Double> _dayExpenseList;

        //kaç hafta var
    public int GetArraySize(){
        int count=0;
        for (int i = 0;i<_dayExpenseList.size();i++){
            if (i%7==0)
                count++;

        }
        return count;


    }

    // gün sayısına göre hafta oluştur
    public double [] CreateArray(){
        int count = GetArraySize();
        double [] weekArray = new double[count];

        double total=0;

        for (int i=0;i<count;i++){
            for (int j=i*7;j<(i+1)*7;j++){
                total+=_dayExpenseList.get(j);
            }
            weekArray[i]=total;
            total=0;
        }

        return weekArray;

    }

    //min 12 hafta veri ile 1 aylık tahmin
    private List<Double> ForecastWeekToMonth(){



        double [] weekArray= CreateArray();



        TimePeriod weektoweek = TimePeriod.oneWeek();
        TimeSeries series = TimeSeries.from(weektoweek, weekArray);


        ArimaOrder order = ArimaOrder.order(1,0,1,1,1,0);


        TimePeriod month = TimePeriod.oneMonth();
        Arima model = Arima.model(series, order,month);
        Forecast forecast = model.forecast(4);
        for (int i=0;i<weekArray.length;i++){
            System.out.println(weekArray[i]);
        }

        return forecast.pointEstimates().asList();


    }

    @Override
    public List<Double> DoForecast() {
        _dayExpenseList= ForecastWeekToMonth();
        return _dayExpenseList;
    }
}
