import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static int mode = 0;
    public static ArrayList<Line> lineList = new ArrayList<>();
    public static ArrayList<Train> trainList = new ArrayList<>();

    public static void main(String[] args) {
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
                    if (argOpts.length != 4)
                        System.out.println("Arguments illegal");
                    else {
                        User user = new User();
                        user.addUser(argOpts[1], argOpts[2], argOpts[3]);
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
