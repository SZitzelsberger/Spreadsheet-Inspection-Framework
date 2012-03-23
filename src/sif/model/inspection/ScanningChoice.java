package sif.model.inspection;

import java.util.ArrayList;

import sif.model.elements.AbstractElement;
import sif.model.elements.IElement;
import sif.technicalDepartment.equipment.scanning.ElementScanner;

/***
 * A scanning choice is needed, to provide a means to chose from different
 * {@link ElementScanner}s for the same element class. The first scanner that is
 * added, will be set as the default one.
 * 
 * @author Sebastian Zitzelsberger
 * 
 * @param <ElementType>
 *            The element class for which to chose a element scanner.
 */
public class ScanningChoice<ElementType extends AbstractElement> {

	private ArrayList<ElementScanner<ElementType>> scanners;
	private ElementScanner<ElementType> chosenScanner;
	private Class<? extends IElement> elementClass;

	public ScanningChoice(Class<ElementType> elementClass) {
		this.elementClass = elementClass;
		scanners = new ArrayList<ElementScanner<ElementType>>();
	}

	/**
	 * Adds the given element scanner to the choice. The first scanner that is
	 * added, will be set as the default one.
	 * 
	 * @param scanner
	 *            The given scanner.
	 */
	public void addScanner(ElementScanner<ElementType> scanner) {
		if (this.scanners.isEmpty()) {
			chosenScanner = scanner;
		}
		this.scanners.add(scanner);

	}

	public ElementScanner<ElementType> getChosenScanner() {
		return chosenScanner;
	}

	public Class<? extends IElement> getElementClass() {
		return this.elementClass;
	}

	public ArrayList<ElementScanner<ElementType>> getScanners() {
		return scanners;
	}

	/**
	 * Sets the given scanner as the chosen scanner that shall be used to scan
	 * the elements.
	 * 
	 * @param chosenScanner
	 *            The given scanner.
	 */
	public void setChosenScanner(ElementScanner<ElementType> chosenScanner) {
		this.chosenScanner = chosenScanner;
	}
}