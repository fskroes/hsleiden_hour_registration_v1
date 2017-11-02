package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTimePicker;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.enumeration.Role;
import nl.webedu.hourregistration.model.ActivitiesModel;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.ProjectModel;
import nl.webedu.hourregistration.model.WorkdayModel;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TimesheetController {

    private EmployeeModel sessionEmployee;
    private EmployeeModel activeEmployee;
    private int weekNumber;
    private Map<ProjectModel, JFXTimePicker[][]> row;

    public JFXListView timesheetListview;
    public AnchorPane root;
    public Label weekLabel;

    public void initialize() {
        row = new HashMap<>();
    }

    private void setupUserInterface() {
        for (ProjectModel project : activeEmployee.getProjects()) {
            HBox weekContainer = new HBox();
            weekContainer.setFillHeight(true);
            weekContainer.setSpacing(12);
            weekContainer.setAlignment(Pos.CENTER);

            HBox titleBox = new HBox();
            titleBox.setAlignment(Pos.CENTER);
            titleBox.setFillHeight(true);
            titleBox.setMinWidth(100);
            titleBox.getChildren().add(new Label(project.getName()));

            weekContainer.getChildren().add(titleBox);

            JFXTimePicker[][] days = new JFXTimePicker[7][2];
            for (int i = 0; i < days.length; i++) {
                LocalDate day = toLocalDate(i + 1);
                System.out.println(day);
                VBox dayContainer = new VBox();
                dayContainer.setAlignment(Pos.CENTER);
                // dayContainer.setSpacing(10);
                // dayContainer.setPadding(new Insets(5));
                dayContainer.setPrefWidth(115);
                dayContainer.setPrefHeight(200);

                ActivitiesModel useActivity = null;
                for (WorkdayModel workdayModel : activeEmployee.getWorksdaysByWeekNumber(weekNumber)) {
                    for (ActivitiesModel activity : workdayModel.getActivities()) {
                        if (activity.getProject().getId().equals(project.getId())) {
                            if (day.equals(workdayModel.getDate())) {
                                System.out.println(i + " Only one");
                                useActivity = activity;
                            }
                        }
                    }
                }
                JFXTimePicker startTime = new JFXTimePicker();
                startTime.setIs24HourView(true);
                if (useActivity != null)
                    startTime.setValue(useActivity.getStartTime());

                JFXTimePicker endTime = new JFXTimePicker();
                endTime.setIs24HourView(true);
                if (useActivity != null)
                    endTime.setValue(useActivity.getEndTime());

                days[i][0] = startTime;
                days[i][1] = endTime;
                dayContainer.getChildren().addAll(days[i]);
                weekContainer.getChildren().add(dayContainer);
                if (sessionEmployee.getRole().equals(Role.ADMIN)) {
                    JFXButton btnApprove = new JFXButton("Goedkeuren");
                    JFXButton btnDisapprove = new JFXButton("Afkeuren");

                }
            }
            row.put(project, days);
            timesheetListview.getItems().add(weekContainer);
        }
    }

    private Date getDayDate(int day) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.WEEK_OF_YEAR, weekNumber);
        date.set(Calendar.DAY_OF_WEEK, day);
        return date.getTime();
    }

    private LocalDate toLocalDate(int day) {
        return getDayDate(day).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }


    private String getDayName(int day) {
        return new SimpleDateFormat("EEEE").format(getDayDate(day));
    }

    public void postConstructor(EmployeeModel sessionEmployee, EmployeeModel activeEmployee, int weekId) {
        this.sessionEmployee = sessionEmployee;
        this.activeEmployee = activeEmployee;
        this.weekNumber = weekId;
        weekLabel.setText("Week: " + weekNumber);
        setupUserInterface();
    }

    public void saveSheet(ActionEvent actionEvent) {
        for (ProjectModel project : activeEmployee.getProjects()) {
            WorkdayModel[] workdays = new WorkdayModel[7];
            for (int i = 0; i < workdays.length; i++) {
                if (row.get(project)[i][0].getValue() == null) {
                    continue;
                }
                if (workdays[i] != null) {
                    if (workdays[i].getStartTime().isAfter(row.get(project)[i][0].getValue())) {
                        workdays[i].setStartTime(row.get(project)[i][0].getValue());
                    }
                    if (workdays[i].getEndTime().isBefore(row.get(project)[i][1].getValue())) {
                        workdays[i].setEndTime(row.get(project)[i][1].getValue());
                    }
                } else {
                    System.out.println("[DEBUG] Line 141: LocalDate Check -> " + toLocalDate(i + 1));
                    WorkdayModel workday = new WorkdayModel(toLocalDate(i + 1), row.get(project)[i][0].getValue(), row.get(project)[i][1].getValue(), weekNumber, getDayName(i + 1));
                    workdays[i] = workday;
                }
            }
            for (int i = 0; i < workdays.length; i++) {
                WorkdayModel workday = null;
                if (workdays[i] != null) {
                    for (WorkdayModel eWorkday : activeEmployee.getWorksdaysByWeekNumber(weekNumber)) {
                        if (eWorkday.getDate().isEqual(workdays[i].getDate())) {
                            workday = eWorkday;
                            System.out.println("[DEBUG] Line 153: eWorkday date -> " + eWorkday.getDate());
                        }
                    }
                    if (workday != null) {
                        System.out.println("[DEBUG] Line 156: Workday date -> " + workday.getDate());
                        workday.setStartTime(workdays[i].getStartTime());
                        workday.setEndTime(workdays[i].getEndTime());
                        for (ActivitiesModel activity : workday.getActivities()) {
                            if (activity.getProject() != null) {
                                if (activity.getProject().getId().equals(project.getId())) {
                                    activity.setStartTime(row.get(project)[i][0].getValue());
                                    activity.setEndTime(row.get(project)[i][1].getValue());
                                }
                            }
                        }
                    } else {
                        workday = workdays[i];
                        System.out.println("[DEBUG] Line 169: Workday date -> " + workday.getDate());
                        ActivitiesModel activity = new ActivitiesModel(row.get(project)[i][0].getValue(), row.get(project)[i][1].getValue(), workday);
                        activity.setProject(project);
                        activity.setWorkday(workday);
                        workday.addActivity(activity);
                        activeEmployee.addWorkday(workday);
                    }
                }

            }
        }
        DatabaseManager.getInstance().getDaoFactory().getEmployeeDAO().updateEmployee(activeEmployee);
    }
}


