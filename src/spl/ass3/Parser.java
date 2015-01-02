/**
 * 
 */
package spl.ass3;

import java.io.IOException;

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
	protected DocumentBuilderFactory factory;
	protected static DocumentBuilder builder;
	
	
	Parser(){
		this.factory = DocumentBuilderFactory.newInstance();
		try {
			this.builder = factory.newDocumentBuilder();
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void parseIntialData (String intitialDataFilename, Managment managment){
		try {
			Document doc = builder.parse(intitialDataFilename);
			Driver.LOGGER.info("Strting to Parse " + intitialDataFilename + "\n");
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
			
			
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
