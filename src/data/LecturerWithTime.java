package data;

import data.lecturers_additional.DataProvideFieldsAsStringArrayList;
import data.lecturers_additional.WorkTime;

public class LecturerWithTime extends Lecturer {

    @Override
    public void setData(DataProvideFieldsAsStringArrayList data) throws Exception {
        if(!(data instanceof WorkTime))
           throwDataException();
        this.data = data;
    }

    public LecturerWithTime(int id, FullName fullName) throws Exception {
        super(id, fullName);
    }

    public boolean workOnDate(String date){
        WorkTime workTime = (WorkTime)data;
        return workTime.workOnDate(date);
    }
}
