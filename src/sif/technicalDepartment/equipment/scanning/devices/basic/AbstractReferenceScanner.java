package sif.technicalDepartment.equipment.scanning.devices.basic;

import sif.model.elements.basic.cell.Cell;
import sif.model.elements.basic.reference.AbstractReference;
import sif.model.elements.basic.worksheet.Row;
import sif.model.elements.basic.worksheet.Worksheet;
import sif.model.elements.containers.AbstractElementList;
import sif.model.elements.containers.SimpleElementList;
import sif.model.inspection.SpreadsheetInventory;
import sif.technicalDepartment.equipment.scanning.ElementScanner;

public class AbstractReferenceScanner extends ElementScanner<AbstractReference> {

	public AbstractReferenceScanner(Class<AbstractReference> scannedElementClass) {
		super(scannedElementClass);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AbstractElementList<AbstractReference> scan(
			SpreadsheetInventory spreadsheetInventory) {
		AbstractElementList<AbstractReference> abstractReferences = new SimpleElementList<AbstractReference>(
				AbstractReference.class);
		for (Worksheet worksheet : spreadsheetInventory.getSpreadsheet()
				.getWorksheets()) {
			for (Row row : worksheet.getRows()) {
				for (Cell cell : row.getCells()) {
					if (!cell.getOutgoingReferences().isEmpty()) {
						for (AbstractReference reference : cell
								.getOutgoingReferences()) {
							abstractReferences.add(reference);

						}

					}
				}

			}
		}
		return abstractReferences;
	}

}
