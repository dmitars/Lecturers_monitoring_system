package data.lecturers_additional;

import java.util.ArrayList;

public class SumStatistic implements DataProvideFieldsAsStringArrayList {
    private int realSum;
    private int fullSum;

    public SumStatistic(int realSum,int fullSum){
        this.realSum = realSum;
        this.fullSum = fullSum;
    }

    @Override
    public ArrayList<String> getFieldsAsStringArrayList(){
        ArrayList<String>fields = new ArrayList<>();
        fields.add(String.valueOf(realSum));
        fields.add(String.valueOf(fullSum));
        return fields;
    }
}
