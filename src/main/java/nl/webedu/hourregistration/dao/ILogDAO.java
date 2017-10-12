package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.LogModel;

import java.util.Collection;

public interface ILogDAO {
    public boolean insertLog(LogModel Log);
    public boolean deleteLog(String id);
    public LogModel findLog(String id);
    public boolean updateLog(LogModel Log);
    public Collection selectLogByEmployee(int employeeId);
    public Collection selectLogBySubject(int subjectId);

}
