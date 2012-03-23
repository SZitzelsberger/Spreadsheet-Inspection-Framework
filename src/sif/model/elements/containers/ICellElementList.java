package sif.model.elements.containers;

import sif.model.elements.basic.cell.Cell;
import sif.model.elements.basic.cell.ICellElement;

/**
 * A custom element list to store {@link ICellElement}s.
 * 
 * @author Sebastian Zitzelsberger
 * 
 * @param <ElementClass>
 *            The class of the elements that can be stored in the list.
 */
public class ICellElementList<ElementClass extends ICellElement> extends
		SimpleElementList<ElementClass> {

	public ICellElementList(Class<ElementClass> elementClass) {
		super(elementClass);
	}

	public ElementClass getElementFor(Cell cell) {
		ElementClass element = null;
		for (ElementClass containedElement : getElements()) {
			if (containedElement.getCell().equals(cell)) {
				element = containedElement;
				break;
			}
		}
		return element;
	}

}
