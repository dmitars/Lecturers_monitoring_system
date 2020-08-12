package functional;

import data.Lecturer;
import data.Plan;
import data.User;

import java.sql.*;

public class Requester {

    private static Connection conn;
    private static Statement statement;
    private String dataPath;

    public Requester(String dataPath){
        this.dataPath = dataPath;
    }


    public boolean performConnection(){
        conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:"+dataPath);
            statement = conn.createStatement();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }

    public int getProfessorID(User user){
        String query = String.format("SELECT professors.id" +
                " FROM professors" +
                " INNER JOIN users ON professors.userID = users.id" +
                " WHERE users.id = %d",
                user.getId());
        try {
            var resultSet = statement.executeQuery(query);
            return resultSet.getInt(1);
        } catch (SQLException e) {
            ErrorDisplay.showError(e);
            return 0;
        }
    }

    public ResultSet checkUser(User user){
        String query = String.format("SELECT * FROM users WHERE login = '%s' AND password = '%s'",
                user.login,user.password);
        var resultSet = executeResultQuery(query);
        return resultSet;
    }

    public ResultSet getPlans(User user) {
        String query = String.format("SELECT plans.id,plans.name,plans.beginYear,plans.endYear,plans.allHours," +
                        "plans.realHours,plans.typesOfWorkID,plans.professorID, typesOfWork.shortName" +
                        " FROM plans" +
                        " INNER JOIN professors ON plans.professorID = professors.id" +
                        " INNER JOIN typesOfWork ON plans.typesOfWorkID = typesOfWork.id" +
                        " INNER JOIN users ON users.id = userID" +
                        " WHERE users.id = %d",
                user.getId());
        var resultSet = executeResultQuery(query);
        return resultSet;
    }

    public ResultSet getWorks(){
        String query = String.format("SELECT * FROM typesOfWork");
        var resultSet = executeResultQuery(query);
        return resultSet;
    }

    public ResultSet getAcademicDegrees(){
        String query = String.format("SELECT shortName FROM academicDegrees");
        var resultSet = executeResultQuery(query);
        return resultSet;
    }

    public ResultSet getDepartments(){
        String query = String.format("SELECT shortName FROM departments");
        var resultSet = executeResultQuery(query);
        return resultSet;
    }

    public ResultSet getLecturers(){
        String query = "SELECT lastName,professors.name,patronymic,professors.id" +
                " FROM professors" +
                " INNER JOIN workplace ON professors.workplaceID = workplace.id";
        var resultSet = executeResultQuery(query);
        return resultSet;
    }

    public ResultSet getFinalSumOfLecturer(Lecturer lecturer){
        String query = String.format("SELECT SUM(realHours) as realSum, SUM(plans.allHours) as allSum" +
                " FROM plans" +
                " INNER JOIN professors ON plans.professorID = professors.id" +
                " WHERE professors.id = %d",
                lecturer.getId());
        var resultSet = executeResultQuery(query);
        return resultSet;
    }

    public ResultSet getWorkTimeOfLecturer(Lecturer lecturer){
        String query = String.format("SELECT startTime,timeOfWork" +
                        " FROM professors" +
                        " INNER JOIN workplace ON professors.workplaceID = workplace.id" +
                        " WHERE professors.id = %d",
                lecturer.getId());
        var resultSet = executeResultQuery(query);
        return resultSet;
    }

    public ResultSet getWorkInfoWithLecturers(Lecturer[]lecturers){
        String query = "SELECT departments.shortName as departmentName," +
                "universities.shortName as universityName," +
                "faculties.shortName as facultyName" +
                " FROM professors" +
                " INNER JOIN universities ON professors.universityID = universities.id" +
                " INNER JOIN workplace ON professors.workplaceID = workplace.id"+
                " INNER JOIN faculties ON workplace.facultyID = faculties.id" +
                " INNER JOIN departments ON workplace.departmentID = departments.id WHERE ";
        StringBuilder queryBuilder = new StringBuilder(query);
        for(int i = 0;i<lecturers.length;i++){
            queryBuilder.append("professors.id = "+ lecturers[i].getId() +" OR ");
        }
        query = queryBuilder.toString();
        query = query.substring(0,query.length()-3);
        var resultSet = executeResultQuery(query);
        return resultSet;
    }

    public ResultSet getHardWorkingLecturers(){
        String query = "SELECT lastName,professors.name,patronymic,professors.id" +
                " FROM plans INNER JOIN professors ON plans.professorID = professors.id" +
                " INNER JOIN workplace ON workplace.id = workplaceID" +
                " WHERE realHours > allHours group BY professors.name";
        var resultSet = executeResultQuery(query);
        return resultSet;
    }

    public ResultSet getCurrentLecturers(String department, String academicDegree){
        String query = String.format("SELECT professors.id,lastName,professors.name, patronymic, startTime, timeOfWork" +
                " FROM professors" +
                " INNER JOIN academicDegrees ON academicDegrees.id = professors.degreeID" +
                " INNER JOIN workplace ON professors.workplaceID = workplace.id" +
                " INNER JOIN departments ON departments.id = workplace.departmentID" +
                " WHERE departments.shortName = '%s'" +
                " AND academicDegrees.shortName = '%s'",
                department, academicDegree);
        var resultSet = executeResultQuery(query);
        return resultSet;
    }


    public void insertPlan(Plan plan){
        String query = String.format("INSERT INTO plans VALUES(%d,'%s',%d,%d,%d,%d,'%d',%d)",plan.getId(),plan.namePlan,
                plan.startYear, plan.endYear, plan.allHours, plan.performedHours, plan.workType.getId(), plan.professorID);
        executeVoidQuery(query);
    }

    public void addHoursOfPlan(Plan plan){
        String query = String.format("UPDATE plans SET realHours = %d where id = %d",
                plan.performedHours,plan.getId());
        executeVoidQuery(query);
    }

    private void executeVoidQuery(String query){
        try{
            statement.execute(query);
        } catch (SQLException e) {
            ErrorDisplay.showError(e);
        }
    }

    private ResultSet executeResultQuery(String query){
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            ErrorDisplay.showError(e);
        }
        return resultSet;
    }

    public int getNextPlanId(){
        String query = String.format("SELECT max(id) FROM plans");
        try {
            var resultSet = statement.executeQuery(query);
            return resultSet.getInt(1) + 1;
        } catch (SQLException e) {
            ErrorDisplay.showError(e);
            return 0;
        }
    }

    void dispose(){
        try {
            statement.close();
            conn.close();
        } catch (SQLException e) {
            ErrorDisplay.showError(e);
        }
    }
}
