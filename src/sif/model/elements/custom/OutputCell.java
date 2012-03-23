package sif.model.elements.custom;

import sif.model.elements.BasicAbstractElement;
import sif.model.elements.CustomAbstractElement;
import sif.model.elements.basic.cell.Cell;
import sif.model.elements.basic.cell.ICellElement;
import sif.model.elements.basic.reference.IReferencedElement;
import sif.model.elements.basic.reference.IReferencingElement;

/***
 * An OutputCell is a custom element that represents a {@link Cell} that has
 * outgoing references but no incoming references. @see
 * {@link IReferencingElement} and @see {@link IReferencedElement};
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class OutputCell extends CustomAbstractElement implements ICellElement {

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
