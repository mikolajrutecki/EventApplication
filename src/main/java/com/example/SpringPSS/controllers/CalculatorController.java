package com.example.SpringPSS.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Controller
public class CalculatorController {

    @RequestMapping(value="/user/index", method = RequestMethod.POST)
    public ModelAndView getInfo(){
        ModelAndView model = new ModelAndView("/user/index");

        return model;
    }

    @RequestMapping(value="/result", method = RequestMethod.POST)
    public ModelAndView submitInfo(@RequestParam(value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
                                   @RequestParam(value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime endDate,
                                   @RequestParam(value = "pricePerDay", required = false) int pricePerDay,
                                   @RequestParam(value = "below900cc", required = false) String below900cc,
                                   @RequestParam(value = "above900cc", required = false) String above900cc,
                                   @RequestParam(value = "breakfastAmount", required = false) String breakfastAmount,
                                   @RequestParam(value = "dinnerAmount", required = false) String dinnerAmount,
                                   @RequestParam(value = "supperAmount", required = false) String supperAmount,
                                   @RequestParam(value = "motocycle", required = false) String motocycle,
                                   @RequestParam(value = "motorbike", required = false) String motorbike){
        ModelAndView model = new ModelAndView("result");
        model.addObject("startDate", startDate);
        model.addObject("endDate", endDate);
        model.addObject("duration", timeDiff(startDate,endDate));
        model.addObject("pricePerDay", multiplyDaysByPrice(days(startDate, endDate), pricePerDay));
        model.addObject("checkCC", checkCC(below900cc, above900cc, motocycle, motorbike));
        model.addObject("countKM", countKM(below900cc, above900cc, motocycle, motorbike));
        model.addObject("sum", countSum(multiplyDaysByPrice(days(startDate, endDate), pricePerDay), countKM(below900cc, above900cc, motocycle, motorbike)));
        model.addObject("finalSum", countSum(multiplyDaysByPrice(days(startDate, endDate), pricePerDay), countKM(below900cc, above900cc, motocycle, motorbike)));
        return model;
    }

    //counts difference between two dates
    private String timeDiff(LocalDateTime startDate, LocalDateTime endDate){
        Duration duration = Duration.between(startDate, endDate);
        int day = (int) TimeUnit.SECONDS.toDays(duration.getSeconds());
        long hours = TimeUnit.SECONDS.toHours(duration.getSeconds()) - (day *24);
        long minute = TimeUnit.SECONDS.toMinutes(duration.getSeconds()) - (TimeUnit.SECONDS.toHours(duration.getSeconds())* 60);
        return day + "d " + hours +"h " + minute+ "min";
    }

    //returns amount of days between two dates, if its for example 1d 3h it will return 2days
    private int days(LocalDateTime startDate, LocalDateTime endDate){
        Duration duration = Duration.between(startDate, endDate);
        int day = (int)TimeUnit.SECONDS.toDays(duration.getSeconds());
        long hours = TimeUnit.SECONDS.toHours(duration.getSeconds()) - (day*24);
        if(hours > 0) day++;
        return day;
    }

    private double multiplyDaysByPrice(int days, int pricePerDay)
    {
        return days * pricePerDay;
    }

    //checks if user used >900cc or <900cc and returns amount of kilometers
    private String checkCC(String below900cc, String above900cc, String motocycle, String motorbike){
        if(!below900cc.isEmpty()) return "Przejazd samochodem osobowym(<=900cm3): " +below900cc +"km";
        if(!above900cc.isEmpty()) return "Przejazd samochodem osobowym(>900cm3): "+above900cc+"km";
        if(!motocycle.isEmpty()) return "Przejazd motocyklem: "+motocycle+"km";
        if(!motorbike.isEmpty()) return "Przejazd motorowerem" +motorbike+"km";
        else return "Brak wyboru transportu";
    }

    //counts price per kilometer
    private double countKM(String below900cc, String above900cc, String motocycle, String motorbike){
        int below900, above900, motor, motobike = 0;
        double sum = 0;
        if(below900cc.isEmpty() && above900cc.isEmpty() && motocycle.isEmpty() && motorbike.isEmpty()) return 0;
        else if (!below900cc.isEmpty()) {
            below900 = Integer.parseInt(below900cc);
            sum = below900 * 0.5214;
        } else if(!above900cc.isEmpty()){
            above900 = Integer.parseInt(above900cc);
            sum = above900 * 0.8358;
        } else if(!motocycle.isEmpty()){
            motor = Integer.parseInt(motocycle);
            sum = motor * 0.2302;
        } else {
            motobike = Integer.parseInt(motorbike);
            sum = motobike * 0.1382;
        }
        return sum;
    }

    private double countSum(double priceMultipliedByDays, double kmSum){
        return priceMultipliedByDays + kmSum;
    }

}
