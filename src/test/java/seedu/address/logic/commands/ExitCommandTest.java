package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.travezy.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.travezy.address.model.AddressBookModel;
import seedu.travezy.address.model.AddressBookModelManager;
import seedu.travezy.logic.commands.CommandResult;
import seedu.travezy.logic.commands.ExitCommand;

public class ExitCommandTest {
    private AddressBookModel addressBookModel = new AddressBookModelManager();
    private AddressBookModel expectedAddressBookModel = new AddressBookModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertCommandSuccess(new ExitCommand(), addressBookModel, expectedCommandResult, expectedAddressBookModel);
    }
}
