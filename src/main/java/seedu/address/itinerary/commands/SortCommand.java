package seedu.address.itinerary.commands;

import javafx.collections.transformation.SortedList;

import seedu.address.itinerary.model.Model;
import seedu.address.itinerary.model.event.Event;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * sort the event list in the itinerary based on the condition given.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": "
            + "Sort based on the following format: sort by/[title | location | chronological "
            + "| completion | priority].\n"
            + "Example: sort by/title";
    public static final String MESSAGE_FAIL = "You have gave an invalid sorting condition!\n"
            + "Valid sorting condition: title, location, chronological, completion, priority.\n"
            + "Gonna proceed on with Bogo sort. (－‸ლ)";

    public static final String MESSAGE_SUCCESS = "Processing...\nDone!\n"
            + "TravEzy has helped sorted out your life!";

    private String type;

    public SortCommand(String type) {
        this.type = type;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        SortedList<Event> sortedEvents = model.getFilteredEventList();

        switch(type) {
        case "title":
            sortedEvents.setComparator(Event.titleComparator);
            break;

        case "location":
            sortedEvents.setComparator(Event.locationComparator);
            break;

        case "chronological":
            sortedEvents.setComparator(Event.dateComparator);
            break;

        case "completion":
            sortedEvents.setComparator(Event.completionComparator);
            break;

        case "priority":
            sortedEvents.setComparator(Event.priorityComparator);
            break;

        default:
            throw new CommandException(MESSAGE_FAIL);
        }
        model.updateFilteredEventList(Model.PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }
}
