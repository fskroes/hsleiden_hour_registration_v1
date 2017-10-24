package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.WorkdayModel;

import java.util.Collection;

public interface IWorkdayDAO {
    public boolean insertWorkday(WorkdayModel Workday);
    public boolean deleteWorkday(String id);
    public WorkdayModel findWorkday(String id);
    public boolean updateWorkday(WorkdayModel Workday);
}
