package sif.technicalDepartment.equipment.testing.facilities.types;

import sif.model.elements.IElement;
import sif.model.elements.containers.AbstractElementList;
import sif.model.violations.lists.ViolationList;
import sif.technicalDepartment.equipment.testing.instruments.types.AbstractTestInstrument;
import sif.technicalDepartment.equipment.testing.selection_devices.types.AbstractSelectionDevice;

public class CompositeTestFacility extends AbstractTestFacility {

	Class<? extends IElement> elementClass;
	AbstractElementList<?> operands;
	AbstractSelectionDevice abstractSelectionDevice;
	AbstractTestInstrument<? extends IElement> abstractTestInstrument;

	public AbstractTestInstrument<? extends IElement> getAbstractTestInstrument() {
		return this.abstractTestInstrument;
	}

	public AbstractSelectionDevice getElementGroupSelector() {
		return this.abstractSelectionDevice;
	}

	@Override
	public ViolationList run() {
		ViolationList violationList = new ViolationList(getTestedPolicyRule());

		this.operands = this.abstractSelectionDevice.selectFrom(getTestBay()
				.getInspection().getInventory());
		for (IElement element : this.operands.getElements())
			if (this.abstractTestInstrument.testIElement(element) == false) {
				violationList.add(this.abstractTestInstrument
						.getCachedViolation());
			}
		return violationList;
	}

	public void setAbstractTestInstrument(
			AbstractTestInstrument<? extends IElement> abstractTestInstrument) {
		this.abstractTestInstrument = abstractTestInstrument;
	}

	public void setElementGroupSelector(
			AbstractSelectionDevice abstractSelectionDevice) {
		this.abstractSelectionDevice = abstractSelectionDevice;
	}
}