package spl.ass3;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;



public class Driver {
	
	protected final static Logger LOGGER = Logger.getLogger(Driver.class.getName());
	static private FileHandler fileTxt;
	static private SimpleFormatter formatterTxt;


 


	public static void main(String[] args) throws SecurityException, IOException {
		
		// Create an instance of SimpleDateFormat used for formatting 
		// the string representation of date (month/day/year)
		DateFormat df = new SimpleDateFormat("DD-MM-yyyy HH-mm-ss");

		// Get the date today using Calendar object.
		Date today = Calendar.getInstance().getTime();        
		// Using DateFormat format method we can create a string 
		// representation of a date with the defined format.
		String reportDate = df.format(today);

		// Print what date is today!
		System.out.println("Report Date: " + reportDate);
		
		LOGGER.setLevel(Level.INFO);
		fileTxt = new FileHandler("Logging "+reportDate+".txt");
		
		 formatterTxt = new SimpleFormatter();
		 fileTxt.setFormatter(formatterTxt);
		 LOGGER.addHandler(fileTxt);



	
		
		if (args.length != 4){
			System.out.println("Incorrect number of arguments ! \nExiting ...");
			System.out.println(args.length);
		}else{

			final String INITIAL_DATA_FILENAME = 					args[0];
			final String ASSET_CONTENTS_REPAIR_DETAILS_FILENAME = args[1];
			final String ASSETS_FILENAME = 						args[2];
			final String CUSTOMER_GROUPS_FILENAME = 				args[3];
			
			
			String cmdLineArguments = "\nPrinting command line arguments:\n";
			for (int i = 0 ; i < args.length ; i++){
				cmdLineArguments = cmdLineArguments + args[i] + "\n";
//				
			}
			LOGGER.info(cmdLineArguments);
			
			Managment managment = new Managment();
			
//			Parser parser = new Parser();
			Parser.initializeParser();
			Parser.parseIntialData(INITIAL_DATA_FILENAME, managment);
			Parser.parseAssetContentsRepairDetails(ASSET_CONTENTS_REPAIR_DETAILS_FILENAME, managment);
			Parser.parseAssets(ASSETS_FILENAME, managment);
			Parser.parseCustomerGroups(CUSTOMER_GROUPS_FILENAME, managment);
			
			LOGGER.info("Finish Parsing All Files! \n\n\n\n");
			
			LOGGER.fine("Testing the parsing...");
			LOGGER.fine("Printing Clerk Details:\n" + managment.clerkDetailsToString());
			LOGGER.fine("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOGGER.fine("Printing Customer Groups Details:\n" + managment.customerGroupDetailsToString());
			LOGGER.fine("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOGGER.fine("Printing Assets Details:\n" + managment.assetsToString());
			LOGGER.fine("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOGGER.fine("Printing Warehouse content:\n" + managment.warehouseToString());
			LOGGER.fine("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOGGER.fine("Printing Repair Tool Information:\n" + managment.repairToolInformationToString());
			LOGGER.fine("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOGGER.fine("Printing Repair Material Information:\n" + managment.repairMaterialInformationToString());
			
//			
//			Location loc = new Location(3.0, 3.0);
//			System.out.println((loc.calculateDistance(new Location(4.0,2.0)))*2000);
			
			managment.startClerks();
			managment.startGroupManager();
			
//			
//			
			managment.newShiftForClearks();
//			
			
//			System.out.println(managment.getNumberOfMaintencePerons());
			
		}
		
		
		
		
		
		
		
		

	}

}
