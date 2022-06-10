public class Ticket {
    String aadhaar;
    String ticketTrainNo;
    String stationName1;
    String stationName2;
    String seatType;
    int num;
    double totalPrice;
    boolean paid;

    public Ticket(String aadhaar, String ticketTrainNo, String stationName1, String stationName2, String seatType, int num, double totalPrice) {
        this.aadhaar = aadhaar;
        this.ticketTrainNo = ticketTrainNo;
        this.stationName1 = stationName1;
        this.stationName2 = stationName2;
        this.seatType = seatType;
        this.num = num;
        this.totalPrice = totalPrice;
        this.paid = false;
    }

    @Override
    public String toString() {
        char paidChar;
        if (paid)
            paidChar = 'T';
        else
            paidChar = 'F';
        return "[" + ticketTrainNo + ": " + stationName1 + "->" + stationName2 + "] seat:" + seatType + " num:" + num + " price:" + String.format("%.2f", totalPrice) + " paid:" + paidChar;
    }
}
