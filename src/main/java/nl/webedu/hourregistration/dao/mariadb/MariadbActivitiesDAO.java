package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IActivitiesDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.ActivitiesModel;

import java.util.Collection;

public class MariadbActivitiesDAO implements IActivitiesDAO {

    private mariaClient client;

    private MariadbActivitiesDAO() {
        this.client = (mariaClient) DatabaseManager.getInstance().getDatabase();
    }

    @Override
    public boolean insertActivitie(ActivitiesModel activitie) {

        Document query = new Document("category", activitie.getCategory())
                .append("start_time",activitie.getStartTime())
                .append("end_time",activitie.getEndTime())
                .append("workdayId", activitie.getWorkdayId());

        client.getDatabase("hour_registration").getCollection("activities")
                .insertOne(query, (result, t) -> System.out.println("Documents inserted!"));

        return true;
    }

    @Override
    public ActivitiesModel findActivitie(int id) {

        return null;
    }

    @Override
    public boolean deleteActivitie(int id) {

        return false;
    }

    @Override
    public boolean updateActivitie(ActivitiesModel activitie) {

        return false;
    }

    @Override
    public Collection selectActivitiesByWorkday(int wordkdatId) {

        return null;
    }

    @Override
    public Collection selectActivitiesByEmployee(int employeeId) {

        return null;
    }
}
