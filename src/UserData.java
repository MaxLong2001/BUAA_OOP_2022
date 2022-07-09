import java.util.ArrayList;

public class UserData {
    private static UserData uniqueInstance = new UserData();
    private ArrayList<String> dataset = new ArrayList<String>();
    
    private UserData() {
    }

    public static UserData getInstance() {
        return uniqueInstance;
    }

    public void addData(String str) {
        dataset.add(str);
    }

    public boolean hasData(String str) {
        return dataset.contains(str);
    }
}
