package data;

import data.lecturers_additional.DataProvideFieldsAsStringArrayList;

import java.util.ArrayList;

public abstract class Lecturer {
    final int id;

    FullName fullName;
    DataProvideFieldsAsStringArrayList data;


    public int getId() {
        return id;
    }

    void throwDataException() throws Exception{
        throw new Exception("incorrect data class");
    }

    public abstract void setData(DataProvideFieldsAsStringArrayList data) throws Exception;

    public Lecturer(int id, FullName fullName) throws Exception{
        this.id = id;
        this.fullName = fullName;
    }

    public String[] getFieldsAsStringArray(){
        ArrayList<String>fields = fullName.getFieldsAsArrayList();
        if(data!=null){
            fields.addAll(data.getFieldsAsStringArrayList());
        }
        return fields.toArray(String[]::new);
    }
}
