package seedu.address.calendar.commands;

import seedu.address.calendar.model.Calendar;
import seedu.address.calendar.model.event.Commitment;
import seedu.address.calendar.model.event.exceptions.ClashException;
import seedu.address.calendar.model.event.exceptions.DuplicateEventException;
import seedu.address.calendar.parser.CliSyntax;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;

public class AddCommitmentCommand extends AddCommand {
    public static final String COMMAND_WORD = "commitment";
    public static final String MESSAGE_USAGE = AddCommand.COMMAND_WORD + " " + COMMAND_WORD
            + ": Adds a commitment to the specified date(s)"
            + CliSyntax.PREFIX_START_DAY + " START DAY "
            + "[" + CliSyntax.PREFIX_START_MONTH + " START MONTH] "
            + "[" + CliSyntax.PREFIX_START_YEAR + "START YEAR] "
            + "[" + CliSyntax.PREFIX_END_DAY + " END DAY] "
            + "[" + CliSyntax.PREFIX_END_MONTH + " END MONTH] "
            + "[" + CliSyntax.PREFIX_END_YEAR + " END YEAR] "
            + CliSyntax.PREFIX_NAME + " NAME "
            + "[" + CliSyntax.PREFIX_INFO + " INFO]" + "\n"
            + "Example: " + AddCommand.COMMAND_WORD + " " + COMMAND_WORD + " " + CliSyntax.PREFIX_START_DAY + " 29 "
            + CliSyntax.PREFIX_START_MONTH + " Nov " + CliSyntax.PREFIX_NAME + " CS2103 exam";

    private Commitment commitment;

    public AddCommitmentCommand(Commitment commitment) {
        this.commitment = commitment;
    }

    public CommandResult execute(Calendar calendar) throws CommandException {
        try {
            calendar.addEvent(commitment);
        } catch (DuplicateEventException | ClashException e) {
            throw new CommandException(e.getMessage());
        }

        String formattedFeedback = String.format(MESSAGE_ADD_SUCCESS, commitment.toString());
        return new CommandResult(formattedFeedback);
    }
}
