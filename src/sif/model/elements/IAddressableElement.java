package sif.model.elements;

import sif.model.elements.basic.address.AbstractAddress;

/***
 * Interface for elements that have an abstract address, e.g. are located within
 * a worksheet.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public interface IAddressableElement extends IElement {

	/***
	 * Gets the abstract address of this element.
	 * 
	 * @return The abstract address of this element.
	 */
	public AbstractAddress getAbstractAddress();

}
