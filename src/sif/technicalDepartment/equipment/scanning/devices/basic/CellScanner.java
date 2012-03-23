package sif.technicalDepartment.equipment.scanning.devices.basic;

import sif.model.elements.basic.cell.Cell;
import sif.model.elements.basic.worksheet.Row;
import sif.model.elements.basic.worksheet.Worksheet;
import sif.model.elements.containers.AbstractElementList;
import sif.model.elements.containers.SimpleElementList;
import sif.model.inspection.SpreadsheetInventory;
import sif.technicalDepartment.equipment.scanning.ElementScanner;

public class CellScanner extends ElementScanner<Cell> {

	public CellScanner(Class<Cell> scannedElementClass) {
		super(scannedElementClass);
	}

	@Override
	public AbstractElementList<Cell> scan(
			SpreadsheetInventory spreadsheetInventory) {
		AbstractElementList<Cell> cellList = new SimpleElementList<Cell>(
				Cell.class);
		for (Worksheet worksheet : spreadsheetInventory.getSpreadsheet()
				.getWorksheets()) {
			for (Row row : worksheet.getRows()) {
				for (Cell cell : row.getCells()) {
					cellList.add(cell);
				}
			}

		}

		return cellList;
	}

}
