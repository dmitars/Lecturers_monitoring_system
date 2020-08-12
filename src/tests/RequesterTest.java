package tests;

import functional.Requester;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequesterTest {

    @Test
    public void performConnection() {
        Requester requester = new Requester("D://DataBase//myBase.d");
        assertEquals(requester.performConnection(),true);
    }
}