package sif.technicalDepartment.equipment.scanning.devices.custom;

import sif.model.elements.basic.cell.Cell;
import sif.model.elements.basic.cell.CellContentType;
import sif.model.elements.basic.worksheet.Row;
import sif.model.elements.basic.worksheet.Worksheet;
import sif.model.elements.containers.AbstractElementList;
import sif.model.elements.containers.SimpleElementList;
import sif.model.elements.custom.BlankNonReferencedCell;
import sif.model.inspection.SpreadsheetInventory;
import sif.technicalDepartment.equipment.scanning.ElementScanner;

public class BlankNonReferencedCellScanner extends
		ElementScanner<BlankNonReferencedCell> {

	public BlankNonReferencedCellScanner(
			Class<BlankNonReferencedCell> scannedElementClass) {
		super(scannedElementClass);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AbstractElementList<BlankNonReferencedCell> scan(
			SpreadsheetInventory spreadsheetInventory) {
		AbstractElementList<BlankNonReferencedCell> blankNRCells = new SimpleElementList<BlankNonReferencedCell>(
				BlankNonReferencedCell.class);
		for (Worksheet worksheet : spreadsheetInventory.getSpreadsheet()
				.getWorksheets()) {
			for (Row row : worksheet.getRows()) {
				for (Cell cell : row.getCells()) {
					if (cell.getCellContentType() == CellContentType.BLANK
							& cell.getIncomingReferences().isEmpty()) {
						BlankNonReferencedCell blankNRCell = new BlankNonReferencedCell();
						blankNRCell.setCell(cell);
						blankNRCells.add(blankNRCell);
					}
				}

			}
		}

		return blankNRCells;
	}

}
