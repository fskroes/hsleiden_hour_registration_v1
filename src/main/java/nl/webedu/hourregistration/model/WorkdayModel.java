package nl.webedu.hourregistration.model;

import nl.webedu.hourregistration.database.DatabaseRowMapper;
import org.bson.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkdayModel extends DatabaseRowMapper<WorkdayModel> {

    private String id;
    private Date date, startTime, endTime;
    private String dayName;
    private int weekNumber;
    private List<ActivitiesModel> activities;
    private List<String> employee_ids;

    public WorkdayModel() {
        type = WorkdayModel.class;
    }

    public WorkdayModel(Date date, Date startTime, Date endTime, int weekNumber, String workday) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekNumber = weekNumber;
        this.dayName = workday;
    }

//    public WorkdayModel(String id, Date date, Date startTime, Date endTime, int weekNumber, List<String> activities, List<String> employee_ids) {
//        this.id = id;
//        this.date = date;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.weekNumber = weekNumber;
//        this.activities = activities;
//        this.employee_ids = employee_ids;
//    }
//    public WorkdayModel(Date date, Date startTime, Date endTime, int weekNumber, List<String> activities, List<String> employee_ids) {
//        this.date = date;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.weekNumber = weekNumber;
//        this.activities = activities;
//        this.employee_ids = employee_ids;
//    }

    public String getId() {
        return id;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public List<ActivitiesModel> getActivitieIds() {
        return new ArrayList<>(activities);
    }

    public void setActivities(List<ActivitiesModel> activities) {
        this.activities = activities;
    }

    public List<String> getEmployeeId() {
        return new ArrayList<>(employee_ids);
    }

    public void setEmployeeModels(List<String> employeeModels) {
        this.employee_ids = employeeModels;
    }

    public void addEmployee(String employeeModel) {
        this.employee_ids.add(employeeModel);
    }

    public void removeEmployee(String employeeModel) {
        this.employee_ids.remove(employeeModel);
    }

    @Override
    public WorkdayModel convertSQL(ResultSet set, int rowNum) throws SQLException {
        this.id = String.valueOf(set.getInt("workdayID"));
        this.date = set.getDate("date");
        this.weekNumber = set.getInt("week_number");
        this.startTime = set.getTime("start_time");
        this.endTime = set.getTime("end_time");
        this.dayName = set.getString("day_name");
        return this;
    }

    @Override
    public WorkdayModel convertMongo(Document document) {
        // TODO: Setup MongoDB config
        return this;
    }

}
