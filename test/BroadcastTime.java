package ITIS.test;

import java.util.Objects;

public class BroadcastTime implements Comparable<BroadcastTime>{
    private final int time;
    private final int d;
    private final int h;
    private final int m;

    private final String HHMM;

    public BroadcastTime(String HHMM, int d) {
        this.d = d;
        this.HHMM = HHMM;
        String[] temp = HHMM.split(":");
        if (temp.length != 2) {
            throw new RuntimeException("Кривое время");
        }
        this.h = Integer.parseInt(temp[0]);
        this.m = Integer.parseInt(temp[1]);
        this.time = this.d * 24 * 60 + this.h * 60 + this.m;
    }

    public byte hour() {
        return (byte) this.h;
    }

    public byte minutes() {
        return (byte) this.m;
    }

    public boolean before(BroadcastTime t) {
        return this.compareTo(t) < 0;
    }

    public boolean after(BroadcastTime t) {
        return this.compareTo(t) > 0;
    }

    public boolean between(BroadcastTime t1, BroadcastTime t2) {
        return this.after(t1) && this.before(t2);
    }

    @Override
    public int compareTo(BroadcastTime o) {
        return Integer.compare(this.time, o.time);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BroadcastTime that = (BroadcastTime) o;
        return time == that.time;
    }

    @Override
    public int hashCode() {
        return time;
    }

    @Override
    public String toString() {
        return "BroadcastTime{" +
                "HHMM='" + HHMM + '\'' +
                '}';
    }
}
