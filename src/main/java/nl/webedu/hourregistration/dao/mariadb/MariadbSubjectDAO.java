package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.ISubjectDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.SubjectModel;

public class MariadbSubjectDAO implements ISubjectDAO {

    private MariaDatabaseExtension database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();

    @Override
    public boolean insertSubject(SubjectModel Subject) {
        return false;
    };

    @Override
    public SubjectModel findSubject(int id) {
        return null;
    };

    @Override
    public boolean deleteSubject(int id) {
        return false;
    };

    @Override
    public boolean updateSubject(SubjectModel Subject) {
        return false;
    };
}
