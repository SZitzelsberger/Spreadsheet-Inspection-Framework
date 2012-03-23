package sif.technicalDepartment.equipment.scanning.devices.custom;

import sif.model.elements.basic.cell.Cell;
import sif.model.elements.basic.worksheet.Row;
import sif.model.elements.basic.worksheet.Worksheet;
import sif.model.elements.containers.AbstractElementList;
import sif.model.elements.containers.ICellElementList;
import sif.model.elements.custom.InputCell;
import sif.model.inspection.SpreadsheetInventory;
import sif.technicalDepartment.equipment.scanning.ElementScanner;

public class InputCellScanner extends ElementScanner<InputCell> {

	public InputCellScanner() {
		super(InputCell.class);
	}

	public InputCellScanner(Class<InputCell> scannedElementClass) {
		super(scannedElementClass);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AbstractElementList<InputCell> scan(
			SpreadsheetInventory spreadsheetInventory) {

		ICellElementList<InputCell> inputCells = new ICellElementList<InputCell>(
				InputCell.class);
		for (Worksheet worksheet : spreadsheetInventory.getSpreadsheet()
				.getWorksheets()) {
			for (Row row : worksheet.getRows()) {
				for (Cell cell : row.getCells()) {

					if (cell.getOutgoingReferences().isEmpty()
							& !cell.getIncomingReferences().isEmpty()) {
						InputCell inputCell = new InputCell();
						inputCell.setCell(cell);
						inputCells.add(inputCell);
					}
				}

			}
		}
		return inputCells;
	}
}
