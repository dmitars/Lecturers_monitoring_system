package functional;

import data.FullName;
import data.Lecturer;
import data.Plan;
import data.WorkType;
import data.lecturers_additional.LecturerWorkplace;
import data.lecturers_additional.SumStatistic;
import data.lecturers_additional.WorkTime;
import enum_types.LecturerType;
import factories.LecturerFactory;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ResultSetParser {
    public static Plan[] parseAsPlans(ResultSet resultSet) throws Exception{
        ArrayList<Plan>plans = new ArrayList<>();
        while(resultSet.next()) {
            Integer planId = resultSet.getInt("id");
            String planName = resultSet.getString("name");
            Integer startYear = resultSet.getInt("beginYear");
            Integer endYear = resultSet.getInt("endYear");
            Integer allHours = resultSet.getInt("allHours");
            Integer performedHours = resultSet.getInt("realHours");
            String nameWork = resultSet.getString("shortName");
            int workID = resultSet.getInt("typesOfWorkID");
            WorkType workType = new WorkType(workID,nameWork);
            Integer professorID = resultSet.getInt("professorID");
            plans.add(new Plan(planId, workType, planName, startYear, endYear, allHours, performedHours,professorID));
        }
        return plans.toArray(new Plan[]{});
    }

    public static String[] parseAsDepartments(ResultSet resultSet)throws Exception{
        ArrayList<String>departments = new ArrayList<>();
        while (resultSet.next())
            departments.add(resultSet.getString("shortName"));
        return departments.toArray(String[]::new);
    }

    public static String[] parseAsAcademicDegrees(ResultSet resultSet)throws Exception{
        ArrayList<String>academicDegrees = new ArrayList<>();
        while (resultSet.next())
            academicDegrees.add(resultSet.getString("shortName"));
        return academicDegrees.toArray(String[]::new);
    }

    public static String[] parseAsWorks(ResultSet resultSet) throws Exception{
        ArrayList<String>workTypes = new ArrayList<>();
        String workType;
        while(resultSet.next()){
            workType = resultSet.getString("shortName");
            workTypes.add(workType);
        }
        return workTypes.toArray(new String[]{});
    }

    public static SumStatistic parseAsSums(ResultSet resultSet) throws Exception {
        int realSum = resultSet.getInt("realSum");
        int fullSum = resultSet.getInt("allSum");
        SumStatistic statistic = new SumStatistic(realSum,fullSum);
        return statistic;
    }

    public static WorkTime parseAsWorkTime(ResultSet resultSet) throws Exception {
        String startDate = resultSet.getString("startTime");
        String timeOfWorking = resultSet.getString("timeOfWork");
        WorkTime workTime = new WorkTime(startDate,timeOfWorking);
        return workTime;
    }

    public static int parseAsSingleIntValue(ResultSet resultSet)throws Exception{
        return resultSet.getInt(1);
    }

    public static Lecturer[] parseAsLecturers(ResultSet resultSet, LecturerType lecturerType) throws Exception{
        ArrayList<Lecturer>lecturers = new ArrayList<>();
        LecturerFactory factory = new LecturerFactory();
        while(resultSet.next()) {
            int lecturerID = resultSet.getInt("id");
            String lecturerName = resultSet.getString("name");
            String lecturerLastName = resultSet.getString("lastName");
            String patronymic = resultSet.getString("patronymic");
            FullName fullName = new FullName(lecturerLastName,lecturerName,patronymic);
            lecturers.add(factory.getLecturer(lecturerType,lecturerID,fullName));
        }
        return lecturers.toArray(new Lecturer[]{});
    }

    public static LecturerWorkplace[] parseAsWorkplaces(ResultSet resultSet) throws Exception{
        ArrayList<LecturerWorkplace>lecturerWorkplaces = new ArrayList<>();
        while(resultSet.next()) {
            String university = resultSet.getString("universityName");
            String faculty = resultSet.getString("facultyName");
            String department = resultSet.getString("departmentName");
            LecturerWorkplace workplace = new LecturerWorkplace(university,faculty,department);
            lecturerWorkplaces.add(workplace);
        }
        return lecturerWorkplaces.toArray(new LecturerWorkplace[]{});
    }
}
