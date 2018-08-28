package tbs.server;

public class Artist {
    private String _artistID;
    private String _artistName;

    public Artist(String artistID, String artistName) {
        _artistID = artistID;
        _artistName = artistName;
    }

    public String getArtistName() {

        return _artistName;
    }

    public String getArtistID() {

        return _artistID;
    }


}
