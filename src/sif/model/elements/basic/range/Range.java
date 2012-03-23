package sif.model.elements.basic.range;

import java.util.ArrayList;

import sif.model.elements.BasicAbstractElement;
import sif.model.elements.basic.address.AbstractAddress;
import sif.model.elements.basic.address.RangeAddress;
import sif.model.elements.basic.cell.Cell;
import sif.model.elements.basic.reference.AbstractReference;
import sif.model.elements.basic.reference.CellReference;
import sif.model.elements.basic.reference.IReferencedElement;
import sif.model.elements.basic.reference.RangeReference;
import sif.model.elements.basic.worksheet.Worksheet;

import com.google.common.collect.TreeBasedTable;

/**
 * Representation of a range.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class Range extends BasicAbstractElement implements IReferencedElement,
		IRangeElement {

	private TreeBasedTable<Integer, Integer, Cell> cellsInRange;

	private RangeAddress address;

	private ArrayList<AbstractReference> incomingReferences;

	public Range() {
		this.cellsInRange = TreeBasedTable.create();
		this.incomingReferences = new ArrayList<AbstractReference>();
	}

	public Range(RangeAddress addres) {
		this();
		this.address = addres;
	}

	/**
	 * Adds the given cell to the range.
	 * 
	 * @param cell
	 *            The given cell.
	 */
	public void add(Cell cell) {

		// Create new range address if needed.
		if (address == null) {
			address = new RangeAddress();
		}
		cellsInRange.put(cell.getColumnIndex(), cell.getRowIndex(), cell);

		// Update if needed.
		if (address.getWorksheet() == null) {
			address.setWorksheet(cell.getWorksheet());
		}
		if (address.getLeftmostColumnIndex() == null
				|| address.getLeftmostColumnIndex() > cell.getColumnIndex()) {
			address.setLeftmostColumnIndex(cell.getColumnIndex());
		}
		if (address.getRightmostColumnIndex() == null
				|| address.getRightmostColumnIndex() < cell.getColumnIndex()) {
			address.setRightmostColumnIndex(cell.getColumnIndex());
		}

		if (address.getTopmostRowIndex() == null
				|| address.getTopmostRowIndex() > cell.getRowIndex()) {
			address.setTopmostRowIndex(cell.getRowIndex());
		}
		if (address.getBottommostRowIndex() == null
				|| address.getBottommostRowIndex() < cell.getRowIndex()) {
			address.setBottommostRowIndex(cell.getRowIndex());
		}

	}

	@Override
	public void addIncomingReference(AbstractReference reference) {
		if (reference instanceof RangeReference) {
			RangeReference rangeReference = (RangeReference) reference;
			this.incomingReferences.add(reference);

			for (com.google.common.collect.Table.Cell<Integer, Integer, Cell> tableCell : cellsInRange
					.cellSet()) {
				CellReference subreference = new CellReference();
				subreference.setReferencedElement(tableCell.getValue());
				subreference.setReferencingElement(rangeReference);
			}
		}
	}

	@Override
	public boolean equals(Object object) {
		boolean equals = false;
		if (object instanceof Range) {
			Range range = (Range) object;
			if (range.getWorksheet().equals(getWorksheet())) {

				if (range.getLeftmostColumnIndex() == getLeftmostColumnIndex()
						&& range.getRightmostColumnIndex() == getRightmostColumnIndex()
						&& range.getBottommostRowIndex() == getBottommostRowIndex()
						&& range.getTopmostRowIndex() == getTopmostRowIndex()) {
					equals = true;
				}
			}
		}
		return equals;

	}

	@Override
	public AbstractAddress getAbstractAddress() {
		return address;
	}

	public Integer getBottommostRowIndex() {
		return this.address.getBottommostRowIndex();
	}

	public int getHeight() {
		return getBottommostRowIndex() - getTopmostRowIndex() + 1;
	}

	@Override
	public ArrayList<AbstractReference> getIncomingReferences() {
		return incomingReferences;
	}

	public Integer getLeftmostColumnIndex() {
		return this.address.getLeftmostColumnIndex();
	}

	@Override
	public String getLocation() {
		return address.getFullAddress();
	}

	@Override
	public Range getRange() {
		return this;
	}

	public Integer getRightmostColumnIndex() {
		return this.address.getRightmostColumnIndex();
	}

	@Override
	public String getStringRepresentation() {
		return "Range[" + getValueAsString() + "]";
	}

	public Integer getTopmostRowIndex() {
		return this.address.getTopmostRowIndex();
	}

	@Override
	public String getValueAsString() {
		return address.getWorksheetAddress();
	}

	public int getWidth() {
		return getRightmostColumnIndex() - getLeftmostColumnIndex() + 1;
	}

	public Worksheet getWorksheet() {
		return this.address.getWorksheet();
	}

	public Boolean isSingleCell() {
		return this.address.isSingleCell();
	}
}