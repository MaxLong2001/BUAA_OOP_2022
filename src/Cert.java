import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Cert {

    public static void importCert(String[] argOpts) throws IOException {
        if (Test.mode == 0) {
            System.out.println("Command does not exist");
            return;
        }

        if (argOpts.length != 2) {
            System.out.println("Arguments illegal");
            return;
        }

        String fileName = argOpts[1];
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        int cntPositives = 0;
        int cntNegatives = 0;
        while ((line = br.readLine()) != null) {
            String[] tokens = line.split(",");
            Test.certMap.put(tokens[0], tokens[1].equals("P"));
        }
        br.close();

        for (Map.Entry<String, Boolean> entry : Test.certMap.entrySet()) {
            if (entry.getValue()) cntPositives++;
            else cntNegatives++;
        }
        System.out.println("Import Success, Positive:" + cntPositives + " Negative:" + cntNegatives);
    }
}