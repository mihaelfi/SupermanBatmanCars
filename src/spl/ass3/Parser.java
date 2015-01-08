/**
 * 
 */
package spl.ass3;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Michael Fildstein Id:309161594
 *
 */
public class Parser {
	protected static DocumentBuilderFactory factory;
	protected static DocumentBuilder builder;
	
	
//	Parser(){
//		this.factory = DocumentBuilderFactory.newInstance();
//		try {
//			this.builder = factory.newDocumentBuilder();
//			
//		} catch (ParserConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
	public static void initializeParser(){
		factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void parseIntialData (String intitialDataFilename, Managment managment){
		try {
			Document doc = builder.parse(intitialDataFilename);
			Driver.LOGGER.info("\n\nStrting to Parse " + intitialDataFilename + "\n");
			Driver.LOGGER.info("Parsing the tools ...");
			Element DocElements = (Element) doc.getDocumentElement();
			NodeList numberOfMaintenancePersons = DocElements.getElementsByTagName("NumberOfMaintenancePersons");
			int numOfMaintencePersons = Integer.parseInt(numberOfMaintenancePersons.item(0).getTextContent());
			
			NodeList totalNumberOfRentalRequests = DocElements.getElementsByTagName("TotalNumberOfRentalRequests");
			int totalNumberOfRentalRequestsInt = Integer.parseInt(totalNumberOfRentalRequests.item(0).getTextContent());
			
//			Element numberOfMaintenencePersons = (Element) numberOfMaintenancePersons.item(0);
			managment.setNumberOfMaintencePersons(numOfMaintencePersons);
			managment.setTotalNumberOfRentalRequestsInt(totalNumberOfRentalRequestsInt);
			
//			System.out.println("Number of maintence persons is:" + numOfMaintencePersons);
			NodeList toolsList = DocElements.getElementsByTagName("Tool");
			for (int i = 0 ; i < toolsList.getLength(); i++){
				Element tools = (Element) toolsList.item(i);
				NodeList toolName = tools.getElementsByTagName("Name");
				String toolNameString = toolName.item(0).getTextContent();
				NodeList toolQuantity = tools.getElementsByTagName("Quantity");
				String toolQuantityString = toolQuantity.item(0).getTextContent();
				Driver.LOGGER.fine("The " + i + " Tool name is " + toolNameString + " And the quantity is: " + toolQuantityString + "\n");
				
				int toolQuantityInt = Integer.parseInt(toolQuantityString);
				
				RepairTool toolToInsert = new RepairTool(toolNameString, toolQuantityInt);
				
				managment.getWarehouse().insertToolFromParsing(toolToInsert);
				
				
				
			}
			
			Driver.LOGGER.fine(managment.getWarehouse().warehouseToolstoString());
			
			Driver.LOGGER.info("Finished parsing tools.");
			
			Driver.LOGGER.info("Parsing the Materials ...");
			
			NodeList materialList = DocElements.getElementsByTagName("Material");
			for (int i = 0 ; i < materialList.getLength(); i++){
				Element materials = (Element) materialList.item(i);
				NodeList materialName = materials.getElementsByTagName("Name");
				String materialNameString = materialName.item(0).getTextContent();
				NodeList materialQuantity = materials.getElementsByTagName("Quantity");
				String materialQuantityString = materialQuantity.item(0).getTextContent();
				Driver.LOGGER.fine("The " + i + " Tool name is " + materialNameString + " And the quantity is: " + materialQuantityString + "\n");
				
				int materialQuantityInt = Integer.parseInt(materialQuantityString);
				
				RepairMaterial materialToInsert = new RepairMaterial(materialNameString, materialQuantityInt);
				
				managment.getWarehouse().insertMaterialFromParsing(materialToInsert);
				
				
				
			}
			
			Driver.LOGGER.fine(managment.getWarehouse().warehouseMaterialsstoString());
			Driver.LOGGER.info("Finished parsing materials.");
			
			Driver.LOGGER.info("Starting to parse clerks:");
			NodeList clerkList = DocElements.getElementsByTagName("Clerk");
			for (int i = 0 ; i < clerkList.getLength() ; i++){
				Element clerks = (Element) clerkList.item(i);
				NodeList clerkName = clerks.getElementsByTagName("Name");
				String clerkNameString = clerkName.item(0).getTextContent();
				NodeList clerkLocationList = clerks.getElementsByTagName("Location");
	            Element clerkLocationElement = (Element) clerkLocationList.item(0);
	            int x = Integer.parseInt(clerkLocationElement.getAttribute("x"));
	            int y = Integer.parseInt(clerkLocationElement.getAttribute("y"));
	            
	            Location clerkLocation = new Location(x,y);
	            
	            
	            ClerkDetails clerkDetails = new ClerkDetails(clerkNameString, clerkLocation);
	            
//	            RunnableClerk runnableClerk = new RunnableClerk(clerkDetails,  new ArrayList<RentalRequest>() , -1);
	            
	            Driver.LOGGER.fine("Creating clerk: " + clerkDetails.toString());
	            
	            managment.addClerkDetails(clerkDetails);
	            
	            Driver.LOGGER.fine(managment.clerkDetailsToString());
	            
	            Driver.LOGGER.fine("Finished parsing " + intitialDataFilename);
				
			}
			
			
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void parseAssetContentsRepairDetails (String assetContentsRepairDetailsFilename, Managment managment){
		Driver.LOGGER.info("\n\nParsing " + assetContentsRepairDetailsFilename);
		try {
			Document doc = builder.parse(assetContentsRepairDetailsFilename);
			Element DocElements = (Element) doc.getDocumentElement();
			NodeList assetContentList = DocElements.getElementsByTagName("AssetContent");
			for (int i = 0 ; i < assetContentList.getLength(); i++){
				Element assetContents = (Element) assetContentList.item(i);
				NodeList assetContentName = assetContents.getElementsByTagName("Name");
				String assetContentNameString = assetContentName.item(0).getTextContent();
				
				RepairToolInformation repairToolInformation = new RepairToolInformation(assetContentNameString);
				RepairMaterialInformation repairMaterialInformation = new RepairMaterialInformation(assetContentNameString);
				
				NodeList repairToolsInAssetContent = assetContents.getElementsByTagName("Tool");
				for (int j = 0 ; j < repairToolsInAssetContent.getLength() ; j++){
					
					Element tools = (Element) repairToolsInAssetContent.item(j);
					NodeList toolName = tools.getElementsByTagName("Name");
					String toolNameString = toolName.item(0).getTextContent();
					NodeList toolQuantity = tools.getElementsByTagName("Quantity");
					String toolQuantityString = toolQuantity.item(0).getTextContent();
					Driver.LOGGER.fine("The " + i + " Tool name is " + toolNameString + " And the quantity is: " + toolQuantityString + "\n");
					
					int toolQuantityInt = Integer.parseInt(toolQuantityString);
					
					RepairTool toolToInsert = new RepairTool(toolNameString, toolQuantityInt);
					
					
					repairToolInformation.addRepairToolInformation(toolToInsert);
					
				}
				NodeList repairMaterialsInAssetContent = assetContents.getElementsByTagName("Material");
				for (int j = 0 ; j < repairMaterialsInAssetContent.getLength() ; j++){
					Element materials = (Element) repairMaterialsInAssetContent.item(j);
					NodeList materialName = materials.getElementsByTagName("Name");
					String materialNameString = materialName.item(0).getTextContent();
					NodeList materialQuantity = materials.getElementsByTagName("Quantity");
					String materialQuantityString = materialQuantity.item(0).getTextContent();
					Driver.LOGGER.fine("The " + i + " Material name is " + materialNameString + " And the quantity is: " + materialQuantityString + "\n");
					
					int materialQuantityInt = Integer.parseInt(materialQuantityString);
					
					RepairMaterial materialToInsert = new RepairMaterial(materialNameString, materialQuantityInt);
					
					repairMaterialInformation.addRepairMaterialInformation(materialToInsert);
					
				}
				repairToolInformation.sortRepairTools();
				managment.addRepairToolInformation(repairToolInformation);
				managment.addRepairMaterialInformation(repairMaterialInformation);
			}
			
				Driver.LOGGER.fine(managment.getRepairMaterialInformationCollection().toString());
				Driver.LOGGER.fine(managment.getRepairToolInformationCollection().toString());
	            Driver.LOGGER.info("Finished parsing " + assetContentsRepairDetailsFilename);
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void parseAssets (String ASSETS_FILENAME, Managment managment){
		
		Driver.LOGGER.info("\n\nParsing " + ASSETS_FILENAME);
		ArrayList<AssetContent> assetContentArrayList = new ArrayList<AssetContent>();
		Assets assetsToPutInManagment = new Assets();
		try {
			Document doc = builder.parse(ASSETS_FILENAME);
			Element DocElements = (Element) doc.getDocumentElement();
			NodeList assetList = DocElements.getElementsByTagName("Asset");
			for (int i = 0 ; i < assetList.getLength(); i++){
				Element asset = (Element) assetList.item(i);
				NodeList assetName = asset.getElementsByTagName("Name");
				String assetNameString = assetName.item(0).getTextContent();
				Driver.LOGGER.fine("Asset Name is: " + assetNameString);
				
				NodeList assetType = asset.getElementsByTagName("Type");
				String assetTypeString = assetType.item(0).getTextContent();
				
				Driver.LOGGER.fine("Asset Type is: " + assetTypeString);
				
				NodeList assetSize = asset.getElementsByTagName("Size");
				int assetSizeInt = Integer.parseInt(assetSize.item(0).getTextContent());
				
				Driver.LOGGER.fine("Asset Size is: " + assetSizeInt);
				
				
				NodeList assetLocation = asset.getElementsByTagName("Location");
				Element assetLocationElement = (Element) assetLocation.item(0);
				
	            int assetLocationX = Integer.parseInt(assetLocationElement.getAttribute("x"));
	            int assetLocationY = Integer.parseInt(assetLocationElement.getAttribute("y"));
	            
	            Location assetLoactionObject = new Location(assetLocationX, assetLocationY);
	            
	            Driver.LOGGER.fine("Asset Location is: " + assetLoactionObject.toString());
	            
	            NodeList assetCostPerNight = asset.getElementsByTagName("CostPerNight");
				int assetCostPerNightInt = Integer.parseInt(assetCostPerNight.item(0).getTextContent());
				
				Driver.LOGGER.fine("Asset Cost Per Night is: " + assetCostPerNightInt);
				
				
				NodeList assetContents = asset.getElementsByTagName("AssetContent");
					for (int j = 0 ; j < assetContents.getLength() ; j++){
					
					
					
					Element assetContent = (Element) assetContents.item(j);
					NodeList assetContentName = assetContent.getElementsByTagName("Name");
					String assetContentNameString = assetContentName.item(0).getTextContent();
					
					NodeList assetContentRepairMultiplier = assetContent.getElementsByTagName("RepairMultiplier");
					double assetContentRepairMultiplierInt = Double.parseDouble(assetContentRepairMultiplier.item(0).getTextContent());
					
					AssetContent tempAssetContent = new AssetContent(assetContentNameString, 100, assetContentRepairMultiplierInt);
					Driver.LOGGER.fine("Asset Content "+ tempAssetContent.toString() + " for Asset " + assetNameString + " was created.\n");
					assetContentArrayList.add(tempAssetContent);
				
				
				
			}
			
			Asset tempAsset = new Asset(assetNameString, assetTypeString, assetLoactionObject, new ArrayList<AssetContent>(assetContentArrayList) ,"AVAILABLE",assetCostPerNightInt , assetSizeInt);
			
			Driver.LOGGER.fine("The Final Asset is: " + tempAsset.toString());
			tempAsset.sortAssetContents();
			assetsToPutInManagment.addAsset(tempAsset);
			assetContentArrayList.clear();
				
			}
			
			Driver.LOGGER.fine("The Assetes we are going to put in managment are: " + assetsToPutInManagment.toString());
			managment.addAssets(assetsToPutInManagment);
			Driver.LOGGER.fine("Lets check that the Assets got into managment ok...\n " + managment.assetsToString());
				
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

public static void parseCustomerGroups (String CUSTOMER_GROUPS_FILENAME, Managment managment){
		
		Driver.LOGGER.info("\n\nParsing " + CUSTOMER_GROUPS_FILENAME);
		ArrayList<RentalRequest> rentalRequestCollection = new ArrayList<RentalRequest>() ;
		ArrayList<Customer> customerCollection = new ArrayList<Customer>();
		String customerGroupDetailGroupManagerNameString;
		
		try {
			Document doc = builder.parse(CUSTOMER_GROUPS_FILENAME);
			Element DocElements = (Element) doc.getDocumentElement();
			NodeList customerGroupDetailsList = DocElements.getElementsByTagName("CustomerGroupDetails");
			for (int i = 0 ; i < customerGroupDetailsList.getLength(); i++){
				Element customerGroupDetail = (Element) customerGroupDetailsList.item(i);
				NodeList customerGroupDetailGroupManagerName = customerGroupDetail.getElementsByTagName("GroupManagerName");
				customerGroupDetailGroupManagerNameString = customerGroupDetailGroupManagerName.item(0).getTextContent();
				Driver.LOGGER.fine("Customer Group Manager Name is: " + customerGroupDetailGroupManagerNameString);
				
				NodeList customerList = customerGroupDetail.getElementsByTagName("Customer");
				NodeList rentalRequestsList = customerGroupDetail.getElementsByTagName("Request");
				for (int j = 0 ; j < customerList.getLength() ; j++ ){
					Element customer = (Element) customerList.item(j);
					NodeList customerName = customer.getElementsByTagName("Name");
					String customerNameString = customerName.item(0).getTextContent();
					Driver.LOGGER.fine("Customer Name is: " + customerNameString);
					
					NodeList customerVandalism = customer.getElementsByTagName("Vandalism");
					String customerVandalismString = customerVandalism.item(0).getTextContent();
					Driver.LOGGER.fine("Customer Vandalism type is: " + customerVandalismString);
					
					NodeList customerMinimumDamage = customer.getElementsByTagName("MinimumDamage");
					int customerMinimumDamageInt = Integer.parseInt(customerMinimumDamage.item(0).getTextContent());
					Driver.LOGGER.fine("Customer MinimumDamage is: " + customerMinimumDamageInt);
					
					NodeList customerMaximumDamage = customer.getElementsByTagName("MaximumDamage");
					int customerMaximumDamageInt = Integer.parseInt(customerMaximumDamage.item(0).getTextContent());
					Driver.LOGGER.fine("Customer Maximum Damage is: " + customerMaximumDamageInt);
					
					Customer tempCustomer = new Customer(customerNameString, customerVandalismString, customerMinimumDamageInt, customerMaximumDamageInt);
					Driver.LOGGER.fine("The Customer created is:\n" + tempCustomer.toString());
					
					
					customerCollection.add(tempCustomer);
				}
				
//				NodeList clerkLocationList = clerks.getElementsByTagName("Location");
//	            Element clerkLocationElement = (Element) clerkLocationList.item(0);
//	            int x = Integer.parseInt(clerkLocationElement.getAttribute("x"));
//	            int y = Integer.parseInt(clerkLocationElement.getAttribute("y"));
//	            
				
				for (int j = 0 ; j < rentalRequestsList.getLength() ; j++){
					Element rentalRequest = (Element) rentalRequestsList.item(j);
					
					int id = Integer.parseInt(rentalRequest.getAttribute("id"));
					
					NodeList rentalRequestType = rentalRequest.getElementsByTagName("Type");
					String rentalRequestTypeString = rentalRequestType.item(0).getTextContent();
					Driver.LOGGER.fine("Rental request type is: " + rentalRequestTypeString);
					
					NodeList rentalRequestSize = rentalRequest.getElementsByTagName("Size");
					int rentalRequestSizeInt = Integer.parseInt(rentalRequestSize.item(0).getTextContent());
					Driver.LOGGER.fine("Rental request size of asset is: " + rentalRequestSize);
					
					NodeList rentalRequestDuration = rentalRequest.getElementsByTagName("Duration");
					int rentalRequestDurationInt = Integer.parseInt(rentalRequestDuration.item(0).getTextContent());
					Driver.LOGGER.fine("Rental request duration is: " + rentalRequestDuration);
					
					
					RentalRequest tempRentalRequest = new RentalRequest(id, rentalRequestTypeString, rentalRequestSizeInt, rentalRequestDurationInt, "INCOMPLETE");
					
					Driver.LOGGER.fine("Rental request created is: " + tempRentalRequest.toString());
					
					rentalRequestCollection.add(tempRentalRequest);
				}
				
				CustomerGroupDetails customerGroupDetailsToManagment = new CustomerGroupDetails(new ArrayList<RentalRequest>(rentalRequestCollection),new ArrayList<Customer> (customerCollection), customerGroupDetailGroupManagerNameString);
				rentalRequestCollection.clear();
				customerCollection.clear();
				Driver.LOGGER.fine("\nThe Customer Group Details are: " + customerGroupDetailsToManagment.toString());
				managment.addCustomerGroupDetailsToCollection(customerGroupDetailsToManagment);
			}
			
			
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	

}
