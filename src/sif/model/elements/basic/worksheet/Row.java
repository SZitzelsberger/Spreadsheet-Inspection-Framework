package sif.model.elements.basic.worksheet;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

import sif.model.elements.BasicAbstractElement;
import sif.model.elements.basic.cell.Cell;

/**
 * The representation of a row.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class Row extends BasicAbstractElement {
	private Integer index;
	TreeMap<Integer, Cell> cells;

	public Row() {
		cells = new TreeMap<Integer, Cell>();
	}

	/**
	 * Adds the given cell to the row.
	 * 
	 * @param cell
	 *            The given cell.
	 */
	public void add(Cell cell) {
		this.cells.put(cell.getColumnIndex(), cell);
	}

	/**
	 * Gets the cell at the given index. May return null.
	 * 
	 * @param columnIndex
	 *            The given index.
	 * @return The cell at the given index.
	 */
	public Cell getCellAt(Integer columnIndex) {
		Cell cell = null;
		if (cells.containsKey(columnIndex)) {
			cell = cells.get(columnIndex);
		}
		return cell;
	}

	public Iterator<Cell> getCellIterator() {
		return cells.values().iterator();
	}

	public Collection<Cell> getCells() {
		return this.cells.values();
	}

	public String getDescription() {
		return null;
	}

	public Integer getHighestColumnIndex() {
		return (Integer) this.cells.lastKey();
	}

	public Integer getIndex() {
		return this.index;
	}

	@Override
	public String getLocation() {
		return null;
	}

	public Integer getLowestColumnIndex() {
		return (Integer) this.cells.firstKey();
	}

	public Integer getNumberOfCells() {
		return Integer.valueOf(this.cells.size());
	}

	public String getPluralName() {
		return null;
	}

	@Override
	public String getStringRepresentation() {
		return "Row " + index;
	}

	@Override
	public String getValueAsString() {
		StringBuilder builder = new StringBuilder();

		for (Cell cell : cells.values()) {
			builder.append(cell.getStringRepresentation());
			builder.append("  ");
		}
		return builder.toString();
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

}