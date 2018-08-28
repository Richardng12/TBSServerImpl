package tbs.client;

import tbs.server.TBSServer;
import tbs.server.TBSServerImpl;

public class CLI {


	public static void main(String[] args) {
		readFromKeyboard("Hello! This is testing system V1 made by Joshua Kim.\n"
				+ "Remember that you can ask me to help you out any time\n"
				+ "if you have any problems with this assignment\n"
				+ "You can test \"initialize\" on the standard client\n"
				+ "Press enter to start the test:");
		String path = "theatres1.csv";
		if (args.length > 0) {
			path = args[0]; // This allows a different file to be specified as an argument, but the default is theatres1.csv
		}
		TBSServer server = new TBSServerImpl();
		String result = server.initialise(path);
		System.out.println("%%Result from initialisation is {" + result + "}");  // Put in { } to make empty strings easier to see.
		System.out.println(server.getTheatreIDs()); // Implement dump() to print something useful here to determine whether your initialise method has worked.

		
		/*
		String artistID1 = server.addArtist("Ewan");
		System.out.println("Result from adding artist 'Ewan' is {" + artistID1 + "}");
		server.dump(); // Check that the server has been updated

		String actID1 = server.addAct("Lecture 3b: Making Objects", artistID1, 50); // this also checks that the artist ID is used properly
		System.out.println("Result from adding act to artist 'Ewan' is {" + actID1 + "}");
		server.dump();
		*/
		printHelp();
		boolean test = true;
		while(test) {
			String mtd = readFromKeyboard("Cmd: ");
			if (mtd.equals("h")) {
				printHelp();
			}else if(mtd.equals("q")) {
				test = false;
			}else if (mtd.equals("1")) {
				String name = readFromKeyboard("Enter the name of the artist:  ");
				String artistId = server.addArtist(name);
				System.out.println("Artist Id: "+artistId);
			}else if (mtd.equals("2")) {
				System.out.println("Artist Ids: "+server.getArtistIDs());
			}else if (mtd.equals("3")) {
				System.out.println("Artist names: "+server.getArtistNames());
			}else if (mtd.equals("4")) {
				String title = readFromKeyboard("%%Enter the title of the act:  ");
				String artistId = readFromKeyboard("%%Enter the artists ID:  ");
				int duration =Integer.parseInt(readFromKeyboard("%%Enter the duration of the act:  "));
				String actId=server.addAct(title, artistId, duration);
				System.out.println("Act Id: "+actId);
			}else if (mtd.equals("5")) {
				String artistId = readFromKeyboard("%%Enter the artists ID:  ");
				System.out.println("Act Ids: "+server.getActIDsForArtist(artistId));
			}else if (mtd.equals("6")) {
				String actId = readFromKeyboard("%%Enter the act ID:  ");
				String theatreId = readFromKeyboard("%%Enter the theatre ID:  ");
				String startTime = readFromKeyboard("%%Enter the start time:  ");
				String premiumPrice = readFromKeyboard("%%Enter the premium price   <$x>:  ");
				String cheapPrice = readFromKeyboard("%%Enter the cheap price   <$x>:  ");
				System.out.println("Performance Ids: "+server.schedulePerformance(actId, theatreId, startTime, premiumPrice, cheapPrice));
			}else if (mtd.equals("7")) {
				String actId = readFromKeyboard("%%Enter the act ID:  ");
				System.out.println("Performance Ids: "+server.getPeformanceIDsForAct(actId));
			}else if (mtd.equals("8")) {
				String performanceId = readFromKeyboard("%%Enter the performance ID:  ");
				int rowNumber =Integer.parseInt(readFromKeyboard("%%Enter the row number of the seat:  "));
				int seatNumber =Integer.parseInt(readFromKeyboard("%%Enter the seat number of the seat:  "));
				System.out.println("Ticket Id: "+server.issueTicket(performanceId, rowNumber, seatNumber));
			}else if (mtd.equals("9")) {
				String performanceId = readFromKeyboard("%%Enter the performance ID:  ");
				System.out.println("Ticket Ids: "+server.getTicketIDsForPerformance(performanceId));
			}else if (mtd.equals("10")) {
				String actId = readFromKeyboard("%%Enter the act ID:  ");
				System.out.println("sales report: "+server.salesReport(actId));
			}else if (mtd.equals("11")) {
				String performanceId = readFromKeyboard("%%Enter the performance ID:  ");
				System.out.println("available seats: "+server.seatsAvailable(performanceId));
			}else if (mtd.equals("test")) {
				autoTest(server);
			}else {
				System.out.println("%%Message: invalid command (" + mtd + ")");
			}
		}
	}
	public static String readFromKeyboard(String prompt) {
		java.io.InputStreamReader stdin =
				new java.io.InputStreamReader(System.in);
		java.io.BufferedReader in =
				new java.io.BufferedReader(stdin);
		try {
			System.out.print(prompt);
			return in.readLine();
		} catch (java.io.IOException e) {
		}
		return null; 
	}
	
	public static void printHelp() {
		System.out.println("\nLIST OF COMMANDS\nq -quit");
		System.out.println("h -list of commands");
		System.out.println("1 -addArtist(name)");
		System.out.println("2 -getArtistIds()");
		System.out.println("3 -getArtistNames()");
		System.out.println("4 -addAct(title, artistId, minDuration)");
		System.out.println("5 -getActIDsForArtist(artistId)");
		System.out.println("6 -schedulePerformance(actID, theatreID, startTime, premiumPrice, cheapPrice)");
		System.out.println("7 -getPeformanceIDsForAct(actID)");
		System.out.println("8 -issueTicket(performanceID, rowNumber, seatNumber)");
		System.out.println("9 -getTicketIDsForPerformance(performanceID)");
		System.out.println("10 -salesReport(actID)");
		System.out.println("11 -seatsAvailable(performanceID)");
		System.out.println("test -runs an automatic test I made");
	}

	public static void autoTest(TBSServer server) {
		
		String[] names = {"Ewan", "Richard", "Kevin", "Bahk", "Ewan"};
		int index;
		for (index = 0;index<names.length; index++) {
			String artistId =server.addArtist(names[index]);
			System.out.println("Artist Id: "+artistId);
		}
		readFromKeyboard("%%There should be an error message about 2 artists with the same name\n"
				+"%%press enter to continue...");
		System.out.println("Artist Ids: "+server.getArtistIDs()+"\n%%There should be 4 artist Ids\n");
		System.out.println("Artist names: "+server.getArtistNames()+"\n%%4 names in alphabetical order\n");
		readFromKeyboard("%%press enter to continue...");
		String[] titles = {"250Lecture", "251Lecture", "201Lecture", "213Lecture", "251Lecture"};
		for(index = 0; index<titles.length;index++) {
			String actId = server.addAct(titles[index], server.getArtistIDs().get(0), 30);
			System.out.println("Act Id: "+actId);
		}
		readFromKeyboard("%%There should be an error message about 2 acts with the same name\n"
				+"%%press enter to continue...");
		System.out.println("Act Ids: "+server.getActIDsForArtist(server.getArtistIDs().get(0))+"\n%%There should be 4 act Ids\n");
		readFromKeyboard("%%press enter to continue...");
		String performanceId;
		for (index=0;index<3;index++) {
			performanceId =server.schedulePerformance(server.getActIDsForArtist(server.getArtistIDs().get(0)).get(0), "T1", "2017-12-11T00:00", "$15", "$10");
			System.out.println("Performance Id: "+performanceId);
		}
		performanceId =server.schedulePerformance(server.getActIDsForArtist(server.getArtistIDs().get(0)).get(0), "T1", "2017-12-11T12:01", "%15", "$10");
		System.out.println("Performance Id: "+performanceId
				+ "\n%%This one should be an error because the price keyed in was %15");
		boolean success = true;
		try {
			performanceId =server.schedulePerformance(server.getActIDsForArtist(server.getArtistIDs().get(0)).get(0), "T1", "startTime", "$weg", "$10");
		}catch(NumberFormatException e) {
			success = false;
			System.out.println("%%your code does not take wrong price format into account. \"$weg\" cannot be read");
		}
		if(success == true){
			System.out.println("Performance Id: "+performanceId+"\n%%Should be some sort of format error");
		}
		readFromKeyboard("%%press enter to continue...");
		System.out.println("Performance Ids: "+server.getPeformanceIDsForAct(server.getActIDsForArtist(server.getArtistIDs().get(0)).get(0))+"\n%%There should be 3 performance Ids\n");
		readFromKeyboard("%%press enter to continue...");
		performanceId = server.getPeformanceIDsForAct(server.getActIDsForArtist(server.getArtistIDs().get(0)).get(0)).get(0);
		int row;
		int seatNum;
		String ticketId;
		for(row = 0; row<8;row=row+2) {
			for (seatNum=1;seatNum<10; seatNum=seatNum+2) {
				ticketId =server.issueTicket(performanceId, row, seatNum);
				System.out.println("Ticket Id: "+ticketId);
			}
		}
		System.out.println("%%There should be errors because a row number was too small and a seat num was too big");
		readFromKeyboard("%%press enter to continue...");
		System.out.println("Ticket Ids: "+server.getTicketIDsForPerformance(performanceId)+"\n%%There should be 12 ticket Ids\n");
		readFromKeyboard("%%press enter to continue...");
		System.out.println("seats available: "+server.seatsAvailable(performanceId));
		System.out.println("%%There should be[1\t1, 1	2, 1	3, 1	4, 1	5, 1	6, 1	7, 2	2, 2	4, 2	6, 3	1, 3	2, 3	3, 3	4, 3	5, 3	6, 3	7, 4	2, 4	4, 4	6, 5	1, 5	2, 5	3, 5	4, 5	5, 5	6, 5	7, 6	2, 6	4, 6	6, 7	1, 7	2, 7	3, 7	4, 7	5, 7	6, 7	7]");
		readFromKeyboard("%%press enter to continue...");
		performanceId = server.getPeformanceIDsForAct(server.getActIDsForArtist(server.getArtistIDs().get(0)).get(0)).get(1);
		for(row = 1; row<8;row=row+2) {
			for (seatNum=1;seatNum<8; seatNum=seatNum+2) {
				server.issueTicket(performanceId, row, seatNum);
			}
		}
		System.out.println("sales report: "+server.salesReport(server.getActIDsForArtist(server.getArtistIDs().get(0)).get(0)));
		System.out.println("%%There should be 3 performances, one with 12 tickets and $140 sales, one with 16 tickets and $200 sales and one with 0, 0 ");
		readFromKeyboard("%%END END END\n%%press enter to continue...");
	}
}
