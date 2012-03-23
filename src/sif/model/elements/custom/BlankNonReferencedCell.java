package sif.model.elements.custom;

import sif.model.elements.BasicAbstractElement;
import sif.model.elements.CustomAbstractElement;
import sif.model.elements.basic.cell.Cell;
import sif.model.elements.basic.cell.ICellElement;

/**
 * A blank non-referenced cell is a cell that has no content and is not
 * referenced by any other cell.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class BlankNonReferencedCell extends CustomAbstractElement implements
		ICellElement {

	private Cell cell;

	@Override
	public BasicAbstractElement getBaseElement() {
		return cell;
	}

	@Override
	public Cell getCell() {
		return cell;
	}

	@Override
	public String getLocation() {
		return cell.getLocation();
	}

	@Override
	public String getStringRepresentation() {
		return cell.getStringRepresentation();
	}

	@Override
	public String getValueAsString() {
		return cell.getValueAsString();
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

}
