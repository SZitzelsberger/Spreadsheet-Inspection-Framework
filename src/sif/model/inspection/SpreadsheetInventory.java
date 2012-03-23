package sif.model.inspection;

import java.io.File;
import java.util.Collection;
import java.util.TreeMap;

import sif.model.elements.AbstractElement;
import sif.model.elements.basic.spreadsheet.Spreadsheet;
import sif.model.elements.containers.AbstractElementList;
import sif.model.elements.containers.SimpleElementList;

/***
 * Extended model class to represent spreadsheets.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class SpreadsheetInventory {
	private TreeMap<String, AbstractElementList<?>> elementLists = new TreeMap<String, AbstractElementList<?>>();

	private Spreadsheet spreadsheet;
	private File spreadsheetFile;

	/***
	 * Adds the given list of spreadsheet elements to the inventory. Only one
	 * list per element class can be stored.
	 * 
	 * @param list
	 *            The given list.
	 */
	public void addElementList(AbstractElementList<?> list) {
		this.elementLists.put(list.getElementClass().getCanonicalName(), list);
	}

	/***
	 * Clears all scanned elements from the spreadsheet inventory.
	 */
	public void clearScannedElements() {
		elementLists.clear();
	}

	/***
	 * Gets the list of all element list that have been added to the inventory.
	 * 
	 * @return The list of element lists.
	 */
	public Collection<AbstractElementList<?>> getElementLists() {
		return this.elementLists.values();
	}

	public <Type extends AbstractElement> AbstractElementList<Type> getListFor(
			Class<Type> elementClass) {
		@SuppressWarnings("unchecked")
		AbstractElementList<Type> elementList = (AbstractElementList<Type>) this.elementLists
				.get(elementClass.getCanonicalName());
		if (elementList == null) {
			elementList = new SimpleElementList<Type>(elementClass);
			this.elementLists.put(elementList.getElementClass()
					.getCanonicalName(), (AbstractElementList<?>) elementList);
		}

		return elementList;
	}

	public Spreadsheet getSpreadsheet() {
		return this.spreadsheet;
	}

	public File getSpreadsheetFile() {
		return spreadsheetFile;
	}

	public void setSpreadsheet(Spreadsheet spreadsheet) {
		this.spreadsheet = spreadsheet;
	}

	public void setSpreadsheetFile(File spreadsheetFile) {
		this.spreadsheetFile = spreadsheetFile;
	}
}