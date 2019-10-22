package seedu.address.itinerary.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.itinerary.commands.AddEventCommand;
import seedu.address.itinerary.commands.Command;
import seedu.address.itinerary.commands.DeleteEventCommand;
import seedu.address.itinerary.commands.DoneEventCommand;
import seedu.address.itinerary.commands.EditCommand;
import seedu.address.itinerary.commands.ExitCommand;
import seedu.address.itinerary.commands.GoToCommand;
import seedu.address.itinerary.commands.GreetCommand;
import seedu.address.itinerary.commands.HelpCommand;
import seedu.address.itinerary.commands.ListCommand;
import seedu.address.itinerary.commands.RedoCommand;
import seedu.address.itinerary.commands.SearchCommand;
import seedu.address.itinerary.commands.SummaryCommand;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ItineraryParser {

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
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    seedu.address.logic.commands.HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        case GreetCommand.COMMAND_WORD:
            return new GreetCommand();

        case SummaryCommand.COMMAND_WORD:
            return new SummaryCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case GoToCommand.COMMAND_WORD:
            return new GoToParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case AddEventCommand.COMMAND_WORD:
            return new AddEventCommandParser().parse(arguments);

        case DeleteEventCommand.COMMAND_WORD:
            return new DeleteEventCommandParser().parse(arguments);

        case DoneEventCommand.COMMAND_WORD:
            return new DoneEventCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case SearchCommand.COMMAND_WORD:
            return new SearchCommandParser().parse(arguments);

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        default:
            throw new ParseException("I know you like travelling but the input command is out of this "
                        + "world! :3");
        }
    }

}
