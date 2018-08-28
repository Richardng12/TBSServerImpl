package tbs.server;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class TBSServerImpl implements TBSServer {
    private List<Theatre> theatresList = new ArrayList<>();
    private List<Artist> artistsList = new ArrayList<>();
    private List<Act> actList = new ArrayList<>();
    private List<Performance> performanceList = new ArrayList<>();
    private List<Ticket> ticketList = new ArrayList<>();
    private static int ActIDCounter = 0;
    private static int ArtistIDCounter = 0;
    private static int PerformanceIDCounter = 0;
    private static int TicketIDCounter = 0;

    @Override
    public String initialise(String path) {
        String success = "";
        int Counter = 0;

        List<String> listIds = new ArrayList<>();

        try {
            File file = new File(path);

            BufferedReader read = new BufferedReader(new FileReader(file));
            String line;
            while ((line = read.readLine()) != null) {
                listIds.add(line);
                Counter++;
            }
            read.close();

            for (int i = 0; i < Counter; i++) {
                String[] splitStr = listIds.get(i).split("\\s+");
                String ID = splitStr[1];
                int size = Integer.parseInt(splitStr[2]);
                int area = Integer.parseInt(splitStr[3]);
                Theatre theatre = new Theatre(ID, size, area);
                theatresList.add(theatre);
            }

        } catch (FileNotFoundException f) {
            return ("ERROR no file found");
        } catch (IOException f) {
            return ("ERROR IO exception");
        } catch (NumberFormatException f) {
            return ("ERROR incorrect format");
        }
        return (success);
    }


    @Override
    public List<String> getTheatreIDs() {
        List<String> TheatreIDsList = new ArrayList<>();

        for (int i = 0; i < theatresList.size(); i++) {
            TheatreIDsList.add(theatresList.get(i).getTheatreID());
        }

        Collections.sort(TheatreIDsList);
        return TheatreIDsList;
    }

    @Override
    public List<String> getArtistIDs() {
        List<String> ArtistIDsList = new ArrayList<>();

        for (int i = 0; i < artistsList.size(); i++) {
            ArtistIDsList.add(artistsList.get(i).getArtistID());
        }

        Collections.sort(ArtistIDsList);
        return ArtistIDsList;
    }

    @Override
    public List<String> getArtistNames() {
        List<String> ArtistNamesList = new ArrayList<>();

        for (int i = 0; i < artistsList.size(); i++) {
            ArtistNamesList.add(artistsList.get(i).getArtistName());
        }

        Collections.sort(ArtistNamesList);
        return ArtistNamesList;
    }

    @Override
    public List<String> getActIDsForArtist(String artistID) {
        List<String> ActIDsList = new ArrayList<>();
        String error1 = ("ERROR artistID is empty");

        if (artistID == null || artistID.isEmpty()) {
            ActIDsList.add(error1);
            return ActIDsList;
        }
        for (int i = 0; i < actList.size(); i++) {
            if (artistID.equals(actList.get(i).getArtistID())) {
                ActIDsList.add(actList.get(i).getActID());
            }
        }

        Collections.sort(ActIDsList);
        return ActIDsList;
    }

    @Override
    public List<String> getPeformanceIDsForAct(String actID) {
        List<String> PerformanceIDsList = new ArrayList<>();
        String error1 = ("ERROR actID is empty");

        if (actID == null || actID.isEmpty()) {
            PerformanceIDsList.add(error1);
            return PerformanceIDsList;
        }

        for (int i = 0; i < performanceList.size(); i++) {
            if (actID.equals(performanceList.get(i).getActID())) {
                PerformanceIDsList.add(performanceList.get(i).getPerformanceID());
            }
        }
        Collections.sort(PerformanceIDsList);
        return PerformanceIDsList;
    }

    @Override
    public List<String> getTicketIDsForPerformance(String performanceID) {
        List<String> TicketIDsList = new ArrayList<>();
        String error1 = ("ERROR performanceID is empty");

        if (performanceID == null || performanceID.isEmpty()) {
            TicketIDsList.add(error1);
            return TicketIDsList;
        }
        for (int i = 0; i < ticketList.size(); i++) {
            if (performanceID.equals(ticketList.get(i).getPerformanceID())) {
                TicketIDsList.add(ticketList.get(i).getTicketID());
            }
        }
        Collections.sort(TicketIDsList);
        return TicketIDsList;
    }

    @Override
    public String addArtist(String name) {
        String add = "Artist";
        if (name == null || name.isEmpty()){
            return ("ERROR artist name is blank");
        }
        for (int i = 0; i < artistsList.size(); i++) {
            if (artistsList.get(i).getArtistName().equalsIgnoreCase(name)) {
                return ("ERROR artist already exists");
            }
        }
        int number = ArtistIDCounter;
        String counter = Integer.toString(number);
        String ArtistID = add + counter;
        Artist artist = new Artist(ArtistID, name);
        artistsList.add(artist);
        ArtistIDCounter++;
        return ArtistID;
    }

    @Override
    public String addAct(String title, String artistID, int minutesDuration) {
        String add = "Act";

        if (title == null || title.isEmpty()) {
            return ("ERROR title is empty");
        }
        if (artistID == null || artistID.isEmpty()) {
            return ("ERROR artistID is empty");
        }
        if (minutesDuration <= 0) {
            return ("ERROR minutes undefined");
        }
        for (int i = 0; i < actList.size(); i++) {
            if (title.equals(actList.get(i).getActTitle())) {
                return ("ERROR act title already exists for artist");
            }
        }
        for (int i = 0; i < artistsList.size(); i++) {
            if (artistID.equals(artistsList.get(i).getArtistID())) {
                int number = ActIDCounter;
                String counter = Integer.toString(number);
                String actID = add + counter;
                Act act = new Act(title, actID, minutesDuration, artistID);
                actList.add(act);
                ActIDCounter++;
                return actID;

            }
        }
        return ("ERROR No associated artistID");
    }

    @Override
    public String schedulePerformance(String actID, String theatreID, String startTimeStr, String premiumPriceStr, String cheapSeatsStr) {
        String add = "P";
        int counter = 0;

        for (int i = 0; i < actList.size(); i++) {
            if (actID.equals(actList.get(i).getActID())) {
                counter++;
            }
        }
        if (counter == 0) {
            return ("ERROR actID does not exist");
        }

        for (int i = 0; i < theatresList.size(); i++) {
            if (theatreID.equals(theatresList.get(i).getTheatreID())) {
                counter++;
            }
        }
        if (counter == 0) {
            return ("ERROR theatreID does not exist");
        }
        try {
            SimpleDateFormat Check = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Check.setLenient(false);
            Date Checker = Check.parse(startTimeStr);
        } catch (ParseException e) {
            return ("ERROR unrecognized date format");
        }
        if (premiumPriceStr.substring(0, 1).equals("$") && cheapSeatsStr.substring(0, 1).equals("$")) {
            if (premiumPriceStr.substring(1, premiumPriceStr.length()).matches("[0-9]+") && cheapSeatsStr.substring(1, cheapSeatsStr.length()).matches("[0-9]+")) {
                int number = PerformanceIDCounter;
                String count = Integer.toString(number);
                String performanceID = add + count;
                Performance performance = new Performance(actID, theatreID, startTimeStr, premiumPriceStr, cheapSeatsStr, performanceID);
                performanceList.add(performance);
                PerformanceIDCounter++;
                return performanceID;
            }
        }

        return ("ERROR unrecognized price format");
    }

    @Override
    public String issueTicket(String performanceID, int rowNumber, int seatNumber) {
        String add = "Ticket";
        if (performanceID == null || performanceID.isEmpty()) {
            return ("ERROR performanceID is empty");
        }
        for (int i = 0; i < performanceList.size(); i++) {
            if (performanceID.equals(performanceList.get(i).getPerformanceID())) {
                String TheatreID = performanceList.get(i).getTheatreID();
                for (int j = 0; j < theatresList.size(); j++) {
                    if (TheatreID.equals(theatresList.get(j).getTheatreID())) {
                        int seatSize = theatresList.get(j).getSeatsSize();

                        if (rowNumber > seatSize || seatNumber > seatSize) {
                            return ("ERROR seat does not exist");
                        }
                        if (rowNumber <= 0 || seatNumber <= 0) {
                            return ("ERROR seat does not exist");
                        }
                    }
                }
                for (int k = 0; k < ticketList.size(); k++) {
                    if (seatNumber == (ticketList.get(k).getSeatNumber()) && performanceID.equals(ticketList.get(k).getPerformanceID())) {
                        if (rowNumber == (ticketList.get(k).getRowNumber()) && performanceID.equals(ticketList.get(k).getPerformanceID())) {
                            return ("ERROR Seat already taken");
                        }
                    }
                }
            }
            int number = TicketIDCounter;
            String counter = Integer.toString(number);
            String ticketID = add + counter;
            Ticket ticket = new Ticket(performanceID, rowNumber, seatNumber, ticketID);
            ticketList.add(ticket);
            TicketIDCounter++;
            return ticketID;
        }

        return ("ERROR no associated performanceID");
    }

    @Override
    public List<String> seatsAvailable(String performanceID) {
        List<String> seatsAvailableList = new ArrayList<>();

        String error1 = ("ERROR performanceID is empty");
        String error2 = ("ERROR no associated performanceID");

        if (performanceID == null && performanceID.isEmpty()) {
            seatsAvailableList.add(error1);
            return seatsAvailableList;
        }
        for (int i = 0; i < performanceList.size(); i++) {
            if (performanceID.equals(performanceList.get(i).getPerformanceID())) {
                String theatreID = performanceList.get(i).getTheatreID();
                for (int j = 0; j < theatresList.size(); j++) {
                    if (theatreID.equals(theatresList.get(j).getTheatreID())) {
                        int seatSize = theatresList.get(j).getSeatsSize();
                        for (int rowNumber = 1; rowNumber <= seatSize; rowNumber++) { //rowNumber
                            for (int seatNumber = 1; seatNumber <= seatSize; seatNumber++) { //seatNumber
                                int counter = 0;
                                for (int k = 0; k < ticketList.size(); k++) {
                                    if (seatNumber == ticketList.get(k).getSeatNumber() && rowNumber == ticketList.get(k).getRowNumber()) {
                                        counter++;
                                    }
                                }
                                if (counter == 0) {
                                    String string1 = Integer.toString(rowNumber);
                                    String string2 = Integer.toString(seatNumber);
                                    String Seat = string1 + "\t" + string2;
                                    seatsAvailableList.add(Seat);

                                }
                            }

                        }
                    }
                }
                return seatsAvailableList;
            }
        }
        seatsAvailableList.add(error2);
        return seatsAvailableList;
    }


    @Override
    public List<String> salesReport(String actID) {
        List<String> salesReportList = new ArrayList<>();
        String error1 = ("ERROR actID is empty");

        if (actID == null || actID.isEmpty()) {
            salesReportList.add(error1);
            return salesReportList;
        }
        for (int i = 0; i < performanceList.size(); i++) {
            int ticketsSold = 0;
            int sales = 0;
            if (actID.equals(performanceList.get(i).getActID())) {
                String performanceID = performanceList.get(i).getPerformanceID();
                String startTime = performanceList.get(i).getStartTime();
                String theatreID = performanceList.get(i).getTheatreID();
                for (int j = 0; j < theatresList.size(); j++) {
                    if (theatreID.equals(theatresList.get(j).getTheatreID())) {
                        int seatSize = theatresList.get(j).getSeatsSize();
                        int premium = seatSize / 2;
                        for (int k = 0; k < ticketList.size(); k++) {
                            if (performanceID.equals(ticketList.get(k).getPerformanceID())) {
                                int rowNumber = ticketList.get(k).getRowNumber();
                                if (rowNumber > premium) {
                                    String temp = performanceList.get(i).getCheapSeatStr();
                                    int length = temp.length();
                                    String Cheap = (temp.substring(1, length));
                                    sales = sales + Integer.parseInt(Cheap);
                                    ticketsSold++;
                                } else {
                                    String temp = performanceList.get(i).getPremiumPriceStr();
                                    int length = temp.length();
                                    String Premium = (temp.substring(1, length));
                                    sales = sales + Integer.parseInt(Premium);
                                    ticketsSold++;
                                }
                            }
                        }
                    }
                }
                String total = "$" + Integer.toString(sales);
                String sold = Integer.toString(ticketsSold);
                String salesReport = performanceID + "\t" + startTime + "\t" + sold + "\t" + total;
                salesReportList.add(salesReport);
            }

        }
        return salesReportList;
    }


    @Override
    public List<String> dump() {
        return null;
    }
}

