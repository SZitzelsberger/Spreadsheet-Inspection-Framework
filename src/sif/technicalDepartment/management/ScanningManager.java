package sif.technicalDepartment.management;

import java.util.TreeMap;

import sif.model.elements.AbstractElement;
import sif.model.elements.basic.cell.Cell;
import sif.model.elements.basic.reference.AbstractReference;
import sif.model.elements.basic.spreadsheet.Spreadsheet;
import sif.model.elements.basic.tokencontainers.Formula;
import sif.model.elements.custom.BlankNonReferencedCell;
import sif.model.elements.custom.InputCell;
import sif.model.elements.custom.IntermediateCell;
import sif.model.elements.custom.OutputCell;
import sif.model.inspection.InspectionRequest;
import sif.technicalDepartment.equipment.scanning.ElementScanner;
import sif.technicalDepartment.equipment.scanning.ScanningFacility;
import sif.technicalDepartment.equipment.scanning.devices.basic.AbstractReferenceScanner;
import sif.technicalDepartment.equipment.scanning.devices.basic.CellScanner;
import sif.technicalDepartment.equipment.scanning.devices.basic.FormulaScanner;
import sif.technicalDepartment.equipment.scanning.devices.custom.BlankNonReferencedCellScanner;
import sif.technicalDepartment.equipment.scanning.devices.custom.InputCellScanner;
import sif.technicalDepartment.equipment.scanning.devices.custom.IntermediateCellScanner;
import sif.technicalDepartment.equipment.scanning.devices.custom.OutputCellScanner;

/***
 * The ScanningManager is in charge of scanning a {@link Spreadsheet} for
 * elements
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class ScanningManager {
	private TreeMap<ElementScanner<?>, Integer> registeredScannerList = new TreeMap<ElementScanner<?>, Integer>(
			new ElementScannerComparator());

	public ScanningManager() {
		// Register element scanners
		register(new CellScanner(Cell.class));
		register(new FormulaScanner(Formula.class));
		register(new BlankNonReferencedCellScanner(BlankNonReferencedCell.class));
		register(new InputCellScanner(InputCell.class));
		register(new IntermediateCellScanner(IntermediateCell.class));
		register(new OutputCellScanner(OutputCell.class));
		register(new AbstractReferenceScanner(AbstractReference.class));
	}

	/**
	 * Gets a list of all registered element scanners.
	 * 
	 * @return A list of all registered element scanners.
	 */
	public TreeMap<ElementScanner<?>, Integer> getScannerList() {
		return registeredScannerList;
	}

	/**
	 * Registers the given element scanner with the scanning manager.
	 * 
	 * @param elementScanner
	 *            The given element scanner.
	 */
	public <ElementType extends AbstractElement> void register(
			ElementScanner<ElementType> elementScanner) {
		registeredScannerList.put(elementScanner,
				elementScanner.getScanPriority());

	}

	/***
	 * Scans the spreadsheet from the given inspection request for the
	 * occurrences of those elements for which {@link ElementScanner}s have been
	 * registered.
	 * 
	 * @param inspectionRequest
	 *            The given inspection request.
	 */
	public void scanElementsFor(InspectionRequest inspectionRequest) {
		// Create new scanning facility.
		ScanningFacility scanFacility = new ScanningFacility();

		// Add registered element scanners to the scanning facility.
		for (ElementScanner<?> scanner : registeredScannerList.keySet()) {
			scanFacility.add(scanner);
		}
		// Run all element scanners.
		scanFacility.scanElementsFor(inspectionRequest);

	}

}