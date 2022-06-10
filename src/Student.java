public class Student extends User {
    int discount;

    public void addStudent(String argsName, String argsSex, String argsAadhaar, String argsDiscount) {
        setName(argsName);
        if (errno != 0) return;
        setSex(argsSex.charAt(0));
        if (errno != 0) return;
        setAadhaar(argsAadhaar, argsName);
        if (errno != 0) return;
        setDiscount(Integer.parseInt(argsDiscount));
        if (errno != 0) return;
        System.out.println(this);
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Name:" + name + "\n" +
                "Sex:" + sex + "\n" +
                "Aadhaar:" + Aadhaar + "\n" +
                "Discount:" + discount;
    }
}
