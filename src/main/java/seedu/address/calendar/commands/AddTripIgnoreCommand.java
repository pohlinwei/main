package seedu.address.calendar.commands;

import seedu.address.calendar.model.Calendar;
import seedu.address.calendar.model.event.Trip;
import seedu.address.calendar.model.event.exceptions.DuplicateEventException;
import seedu.address.calendar.parser.Option;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;

public class AddTripIgnoreCommand extends AddTripCommand implements AlternativeCommand {
    private static final boolean IS_BINARY_OPTION = true;

    public AddTripIgnoreCommand(Trip trip) {
        super(trip);
    }

    public CommandResult execute(Calendar calendar, Option option) throws CommandException {
        AlternativeCommandUtil.isValidUserCommand(option, IS_BINARY_OPTION);
        boolean isExecute = option.getBinaryOption();

        if (!isExecute) {
            return new CommandResult(AlternativeCommandUtil.MESSAGE_COMMAND_NOT_EXECUTED);
        }

        return execute(calendar);
    }

    @Override
    public CommandResult execute(Calendar calendar) throws CommandException {
        try {
            calendar.addIgnoreClash(trip);
        } catch (DuplicateEventException e) {
            throw new CommandException(e.getMessage());
        }

        String formattedFeedback = String.format(MESSAGE_ADD_SUCCESS, trip.toString());
        return new CommandResult(formattedFeedback);
    }
}
