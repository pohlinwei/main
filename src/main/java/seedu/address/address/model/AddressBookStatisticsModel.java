package seedu.address.address.model;

import javafx.scene.chart.XYChart;

/**
 * Restricted view of address book model for generating statistics.
 */
public interface AddressBookStatisticsModel {

    /**
     * Get statistics of number of total contacts in Address Book.
     * @return total number of contacts in Address Book
     */
    int getTotalPersons();

    /**
     * Horizontal bar chart data for Address Book.
     * @return horizontal bar chart data for Address Book
     */
    XYChart.Series<Integer, String> getAddressChartData();
}
