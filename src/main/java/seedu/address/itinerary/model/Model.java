package seedu.address.itinerary.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.itinerary.model.event.Event;
import seedu.address.itinerary.model.exceptions.ItineraryException;
import seedu.address.commons.util.CollectionUtil;

/**
 * Access the filtered event list of the itinerary.
 */
public class Model {
    public static final Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;
    private Itinerary itinerary;
    private final FilteredList<Event> filteredEvents;
    private final SortedList<Event> sortedEvents;

    public Model() {
        this.itinerary = new Itinerary();
        filteredEvents = new FilteredList<>(this.itinerary.getEventList());
        sortedEvents = new SortedList<>(filteredEvents);
    }

    public void addEvent(Event event) {
        this.itinerary.addEvent(event);
    }

    public void deleteEvent(int index) {
        this.itinerary.deleteEvent(index);
    }

    public void setEvent(Event eventToEdit, Event editedEvent) throws ItineraryException {
        CollectionUtil.requireAllNonNull(eventToEdit, editedEvent);

        itinerary.setEvent(eventToEdit, editedEvent);
    }

    public ArrayList<String> getActionList() {
        return itinerary.getActionList();
    }

    /**
     * Returns an unmodifiable view of the list of {@code Expense}
     * @return
     */
    public SortedList<Event> getFilteredEventList() {
        return sortedEvents;
    }

    /**
     * Filter out the events in the event list base on the predicate.
     * @param predicate the condition use to filter out the events.
     */
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
    }

    /**
     * Mark the specified event in the itinerary event list as done.
     * @param target the specified event to be marked done.
     * @param doneEvent the event with the attribute mark done.
     */
    public void doneEvent(Event target, Event doneEvent) {
        CollectionUtil.requireAllNonNull(target, doneEvent);

        itinerary.doneEvent(target, doneEvent);
    }

    /**
     * Check whether the event list in the itinerary contain the specified event.
     * @param editedEvent the newly created event with the fields changed.
     * @return a boolean whether the event exists in the event list of the itinerary.
     */
    public boolean hasEvent(Event editedEvent) {
        requireNonNull(editedEvent);
        return itinerary.hasEvent(editedEvent);
    }

    /**
     * Add the current action call into the action list.
     * @param commandText the command input that is given by the user.
     */
    public void addAction(String commandText) {
        requireNonNull(commandText);

        itinerary.addAction(commandText);
        itinerary.resetPointer();
    }
}
