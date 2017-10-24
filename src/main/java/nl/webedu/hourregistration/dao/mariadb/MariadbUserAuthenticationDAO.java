package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IUserAuthenticationDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.UserAuthenticationModel;

public class MariadbUserAuthenticationDAO implements IUserAuthenticationDAO {

    private static MariadbUserAuthenticationDAO instance;
    private MariaDatabaseExtension database;

    private MariadbUserAuthenticationDAO() {
        this.database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();
    }

    public static MariadbUserAuthenticationDAO getInstance() {
        if (instance == null) {
            instance = new MariadbUserAuthenticationDAO();
        }
        return instance;
    }

    @Override
    public UserAuthenticationModel findUser(String email, String password) {

        return null;
    }

    @Override
    public void registerUser(String username, String password) {

    }

    @Override
    public boolean authenticateUser(String email, String password) {
        return false;
    }
}
