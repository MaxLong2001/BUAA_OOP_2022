public class User {
    private String name;
    private char sex;
    private String Aadhaar;

    private int errno = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String pattern = "^[a-zA-Z_]+$";
        if (!name.matches(pattern)) {
            System.out.println("Name illegal");
            errno = 1;
        }
        else
            this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        if (sex != 'F' && sex != 'M' && sex != 'O') {
            System.out.println("Sex illegal");
            errno = 2;
        }
        else
            this.sex = sex;
    }

    public String getAadhaar() {
        return Aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        int area = Integer.parseInt(aadhaar.substring(0, 4));
        int caste = Integer.parseInt(aadhaar.substring(4, 8));
        int identity = Integer.parseInt(aadhaar.substring(8, 11));
        int sexID = Integer.parseInt(aadhaar.substring(11));
        if (area < 1 || area > 1237 || caste < 20 || caste > 460 || identity < 0 || identity > 100) {
            System.out.println("Aadhaar number illegal");
            errno = 3;
        }
        else if ((getSex() == 'F' && sexID != 0) ||
                (getSex() == 'M' && sexID != 1) ||
                (getSex() == 'O' && sexID != 2)) {
            System.out.println("Aadhaar number illegal");
            errno = 3;
        }
        else if (UserData.getInstance().hasData(aadhaar)) {
            System.out.println("Aadhaar number exist");
            errno = 4;
        }
        else {
            Aadhaar = aadhaar;
            UserData.getInstance().addData(aadhaar);
        }

    }

    @Override
    public String toString() {
        return "Name:" + name + "\n" +
                "Sex:" + sex + "\n" +
                "Aadhaar:" + Aadhaar;
    }

    public void addUser(String argsName, String argsSex, String argsAadhaar) {
        setName(argsName);
        if(errno != 0) return;
        setSex(argsSex.charAt(0));
        if(errno != 0) return;
        setAadhaar(argsAadhaar);
        if(errno != 0) return;
        System.out.println(toString());
    }
}
