package database_managers;

import functional.ErrorDisplay;
import functional.Requester;
import functional.ResultSetParser;

public class DBGetOnlyDataManager extends DBDataManager{

    public DBGetOnlyDataManager(Requester requester){
        super(requester);
    }

    public String[] getWorks() {
        String[] workTypes = new String[]{};
        try {
            var worksSet = requester.getWorks();
            workTypes = ResultSetParser.parseAsWorks(worksSet);
        } catch (Exception e) {
            ErrorDisplay.showError(e);
        }
        return workTypes;
    }

    public String[] getDepartments() {
        try {
            var resultSet = requester.getDepartments();
            return ResultSetParser.parseAsDepartments(resultSet);
        } catch (Exception e) {
            ErrorDisplay.showError(e);
            return new String[]{};
        }
    }

    public String[] getAcademicDegrees() {
        try {
            var resultSet = requester.getAcademicDegrees();
            return ResultSetParser.parseAsAcademicDegrees(resultSet);
        } catch (Exception e) {
            ErrorDisplay.showError(e);
            return new String[]{};
        }
    }
}
