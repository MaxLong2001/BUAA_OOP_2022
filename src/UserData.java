import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserData {
    public static UserData uniqueInstance = new UserData();
    public ArrayList<Map<String, String>> dataset = new ArrayList<>();


    private UserData() {
    }

    public static UserData getInstance() {
        return uniqueInstance;
    }

    public void addData(String aadhaar, String name) {
        Map<String, String> map = new HashMap<>();
        map.put(aadhaar, name);
        dataset.add(map);
    }

    public boolean hasAadhaar(String aadhaar) {
        for (Map<String, String> stringStringMap : dataset) {
            if (stringStringMap.containsKey(aadhaar))
                return true;
        }
        return false;
    }

    public boolean nameMatchesAadhaar(String aadhaar, String name) {
        Map<String, String> match = new HashMap<>();
        match.put(aadhaar, name);
        for (Map<String, String> stringStringMap : dataset) {
            if (stringStringMap.equals(match))
                return true;
        }
        return false;
    }
}
