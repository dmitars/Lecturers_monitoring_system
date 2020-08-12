package database_managers;

import data.Plan;
import data.User;
import functional.ErrorDisplay;
import functional.Requester;
import functional.ResultSetParser;

import java.sql.SQLException;

public class DBUserDataManager extends DBDataManager{
    private final int ADMINISTRATOR_CODE = 1;

    User user;

    public DBUserDataManager(Requester requester){
        super(requester);
    }

    public Plan[] getPlans() {
        Plan[] plans = new Plan[]{};
        try {
            var plansSet = requester.getPlans(user);
            plans = ResultSetParser.parseAsPlans(plansSet);
        } catch (Exception e) {
            ErrorDisplay.showError(e);
        }
        return plans;
    }

    public boolean checkUser(User user) {
        var resultSet = requester.checkUser(user);
        try {
            this.user = user;
            this.user.setId(resultSet.getInt("id"));
            int code = resultSet.getInt("isAdmin");
            if (code == ADMINISTRATOR_CODE)
                user.isAdministrator = true;
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public Integer getProfessorID() {
        try {
            return requester.getProfessorID(user);
        } catch (Exception e) {
            ErrorDisplay.showError(e);
            return 0;
        }
    }

    public void addPlan(Plan plan) {
        int nextID = requester.getNextPlanId();
        plan.setId(nextID);
        requester.insertPlan(plan);
    }

    public void addHours(Plan plan) {
        requester.addHoursOfPlan(plan);
    }
}
