package sif.technicalDepartment.equipment.scanning.devices.custom;

import sif.model.elements.basic.cell.Cell;
import sif.model.elements.basic.worksheet.Row;
import sif.model.elements.basic.worksheet.Worksheet;
import sif.model.elements.containers.AbstractElementList;
import sif.model.elements.containers.ICellElementList;
import sif.model.elements.custom.OutputCell;
import sif.model.inspection.SpreadsheetInventory;
import sif.technicalDepartment.equipment.scanning.ElementScanner;

public class OutputCellScanner extends ElementScanner<OutputCell> {

	public OutputCellScanner(Class<OutputCell> scannedElementClass) {
		super(scannedElementClass);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AbstractElementList<OutputCell> scan(
			SpreadsheetInventory spreadsheetInventory) {

		ICellElementList<OutputCell> outputCells = new ICellElementList<OutputCell>(
				OutputCell.class);
		for (Worksheet worksheet : spreadsheetInventory.getSpreadsheet()
				.getWorksheets()) {
			for (Row row : worksheet.getRows()) {
				for (Cell cell : row.getCells()) {

					if ((cell.containsFormula())
							& cell.getIncomingReferences().isEmpty()) {
						OutputCell outputCell = new OutputCell();
						outputCell.setCell(cell);
						outputCells.add(outputCell);
					}
				}

			}
		}
		return outputCells;
	}

}
