package sif.model.elements.basic.spreadsheet;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

import sif.model.elements.basic.address.CellAddress;
import sif.model.elements.basic.address.RangeAddress;
import sif.model.elements.basic.cell.Cell;
import sif.model.elements.basic.range.Range;
import sif.model.elements.basic.worksheet.Worksheet;

/**
 * The representation of a spreadsheet.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class Spreadsheet {

	private String name;
	private SpreadsheetProperties properties;
	private TreeMap<Integer, Worksheet> worksheets;

	public Spreadsheet() {
		worksheets = new TreeMap<Integer, Worksheet>();
	}

	public void add(Worksheet worksheet) {
		this.worksheets.put(worksheet.getIndex(), worksheet);
	}

	/**
	 * Gets the cell at the given cell address.
	 * 
	 * @param cellAddress
	 *            The given cell address.
	 * @return The cell at the given address.
	 */
	public Cell getCellFor(CellAddress cellAddress) {
		return cellAddress.getWorksheet().getCellAt(
				cellAddress.getColumnIndex(), cellAddress.getRowIndex());
	}

	public String getName() {
		return name;
	}

	public SpreadsheetProperties getProperties() {
		return this.properties;
	}

	public Range getRangeFor(RangeAddress rangeAddress) {
		return rangeAddress.getWorksheet().createRangeFor(rangeAddress);
	}

	/***
	 * Gets the the worksheet at the given index (1-based).
	 * 
	 * @param worksheetPosition
	 * @return
	 */
	public Worksheet getWorksheetAt(Integer worksheetIndex) {
		return this.worksheets.get(worksheetIndex);
	}

	/**
	 * Gets the worksheet whose name matches the given workhseet name.
	 * 
	 * @param worksheetName
	 *            The given worksheet name.
	 * @return The worksheet for the given name.
	 */
	public Worksheet getWorksheetFor(String worksheetName) {
		Worksheet worksheet = null;
		for (Worksheet sheet : this.worksheets.values()) {
			if (sheet.getName().equals(worksheetName)) {
				worksheet = sheet;
			}
		}
		return worksheet;
	}

	public Iterator<Worksheet> getWorksheetIterator() {
		return this.worksheets.values().iterator();
	}

	public Collection<Worksheet> getWorksheets() {
		return this.worksheets.values();
	}

	/**
	 * Creates new cells for all null cells within the given range address and
	 * adds them to the spreadsheet.
	 * 
	 * @param rangeAddress
	 *            The given range address.
	 */
	public void createBlankCellsForNullCellsIn(RangeAddress rangeAddress) {
		Worksheet worksheet = rangeAddress.getWorksheet();

		for (int r = rangeAddress.getTopmostRowIndex(); r <= rangeAddress
				.getBottommostRowIndex(); r++) {
			for (int c = rangeAddress.getLeftmostColumnIndex(); c <= rangeAddress
					.getRightmostColumnIndex(); c++) {
				Cell cell = worksheet.getCellAt(c, r);
				if (cell == null) {
					cell = new Cell();
					cell.setColumnIndex(c);
					cell.setRowIndex(r);
					cell.setWorksheet(worksheet);
					worksheet.add(cell);
				}
			}
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProperties(SpreadsheetProperties properties) {
		this.properties = properties;
	}

}