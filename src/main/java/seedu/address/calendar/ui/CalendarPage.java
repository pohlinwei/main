package seedu.address.calendar.ui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import seedu.address.calendar.commands.Command;
import seedu.address.calendar.model.Calendar;
import seedu.address.calendar.model.Month;
import seedu.address.calendar.model.date.MonthOfYear;
import seedu.address.calendar.model.date.Year;
import seedu.address.calendar.parser.CalendarParser;
import seedu.address.calendar.storage.CalendarStorage;
import seedu.address.calendar.storage.JsonCalendarStorage;
import seedu.address.logic.commands.CommandResult;
import seedu.address.address.logic.AddressBookLogic;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.Page;
import seedu.address.ui.PageType;
import seedu.address.ui.UiPart;

import java.io.IOException;
import java.nio.file.Paths;

public class CalendarPage extends UiPart<Scene> implements Page {
    private static final String FXML = "CalendarPage.fxml";
    private static final PageType pageType = PageType.CALENDAR;
    private static final String FILE_OPS_ERROR_MESSAGE = "Unable to save calendar";

    private ResultDisplay resultDisplay;

    private Calendar calendar;
    private CalendarStorage calendarStorage;

    @FXML
    StackPane commandBoxPlaceholder;
    @FXML
    StackPane monthHeaderPlaceholder;
    @FXML
    StackPane yearHeaderPlaceholder;
    @FXML
    StackPane monthViewPlaceholder;
    @FXML
    VBox resultDisplayPlaceholder;

    public CalendarPage() {
        super(FXML);
        calendar = new Calendar();
        calendarStorage = new JsonCalendarStorage(Paths.get("data" , "calendar.json"));
        try {
            calendar.updateCalendar(calendarStorage.readCalendar().get());
        } catch(Exception e) {
            System.out.println(e);
        }
        fillInnerParts();
    }

    public Scene getScene() {
        return getRoot();
    }

    public PageType getPageType() {
        return pageType;
    }

    /**
     * Sets up calendar page by laying out nodes.
     */
    private void fillInnerParts() {
        Month currentMonth = calendar.getMonth();
        MonthOfYear monthOfYear = currentMonth.getMonthOfYear();
        MonthHeader monthHeader = new MonthHeader(monthOfYear);
        monthHeaderPlaceholder.getChildren().add(monthHeader.getRoot());

        Year year = currentMonth.getYear();
        YearHeader yearHeader = new YearHeader(year);
        yearHeaderPlaceholder.getChildren().add(yearHeader.getRoot());

        MonthView monthView = new MonthView(currentMonth);
        monthViewPlaceholder.getChildren().add(monthView.generateMonthGrid());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    private void updateCalendarPage(Month updatedMonth) {
        Year year = updatedMonth.getYear();
        MonthOfYear monthOfYear = updatedMonth.getMonthOfYear();

        updateYearHeader(year);
        updateMonthHeader(monthOfYear);
        updateMonthView(updatedMonth);
    }

    private void updateYearHeader(Year year) {
        YearHeader yearHeader = new YearHeader(year);
        yearHeaderPlaceholder.getChildren().clear();
        yearHeaderPlaceholder.getChildren().add(yearHeader.getRoot());
    }

    private void updateMonthHeader(MonthOfYear monthOfYear) {
        MonthHeader monthHeader = new MonthHeader(monthOfYear);
        monthHeaderPlaceholder.getChildren().clear();
        monthHeaderPlaceholder.getChildren().add(monthHeader.getRoot());
    }

    private void updateMonthView(Month month) {
        MonthView monthView = new MonthView(month);
        monthViewPlaceholder.getChildren().clear();
        monthViewPlaceholder.getChildren().add(monthView.generateMonthGrid());
    }

    /**
     * Executes the command and returns the result.
     *
     * @see AddressBookLogic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            Command command = (new CalendarParser()).parseCommand(commandText);
            CommandResult commandResult = command.execute(calendar);

            if (calendar.hasVisibleUpdates()) {
                Month updatedMonth = calendar.getMonth();
                updateCalendarPage(updatedMonth);
                calendar.completeVisibleUpdates();
            }

            calendarStorage.saveCalendar(calendar.getCalendar());

            resultDisplay.setDisplayText(commandResult.getFeedbackToUser());
            return commandResult;
        } catch (ParseException e) {
            resultDisplay.setDisplayText(e.getMessage());
            throw e;
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
    }
}
