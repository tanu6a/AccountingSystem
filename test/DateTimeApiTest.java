import com.google.gson.Gson;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.*;


public class DateTimeApiTest {

    @Test
    public void testOldApi() {
//        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 8);
//        calendar.add(Calendar.DAY_OF_MONTH, 2);

        SimpleDateFormat simpleDateFormat
                = new SimpleDateFormat("EEEEE d MM yyyy");
        String format = simpleDateFormat.format(calendar.getTime());
        System.out.println(format);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEEEE dd M yyyy");
        try {
            Date date = sdf.parse(format);
            System.out.println(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLocalDateCreation() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

        LocalDate oldDate = LocalDate.of(1800, Month.NOVEMBER, 5);
        System.out.println(oldDate);
    }

    @Test
    public void testModification() {
        LocalDate today = LocalDate.now();
        LocalDate localDate = today.plus(20, ChronoUnit.MONTHS);
        System.out.println(localDate);
    }

    @Test
    public void testZonedTimes() {
        ZonedDateTime londonTime = ZonedDateTime.now(ZoneId.of("Europe/London"));
        ZonedDateTime parisTime = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        ZonedDateTime minskTime = ZonedDateTime.now(ZoneId.of("Europe/Minsk"));
        ZonedDateTime denverTime = ZonedDateTime.now(ZoneId.of("America/Denver"));
        System.out.println(londonTime.getHour() - parisTime.getHour());
        System.out.println(minskTime.getHour() - denverTime.getHour());

    }

    @Test
    public void testFormatting() {
        LocalDate now = LocalDate.now();
        String formattedString
                = now.format(DateTimeFormatter.ofPattern("dd--MM--yyyy"));
        System.out.println(formattedString);

        String dateAsString = "20--06-+1990";
        LocalDate parsedDate
                = LocalDate.parse(dateAsString, DateTimeFormatter.ofPattern("dd--MM-+yyyy"));
        System.out.println(parsedDate.getYear());
        System.out.println(parsedDate.getMonth());
        System.out.println(parsedDate.getDayOfMonth());
    }

    @Test
    public void testInstant() {
        Instant instant = Instant.now();
        Date date = new Date();
        System.out.println(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    @Test
    public void testPeriodAddition() {
        LocalDate plus = LocalDate.now().plus(Period.of(3, 2, 1));
        System.out.println(plus);
    }

    @Test
    public void testDurationsAndPeriods() {
        LocalDate today = LocalDate.now();
        LocalDateTime localDateTime = today.atTime(23, 10, 14);
        System.out.println(localDateTime);
        LocalDateTime todayWithTime = LocalDateTime.now();
        LocalDateTime twoDaysInFuture = todayWithTime.plusDays(2);
        Duration between = Duration.between(todayWithTime, twoDaysInFuture);
        System.out.println(between.getSeconds());

        LocalDate birthday = LocalDate.of(2032, Month.JULY, 7);
        Period period = Period.between(birthday, today);
        boolean supported = today.isSupported(ChronoUnit.SECONDS);
        System.out.println(supported);
        System.out.println("Лет: " + period.getYears());
        System.out.println("Месяцев: " + period.getMonths());
        System.out.println("Дней: " + period.getDays());
    }

    @Test
    public void testTemporalAdjusters() {
        LocalDate adjustedDate
                = LocalDate.of(2008, Month.FEBRUARY, 30)
                .with(TemporalAdjusters.lastDayOfYear());
        DayOfWeek dayOfWeek = adjustedDate.getDayOfWeek();
        System.out.println(dayOfWeek);
        System.out.println(adjustedDate);
    }

    @Test
    public void testCustomAdjuster() {
        LocalTime with = LocalTime.now().with(new BackInTimeAdjuster());
        System.out.println(with);
    }

    @Test
    public void testReplaceDate() {
        LocalDate now = LocalDate.now();
        LocalDate inFuture = LocalDate.of(2023, Month.MARCH, 13);
        LocalDate with = now.with(inFuture);
        System.out.println(with);
    }

    private class BackInTimeAdjuster implements TemporalAdjuster {
        @Override
        public Temporal adjustInto(Temporal temporal) {
            return temporal.minus(80, ChronoUnit.MINUTES);
        }
    }

    @Test
    public void testLocale() {
//        for (Locale locale : Locale.getAvailableLocales()) {
//            System.out.println("Display lg " + locale.getDisplayLanguage() +
//                    " Display country " + locale.getDisplayCountry() + " " + locale. getDisplayName());
//        }

        for (String s : ZoneId.getAvailableZoneIds()) {
            System.out.println(s);
        }
    }

    @Test
    public void testGson(){
        List<String> list = new ArrayList<String>();
        list.add("uri1");
        list.add("uri2");
        Gson gsonIn = new Gson();
        String json = gsonIn.toJson(list);
        System.out.println("Представление List в виде Json: " + json);
        Gson gson = new Gson();
        List<String> outputList = gson.fromJson(json, List.class);
        System.out.println("Представление Json в виде List: " + outputList);
    }
}
