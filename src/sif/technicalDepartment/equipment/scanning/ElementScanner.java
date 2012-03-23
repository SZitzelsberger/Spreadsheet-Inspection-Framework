package sif.technicalDepartment.equipment.scanning;

import sif.model.elements.AbstractElement;
import sif.model.elements.containers.AbstractElementList;
import sif.model.inspection.SpreadsheetInventory;

public abstract class ElementScanner<ScannedElementClass extends AbstractElement> {

	protected static Integer scanPriority = 0;
	private Class<ScannedElementClass> scannedElementClass;

	public ElementScanner(Class<ScannedElementClass> scannedElementClass) {
		this.scannedElementClass = scannedElementClass;
	}

	public Class<ScannedElementClass> getScannedElementClass() {
		return this.scannedElementClass;
	}

	public Integer getScanPriority() {
		return scanPriority;
	}

	public abstract AbstractElementList<ScannedElementClass> scan(
			SpreadsheetInventory spreadsheetInventory);

}