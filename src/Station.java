import java.util.Comparator;

public class Station {
    String name;
    int distance;

    public static void addStation(String[] args) {
        if (Test.mode == 0) {
            System.out.println("Command does not exist");
            return;
        }

        if (args.length != 4) {
            System.out.println("Arguments illegal");
            return;
        }

        int thisLineIndex = Line.findLine(args[1]);
        if (thisLineIndex == -1) {
            System.out.println("Line does not exist");
            return;
        }

        Line thisLine = Test.lineList.get(thisLineIndex);
        if (findStation(args[2], thisLine) != -1) {
            System.out.println("Station duplicate");
            return;
        }

        if (!args[3].matches("^\\d+$")) {
            System.out.println("Arguments illegal");
            return;
        }

        thisLine.stations.add(new Station(args[2], Integer.parseInt(args[3])));
        System.out.println("Add Station success");
    }

    public static void delStation(String[] args) {
        if (Test.mode == 0) {
            System.out.println("Command does not exist");
            return;
        }

        if (args.length != 3) {
            System.out.println("Arguments illegal");
            return;
        }

        int thisLineIndex = Line.findLine(args[1]);
        if (thisLineIndex == -1) {
            System.out.println("Line does not exist");
            return;
        }

        Line thisLine = Test.lineList.get(thisLineIndex);
        int thisStationIndex = findStation(args[2], thisLine);
        if (thisStationIndex == -1) {
            System.out.println("Station does not exist");
            return;
        }

        thisLine.stations.remove(thisStationIndex);
        System.out.println("Delete Station success");
    }

    public static int findStation(String thisStation, Line thisLine) {
        for (int i = 0; i < thisLine.stations.size(); i++) {
            if (thisLine.stations.get(i).getName().equals(thisStation)) {
                return i;
            }
        }
        return -1;
    }

    public Station(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}

class StationComparator implements Comparator<Station> {
    @Override
    public int compare(Station o1, Station o2) {
        if (o1.getDistance() == o2.getDistance())
            return o1.getName().compareTo(o2.getName());
        return o1.getDistance() - o2.getDistance();
    }
}