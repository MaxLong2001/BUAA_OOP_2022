import java.util.Scanner;

public class Test {
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
            if (argOpts.length != 4)
                System.out.println("Arguments illegal");
            else if (argOpts[0].equals("addUser")) {
                User user = new User();
                user.addUser(argOpts[1], argOpts[2], argOpts[3]);
            }
        }
        System.exit(0);
    }
}
