package com.example.demo4;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.time.LocalDate;

public class ProjectManagementController {
    @FXML
    private TextField projectNameField;
    @FXML
    private TextArea projectDescriptionArea;
    @FXML
    private DatePicker taskStartDatePicker;
    @FXML
    private DatePicker taskEndDatePicker;
    @FXML
    private TextField taskNameField;
    @FXML
    private TextArea taskDescriptionArea;
    @FXML
    private TableView<Task> taskTableView;
    @FXML
    private TableColumn<Task, String> taskNameColumn;
    @FXML
    private TextField saveFilePathField;
    @FXML
    private TextField loadFilePathField;


    private Project currentProject;
    private Project selectedProjectForEdit;
    private Task selectedTaskForEdit;

    public void initialize() {
        taskNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    public String getSaveFilePath() {
        return saveFilePathField.getText();
    }

    public void setSaveFilePath(String saveFilePath) {
        saveFilePathField.setText(saveFilePath);
    }

    public String getLoadFilePath() {
        return loadFilePathField.getText();
    }

    public void setLoadFilePath(String loadFilePath) {
        loadFilePathField.setText(loadFilePath);
    }


    @FXML
    private void createProject() {
        String projectName = projectNameField.getText();
        String projectDescription = projectDescriptionArea.getText();
        currentProject = new Project(projectName, projectDescription);
        clearProjectFields();
    }

    @FXML
    private void addTask() {
        String taskName = taskNameField.getText();
        String taskDescription = taskDescriptionArea.getText();
        LocalDate startDate = taskStartDatePicker.getValue();
        LocalDate endDate = taskEndDatePicker.getValue();
        int duration = calculateDuration(startDate, endDate);
        Task task = new Task(taskName, taskDescription, startDate, endDate, duration);
        currentProject.addTask(task);
        taskTableView.getItems().add(task);
        clearTaskFields();
    }

    private int calculateDuration(LocalDate startDate, LocalDate endDate) {
        // Implement your duration calculation logic here
        return 0;
    }

    @FXML
    private void removeTask() {
        Task selectedTask = taskTableView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            currentProject.removeTask(selectedTask);
            taskTableView.getItems().remove(selectedTask);
        }
    }

    @FXML
    private void editProject() {
        selectedProjectForEdit = currentProject;
        if (selectedProjectForEdit != null) {
            projectNameField.setText(selectedProjectForEdit.getName());
            projectDescriptionArea.setText(selectedProjectForEdit.getDescription());
        }
    }

    @FXML
    private void editTask() {
        selectedTaskForEdit = taskTableView.getSelectionModel().getSelectedItem();
        if (selectedTaskForEdit != null) {
            taskNameField.setText(selectedTaskForEdit.getName());
            taskDescriptionArea.setText(selectedTaskForEdit.getDescription());
            taskStartDatePicker.setValue(selectedTaskForEdit.getStartDate());
            taskEndDatePicker.setValue(selectedTaskForEdit.getEndDate());
        }
    }

    @FXML
    private void updateProject() {
        if (selectedProjectForEdit != null) {
            selectedProjectForEdit.setName(projectNameField.getText());
            selectedProjectForEdit.setDescription(projectDescriptionArea.getText());
            clearProjectFields();
            selectedProjectForEdit = null;
        }
    }

    @FXML
    private void updateTask() {
        if (selectedTaskForEdit != null) {
            selectedTaskForEdit.setName(taskNameField.getText());
            selectedTaskForEdit.setDescription(taskDescriptionArea.getText());
            selectedTaskForEdit.setStartDate(taskStartDatePicker.getValue());
            selectedTaskForEdit.setEndDate(taskEndDatePicker.getValue());
            clearTaskFields();
            selectedTaskForEdit = null;
        }
    }

    private void clearProjectFields() {
        projectNameField.clear();
        projectDescriptionArea.clear();
    }

    private void clearTaskFields() {
        taskNameField.clear();
        taskDescriptionArea.clear();
        taskStartDatePicker.setValue(null);
        taskEndDatePicker.setValue(null);
    }
    @FXML
    private void saveProject() {
        if (currentProject != null) {
            String filePath = saveFilePathField.getText();
            ProjectFileManager.saveProject(currentProject, filePath);
            clearProjectFields();
        }
    }

    @FXML
    private void loadProject() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Project");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Project Files", "*.proj"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            Project loadedProject = ProjectFileManager.loadProject(filePath);
            if (loadedProject != null) {
                currentProject = loadedProject;
                updateTaskTableView();
            }
        }
    }


    private void updateTaskTableView() {
        // Update the TableView with tasks from the loaded project
        taskTableView.getItems().setAll(currentProject.getTasks());
    }

}
