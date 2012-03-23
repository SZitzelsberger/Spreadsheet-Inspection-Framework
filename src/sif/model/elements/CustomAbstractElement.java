package sif.model.elements;

/***
 * A custom element is a user-defined element of a spreadsheet, which typically
 * cannot be represented in a spreadsheet language. It is therefore either an
 * aggregation or specialization of a {@link BasicAbstractElement}.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public abstract class CustomAbstractElement extends AbstractElement {
	protected static String elementName = "Undefined name";
	protected static String elementDescritpion = "Undefined description";

	/**
	 * Gets the description of this element class.
	 * 
	 * @return The description of this element class.
	 */
	public static String getElementDescription() {
		return elementDescritpion;
	}

	/**
	 * Gets the name of this element class.
	 * 
	 * @return the name of this element class.
	 */
	public static String getElementName() {
		return elementName;
	}

	/**
	 * Gets the element upon which this custom element is based.
	 * 
	 * @return The base element.
	 */
	public abstract BasicAbstractElement getBaseElement();
}