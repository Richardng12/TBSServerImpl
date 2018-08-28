package tbs.server;

public class Ticket {
    private String _performanceID;
    private int _rowNumber;
    private int _seatNumber;
    private String _ticketID;

    public Ticket(String performanceID, int rowNumber, int seatNumber, String ticketID) {
        _performanceID = performanceID;
        _rowNumber = rowNumber;
        _seatNumber = seatNumber;
        _ticketID = ticketID;
    }

    public int getSeatNumber() {
        return _seatNumber;
    }

    public int getRowNumber() {
        return _rowNumber;
    }

    public String getPerformanceID() {
        return _performanceID;
    }

    public String getTicketID() {
        return _ticketID;
    }
}
