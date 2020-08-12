package data;

public class Plan {
    private int id;
    public WorkType workType;
    public String namePlan;
    public Integer startYear;
    public Integer endYear;
    public Integer allHours;
    public Integer performedHours;
    public Integer professorID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Plan(Integer id, WorkType workType,String namePlan,Integer startYear,Integer endYear,
                Integer allHours,Integer performedHours, Integer professorID) throws Exception{
        if(namePlan.equals(""))
            throw new Exception("empty name");
        this.workType = workType;
        this.namePlan = namePlan;
        checkYears(startYear,endYear);
        this.startYear = startYear;
        this.endYear = endYear;
        this.allHours = allHours;
        this.professorID = professorID;
        if(allHours<=0)
            throw new Exception("incorrect number of plan hours");
        if(performedHours<0)
            throw new Exception("incorrect number of performed hours");
        this.performedHours = performedHours;
        this.id = id;
    }

    public void addHours(int hours){
        performedHours+=hours;
    }

    private void checkYears(Integer startYear,Integer endYear) throws Exception{
        checkDate(startYear);
        checkDate(endYear);
        if(startYear>=endYear)
            throw new Exception("incorrect time interval");
    }

    private void checkDate(int year) throws Exception {
        if(year<1950 || year>2100){
            throw new Exception("incorrect start year");
        }
    }

    @Override
    public String toString() {
        return namePlan;
    }
}
