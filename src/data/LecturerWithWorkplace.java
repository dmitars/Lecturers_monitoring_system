package data;


import data.lecturers_additional.DataProvideFieldsAsStringArrayList;
import data.lecturers_additional.LecturerWorkplace;

public class LecturerWithWorkplace extends Lecturer{

    @Override
    public void setData(DataProvideFieldsAsStringArrayList data) throws Exception {
        if(!(data instanceof LecturerWorkplace))
           throwDataException();
        this.data = data;
    }

    public LecturerWithWorkplace(int id, FullName fullName) throws Exception {
        super(id, fullName);
    }
}
