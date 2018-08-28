package tbs.server;

public class Act {
    private String _actTitle;
    private String _actID;
    private int _actDuration;
    private String _artistID;

    public Act(String actTitle, String actID, int actDuration, String artistID) {
        _actTitle = actTitle;
        _actID = actID;
        _actDuration = actDuration;
        _artistID = artistID;
    }

    public String getArtistID() {

        return _artistID;
    }

    public String getActID() {

        return _actID;
    }

    public String getActTitle() {

        return _actTitle;
    }
}
