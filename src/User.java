import java.util.ArrayList;

public class User {
    String name;
    char sex;
    String Aadhaar;
    double balance;

    int errno = 0;

    public void addUser(String argsName, String argsSex, String argsAadhaar) {
        setName(argsName);
        if (errno != 0) return;
        setSex(argsSex.charAt(0));
        if (errno != 0) return;
        setAadhaar(argsAadhaar, argsName);
        if (errno != 0) return;
        setBalance(0);
        System.out.println(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String pattern = "^[a-zA-Z_]+$";
        if (!name.matches(pattern)) {
            System.out.println("Name illegal");
            errno = 1;
        } else
            this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        if (sex != 'F' && sex != 'M' && sex != 'O') {
            System.out.println("Sex illegal");
            errno = 2;
        } else
            this.sex = sex;
    }

    public String getAadhaar() {
        return Aadhaar;
    }

    public void setAadhaar(String aadhaar, String name) {
        if (aadhaar.length() != 12) {
            System.out.println("Aadhaar number illegal");
            errno = 3;
            return;
        }
        int area = Integer.parseInt(aadhaar.substring(0, 4));
        int caste = Integer.parseInt(aadhaar.substring(4, 8));
        int identity = Integer.parseInt(aadhaar.substring(8, 11));
        int sexID = Integer.parseInt(aadhaar.substring(11));
        if (area < 1 || area > 1237 || caste < 20 || caste > 460 || identity < 0 || identity > 100) {
            System.out.println("Aadhaar number illegal");
            errno = 3;
        } else if ((getSex() == 'F' && sexID != 0) ||
                (getSex() == 'M' && sexID != 1) ||
                (getSex() == 'O' && sexID != 2)) {
            System.out.println("Aadhaar number illegal");
            errno = 3;
        } else if (UserData.getInstance().hasAadhaar(aadhaar)) {
            System.out.println("Aadhaar number exist");
            errno = 4;
        } else {
            Aadhaar = aadhaar;
            UserData.getInstance().addData(aadhaar, name);
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static void login(String[] args) {
        if (args.length != 3) {
            System.out.println("Arguments illegal");
            return;
        }

        if (!Test.logged.equals("")) {
            System.out.println("You have logged in");
            return;
        }

        if (!UserData.getInstance().hasAadhaar(args[1])) {
            System.out.println("User does not exist");
            return;
        }

        if (!UserData.getInstance().nameMatchesAadhaar(args[1], args[2])) {
            System.out.println("Wrong name");
            return;
        }

        Test.logged = args[1];
        System.out.println("Login success");
    }

    public static void logout(String[] args) {
        if (args.length != 1) {
            System.out.println("Arguments illegal");
            return;
        }

        if (Test.logged.equals("")) {
            System.out.println("No user has logged in");
            return;
        }

        Test.logged = "";
        System.out.println("Logout success");
    }

    public static void buyTicket(String[] args) {
        if (args.length != 6) {
            System.out.println("Arguments illegal");
            return;
        }

        if (Test.logged.equals("")) {
            System.out.println("Please login first");
            return;
        }

        int thisTrainIndex = Train.findTrain(args[1]);
        if (thisTrainIndex == -1) {
            System.out.println("Train does not exist");
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
        Station thisStation1 = thisLine.stations.get(thisStationIndex1);
        Station thisStation2 = thisLine.stations.get(thisStationIndex2);
        int stationDistance1 = thisStation1.distance;
        int stationDistance2 = thisStation2.distance;
        int distance = Math.abs(stationDistance1 - stationDistance2);

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

        if (args[4].equals("1A") || args[4].equals("2A")) {
            if (!Test.certMap.containsKey(Test.logged)) {
                System.out.println("Cert illegal");
                return;
            }
            if (Test.certMap.get(Test.logged)) {
                System.out.println("Cert illegal");
                return;
            }
        }

        if (!args[5].matches("^\\d*[1-9]\\d*$")) {
            System.out.println("Ticket number illegal");
            return;
        }

        int thisBuyTicketNumber = Integer.parseInt(args[5]);
        int leftTicket = 0;
        double thisTicketPrice = 0;
        switch (args[1].charAt(0)) {
            case '0':
                if (thisSeat.equals("CC")) {
                    leftTicket = thisTrain.ticketNum[0];
                    thisTicketPrice = thisTrain.ticketPrice[0];
                } else if (thisSeat.equals("SB")) {
                    leftTicket = thisTrain.ticketNum[1];
                    thisTicketPrice = thisTrain.ticketPrice[1];
                } else if (thisSeat.equals("GG")) {
                    leftTicket = thisTrain.ticketNum[2];
                    thisTicketPrice = thisTrain.ticketPrice[2];
                }
                break;
            case 'G':
                if (thisSeat.equals("SC")) {
                    leftTicket = thisTrain.ticketNum[0];
                    thisTicketPrice = thisTrain.ticketPrice[0];
                } else if (thisSeat.equals("HC")) {
                    leftTicket = thisTrain.ticketNum[1];
                    thisTicketPrice = thisTrain.ticketPrice[1];
                } else if (thisSeat.equals("SB")) {
                    leftTicket = thisTrain.ticketNum[2];
                    thisTicketPrice = thisTrain.ticketPrice[2];
                }
                break;
            case 'K':
                if (thisSeat.equals("1A")) {
                    leftTicket = thisTrain.ticketNum[0];
                    thisTicketPrice = thisTrain.ticketPrice[0];
                } else if (thisSeat.equals("2A")) {
                    leftTicket = thisTrain.ticketNum[1];
                    thisTicketPrice = thisTrain.ticketPrice[1];
                }
                break;
        }
        if (leftTicket < thisBuyTicketNumber) {
            System.out.println("Ticket does not enough");
            return;
        }

        switch (args[1].charAt(0)) {
            case '0':
                if (thisSeat.equals("CC")) thisTrain.ticketNum[0] -= thisBuyTicketNumber;
                else if (thisSeat.equals("SB")) thisTrain.ticketNum[1] -= thisBuyTicketNumber;
                else if (thisSeat.equals("GG")) thisTrain.ticketNum[2] -= thisBuyTicketNumber;
                break;
            case 'G':
                if (thisSeat.equals("SC")) thisTrain.ticketNum[0] -= thisBuyTicketNumber;
                else if (thisSeat.equals("HC")) thisTrain.ticketNum[1] -= thisBuyTicketNumber;
                else if (thisSeat.equals("SB")) thisTrain.ticketNum[2] -= thisBuyTicketNumber;
                break;
            case 'K':
                if (thisSeat.equals("1A")) thisTrain.ticketNum[0] -= thisBuyTicketNumber;
                else if (thisSeat.equals("2A")) thisTrain.ticketNum[1] -= thisBuyTicketNumber;
                break;
        }

        Ticket ticket = new Ticket(Test.logged, args[1], args[2], args[3], thisSeat, thisBuyTicketNumber, thisTicketPrice * thisBuyTicketNumber * distance);
        Test.ticketList.add(ticket);
        System.out.println("Thanks for your order");
    }

    public static void listOrder(String[] args) {
        if (args.length != 1) {
            System.out.println("Arguments illegal");
            return;
        }

        if (Test.logged.equals("")) {
            System.out.println("Please login first");
            return;
        }

        int countTicketRecords = 0;
        for (int i = Test.ticketList.size() - 1; i >= 0; i--) {
            if (Test.ticketList.get(i).aadhaar.equals(Test.logged)) {
                countTicketRecords++;
                System.out.println(Test.ticketList.get(i));
            }
        }

        if (countTicketRecords == 0) {
            System.out.println("No order");
        }
    }

    public static void rechargeBalance(String[] args) {
        if (args.length != 2) {
            System.out.println("Arguments illegal");
            return;
        }

        if (Test.logged.equals("")) {
            System.out.println("Please login first");
            return;
        }

        double rechargeAmount = Double.parseDouble(args[1]);
        if (rechargeAmount < 0) {
            System.out.println("Arguments illegal");
            return;
        }

        User thisUser;
        for (int i = 0; i < Test.userList.size(); i++) {
            if (Test.userList.get(i).Aadhaar.equals(Test.logged)) {
                thisUser = Test.userList.get(i);
                thisUser.balance += rechargeAmount;
                System.out.println("Recharge success");
                break;
            }
        }
    }

    public static void checkBalance(String[] args) {
        if (args.length != 1) {
            System.out.println("Arguments illegal");
            return;
        }

        if (Test.logged.equals("")) {
            System.out.println("Please login first");
            return;
        }

        User thisUser;
        for (int i = 0; i < Test.userList.size(); i++) {
            if (Test.userList.get(i).Aadhaar.equals(Test.logged)) {
                thisUser = Test.userList.get(i);
                System.out.println("UserName:" + thisUser.name + "\nBalance:" + String.format("%.2f", thisUser.balance));
                break;
            }
        }

    }

    public static void cancelOrder(String[] args) {
        if (args.length != 6) {
            System.out.println("Arguments illegal");
            return;
        }

        if (Test.logged.equals("")) {
            System.out.println("Please login first");
            return;
        }

        boolean findFlag = false;
        int thisCancelNum = Integer.parseInt(args[5]);
        int totalLeftNum = 0;
        for (int i = Test.ticketList.size() - 1; i >= 0; i--) {
            if (Test.ticketList.get(i).aadhaar.equals(Test.logged) &&
                    Test.ticketList.get(i).ticketTrainNo.equals(args[1]) &&
                    Test.ticketList.get(i).stationName1.equals(args[2]) &&
                    Test.ticketList.get(i).stationName2.equals(args[3]) &&
                    Test.ticketList.get(i).seatType.equals(args[4]) &&
                    !Test.ticketList.get(i).paid) {
                totalLeftNum += Test.ticketList.get(i).num;
                findFlag = true;
            }
        }

        if (!findFlag) {
            System.out.println("No such Record");
            return;
        }

        if (thisCancelNum > totalLeftNum) {
            System.out.println("No enough orders");
            return;
        }

        int thisTrainIndex = Train.findTrain(args[1]);
        Train thisTrain = Test.trainList.get(thisTrainIndex);
        String thisSeat = args[4];
        switch (args[1].charAt(0)) {
            case '0':
                if (thisSeat.equals("CC")) thisTrain.ticketNum[0] += thisCancelNum;
                else if (thisSeat.equals("SB")) thisTrain.ticketNum[1] += thisCancelNum;
                else if (thisSeat.equals("GG")) thisTrain.ticketNum[2] += thisCancelNum;
                break;
            case 'G':
                if (thisSeat.equals("SC")) thisTrain.ticketNum[0] += thisCancelNum;
                else if (thisSeat.equals("HC")) thisTrain.ticketNum[1] += thisCancelNum;
                else if (thisSeat.equals("SB")) thisTrain.ticketNum[2] += thisCancelNum;
                break;
            case 'K':
                if (thisSeat.equals("1A")) thisTrain.ticketNum[0] += thisCancelNum;
                else if (thisSeat.equals("2A")) thisTrain.ticketNum[1] += thisCancelNum;
                break;
        }

        for (int i = Test.ticketList.size() - 1; i >= 0; i--) {
            if (Test.ticketList.get(i).aadhaar.equals(Test.logged) &&
                    Test.ticketList.get(i).ticketTrainNo.equals(args[1]) &&
                    Test.ticketList.get(i).stationName1.equals(args[2]) &&
                    Test.ticketList.get(i).stationName2.equals(args[3]) &&
                    Test.ticketList.get(i).seatType.equals(args[4]) &&
                    !Test.ticketList.get(i).paid) {
                if (thisCancelNum > Test.ticketList.get(i).num) {
                    thisCancelNum -= Test.ticketList.get(i).num;
                    Test.ticketList.remove(i);
                } else if (thisCancelNum == Test.ticketList.get(i).num) {
                    Test.ticketList.remove(i);
                    break;
                } else {
                    Test.ticketList.get(i).num -= thisCancelNum;
                    updateOrder(i);
                    break;
                }
            }
        }

        System.out.println("Cancel success");
    }

    public static void payOrder(String[] args) {
        if (args.length != 1) {
            System.out.println("Arguments illegal");
            return;
        }

        if (Test.logged.equals("")) {
            System.out.println("Please login first");
            return;
        }

        int countTicketRecords = 0;
        ArrayList<Ticket> thisTicketList = new ArrayList<>();
        for (int i = Test.ticketList.size() - 1; i >= 0; i--) {
            if (Test.ticketList.get(i).aadhaar.equals(Test.logged) && !Test.ticketList.get(i).paid) {
                countTicketRecords++;
                thisTicketList.add(Test.ticketList.get(i));
            }
        }

        if (countTicketRecords == 0) {
            System.out.println("No order");
            return;
        }

        User thisUser = null;
        for (int i = Test.userList.size() - 1; i >= 0; i--) {
            if (Test.userList.get(i).Aadhaar.equals(Test.logged)) {
                thisUser = Test.userList.get(i);
                break;
            }
        }

        double totalTicketPrice = 0;
        int thisDiscount = 0;
        int ticketPaidInDiscount = 0;
        if (thisUser instanceof Student) {
            thisDiscount = ((Student) thisUser).discount;
        }
        for (Ticket value : thisTicketList) {
            if (thisDiscount >= value.num) {
                thisDiscount -= value.num;
                ticketPaidInDiscount += value.num;
                totalTicketPrice += value.totalPrice * 0.05;
            } else {
                totalTicketPrice += (value.totalPrice / value.num) * thisDiscount * 0.05;
                ticketPaidInDiscount += thisDiscount;
                totalTicketPrice += (value.totalPrice / value.num) * (value.num - thisDiscount);
                thisDiscount = 0;
            }
        }

        if (thisUser.balance < totalTicketPrice) {
            System.out.println("Balance does not enough");
            return;
        }

        thisUser.balance -= totalTicketPrice;
        if (thisUser instanceof Student) {
            ((Student) thisUser).discount -= ticketPaidInDiscount;
        }
        for (Ticket ticket : thisTicketList) {
            ticket.paid = true;
        }

        System.out.println("Payment success");
    }

    public static void updateOrder(int index) {
        Ticket thisTicket = Test.ticketList.get(index);
        int thisTrainIndex = Train.findTrain(thisTicket.ticketTrainNo);
        Train thisTrain = Test.trainList.get(thisTrainIndex);
        String thisSeat = thisTicket.seatType;
        double thisTicketPrice = 0;
        switch (thisTicket.ticketTrainNo.charAt(0)) {
            case '0':
                if (thisSeat.equals("CC")) {
                    thisTicketPrice = thisTrain.ticketPrice[0];
                } else if (thisSeat.equals("SB")) {
                    thisTicketPrice = thisTrain.ticketPrice[1];
                } else if (thisSeat.equals("GG")) {
                    thisTicketPrice = thisTrain.ticketPrice[2];
                }
                break;
            case 'G':
                if (thisSeat.equals("SC")) {
                    thisTicketPrice = thisTrain.ticketPrice[0];
                } else if (thisSeat.equals("HC")) {
                    thisTicketPrice = thisTrain.ticketPrice[1];
                } else if (thisSeat.equals("SB")) {
                    thisTicketPrice = thisTrain.ticketPrice[2];
                }
                break;
            case 'K':
                if (thisSeat.equals("1A")) {
                    thisTicketPrice = thisTrain.ticketPrice[0];
                } else if (thisSeat.equals("2A")) {
                    thisTicketPrice = thisTrain.ticketPrice[1];
                }
                break;
        }
        Line thisLine = thisTrain.line;
        int thisStationIndex1 = Station.findStation(thisTicket.stationName1, thisLine);
        int thisStationIndex2 = Station.findStation(thisTicket.stationName2, thisLine);
        Station thisStation1 = thisLine.stations.get(thisStationIndex1);
        Station thisStation2 = thisLine.stations.get(thisStationIndex2);
        int stationDistance1 = thisStation1.distance;
        int stationDistance2 = thisStation2.distance;
        int distance = Math.abs(stationDistance1 - stationDistance2);
        thisTicket.totalPrice = thisTicketPrice * thisTicket.num * distance;
    }

    public static boolean checkAadhaar(String aadhaar) {
        try {
            int area = Integer.parseInt(aadhaar.substring(0, 4));
            int caste = Integer.parseInt(aadhaar.substring(4, 8));
            int identity = Integer.parseInt(aadhaar.substring(8, 11));
            int sexID = Integer.parseInt(aadhaar.substring(11));
            return area >= 1 && area <= 1237 && caste >= 20 && caste <= 460 && identity >= 0 && identity <= 100;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Name:" + name + "\n" +
                "Sex:" + sex + "\n" +
                "Aadhaar:" + Aadhaar;
    }
}
