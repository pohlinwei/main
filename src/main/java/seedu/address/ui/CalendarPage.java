package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;


import java.util.stream.IntStream;

public class CalendarPage {
    private boolean isOpened = false;
    @FXML
    Scene calendar;
    @FXML
    VBox calendarPane = new VBox();
    @FXML
    GridPane weekHeader;
    @FXML
    GridPane monthView;
    @FXML // todo change the following to CommandBox class?
    TextField commandBox = new TextField();
    @FXML
    Label monthLabel;

    CalendarPage() {
        // todo: move to week header
        weekHeader = generateWeekHeader();

        monthView = generateMonthView();
        monthLabel = new Label("October");
        monthLabel.setTextAlignment(TextAlignment.CENTER);
        // todo: remove separate command box and integrate it with main page
        commandBox.setOnAction(event -> {
            String s = commandBox.getText();
            System.out.println(s);
            commandBox.setText("");
        });
        calendarPane.setAlignment(Pos.BOTTOM_LEFT);
        calendarPane.getChildren().addAll(monthLabel, weekHeader, monthView, commandBox);
        calendar = new Scene(calendarPane);
    }

    boolean isOpened() {
        return isOpened;
    }

    Scene getCalendar() {
        return calendar;
    }

    void setOpened(boolean isOpened) {
        this.isOpened = isOpened;
    }

    private GridPane generateWeekHeader() {
        GridPane weekHeader = new GridPane();
        String[] days = { "Sun", "Mon", "Tues", "Wed", "Thu", "Fri", "Sat" };
        int widthPercent = 100 / days.length;
        weekHeader.setHgap(10);
        weekHeader.setVgap(10);
        weekHeader.setPadding(new Insets(10));
        IntStream.range(0, days.length)
                .forEach(i -> {
                    ColumnConstraints newColumn = new ColumnConstraints();
                    newColumn.setPercentWidth(widthPercent);
                    newColumn.setHalignment(HPos.CENTER);
                    weekHeader.getColumnConstraints().add(newColumn);
                    Label day = new Label(days[i]);
                    weekHeader.add(day, i, 0);
                });
        return weekHeader;
    }

    private GridPane generateMonthView() {
        int height = 100;
        GridPane monthView = new GridPane();
        monthView.setGridLinesVisible(true);
        IntStream.range(0, 5)
                .forEach(i -> {
                    RowConstraints newRow = new RowConstraints();
                    newRow.setPrefHeight(height);
                    monthView.getRowConstraints().add(newRow);
                });
        int width = 150;
        IntStream.range(0, 7)
                .forEach(i -> {
                    VBox dayView = generateDayView(i);
                    dayView.setPrefWidth(width);
                    monthView.add(dayView, i, 0);
                });
        return monthView;
    }

    // todo change this to a class which has a setDate method
    private VBox generateDayView(int date) {
        VBox dayView = new VBox();
        Label dateOfMonth = new Label(Integer.toString(date));
        dayView.getChildren().add(dateOfMonth);
        dayView.setPadding(new Insets(10));
        return dayView;
    }
}
