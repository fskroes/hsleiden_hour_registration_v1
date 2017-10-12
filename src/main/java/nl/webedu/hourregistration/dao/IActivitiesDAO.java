package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.ActivitiesModel;
import java.util.Collection;
import java.util.Date;

public interface IActivitiesDAO {
    public boolean insertActivitie(ActivitiesModel activitie);
    public ActivitiesModel findActivitie(String id);
    public boolean deleteActivitie(ActivitiesModel activitie);
    public boolean updateActivitie(ActivitiesModel activitie);
    public Collection<ActivitiesModel> selectActivitiesByWorkday(String workdayId);
    public Collection<ActivitiesModel> selectActivitiesByEmployee ( String employeeId);
}
