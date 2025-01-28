package SCD.controllers.SuperAdminControllers.Reports;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;

import SCD.model.models.Branch;
import SCD.model.service.Json.GetReportJSON;
import SCD.model.service.SuperAdminService.SuperAdminService;
import SCD.ui.SuperAdmin.GraphGeneratorPanel;
import SCD.ui.SuperAdmin.ReportGeneratorPanel;
import SCD.ui.SuperAdmin.ViewReportsPage;

public class ViewReportsController {
  ViewReportsPage viewReports;
  ReportGeneratorPanel reportGeneratorPanel;
  GraphGeneratorPanel graphGeneratorPanel;
  SuperAdminService superAdminService;

  public ViewReportsController() {
    superAdminService = new SuperAdminService();
    viewReports = new ViewReportsPage();
    viewReports.setVisible(true);
    System.out.println("HERE");
    initController();
  }

  public void initController() {
    // scd- proj initEvent Listeners
    viewReports.reportDurationComboBox.addActionListener(e -> {
      boolean isCustomRange = "Custom Range".equals(viewReports.reportDurationComboBox.getSelectedItem());
      viewReports.datePanel.setVisible(isCustomRange);
      viewReports.startDateSpinner.setEnabled(isCustomRange);
      viewReports.endDateSpinner.setEnabled(isCustomRange);
    });

    viewReports.generateReportButton.addActionListener(e -> {
      String branchCode = viewReports.branchCodeField.getText().trim();
      String duration = (String) viewReports.reportDurationComboBox.getSelectedItem();
      String graphType = (String) viewReports.graphTypeComboBox.getSelectedItem();

      if (!validateBranchCode(branchCode)) {
        showErrorDialog("Branch Code is required and must follow the format 'BR-XXXX'.");
        return;
      }
      // scd- proj initString[] reportDurations = { "Today", "Weekly", "Monthly",
      // "Yearly", "Custom
      // scd- proj initRange" };

      LocalDate startDate;
      LocalDate endDate;

      if (duration == null) {
        showErrorDialog("Please select a report duration.");
        return;
      }

      Branch branch = superAdminService.getBranchByCode(branchCode);
      if (branch == null) {
        JOptionPane.showMessageDialog(viewReports, "Branch not found.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }

      if (duration.equals("Today")) {
        GetReportJSON json = superAdminService.getTodaysReport(branchCode);
        reportGeneratorPanel = new ReportGeneratorPanel(branchCode, duration);

        reportGeneratorPanel.setReportData(json);
        reportGeneratorPanel.setVisible(true);

      }
      if (duration.equals("Weekly")) {
        GetReportJSON json = superAdminService.getWeeklyReport(branchCode);
        reportGeneratorPanel = new ReportGeneratorPanel(branchCode, duration);
        reportGeneratorPanel.setReportData(json);
        reportGeneratorPanel.setVisible(true);
      }
      if (duration.equals("Monthly")) {
        GetReportJSON json = superAdminService.getMonthlyReport(branchCode);
        reportGeneratorPanel = new ReportGeneratorPanel(branchCode, duration);
        reportGeneratorPanel.setReportData(json);
        reportGeneratorPanel.setVisible(true);
      }
      if (duration.equals("Yearly")) {
        GetReportJSON json = superAdminService.getYearlyReport(branchCode);
        reportGeneratorPanel = new ReportGeneratorPanel(branchCode, duration);
        reportGeneratorPanel.setReportData(json);
        reportGeneratorPanel.setVisible(true);
      }
      if (duration.equals("Custom Range")) {

        startDate = "Custom Range".equals(duration)
            ? getCustomStartDate(viewReports.startDateSpinner)
            : calculateStartDate(duration);

        endDate = "Custom Range".equals(duration)
            ? getCustomEndDate(viewReports.endDateSpinner)
            : calculateEndDate(duration);

        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
          showErrorDialog("Invalid date range. Ensure the start date is before or equal to the end date.");
          return;
        }

        String formattedStartDate = formatDate(startDate, duration);
        String formattedEndDate = formatDate(endDate, duration);
        GetReportJSON json = superAdminService.getRangeReport(branchCode, startDate, endDate);
        reportGeneratorPanel = new ReportGeneratorPanel(branchCode, duration, formattedStartDate, formattedEndDate);
        reportGeneratorPanel.setReportData(json);
        reportGeneratorPanel.setVisible(true);

      }

    });

    viewReports.generateGraphButton.addActionListener(e -> {
      String graphType = (String) viewReports.graphTypeComboBox.getSelectedItem();
      String duration = (String) viewReports.reportDurationComboBox.getSelectedItem();
      String branchCode = viewReports.branchCodeField.getText().trim();

      if (!validateBranchCode(branchCode)) {
        showErrorDialog("Branch Code is required and must follow the format 'BR-XXXX'.");
        return;
      }
      // scd- proj initString[] reportDurations = { "Today", "Weekly", "Monthly",
      // "Yearly", "Custom
      // scd- proj initRange" };

      LocalDate startDate;
      LocalDate endDate;

      if (duration == null) {
        showErrorDialog("Please select a report duration.");
        return;
      }

      Branch branch = superAdminService.getBranchByCode(branchCode);
      if (branch == null) {
        JOptionPane.showMessageDialog(viewReports, "Branch not found.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }

      if (duration.equals("Today") && !graphType.equals("Remaining Stock")) {
        GetReportJSON json = superAdminService.getTodaysReport(branchCode);

        if (json.getSalesData() == null) {
          showErrorDialog("No sales data available.");
          return;
        }

        startDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateString = startDate.format(formatter);

        graphGeneratorPanel = new GraphGeneratorPanel(graphType, "Daily", branchCode, dateString, dateString, json);
        graphGeneratorPanel.setVisible(true);

      }
      if (duration.equals("Weekly") && !graphType.equals("Remaining Stock")) {

        GetReportJSON json = superAdminService.getWeeklyReport(branchCode);

        if (json.getSalesData() == null) {
          showErrorDialog("No sales data available.");
          return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        startDate = LocalDate.now();

        String dateString = startDate.format(formatter);

        graphGeneratorPanel = new GraphGeneratorPanel(graphType, duration, branchCode, dateString, dateString, json);
        graphGeneratorPanel.setVisible(true);
      }
      if (duration.equals("Monthly") && !graphType.equals("Remaining Stock")) {
        GetReportJSON json = superAdminService.getMonthlyReport(branchCode);

        if (json.getSalesData() == null) {
          showErrorDialog("No sales data available.");
          return;
        }

        startDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateString = startDate.format(formatter);

        graphGeneratorPanel = new GraphGeneratorPanel(graphType, duration, branchCode, dateString, dateString, json);
        graphGeneratorPanel.setVisible(true);
      }
      if (duration.equals("Yearly") && !graphType.equals("Remaining Stock")) {
        GetReportJSON json = superAdminService.getYearlyReport(branchCode);

        if (json.getSalesData() == null) {
          showErrorDialog("No sales data available.");
          return;
        }

        startDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateString = startDate.format(formatter);

        graphGeneratorPanel = new GraphGeneratorPanel(graphType, duration, branchCode, dateString, dateString, json);
        graphGeneratorPanel.setVisible(true);
      }
      if (duration.equals("Custom Range")) {

        // scd- proj initviewReports.generateGraphButton.setEnabled(false);
        // scd- proj initviewReports.repaint();

      }

    });
  }

  LocalDate calculateStartDate(String duration) {
    LocalDate now = LocalDate.now();
    switch (duration) {
      case "Today":
        return now;
      case "Weekly":
        return now.minusDays(6); // scd- proj initLast 7 days including today
      case "Monthly":
        return now.withDayOfMonth(1);
      case "Yearly":
        return now.withDayOfYear(1);
      default:
        return null;
    }
  }

  LocalDate calculateEndDate(String duration) {
    return LocalDate.now(); // scd- proj initToday is the end date for all predefined durations
  }

  private LocalDate getCustomStartDate(JSpinner spinner) {
    try {
      return ((java.util.Date) spinner.getValue()).toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();
    } catch (Exception e) {
      return null;
    }
  }

  private LocalDate getCustomEndDate(JSpinner spinner) {
    try {
      return ((java.util.Date) spinner.getValue()).toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();
    } catch (Exception e) {
      return null;
    }
  }

  String formatDate(LocalDate date, String duration) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    if ("Monthly".equals(duration)) {
      formatter = DateTimeFormatter.ofPattern("yyyy-MM");
    } else if ("Yearly".equals(duration)) {
      formatter = DateTimeFormatter.ofPattern("yyyy");
    }
    return date.format(formatter);
  }

  boolean validateBranchCode(String branchCode) {
    return branchCode.matches("BR-\\d{4}");
  }

  void showErrorDialog(String message) {
    JOptionPane.showMessageDialog(viewReports, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

}
