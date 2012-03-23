package sif.technicalDepartment.equipment.scanning.devices.basic;

import sif.model.elements.basic.cell.Cell;
import sif.model.elements.basic.tokencontainers.Formula;
import sif.model.elements.basic.worksheet.Row;
import sif.model.elements.basic.worksheet.Worksheet;
import sif.model.elements.containers.AbstractElementList;
import sif.model.elements.containers.SimpleElementList;
import sif.model.inspection.SpreadsheetInventory;
import sif.technicalDepartment.equipment.scanning.ElementScanner;

public class FormulaScanner extends ElementScanner<Formula> {

	public FormulaScanner(Class<Formula> scannedElementClass) {
		super(scannedElementClass);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AbstractElementList<Formula> scan(
			SpreadsheetInventory spreadsheetInventory) {
		AbstractElementList<Formula> formulaList = new SimpleElementList<Formula>(
				Formula.class);
		for (Worksheet worksheet : spreadsheetInventory.getSpreadsheet()
				.getWorksheets()) {
			for (Row row : worksheet.getRows()) {
				for (Cell cell : row.getCells()) {
					if (cell.containsFormula()) {
						formulaList.add(cell.getFormula());
					}
				}
			}

		}
		return formulaList;
	}

}
