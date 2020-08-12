package tests;

import database_managers.DBAdminDataManager;
import database_managers.DBGetOnlyDataManager;
import database_managers.DBUserDataManager;
import functional.MainSystem;
import data.User;

import static org.junit.Assert.*;

public class DataManagersTest {

    @org.junit.Test
    public void checkUser() {
        MainSystem system = new MainSystem();
        DBUserDataManager userDataManager = system.getUserDataManager();
        assertTrue(userDataManager.checkUser(new User("admin", "1111")));
        assertFalse(userDataManager.checkUser(new User("admin", "12341")));
    }

    @org.junit.Test
    public void getDepartments() {
        MainSystem system = new MainSystem();
        DBGetOnlyDataManager requestManager = system.getGetOnlyDataManager();
        assertArrayEquals(requestManager.getDepartments(),new String[]{"ТП","ММАД","ИСУ","ТВИМС","ДМА","БМИ"});
    }

    @org.junit.Test
    public void getAcademicDegrees() {
        MainSystem system = new MainSystem();
        DBGetOnlyDataManager requestManager = system.getGetOnlyDataManager();
        assertArrayEquals(requestManager.getAcademicDegrees(),new String[]{"Доктор","Кандидат"});
    }

    @org.junit.Test
    public void getFinalSum() {
        MainSystem system = new MainSystem();
        DBAdminDataManager adminDataManager = system.getAdminDataManager();
        assertArrayEquals(adminDataManager.getFinalSum(),new String[][]{{"Иванов","Сергей","Иванович","4","80"},
                {"Иванов","Иван","Иванович","50","60"},
                {"Петров","Петр","Петрович","0","0"}});
    }

    @org.junit.Test
    public void getHardWorkingLecturers() {
        MainSystem system = new MainSystem();
        DBAdminDataManager adminDataManager = system.getAdminDataManager();
        assertArrayEquals(adminDataManager.getHardWorkingLecturers(),new String[][]{{"Иванов","Иван","Иванович","БГУ","ФПМИ","ММАД"}});
    }

    @org.junit.Test
    public void getCurrentLecturers() {
        MainSystem system = new MainSystem();
        DBAdminDataManager adminDataManager = system.getAdminDataManager();
        assertArrayEquals(adminDataManager.getCurrentLecturers("2010-07-05","ТП","Доктор"),
                new String[][]{{"Иванов","Сергей","Иванович"}});
    }
}