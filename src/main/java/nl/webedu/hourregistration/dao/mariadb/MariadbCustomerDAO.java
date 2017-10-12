package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.ICustomerDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.CustomerModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MariadbCustomerDAO implements ICustomerDAO {

    private static MariadbCustomerDAO instance;
    private MariaDatabaseExtension database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();

    private MariadbCustomerDAO() {
        this.database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();
    }

    public static MariadbCustomerDAO getInstance() {
        if (instance == null) {
            instance = new MariadbCustomerDAO();
        }
        return instance;
    }

    @Override
    public boolean insertCustomer(CustomerModel customer) {
        Connection dbConnection = null;
        PreparedStatement ps = null;

        String insertSQL = "INSERT INTO customer"
                + "(company_name, project_name) VALUES"
                + "(?,?)";
        try {
            dbConnection = database.getConnection();
            ps = database.getConnection().prepareStatement(insertSQL);

            ps.setString(1, customer.getBusinessName());
            ps.setObject(2, customer.getProjectModel());

            ps.executeUpdate();

            System.out.println("Record toegevoegd");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (ps != null) {
                try {
                    ps.getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    @Override
    public boolean deleteCustomer(CustomerModel customer) {

        Connection dbConnection = null;
        PreparedStatement ps = null;

        String delSQL = "DELETE customer"
                + " WHERE customerID = ?";

        try {
            dbConnection = database.getConnection();
            ps = database.getConnection().prepareStatement(delSQL);

            ps.setInt(1, Integer.parseInt(customer.getId()));

            ps.executeUpdate();

            System.out.println("Record deleted");
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        finally {
            if (ps != null) {
                try {
                    ps.getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    @Override
    public CustomerModel findCustomer(int id) {

        CustomerModel customer = null;
        try {
            customer = database.selectObjectSingle(new CustomerModel(), "SELECT * FROM customer WHERE customerID = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public ArrayList<CustomerModel> findCustomerByName(String name) {
        return null;
    }

    @Override
    public boolean updateCustomer(CustomerModel customer) {

        Connection dbConnection = null;
        PreparedStatement ps = null;

        String updateSQL = "UPDATE customer"
                + " SET company_name = ?"
                + " WHERE customerID = ?";

        try {
            dbConnection = database.getConnection();
            ps = database.getConnection().prepareStatement(updateSQL);

            ps.setString(1, customer.getBusinessName());

            ps.executeUpdate();

            System.out.println("Record geupdate");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public Collection selectCustomersByProject(int projectId) {

        List<CustomerModel> customer = null;
        try {
            customer = database.selectObjectList(new CustomerModel(), "SELECT * FROM customer WHERE projectID = ?", projectId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}
