package data.lecturers_additional;

import java.util.ArrayList;

public class LecturerWorkplace implements DataProvideFieldsAsStringArrayList {
    String university;
    String faculty;
    String department;

    public LecturerWorkplace(String university,String faculty,String department){
        this.university = university;
        this.faculty = faculty;
        this.department = department;
    }

    @Override
    public ArrayList<String> getFieldsAsStringArrayList() {
        ArrayList<String>fields = new ArrayList<>();
        fields.add(university);
        fields.add(faculty);
        fields.add(department);
        return fields;
    }
}
