package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.ILogDAO;
import nl.webedu.hourregistration.model.LogModel;

import java.util.Collection;

public class MariadbLogDAO implements ILogDAO {

    String employeeID = "SELECT * FROM employee";

    private MariadbLogDAO() {

    }

    @Override
    public boolean insertLog(LogModel Log) {

        return false;
    }

    @Override
    public boolean deleteLog(int id) {

        return false;
    }

    @Override
    public LogModel findLog(int id) {

        return null;
    }

    @Override
    public boolean updateLog(LogModel Log) {

        return false;
    }

    @Override
    public Collection selectLogByEmployee(int employeeId) {
        return null;
    }

    @Override
    public Collection selectLogBySubject(int subjectId) {

        return null;
    }
}
