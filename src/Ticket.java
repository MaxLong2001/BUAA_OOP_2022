public class Ticket {
    String aadhaar;
    String ticketTrainNo;
    String stationName1;
    String stationName2;
    String seatType;
    int num;
    double totalPrice;

    public Ticket() {
    }

    public Ticket(String aadhaar, String ticketTrainNo, String stationName1, String stationName2, String seatType, int num, double totalPrice) {
        this.aadhaar = aadhaar;
        this.ticketTrainNo = ticketTrainNo;
        this.stationName1 = stationName1;
        this.stationName2 = stationName2;
        this.seatType = seatType;
        this.num = num;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "[" + ticketTrainNo + ": " + stationName1 + "->" + stationName2 + "] seat:" + seatType + " num:" + num + " price:" + String.format("%.2f", totalPrice);
    }
}
