package factories;

import functional.MainSystem;
import loaders.*;
import enum_types.PageType;

public class LoaderFactory {

    MainSystem system;
    public LoaderFactory(MainSystem system){
        this.system = system;
    }

    public Loader getLoader(PageType pageType){
       return  switch (pageType){
            case USER_PAGE -> new UserPageLoader(system);
            case ADMIN_PAGE -> new AdminPageLoader(system);
            case LOGIN_PAGE -> new LoginPageLoader(system);
            case NEW_PLAN_PAGE -> new NewPlanPageLoader(system);
            case SET_LECTURER_PAGE -> new SetLecturerPageLoader(system);
        };
    }
}
