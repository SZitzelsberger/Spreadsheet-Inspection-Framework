package sif.technicalDepartment.equipment.testing.selection_devices.types;

import sif.model.elements.containers.AbstractElementList;
import sif.model.inspection.SpreadsheetInventory;

public abstract class AbstractSelectionDevice {
	public abstract AbstractElementList<?> selectFrom(
			SpreadsheetInventory paramSpreadsheetInventory);
}