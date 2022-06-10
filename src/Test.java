import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test {
    public static int mode = 0;
    public static String logged = "";
    public static ArrayList<User> userList = new ArrayList<>();
    public static ArrayList<Line> lineList = new ArrayList<>();
    public static ArrayList<Train> trainList = new ArrayList<>();
    public static ArrayList<Ticket> ticketList = new ArrayList<>();
    public static Map<String, Boolean> certMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String argStr;

        while (true) {
            argStr = sc.nextLine();
            if (argStr.equals("QUIT")) {
                System.out.println("----- Good Bye! -----");
                break;
            }

            String[] argOpts = argStr.split(" ");
            switch (argOpts[0]) {
                case "addUser":
                    if (argOpts.length != 4 && argOpts.length != 5)
                        System.out.println("Arguments illegal");
                    else {
                        if (argOpts.length == 4) {
                            User user = new User();
                            user.addUser(argOpts[1], argOpts[2], argOpts[3]);
                            userList.add(user);
                        } else {
                            Student student = new Student();
                            student.addStudent(argOpts[1], argOpts[2], argOpts[3], argOpts[4]);
                            userList.add(student);
                        }
                    }
                    break;
                case "TunakTunakTun":
                    if (argOpts.length != 1)
                        System.out.println("Arguments illegal");
                    else
                        toSuperMode();
                    break;
                case "NutKanutKanut":
                    if (argOpts.length != 1)
                        System.out.println("Arguments illegal");
                    else
                        exitSuperMode();
                    break;
                case "addLine":
                    Line.addLine(argOpts);
                    break;
                case "delLine":
                    Line.delLine(argOpts);
                    break;
                case "addStation":
                    Station.addStation(argOpts);
                    break;
                case "delStation":
                    Station.delStation(argOpts);
                    break;
                case "lineInfo":
                    Line.lineInfo(argOpts);
                    break;
                case "listLine":
                    Line.listLine(argOpts);
                    break;
                case "addTrain":
                    Train.addTrain(argOpts);
                    break;
                case "delTrain":
                    Train.delTrain(argOpts);
                    break;
                case "checkTicket":
                    Train.checkTicket(argOpts);
                    break;
                case "listTrain":
                    Train.listTrain(argOpts);
                    break;
                case "login":
                    User.login(argOpts);
                    break;
                case "logout":
                    User.logout(argOpts);
                    break;
                case "buyTicket":
                    User.buyTicket(argOpts);
                    break;
                case "listOrder":
                    User.listOrder(argOpts);
                    break;
                case "rechargeBalance":
                    User.rechargeBalance(argOpts);
                    break;
                case "checkBalance":
                    User.checkBalance(argOpts);
                    break;
                case "importCert":
                    Cert.importCert(argOpts);
                    break;
                case "cancelOrder":
                    User.cancelOrder(argOpts);
                    break;
                case "payOrder":
                    User.payOrder(argOpts);
                    break;
                default:
                    System.out.println("Command does not exist");
                    break;
            }
        }
        System.exit(0);
    }

    public static void toSuperMode() {
        if (mode == 1) {
            System.out.println("WanNiBa");
        } else {
            System.out.println("DuluDulu");
            mode = 1;
        }
    }

    public static void exitSuperMode() {
        if (mode == 0) {
            System.out.println("WanNiBa");
        } else {
            System.out.println("DaDaDa");
            mode = 0;
        }
    }
}
