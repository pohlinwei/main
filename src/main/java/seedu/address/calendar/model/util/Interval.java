package seedu.address.calendar.model.util;

import java.util.Arrays;

public class Interval<S extends IntervalPart<S>, T> implements Comparable<Interval<S, T>>  {
    protected S start;
    protected S end;

    public Interval(S start, S end) {
        this.start = start;
        this.end = end;
    }

    public boolean isEndsAfter(S otherIntervalPart) {
        return end.compareTo(otherIntervalPart) > 0;
    }

    public boolean isStartsAfter(S otherIntervalPart) {
        return start.compareTo(otherIntervalPart) > 0;
    }

    public boolean contains(S otherIntervalPart) {
        boolean isStartBeforeOrAt = start.compareTo(otherIntervalPart) <= 0;
        boolean isEndsAfterOrAt = end.compareTo(otherIntervalPart) >= 0;
        return isStartBeforeOrAt && isEndsAfterOrAt;
    }

    public S getStart() {
        return start;
    }

    public S getEnd() {
        return end;
    }

    public int compareTo(Interval<S, T> other) {
        S otherStart = other.getStart();
        int startCompare = start.compareTo(otherStart);
        if (startCompare != 0) {
            return startCompare;
        }

        S otherEnd = other.getEnd();
        return end.compareTo(otherEnd);
    }

    public boolean isOverlap(Interval newInterval) {
        Interval firstInterval;
        Interval secondInterval;
        int compare = this.compareTo(newInterval);

        if (compare == 0) {
            return true;
        } else if (compare < 0) {
            firstInterval = this;
            secondInterval = newInterval;
        } else {
            firstInterval = newInterval;
            secondInterval = this;
        }

        if (firstInterval.contains(secondInterval.getStart())) {
            return true;
        } else if (secondInterval.contains(firstInterval.getEnd())) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("This interval is from %s to %s", start, end);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof Interval)) {
            return false;
        }

        Interval<S, T> otherInterval = (Interval<S, T>) obj;
        S otherStart = otherInterval.start;
        S otherEnd = otherInterval.end;

        boolean isSameStart = this.start.equals(otherStart);
        boolean isSameEnd = this.end.equals(otherEnd);

        return isSameStart && isSameEnd;
    }

    @Override
    public int hashCode() {
        Object[] infoArr = {start, end};
        return Arrays.hashCode(infoArr);
    }
}
