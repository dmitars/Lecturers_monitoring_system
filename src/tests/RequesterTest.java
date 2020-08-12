package tests;

import functional.Requester;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RequesterTest {

    @Test
    public void performConnection() {
        Requester requester = new Requester("D://DataBase//myBase.d");
        assertTrue(requester.performConnection());
    }
}