package seedu.address.itinerary.model;

import org.junit.jupiter.api.Test;

import seedu.address.itinerary.model.event.Date;
import seedu.address.itinerary.model.event.Description;
import seedu.address.itinerary.model.event.Event;
import seedu.address.itinerary.model.event.Location;
import seedu.address.itinerary.model.event.Tag;
import seedu.address.itinerary.model.event.Time;
import seedu.address.itinerary.model.event.Title;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ItineraryTest {

    private Title titleTest = new Title("Awesome Title");
    private Date dateTest = new Date("28102019");
    private Location locationTest = new Location("Singapore");
    private Description descTest = new Description("My awesome description");
    private Time timeTest = new Time("2000");
    private Tag tagTest = new Tag("Priority: High");
    private Event eventTest = new Event(titleTest, dateTest, locationTest
            , descTest, timeTest, tagTest);

    private Title titleTest2 = new Title("Same Time Awesome Title");
    private Date dateTest2 = new Date("28102019");
    private Location locationTest2 = new Location("USA");
    private Description descTest2 = new Description("My cool description");
    private Time timeTest2 = new Time("2000");
    private Tag tagTest2 = new Tag("Priority: Medium");
    private Event eventTest2 = new Event(titleTest2, dateTest2, locationTest2
            , descTest2, timeTest2, tagTest2);

    private Title titleTest3 = new Title("Another Cool Title");
    private Date dateTest3 = new Date("28102019");
    private Location locationTest3 = new Location("USA");
    private Description descTest3 = new Description("My cool description");
    private Time timeTest3 = new Time("0000");
    private Tag tagTest3 = new Tag("Priority: Medium");
    private Event eventTest3 = new Event(titleTest3, dateTest3, locationTest3
            , descTest3, timeTest3, tagTest3);

    Itinerary itinerary = new Itinerary();

    @Test
    void hasEvent() {
        // Checking the empty list no event available.
        assertFalse(itinerary.hasEvent(eventTest));

        // Adding the event and checking whether it contains it.
        itinerary.addEvent(eventTest);
        assertTrue(itinerary.hasEvent(eventTest));

        // Checking with another event. However, the event has the same date and time -> considered as equal.
        assertTrue(itinerary.hasEvent(eventTest2));

        // Checking with another totally different event.
        assertFalse(itinerary.hasEvent(eventTest3));
    }

    @Test
    void getActionList() {
        // Creating the expected list with the intended added command.
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("greet");

        // Checking that the two list are not the same.
        assertNotSame(expectedList, itinerary.getActionList());

        // Adding the greet command to the action list and checking whether the word is captured in the action list.
        itinerary.addAction("greet");

        assertArrayEquals(expectedList.toArray(), itinerary.getActionList().toArray());
    }

}
