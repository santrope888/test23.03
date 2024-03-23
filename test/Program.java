package ITIS.test;

import ITIS.test.BroadcastTime;


public class Program implements Comparable<Program>{

    private String chanel;
    private BroadcastTime time;
    private String name;

    public String getChanel() {
        return chanel;
    }

    public BroadcastTime getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public Program(String chanel, BroadcastTime time, String name) {
        this.chanel = chanel;
        this.time = time;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Program{" +
                "chanel='" + chanel + '\'' +
                ", time=" + time +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Program o) {
        return this.time.compareTo(o.time);
    }
}
