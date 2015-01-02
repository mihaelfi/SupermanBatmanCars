package spl.ass3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class WarehouseToTestCheck.
 */
public class WarehouseToTestCheck {
	
	/** The test warehouse. */
	WarehouseToTest testWarehouse;

	/** The material1. */
	RepairMaterial material1 = new RepairMaterial("Wood", 5);
	
	/** The material2. */
	RepairMaterial material2 = new RepairMaterial("Stone", 3);
	
	/** The material3. */
	RepairMaterial material3 = new RepairMaterial("Concreate", 2);
	
	/** The tool1. */
	RepairTool tool1 = new RepairTool("Hammer", 3);
	
	/** The tool2. */
	RepairTool tool2 = new RepairTool("Screwdriver", 7);
	
	/** The tool3. */
	RepairTool tool3 = new RepairTool("Leatherman", 4);

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		// making sure we are working with a clean warehouse!
		testWarehouse = new WarehouseToTest();
		
		
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
		
	}

	/**
	 * Test insert tool from parsing.
	 */
	@Test
	public void testInsertToolFromParsing() {
		assertEquals(this.testWarehouse.getNumberOfToolInWarehouse(tool1.getToolName()),0);
		testWarehouse.insertToolFromParsing(tool1);
		assertEquals(this.testWarehouse.getNumberOfToolInWarehouse(tool1.getToolName()),tool1.getNumberOfToolsInWareHouse());
		testWarehouse.insertToolFromParsing(tool2);
		assertEquals(this.testWarehouse.getNumberOfToolInWarehouse(tool2.getToolName()),tool2.getNumberOfToolsInWareHouse());
		
		
	}

	/**
	 * Test warehouse to test.
	 */
	@Test
	public void testWarehouseToTest() {
		fail("Not yet implemented");
	}

	/**
	 * Test insert material from parsing.
	 */
	@Test
	public void testInsertMaterialFromParsing() {
		assertEquals(this.testWarehouse.getNumberOfMaterialInWarehouse(material1.getMaterialName()),0);
		testWarehouse.insertMaterialFromParsing(material1);
		assertEquals(this.testWarehouse.getNumberOfMaterialInWarehouse(material1.getMaterialName()),material1.getNumberOfMaterialInWarehouse());
		testWarehouse.insertMaterialFromParsing(material2);
		assertEquals(this.testWarehouse.getNumberOfMaterialInWarehouse(material2.getMaterialName()),material2.getNumberOfMaterialInWarehouse());
	}

	/**
	 * Test get repair tool.
	 */
	@Test
	public void testGetRepairTool() {
		testWarehouse.insertToolFromParsing(tool1);
		int currentNumberOfTools = tool1.getNumberOfToolsInWareHouse();
		assertEquals(this.testWarehouse.getNumberOfToolInWarehouse(tool1.getToolName()),currentNumberOfTools);
		testWarehouse.getRepairTool(tool1.getToolName());
		assertEquals(this.testWarehouse.getNumberOfToolInWarehouse(tool1.getToolName()),currentNumberOfTools - 1);
		
	}

	/**
	 * Test return repair tool.
	 */
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

	/**
	 * Test get repair material.
	 */
	@Test
	public void testGetRepairMaterial() {
		testWarehouse.insertMaterialFromParsing(material1);
		int currentNumberOfMaterials = material1.getNumberOfMaterialInWarehouse();
		assertEquals(this.testWarehouse.getNumberOfMaterialInWarehouse(material1.getMaterialName()),currentNumberOfMaterials);
		testWarehouse.getRepairTool(tool1.getToolName());
		assertEquals(this.testWarehouse.getNumberOfMaterialInWarehouse(material1.getMaterialName()),currentNumberOfMaterials - 1);
	}

	/**
	 * Test get number of tool in warehouse.
	 */
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

	/**
	 * Test get number of material in warehouse.
	 */
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
