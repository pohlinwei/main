package seedu.main.logic;

import seedu.achievements.logic.AchievementsLogic;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.AddressBookLogic;
import seedu.address.storage.Storage;

/**
 * API of the AddressBookLogic component
 */

public interface Logic {

    public Storage getStorage();

    public AddressBookLogic getAddressBookLogic();

    public AchievementsLogic getAchievementsLogic();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
