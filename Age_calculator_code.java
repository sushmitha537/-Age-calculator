/**
    Java Mini Project 1
    Age project
    using command line arguments
 */
import java.sql.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class AgeDate{
    public static LocalDate converter_to_date(String date, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        return parsedDate;
    }

    public static String converter_to_string(LocalDate date, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        String formattedDate = date.format(formatter);
        return formattedDate;
    }
    public static void DOB_to_Age(String dob, String given, String format){
        LocalDate DOB = converter_to_date(dob, format);
        LocalDate present = converter_to_date(given, format);
        long years, months, days;

        Period periodBetween = Period.between(DOB, present);
        years = periodBetween.getYears();
        months = periodBetween.getMonths();
        days = periodBetween.getDays();

        System.out.println("age is " + years +" years, " + months  +" months, " + days + " days");
    }

    public static void Age_to_DOB(String age, String given, String format, String a){
        LocalDate present = converter_to_date(given, format);
        String[] age_str = age.split(a);
        int years, months, days;
        if(format.charAt(0) == 'y'){
            years = Integer.parseInt(age_str[0]);
            months = Integer.parseInt(age_str[1]);
            days = Integer.parseInt(age_str[2]);
        }
        else if(format.charAt(0) == 'd'){
            years = Integer.parseInt(age_str[2]);
            months = Integer.parseInt(age_str[1]);
            days = Integer.parseInt(age_str[0]);
        }
        else{
            years = Integer.parseInt(age_str[2]);
            months = Integer.parseInt(age_str[0]);
            days = Integer.parseInt(age_str[1]);
        }
        LocalDate temp_dob = present.minusYears(years).minusMonths(months).minusDays(days);

        String temp = converter_to_string(temp_dob, format);

        System.out.println("The DOB is: " + temp);
    }

    public static void main(String args[]){
        String date_format[] = new String[] {"yyyy-MM-dd", "dd-MM-yyyy", "MM-dd-yyyy"};
        for(int i=0; i<date_format.length; i++){
            if(date_format[i].equals(args[2])){
                if(date_format[i].charAt(0) == 'y'){
                    System.out.println("International format");
                }
                else if(date_format[i].charAt(0) == 'd'){
                    System.out.println("Indian format");
                }
                else{
                    System.out.println("USA format");
                }
            }   
        }

        if(args[0].charAt(0) == 'A'){
            Age_to_DOB(args[0].substring(4), args[1], args[2], args[3]);
        }
        else{
            DOB_to_Age(args[0].substring(4), args[1], args[2]);
        }
    }
}
        
