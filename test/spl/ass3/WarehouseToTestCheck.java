package spl.ass3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WarehouseToTestCheck {
	
	WarehouseToTest testWarehouse;

	RepairMaterial material1 = new RepairMaterial("Wood", 5);
	RepairMaterial material2 = new RepairMaterial("Stone", 3);
	RepairMaterial material3 = new RepairMaterial("Concreate", 2);
	RepairTool tool1 = new RepairTool("Hammer", 3);
	RepairTool tool2 = new RepairTool("Screwdriver", 7);
	RepairTool tool3 = new RepairTool("Leatherman", 4);

	@Before
	public void setUp() throws Exception {
		// making sure we are working with a clean warehouse!
		testWarehouse = new WarehouseToTest();
		
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testInsertToolFromParsing() {
		assertEquals(this.testWarehouse.getNumberOfToolInWarehouse(tool1.getToolName()),0);
		testWarehouse.insertToolFromParsing(tool1);
		assertEquals(this.testWarehouse.getNumberOfToolInWarehouse(tool1.getToolName()),tool1.getNumberOfToolsInWareHouse());
		testWarehouse.insertToolFromParsing(tool2);
		assertEquals(this.testWarehouse.getNumberOfToolInWarehouse(tool2.getToolName()),tool2.getNumberOfToolsInWareHouse());
		
		
	}

	@Test
	public void testWarehouseToTest() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertMaterialFromParsing() {
		assertEquals(this.testWarehouse.getNumberOfMaterialInWarehouse(material1.getMaterialName()),0);
		testWarehouse.insertMaterialFromParsing(material1);
		assertEquals(this.testWarehouse.getNumberOfMaterialInWarehouse(material1.getMaterialName()),material1.getNumberOfMaterialInWarehouse());
		testWarehouse.insertMaterialFromParsing(material2);
		assertEquals(this.testWarehouse.getNumberOfMaterialInWarehouse(material2.getMaterialName()),material2.getNumberOfMaterialInWarehouse());
	}

	@Test
	public void testGetRepairTool() {
		testWarehouse.insertToolFromParsing(tool1);
		int currentNumberOfTools = tool1.getNumberOfToolsInWareHouse();
		assertEquals(this.testWarehouse.getNumberOfToolInWarehouse(tool1.getToolName()),currentNumberOfTools);
		testWarehouse.getRepairTool(tool1.getToolName());
		assertEquals(this.testWarehouse.getNumberOfToolInWarehouse(tool1.getToolName()),currentNumberOfTools - 1);
		
	}

	@Test
	public void testReturnRepairTool() {
		testWarehouse.insertToolFromParsing(tool2);
		int currentNumberOfTools = tool2.getNumberOfToolsInWareHouse();
		assertEquals(this.testWarehouse.getNumberOfToolInWarehouse(tool2.getToolName()),currentNumberOfTools);
		testWarehouse.returnRepairTool(tool2.getToolName());
		assertEquals(this.testWarehouse.getNumberOfToolInWarehouse(tool2.getToolName()),currentNumberOfTools + 1);
		testWarehouse.returnRepairTool(tool2.getToolName());
		assertEquals(this.testWarehouse.getNumberOfToolInWarehouse(tool2.getToolName()),currentNumberOfTools + 2);
	}

	@Test
	public void testGetRepairMaterial() {
		testWarehouse.insertMaterialFromParsing(material1);
		int currentNumberOfMaterials = material1.getNumberOfMaterialInWarehouse();
		assertEquals(this.testWarehouse.getNumberOfMaterialInWarehouse(material1.getMaterialName()),currentNumberOfMaterials);
		testWarehouse.getRepairTool(tool1.getToolName());
		assertEquals(this.testWarehouse.getNumberOfMaterialInWarehouse(material1.getMaterialName()),currentNumberOfMaterials - 1);
	}

	@Test
	public void testGetNumberOfToolInWarehouse() {
		testWarehouse.insertToolFromParsing(tool1);
		int currentNumberOfMaterials = tool1.getNumberOfToolsInWareHouse();
		assertEquals(this.testWarehouse.getNumberOfToolInWarehouse(tool1.getToolName()),currentNumberOfMaterials);
		testWarehouse.returnRepairTool(tool1.getToolName());
		assertEquals(this.testWarehouse.getNumberOfToolInWarehouse(tool1.getToolName()),currentNumberOfMaterials + 1);
		testWarehouse.returnRepairTool(tool1.getToolName());
		assertEquals(this.testWarehouse.getNumberOfToolInWarehouse(tool1.getToolName()),currentNumberOfMaterials + 2);
		testWarehouse.getRepairTool(tool1.getToolName());
		assertEquals(this.testWarehouse.getNumberOfToolInWarehouse(tool1.getToolName()),currentNumberOfMaterials + 1);
	}

	@Test
	public void testGetNumberOfMaterialInWarehouse() {
		int currentNumberOfMaterials = testWarehouse.getNumberOfMaterialInWarehouse(material1.getMaterialName());
		assertEquals(0,currentNumberOfMaterials);
		testWarehouse.insertMaterialFromParsing(material1);
		currentNumberOfMaterials = currentNumberOfMaterials + material1.getNumberOfMaterialInWarehouse();
		assertEquals(this.testWarehouse.getNumberOfMaterialInWarehouse(material1.getMaterialName()),currentNumberOfMaterials);
		testWarehouse.getRepairMaterial(material1.getMaterialName());
		assertEquals(this.testWarehouse.getNumberOfMaterialInWarehouse(material1.getMaterialName()),currentNumberOfMaterials - 1);
		
	}
	
	

}
