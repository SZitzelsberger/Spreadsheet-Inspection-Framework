package sif.technicalDepartment.equipment.scanning;

import java.util.ArrayList;

import sif.model.inspection.InspectionRequest;

public class ScanningFacility {
	private ArrayList<ElementScanner<?>> scannerList = new ArrayList<ElementScanner<?>>();

	public void add(ElementScanner<?> scanner) {
		this.scannerList.add(scanner);
	}

	public void scanElementsFor(InspectionRequest inspectionRequest) {
		for (ElementScanner<?> scanner : scannerList) {
			inspectionRequest.getInventory().addElementList(
					scanner.scan(inspectionRequest.getInventory()));

		}
	}

}