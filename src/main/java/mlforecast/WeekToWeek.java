package mlforecast;

import com.github.signaflo.timeseries.TimePeriod;
import com.github.signaflo.timeseries.TimeSeries;
import com.github.signaflo.timeseries.forecast.Forecast;
import com.github.signaflo.timeseries.model.arima.Arima;
import com.github.signaflo.timeseries.model.arima.ArimaOrder;

import java.util.List;

public class WeekToWeek implements IForecast {

    // constructor
    public WeekToWeek(List<Double> dayExpenseList) {

        _dayExpenseList = dayExpenseList;
    }

    private List<Double> _dayExpenseList;

    @Override
    public List<Double> DoForecast() {
        _dayExpenseList = ForecastWeekToWeek();
        return _dayExpenseList;
    }

    // hafta arrayı oluştururken kullan.
    public int GetArraySize() {
        int count = 0;
        for (int i = 0; i < _dayExpenseList.size(); i++) {
            if (i % 7 == 0)
                count++;

        }
        return count;

    }

    public double[] CreateArray() {
        int count = GetArraySize();
        double[] weekArray = new double[count];

        double total = 0;

        for (int i = 0; i < count; i++) {
            for (int j = i * 7; j < (i + 1) * 7; j++) {
                total += _dayExpenseList.get(j);
            }
            weekArray[i] = total;
            total = 0;
        }

        return weekArray;

    }

    // kaç günlük veri ise ona uygun hafta oluştur.
    // sezonsallık yapmadan 1 haftalık tahmin yap.

    private List<Double> ForecastWeekToWeek() {

        double[] weekArray = CreateArray();

        TimePeriod weektoweek = TimePeriod.oneWeek();
        TimeSeries series = TimeSeries.from(weektoweek, weekArray);

        ArimaOrder order = ArimaOrder.order(1, 0, 0, 0, 0, 0);

        Arima model = Arima.model(series, order);
        Forecast forecast = model.forecast(1);
        for (int i = 0; i < weekArray.length; i++) {
            System.out.println(weekArray[i]);
        }
        return forecast.pointEstimates().asList();

    }

}