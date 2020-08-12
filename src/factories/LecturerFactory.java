package factories;

import data.*;
import enum_types.LecturerType;

public class LecturerFactory {
    public LecturerFactory(){}

    public Lecturer getLecturer(LecturerType lecturerType, int id, FullName fullName) throws Exception {
        return  switch (lecturerType){
            case LECTURER_WITH_TIME -> new LecturerWithTime(id,fullName);
            case LECTURER_WITH_SUMS -> new LecturerWithSumStatistic(id,fullName);
            case LECTURER_WITH_WORKPLACE -> new LecturerWithWorkplace(id,fullName);
        };
    }
}
