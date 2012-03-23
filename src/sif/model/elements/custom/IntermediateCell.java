package sif.model.elements.custom;

import sif.model.elements.BasicAbstractElement;
import sif.model.elements.CustomAbstractElement;
import sif.model.elements.basic.cell.Cell;
import sif.model.elements.basic.cell.ICellElement;
import sif.model.elements.basic.reference.IReferencedElement;
import sif.model.elements.basic.reference.IReferencingElement;

/**
 * An intermediate cell has at least one outgoing and at least one incoming
 * reference.
 * 
 * @see {@link IReferencingElement} @see {@link IReferencedElement};
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class IntermediateCell extends CustomAbstractElement implements
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
