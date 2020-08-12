package data.lecturers_additional;

import java.time.LocalDate;
import java.util.ArrayList;

public class WorkTime implements DataProvideFieldsAsStringArrayList{
    String startDate;
    String timeOfWorking;

    public WorkTime(String startDate,String timeOfWorking) throws Exception{
        checkDate(startDate);
        checkDate(timeOfWorking);
        this.startDate = startDate;
        this.timeOfWorking = timeOfWorking;
    }

    private void checkDate(String date) throws Exception{
        LocalDate.parse(date);
    }

    public boolean workOnDate(String date){
        LocalDate localDate = LocalDate.parse(date);
        LocalDate startLocalDate = LocalDate.parse(startDate);
        LocalDate localWorkTime = LocalDate.parse(timeOfWorking);

        LocalDate endWorkLocalDate = startLocalDate.plusYears(localWorkTime.getYear())
                .plusMonths(localWorkTime.getMonthValue())
                .plusDays(localWorkTime.getDayOfMonth());

        return startLocalDate.isBefore(localDate) &&
                endWorkLocalDate.isAfter(localDate);
    }

    @Override
    public ArrayList<String> getFieldsAsStringArrayList() {
        ArrayList<String>fields = new ArrayList<>();
        fields.add(String.valueOf(startDate));
        return fields;
    }
}
