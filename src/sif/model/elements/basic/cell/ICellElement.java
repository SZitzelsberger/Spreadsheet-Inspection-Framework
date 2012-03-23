package sif.model.elements.basic.cell;

import sif.model.elements.IElement;

/**
 * Interface for custom elements to indicate that they can be treated as a cell.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public interface ICellElement extends IElement {
	public Cell getCell();
}