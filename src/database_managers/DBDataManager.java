package database_managers;

import functional.Requester;

public abstract class DBDataManager {
    Requester requester;
    public DBDataManager(Requester requester){
        this.requester = requester;
    }
}
