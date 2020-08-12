package data;

import data.lecturers_additional.DataProvideFieldsAsStringArrayList;
import data.lecturers_additional.SumStatistic;

public class LecturerWithSumStatistic extends Lecturer{

    @Override
    public void setData(DataProvideFieldsAsStringArrayList data) throws Exception {
        if(!(data instanceof SumStatistic))
            throwDataException();
        this.data = data;
    }

    public LecturerWithSumStatistic(int id, FullName fullName) throws Exception {
        super(id, fullName);
    }
}
