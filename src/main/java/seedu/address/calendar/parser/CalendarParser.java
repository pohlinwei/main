package seedu.address.calendar.parser;

import seedu.address.calendar.commands.*;
import seedu.address.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class CalendarParser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        // todo: think about how to deal with non-alphanumeric characters
        // todo: allow adding of commitments that will result in clashes
        switch(commandWord) {

        case ShowCommand.COMMAND_WORD:
            return new ShowCommandParser().parse(arguments);

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case SuggestCommand.COMMAND_WORD:
            return new SuggestCommandParser().parse(arguments);

        case CheckCommand.COMMAND_WORD:
            return new CheckCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
