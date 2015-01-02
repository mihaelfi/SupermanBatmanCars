package spl.ass3;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;



public class Driver {
	
	private final static Logger LOGGER = Logger.getLogger(Driver.class.getName());
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

			String INITIAL_DATA_FILENAME = 					args[0];
			String ASSET_CONTENTS_REPAIR_DETAILS_FILENAME = args[1];
			String ASSETS_FILENAME = 						args[2];
			String CUSTOMER_GROUPS_FILENAME = 				args[3];
			
			
			String cmdLineArguments = "\nPrinting command line arguments...\n";
			for (int i = 0 ; i < args.length ; i++){
				cmdLineArguments = cmdLineArguments + args[i] + "\n";
//				
			}
			LOGGER.info(cmdLineArguments);
			
		}
		
		
		
		
		
		
		
		

	}

}
