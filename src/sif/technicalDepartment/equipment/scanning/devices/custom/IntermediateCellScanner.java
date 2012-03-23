package sif.technicalDepartment.equipment.scanning.devices.custom;

import sif.model.elements.basic.cell.Cell;
import sif.model.elements.basic.worksheet.Row;
import sif.model.elements.basic.worksheet.Worksheet;
import sif.model.elements.containers.AbstractElementList;
import sif.model.elements.containers.ICellElementList;
import sif.model.elements.custom.IntermediateCell;
import sif.model.inspection.SpreadsheetInventory;
import sif.technicalDepartment.equipment.scanning.ElementScanner;

public class IntermediateCellScanner extends ElementScanner<IntermediateCell> {

	public IntermediateCellScanner(Class<IntermediateCell> scannedElementClass) {
		super(scannedElementClass);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AbstractElementList<IntermediateCell> scan(
			SpreadsheetInventory spreadsheetInventory) {
		AbstractElementList<IntermediateCell> intermediateCellList = new ICellElementList<IntermediateCell>(
				IntermediateCell.class);
		for (Worksheet worksheet : spreadsheetInventory.getSpreadsheet()
				.getWorksheets()) {
			for (Row row : worksheet.getRows()) {
				for (Cell cell : row.getCells()) {

					if (!cell.getOutgoingReferences().isEmpty()
							& !cell.getIncomingReferences().isEmpty()) {
						IntermediateCell intermediateCell = new IntermediateCell();
						intermediateCell.setCell(cell);
						intermediateCellList.add(intermediateCell);
					}
				}

			}
		}

		return intermediateCellList;
	}

}
