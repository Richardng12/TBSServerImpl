package tbs.server;

public class Performance {
    private String _actID;
    private String _theatreID;
    private String _startTimeStr;
    private String _premiumPriceStr;
    private String _cheapSeatsStr;
    private String _performanceID;

    public Performance(String actID, String theatreID, String startTimeStr, String premiumPriceStr, String cheapSeatStr,
                       String performanceID) {
        _actID = actID;
        _theatreID = theatreID;
        _startTimeStr = startTimeStr;
        _premiumPriceStr = premiumPriceStr;
        _cheapSeatsStr = cheapSeatStr;
        _performanceID = performanceID;

    }

    public String getActID() {

        return _actID;
    }

    public String getPerformanceID() {

        return _performanceID;
    }

    public String getTheatreID() {

        return _theatreID;
    }

    public String getStartTime() {
        return _startTimeStr;
    }

    public String getPremiumPriceStr() {
        return _premiumPriceStr;
    }

    public String getCheapSeatStr() {
        return _cheapSeatsStr;
    }
}
