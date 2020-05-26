package mlforecast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {


    public static void main(String[] args) {


        Random rnd = new Random();
        List<Double> dayExpenseList = new ArrayList<Double>();

        for (int i=0;i<28;i++)
        {
                dayExpenseList.add((double) (rnd.nextInt(80)+20.0));
        }



        ForecastManager forecastManager = new ForecastManager(new WeekToWeek(dayExpenseList));

        List<Double> result = forecastManager.DoForecast();
        System.out.println("*************");
        for (int i=0; i<result.size();i++){

            System.out.println(result.get(i));
        }



    }


}
