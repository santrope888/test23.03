package ITIS.test;

import ITIS.test.BroadcastTime;
import ITIS.test.Program;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "C://Users//Аскар Усманов//Desktop//schedule.txt";
        List<String> list = Files.readAllLines(new File(fileName).toPath(), Charset.defaultCharset());

        Map<BroadcastTime, List<Program>> broadcastTimeListMap = new HashMap<>();
        List<Program> allProgram = new ArrayList<>();
        readAllPrograms(broadcastTimeListMap, allProgram, list);

        //printAllSortedPrograms(allProgram); //
        //printAllProgramsNow(allProgram);
        //printAllProgramsByName(allProgram, "новости");
        //printAllProgramsNowByChanel(allProgram, "#Первый");
        //printAllProgramsInPeriodByChanel(allProgram, "#Первый", new BroadcastTime("10:14", 0), new BroadcastTime("03:16", 1));

    }

    public static void readAllPrograms(Map<BroadcastTime, List<Program>> broadcastTimeListMap, List<Program> allProgram, List<String> list) {
        ListIterator<String> listIterator = list.listIterator();

        String chanel = "";
        while (listIterator.hasNext()) {
            String s = listIterator.next();
            if (s.startsWith("#")) {
                chanel = s;
                continue;
            }

            BroadcastTime broadcastTime = new BroadcastTime(s, 0);
            if (!allProgram.isEmpty() && allProgram.get(allProgram.size()-1).getTime().after(broadcastTime)) {
                broadcastTime = new BroadcastTime(s, 1);
            }

            Program program = new Program(chanel, broadcastTime, listIterator.next());

            allProgram.add(program);
            if (broadcastTimeListMap.containsKey(broadcastTime)) {
                broadcastTimeListMap.get(broadcastTime).add(program);
            } else {
                List<Program> programList = new ArrayList<>();
                programList.add(program);
                broadcastTimeListMap.put(broadcastTime, programList);
            }
        }
    }

    public static void printAllSortedPrograms(List<Program> allProgram) {
        Collections.sort(allProgram);
        for (Program program : allProgram) {
            System.out.println(program);
        }
    }

    public static void printAllProgramsNow(List<Program> allProgram) {
        Date now = new Date();
        BroadcastTime broadcastTime = new BroadcastTime(now.getHours() + ":" + now.getMinutes(), 0);
        //BroadcastTime broadcastTime = new BroadcastTime("01:45", 1);
        List<Program> r = allProgram
                .stream()
                .filter((program) -> program.getTime().equals(broadcastTime))
                .toList();
        for (Program program : r) {
            System.out.println(program);
        }
    }

    public static void printAllProgramsByName(List<Program> allProgram, String name) {
        List<Program> r = allProgram
                .stream()
                .filter(program -> program.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
        for (Program program : r) {
            System.out.println(program);
        }
    }

    public static void printAllProgramsNowByChanel(List<Program> allProgram, String chanel) {
        Date now = new Date();
        BroadcastTime broadcastTime = new BroadcastTime(now.getHours() + ":" + now.getMinutes(), 0);
        //BroadcastTime broadcastTime = new BroadcastTime("01:45", 1);
        List<Program> r = allProgram
                .stream()
                .filter((program) -> program.getTime().equals(broadcastTime) && program.getChanel().equalsIgnoreCase(chanel))
                .toList();
        for (Program program : r) {
            System.out.println(program);
        }
    }

    public static void printAllProgramsInPeriodByChanel(List<Program> allProgram, String chanel, BroadcastTime t1, BroadcastTime t2) {
        List<Program> r = allProgram
                .stream()
                .filter(program -> program.getChanel().equalsIgnoreCase(chanel) && program.getTime().between(t1, t2))
                .toList();

        for (Program program : r) {
            System.out.println(program);
        }
    }
}