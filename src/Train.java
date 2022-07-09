import java.util.Arrays;
import java.util.Comparator;

public class Train {
    String trainNo;
    int type;
    Line line;
    double[] ticketPrice;
    int[] ticketNum;

    public static void addTrain(String[] args) {
        if (Test.mode == 0) {
            System.out.println("Command does not exist");
            return;
        }

        if (args.length != 7 && args.length != 9) {
            System.out.println("Arguments illegal");
            return;
        }

        if (args.length == 7 && args[1].charAt(0) != 'K') {
            System.out.println("Arguments illegal");
            return;
        }

        if (args.length == 9 && args[1].charAt(0) == 'K') {
            System.out.println("Arguments illegal");
            return;
        }

        if (!checkTrainNo(args)) return;

        int thisTrainIndex = findTrain(args[1]);
        if (thisTrainIndex != -1) {
            System.out.println("Train serial duplicate");
            return;
        }

        int thisLineIndex = Line.findLine(args[2]);
        if (thisLineIndex == -1) {
            System.out.println("Line illegal");
            return;
        }

        Line thisLine = Test.lineList.get(thisLineIndex);
        if (thisLine.load == thisLine.capacity) {
            System.out.println("Line illegal");
            return;
        }

        for (int i = 3; i < args.length; i += 2) {
            if (!args[i].matches("^[0-9.]+$")) {
                System.out.println("Price illegal");
                return;
            }
        }

        for (int i = 4; i < args.length; i += 2) {
            if (!args[i].matches("^[0-9]+$")) {
                System.out.println("Ticket num illegal");
                return;
            }
        }

        Train thisTrain = null;
        switch (args[1].charAt(0)) {
            case '0':
                double[] thisTicketPrice0 = {Double.parseDouble(args[3]), Double.parseDouble(args[5]), Double.parseDouble(args[7])};
                int[] thisTicketNum0 = {Integer.parseInt(args[4]), Integer.parseInt(args[6]), Integer.parseInt(args[8])};
                thisTrain = new Train(args[1], 1, thisLine, thisTicketPrice0, thisTicketNum0);
                break;
            case 'G':
                double[] thisTicketPriceG = {Double.parseDouble(args[3]), Double.parseDouble(args[5]), Double.parseDouble(args[7])};
                int[] thisTicketNumG = {Integer.parseInt(args[4]), Integer.parseInt(args[6]), Integer.parseInt(args[8])};
                thisTrain = new Train(args[1], 2, thisLine, thisTicketPriceG, thisTicketNumG);
                break;
            case 'K':
                double[] thisTicketPriceK = {Double.parseDouble(args[3]), Double.parseDouble(args[5]), 0};
                int[] thisTicketNumK = {Integer.parseInt(args[4]), Integer.parseInt(args[6]), 0};
                thisTrain = new Train(args[1], 3, thisLine, thisTicketPriceK, thisTicketNumK);
                break;
        }
        thisLine.trains.add(thisTrain);
        thisLine.load++;
        Test.trainList.add(thisTrain);
        System.out.println("Add Train Success");
    }

    public static void delTrain(String[] args) {
        if (Test.mode == 0) {
            System.out.println("Command does not exist");
            return;
        }

        if (args.length != 2) {
            System.out.println("Arguments illegal");
            return;
        }

        if (!checkTrainNo(args)) return;

        int thisTrainIndex = findTrain(args[1]);
        if (thisTrainIndex == -1) {
            System.out.println("Train does not exist");
            return;
        }

        Train thisTrain = Test.trainList.get(thisTrainIndex);
        Line thisLine = thisTrain.line;
        int i;
        for (i = 0; i < thisLine.trains.size(); i++) {
            if (thisLine.trains.get(i).trainNo.equals(args[1])) {
                break;
            }
        }
        thisLine.trains.remove(i);
        thisLine.load--;
        Test.trainList.remove(thisTrainIndex);
        System.out.println("Del Train Success");
    }


    public static void checkTicket(String[] args) {
        if (Test.mode == 1) {
            System.out.println("Command does not exist");
            return;
        }

        if (args.length != 5) {
            System.out.println("Arguments illegal");
            return;
        }

        if (!checkTrainNo(args)) return;

        int thisTrainIndex = findTrain(args[1]);
        if (thisTrainIndex == -1) {
            System.out.println("Train serial does not exist");
            return;
        }

        Train thisTrain = Test.trainList.get(thisTrainIndex);
        Line thisLine = thisTrain.line;
        int thisStationIndex1 = Station.findStation(args[2], thisLine);
        int thisStationIndex2 = Station.findStation(args[3], thisLine);
        if (thisStationIndex1 == -1 || thisStationIndex2 == -1) {
            System.out.println("Station does not exist");
            return;
        }

        String thisSeat = args[4];
        if (args[1].charAt(0) == '0') {
            if (!thisSeat.equals("CC") && !thisSeat.equals("SB") && !thisSeat.equals("GG")) {
                System.out.println("Seat does not match");
                return;
            }
        } else if (args[1].charAt(0) == 'G') {
            if (!thisSeat.equals("SC") && !thisSeat.equals("HC") && !thisSeat.equals("SB")) {
                System.out.println("Seat does not match");
                return;
            }
        } else if (args[1].charAt(0) == 'K') {
            if (!thisSeat.equals("1A") && !thisSeat.equals("2A")) {
                System.out.println("Seat does not match");
                return;
            }
        }

        double thisTicketPrice = 0;
        int thisTicketNum = 0;
        int thisDistance = 0;
        thisDistance = Math.abs(thisLine.stations.get(thisStationIndex1).distance - thisLine.stations.get(thisStationIndex2).distance);
        if (thisSeat.equals("CC") || thisSeat.equals("SC") || thisSeat.equals("1A")) {
            thisTicketPrice = thisTrain.ticketPrice[0];
            thisTicketNum = thisTrain.ticketNum[0];
        } else if ((thisSeat.equals("SB") && args[1].charAt(0) == '0') || thisSeat.equals("HC") || thisSeat.equals("2A")) {
            thisTicketPrice = thisTrain.ticketPrice[1];
            thisTicketNum = thisTrain.ticketNum[1];
        } else if (thisSeat.equals("GG") || (thisSeat.equals("SB") && args[1].charAt(0) == 'G')) {
            thisTicketPrice = thisTrain.ticketPrice[2];
            thisTicketNum = thisTrain.ticketNum[2];
        }

        System.out.printf("[%s: %s->%s] seat:%s remain:%d distance:%d price:%.2f\n", args[1], args[2], args[3], args[4], thisTicketNum, thisDistance, (double)thisDistance * thisTicketPrice);
    }

    public static void listTrain(String[] args) {
        if (args.length > 2) {
            System.out.println("Arguments illegal");
            return;
        }

        if (args.length == 1) {
            if (Test.trainList.size() == 0) {
                System.out.println("No Trains");
                return;
            }

            Test.trainList.sort(new TrainComparator());
            for (int i = 0; i < Test.trainList.size(); i++) {
                System.out.printf("[%d] ", i+1);
                System.out.println(Test.trainList.get(i));
            }
        } else {
            int thisLineIndex = Line.findLine(args[1]);
            if (thisLineIndex == -1) {
                System.out.println("Line does not exist");
                return;
            }

            Line thisLine = Test.lineList.get(thisLineIndex);
            if (thisLine.load == 0) {
                System.out.println("No Trains");
                return;
            }

            thisLine.trains.sort(new TrainComparator());
            for (int i = 0; i < thisLine.trains.size(); i++) {
                System.out.printf("[%d] ", i+1);
                System.out.println(thisLine.trains.get(i));
            }
        }
    }

    public static int findTrain(String thisTrain) {
        for (int i = 0; i < Test.trainList.size(); i++) {
            if (Test.trainList.get(i).trainNo.equals(thisTrain)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean checkTrainNo(String[] args) {
        if (args[1].length() != 5) {
            System.out.println("Train serial illegal");
            return false;
        }

        if (args[1].charAt(0) != '0' && args[1].charAt(0) != 'G' && args[1].charAt(0) != 'K') {
            System.out.println("Train serial illegal");
            return false;
        }
        return true;
    }

    public Train(String trainNo, int type, Line line, double[] ticketPrice, int[] ticketNum) {
        this.trainNo = trainNo;
        this.type = type;
        this.line = line;
        this.ticketPrice = ticketPrice;
        this.ticketNum = ticketNum;
    }

    @Override
    public String toString() {
        StringBuilder outStr = new StringBuilder(this.trainNo + ": " + this.line.lineNo + " ");
        if (this.type == 1) {
            outStr.append("[CC]").append(String.format("%.2f", this.ticketPrice[0])).append(":").append(this.ticketNum[0]).append(" ");
            outStr.append("[SB]").append(String.format("%.2f", this.ticketPrice[1])).append(":").append(this.ticketNum[1]).append(" ");
            outStr.append("[GG]").append(String.format("%.2f", this.ticketPrice[2])).append(":").append(this.ticketNum[2]);
        } else if (this.type == 2) {
            outStr.append("[SC]").append(String.format("%.2f", this.ticketPrice[0])).append(":").append(this.ticketNum[0]).append(" ");
            outStr.append("[HC]").append(String.format("%.2f", this.ticketPrice[1])).append(":").append(this.ticketNum[1]).append(" ");
            outStr.append("[SB]").append(String.format("%.2f", this.ticketPrice[2])).append(":").append(this.ticketNum[2]);
        } else if (this.type == 3) {
            outStr.append("[1A]").append(String.format("%.2f", this.ticketPrice[0])).append(":").append(this.ticketNum[0]).append(" ");
            outStr.append("[2A]").append(String.format("%.2f", this.ticketPrice[1])).append(":").append(this.ticketNum[1]);
        }
        return outStr.toString();
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public double[] getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double[] ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int[] getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int[] ticketNum) {
        this.ticketNum = ticketNum;
    }
}

class TrainComparator implements Comparator<Train> {
    @Override
    public int compare(Train o1, Train o2) {
        if (o1.getType() == o2.getType())
            return o1.getTrainNo().compareTo(o2.getTrainNo());
        return o2.getType() - o1.getType();
    }
}