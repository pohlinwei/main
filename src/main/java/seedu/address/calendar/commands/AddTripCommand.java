package seedu.address.calendar.commands;

import seedu.address.calendar.model.Calendar;
import seedu.address.calendar.model.event.Trip;
import seedu.address.calendar.model.event.exceptions.ClashException;
import seedu.address.calendar.model.event.exceptions.DuplicateEventException;
import seedu.address.calendar.parser.CliSyntax;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;

public class AddTripCommand extends AddCommand {
    public static final String COMMAND_WORD = "trip";
    public static final String MESSAGE_USAGE = AddCommand.COMMAND_WORD + " " + COMMAND_WORD
            + ": Adds a trip to the specified date(s)"
            + CliSyntax.PREFIX_START_DAY + " START DAY "
            + "[" + CliSyntax.PREFIX_START_MONTH + " START MONTH] "
            + "[" + CliSyntax.PREFIX_START_YEAR + "START YEAR] "
            + "[" + CliSyntax.PREFIX_END_DAY + " END DAY] "
            + "[" + CliSyntax.PREFIX_END_MONTH + " END MONTH] "
            + "[" + CliSyntax.PREFIX_END_YEAR + " END YEAR] "
            + CliSyntax.PREFIX_NAME + " NAME "
            + "[" + CliSyntax.PREFIX_INFO + " INFO]" + "\n"
            + "Example: " + AddCommand.COMMAND_WORD + " " + COMMAND_WORD + " " + CliSyntax.PREFIX_START_DAY + " 9 "
            + CliSyntax.PREFIX_START_MONTH + " Dec " + CliSyntax.PREFIX_NAME + " 1 day at Johor "
            + CliSyntax.PREFIX_INFO + " with friends";

    private Trip trip;

    public AddTripCommand(Trip trip) {
        this.trip = trip;
    }

    public CommandResult execute(Calendar calendar) throws CommandException {
        try {
            calendar.addEvent(trip);
        } catch (DuplicateEventException | ClashException e) {
            throw new CommandException(e.getMessage());
        }

        String formattedFeedback = String.format(MESSAGE_ADD_SUCCESS, trip.toString());
        return new CommandResult(formattedFeedback);
    }
}
