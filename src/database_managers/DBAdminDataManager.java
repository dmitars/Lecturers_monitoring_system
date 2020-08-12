package database_managers;

import data.Lecturer;
import data.LecturerWithTime;
import data.lecturers_additional.LecturerWorkplace;
import data.lecturers_additional.SumStatistic;
import data.lecturers_additional.WorkTime;
import enum_types.LecturerType;
import functional.ErrorDisplay;
import functional.Requester;
import functional.ResultSetParser;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DBAdminDataManager extends DBDataManager{
    private String[][] selectedLecturers;

    public void setSelectedLecturers(String[][] selectedLecturers) {
        this.selectedLecturers = selectedLecturers;
    }

    public String[][] getSelectedLecturers() {
        return selectedLecturers;
    }

    public DBAdminDataManager(Requester requester) {
        super(requester);
    }

    public String[][] getFinalSum() {
        try {
            ArrayList<String[]> result = new ArrayList<>();
            var lecturersSet = requester.getLecturers();
            Lecturer[] lecturers = ResultSetParser.parseAsLecturers(lecturersSet, LecturerType.LECTURER_WITH_SUMS);
            for (Lecturer lecturer : lecturers) {
                addSumStatistic(lecturer);
                result.add(lecturer.getFieldsAsStringArray());
            }
            return result.toArray(String[][]::new);
        } catch (Exception e) {
            ErrorDisplay.showError(e);
            return null;
        }
    }

    private void addSumStatistic(Lecturer lecturer) throws Exception {
        var lecturerFinalSumSet = requester.getFinalSumOfLecturer(lecturer);
        SumStatistic statistic = ResultSetParser.parseAsSums(lecturerFinalSumSet);
        lecturer.setData(statistic);
    }

    public String[][] getHardWorkingLecturers() {
        try {
            ArrayList<String[]> result = new ArrayList<>();
            Lecturer[] lecturers = findHardWorkingLecturers();
            LecturerWorkplace[] workplaces = findLecturerWorkplaces(lecturers);
            for (int i = 0; i < lecturers.length; i++) {
                lecturers[i].setData(workplaces[i]);
                result.add(lecturers[i].getFieldsAsStringArray());
            }
            return result.toArray(String[][]::new);
        } catch (Exception e) {
            ErrorDisplay.showError(e);
            return null;
        }
    }

    private Lecturer[] findHardWorkingLecturers() throws Exception {
        var resultSet = requester.getHardWorkingLecturers();
        return ResultSetParser.parseAsLecturers(resultSet, LecturerType.LECTURER_WITH_WORKPLACE);
    }

    private LecturerWorkplace[] findLecturerWorkplaces(Lecturer[] lecturers) throws Exception {
        var workplaceSet = requester.getWorkInfoWithLecturers(lecturers);
        return ResultSetParser.parseAsWorkplaces(workplaceSet);
    }

    public String[][] getCurrentLecturers(String date, String department, String academicDegree) {
        try {
            Lecturer[] lecturers = findCurrentLecturers(department, academicDegree);
            if (lecturers.length == 0)
                return new String[][]{};
            for (Lecturer lecturer : lecturers)
                addWorkTime(lecturer);
            ArrayList<String[]> result = getLecturersFieldsWhoWorkedOnDate(lecturers, date);
            return result.toArray(String[][]::new);
        } catch (Exception e) {
            ErrorDisplay.showError(e);
            return null;
        }
    }

    private void addWorkTime(Lecturer lecturer) throws Exception {
        var lecturerFinalSumSet = requester.getWorkTimeOfLecturer(lecturer);
        WorkTime workTime = ResultSetParser.parseAsWorkTime(lecturerFinalSumSet);
        lecturer.setData(workTime);
    }

    private Lecturer[] findCurrentLecturers(String department, String academicDegree) throws Exception {
        var lecturersSet = requester.getCurrentLecturers(department, academicDegree);
        return ResultSetParser.parseAsLecturers(lecturersSet, LecturerType.LECTURER_WITH_TIME);
    }

    private ArrayList<String[]> getLecturersFieldsWhoWorkedOnDate(Lecturer[] lecturers, String date) {
        return Stream.of(lecturers)
                .filter(lecturer -> ((LecturerWithTime) lecturer).workOnDate(date))
                .map(Lecturer::getFieldsAsStringArray)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
