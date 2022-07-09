import java.util.ArrayList;
import java.util.Comparator;

public class Line {
    String lineNo;
    int load;
    int capacity;
    ArrayList<Station> stations;
    ArrayList<Train> trains;

    public static void addLine(String[] args) {
        if (Test.mode == 0) {
            System.out.println("Command does not exist");
            return;
        }

        if (args.length % 2 == 0 || args.length < 5) {
            System.out.println("Arguments illegal");
            return;
        }

        for (int i = 4; i < args.length; i += 2) {
            if (!args[i].matches("^\\d+$")) {
                System.out.println("Arguments illegal");
                return;
            }
        }

        for (int i = 3; i < args.length; i += 2) {
            for (int j = i + 2; j < args.length; j += 2) {
                if (args[i].equals(args[j])) {
                    System.out.println("Station duplicate");
                    return;
                }
            }
        }

        for (int i = 0; i < Test.lineList.size(); i++) {
            if (Test.lineList.get(i).lineNo.equals(args[1])) {
                System.out.println("Line already exists");
                return;
            }
        }

        if (Integer.parseInt(args[2]) <= 0) {
            System.out.println("Capacity illegal");
            return;
        }

        Line line = new Line(args[1], Integer.parseInt(args[2]));
        for (int i = 3; i < args.length - 1; i += 2) {
            line.stations.add(new Station(args[i], Integer.parseInt(args[i + 1])));
        }
        Test.lineList.add(line);
        System.out.println("Add Line success");
    }

    public static void delLine(String[] args) {
        if (Test.mode == 0) {
            System.out.println("Command does not exist");
            return;
        }

        if (args.length != 2) {
            System.out.println("Arguments illegal");
            return;
        }

        for (int i = 0; i < Test.lineList.size(); i++) {
            if (Test.lineList.get(i).lineNo.equals(args[1])) {
                Test.lineList.remove(i);
                System.out.println("Del Line success");
                return;
            }
        }

        System.out.println("Line does not exist");
    }

    public static void lineInfo(String[] args) {
        if (args.length != 2) {
            System.out.println("Arguments illegal");
            return;
        }

        for (int i = 0; i < Test.lineList.size(); i++) {
            if (Test.lineList.get(i).lineNo.equals(args[1])) {
                System.out.println(Test.lineList.get(i));
                return;
            }
        }

        System.out.println("Line does not exist");
    }

    public static void listLine(String[] args) {
        if (args.length != 1) {
            System.out.println("Arguments illegal");
            return;
        }

        if (Test.lineList.size() == 0) {
            System.out.println("No Lines");
            return;
        }

        Test.lineList.sort(new LineComparator());
        for (int i = 0; i < Test.lineList.size(); i++) {
            System.out.printf("[%d] ", i + 1);
            System.out.println(Test.lineList.get(i));
        }
    }

    public static int findLine(String thisLine) {
        for (int i = 0; i < Test.lineList.size(); i++) {
            if (Test.lineList.get(i).lineNo.equals(thisLine)) {
                return i;
            }
        }
        return -1;
    }

    public Line(String lineNo, int capacity) {
        this.lineNo = lineNo;
        this.load = 0;
        this.capacity = capacity;
        this.stations = new ArrayList<>();
        this.trains = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder outStr = new StringBuilder(this.lineNo + " " + this.load + "/" + this.capacity);
        this.stations.sort(new StationComparator());
        for (Station station : this.stations) {
            outStr.append(" ").append(station.name).append(":").append(station.distance);
        }
        return outStr.toString();
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

class LineComparator implements Comparator<Line> {
    @Override
    public int compare(Line o1, Line o2) {
        return o1.getLineNo().compareTo(o2.getLineNo());
    }
}