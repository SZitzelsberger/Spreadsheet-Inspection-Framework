package sif.technicalDepartment.equipment.testing.selection_devices.implementations;

import sif.model.elements.AbstractElement;
import sif.model.elements.containers.AbstractElementList;
import sif.model.inspection.SpreadsheetInventory;
import sif.technicalDepartment.equipment.testing.selection_devices.types.AbstractSelectionDevice;

public class SelectionDevice<ElementType extends AbstractElement> extends
		AbstractSelectionDevice {
	private ElementType element;

	public AbstractElementList<?> selectFrom(SpreadsheetInventory inventory) {
		return inventory.getListFor(this.element.getClass());
	}
}