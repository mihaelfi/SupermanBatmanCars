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
			NodeList toolsList = DocElements.getElementsByTagName("Tool");
			for (int i = 0 ; i < toolsList.getLength(); i++){
				Element tools = (Element) toolsList.item(i);
				NodeList toolName = tools.getElementsByTagName("Name");
				String toolNameString = toolName.item(0).getTextContent();
				NodeList toolQuantity = tools.getElementsByTagName("Quantity");
				String toolQuantityString = toolQuantity.item(0).getTextContent();
				Driver.LOGGER.info("The " + i + " Tool name is " + toolNameString + " And the quantity is: " + toolQuantityString + "\n");
				
				int toolQuantityInt = Integer.parseInt(toolQuantityString);
				
				RepairTool toolToInsert = new RepairTool(toolNameString, toolQuantityInt);
				
				managment.warehouse.insertToolFromParsing(toolToInsert);
				
				
				
			}
			
			Driver.LOGGER.info(managment.warehouse.warehouseToolstoString());
			
			Driver.LOGGER.info("Finished parsing tools.");
			
			Driver.LOGGER.info("Parsing the Materials ...");
			
			NodeList materialList = DocElements.getElementsByTagName("Material");
			for (int i = 0 ; i < materialList.getLength(); i++){
				Element materials = (Element) materialList.item(i);
				NodeList materialName = materials.getElementsByTagName("Name");
				String materialNameString = materialName.item(0).getTextContent();
				NodeList materialQuantity = materials.getElementsByTagName("Quantity");
				String materialQuantityString = materialQuantity.item(0).getTextContent();
				Driver.LOGGER.info("The " + i + " Tool name is " + materialNameString + " And the quantity is: " + materialQuantityString + "\n");
				
				int materialQuantityInt = Integer.parseInt(materialQuantityString);
				
				RepairMaterial materialToInsert = new RepairMaterial(materialNameString, materialQuantityInt);
				
				managment.warehouse.insertMaterialFromParsing(materialToInsert);
				
				
				
			}
			
			Driver.LOGGER.info(managment.warehouse.warehouseMaterialsstoString());
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
	            
	            Driver.LOGGER.info("Creating clerk: " + clerkDetails.toString());
	            
	            managment.addClerkDetails(clerkDetails);
	            
	            Driver.LOGGER.info(managment.warhouseClerkDetailsToString());
	            
	            Driver.LOGGER.info("Finished parsing " + intitialDataFilename);
				
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
					Driver.LOGGER.info("The " + i + " Tool name is " + toolNameString + " And the quantity is: " + toolQuantityString + "\n");
					
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
					Driver.LOGGER.info("The " + i + " Material name is " + materialNameString + " And the quantity is: " + materialQuantityString + "\n");
					
					int materialQuantityInt = Integer.parseInt(materialQuantityString);
					
					RepairMaterial materialToInsert = new RepairMaterial(materialNameString, materialQuantityInt);
					
					repairMaterialInformation.addRepairMaterialInformation(materialToInsert);
					
				}
				
				managment.addRepairToolInformation(repairToolInformation);
				managment.addRepairMaterialInformation(repairMaterialInformation);
			}
			
				Driver.LOGGER.info(managment.repairMaterialInformationCollection.toString());
				Driver.LOGGER.info(managment.repairToolInformationCollection.toString());
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
				Driver.LOGGER.info("Asset Name is: " + assetNameString);
				
				NodeList assetType = asset.getElementsByTagName("Type");
				String assetTypeString = assetType.item(0).getTextContent();
				
				Driver.LOGGER.info("Asset Type is: " + assetTypeString);
				
				NodeList assetSize = asset.getElementsByTagName("Size");
				int assetSizeInt = Integer.parseInt(assetSize.item(0).getTextContent());
				
				Driver.LOGGER.info("Asset Size is: " + assetSizeInt);
				
				
				NodeList assetLocation = asset.getElementsByTagName("Location");
				Element assetLocationElement = (Element) assetLocation.item(0);
				
	            int assetLocationX = Integer.parseInt(assetLocationElement.getAttribute("x"));
	            int assetLocationY = Integer.parseInt(assetLocationElement.getAttribute("y"));
	            
	            Location assetLoactionObject = new Location(assetLocationX, assetLocationY);
	            
	            Driver.LOGGER.info("Asset Location is: " + assetLoactionObject.toString());
	            
	            NodeList assetCostPerNight = asset.getElementsByTagName("CostPerNight");
				int assetCostPerNightInt = Integer.parseInt(assetCostPerNight.item(0).getTextContent());
				
				Driver.LOGGER.info("Asset Cost Per Night is: " + assetCostPerNightInt);
				
				
			NodeList assetContents = asset.getElementsByTagName("AssetContent");
			for (int j = 0 ; j < assetContents.getLength() ; j++){
				
				
				
				Element assetContent = (Element) assetContents.item(j);
				NodeList assetContentName = assetContent.getElementsByTagName("Name");
				String assetContentNameString = assetContentName.item(0).getTextContent();
				
				NodeList assetContentRepairMultiplier = assetContent.getElementsByTagName("RepairMultiplier");
				double assetContentRepairMultiplierInt = Double.parseDouble(assetContentRepairMultiplier.item(0).getTextContent());
				
				AssetContent tempAssetContent = new AssetContent(assetContentNameString, 100, assetContentRepairMultiplierInt);
				Driver.LOGGER.info("Asset Content "+ tempAssetContent.toString() + " for Asset " + assetNameString + " was created.\n");
				assetContentArrayList.add(tempAssetContent);
				
				
				
			}
			
			Asset tempAsset = new Asset(assetNameString, assetTypeString, assetLoactionObject, new ArrayList<AssetContent>(assetContentArrayList) , 0,assetCostPerNightInt , assetSizeInt);
			
			Driver.LOGGER.info("The Final Asset is: " + tempAsset.toString());
			
			assetsToPutInManagment.addAsset(tempAsset);
			assetContentArrayList.clear();
				
			}
			
			Driver.LOGGER.info("The Assetes we are going to put in managment are: " + assetsToPutInManagment.toString());
			managment.addAssets(assetsToPutInManagment);
			Driver.LOGGER.info("Lets check that the Assets got into managment ok...\n " + managment.assetsToString());
				
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
