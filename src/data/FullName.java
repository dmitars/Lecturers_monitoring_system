package data;

import java.util.ArrayList;

public class FullName {
    String name;
    String lastName;
    String patronymic;

    public FullName(String lastName,String name,String patronymic){
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
    }

    public ArrayList<String> getFieldsAsArrayList(){
        ArrayList<String>fields = new ArrayList<>();
        fields.add(lastName);
        fields.add(name);
        fields.add(patronymic);
        return fields;
    }
}
