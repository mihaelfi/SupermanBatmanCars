package spl.ass3;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;



public class Driver {
	
	protected final static Logger LOGGER = Logger.getLogger(Driver.class.getName());
	static private FileHandler fileTxt;
	static private SimpleFormatter formatterTxt;


 


	public static void main(String[] args) throws SecurityException, IOException {
		LOGGER.setLevel(Level.INFO);
		fileTxt = new FileHandler("Logging.txt");
		
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
			
			
		}
		
		
		
		
		
		
		
		

	}

}
