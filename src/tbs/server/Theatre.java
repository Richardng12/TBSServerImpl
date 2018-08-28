package tbs.server;

public class Theatre {
    private String _theatreID;
    private int _seatsSize;
    private int _floorArea;

    public Theatre(String theatreID, int seatsSize, int floorArea) {
        _theatreID = theatreID;
        _seatsSize = seatsSize;
        _floorArea = floorArea;
    }

    public String getTheatreID() {
        return _theatreID;
    }

    public int getSeatsSize() {
        return _seatsSize;
    }
}
