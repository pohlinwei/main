package seedu.address.calendar.parser;

import seedu.address.calendar.commands.CheckCommand;
import seedu.address.calendar.model.date.Date;
import seedu.address.calendar.model.event.EventQuery;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

public class CheckCommandParser {
    private static final String MESSAGE_INVALID_COMMAND_FORMAT = "Incorrect check command format. %s";
    private static final Prefix[] prefixes = { CliSyntax.PREFIX_START_DAY, CliSyntax.PREFIX_START_MONTH,
            CliSyntax.PREFIX_START_YEAR, CliSyntax.PREFIX_END_DAY, CliSyntax.PREFIX_END_MONTH,
            CliSyntax.PREFIX_END_YEAR };

    CheckCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, prefixes);

        boolean hasDatePrefixes = ParserUtil.arePrefixesPresent(argMultimap, CliSyntax.PREFIX_START_DAY);

        if (!hasDatePrefixes) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        } else if (ParserUtil.hasMultiplePrefixes(argMultimap, prefixes)) {
            throw new ParseException(ParserUtil.MESSAGE_DUPLICATED_ARG);
        }

        Date startDate = DateParser.parseStartDate(argMultimap, CliSyntax.PREFIX_START_MONTH,
                CliSyntax.PREFIX_START_YEAR, CliSyntax.PREFIX_START_DAY);

        Date endDate = DateParser.parseEndDate(argMultimap, startDate, CliSyntax.PREFIX_END_MONTH,
                CliSyntax.PREFIX_END_YEAR, CliSyntax.PREFIX_END_DAY);

        boolean isValidPeriod = EventQuery.isValidEventTime(startDate, endDate);

        if (!isValidPeriod) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CheckCommand.MESSAGE_DATE_RESTRICTION));
        }

        EventQuery eventQuery = new EventQuery(startDate, endDate);

        return new CheckCommand(eventQuery);
    }
}
