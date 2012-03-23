package sif.model.elements.containers;

import java.util.ArrayList;

import sif.model.elements.IElement;

/**
 * An element list is a container for all {@link IElement}s. Custom element
 * lists for certain elements can be implemented by extending this class.
 * Furthermore SIF already provides a simple implementation that can be used for
 * storing any elements. {@see SimpleElementList}
 * 
 * @author Sebastian Zitzelsberger
 * 
 * @param <ElementClass>
 *            The class of the elements that can be stored in the list.
 */
public abstract class AbstractElementList<ElementClass extends IElement> {

	protected Class<ElementClass> elementClass;

	/**
	 * Creates an element list typed to the given element class. The instance of
	 * the element class is necessary due to Java's type erasure.
	 * 
	 * @param elementClass
	 *            The element class this element list is typed to.
	 */
	public AbstractElementList(Class<ElementClass> elementClass) {
		this.elementClass = elementClass;
	}

	/**
	 * Adds the given element to the list.
	 * 
	 * @param element
	 *            The given element.
	 */
	public abstract void add(ElementClass element);

	/**
	 * Gets the class of elements this list contains. Cannot be done otherwise
	 * due to Java's type erasure.
	 * 
	 * @return The class of elements this list contains.
	 */
	public Class<ElementClass> getElementClass() {
		return this.elementClass;
	}

	/**
	 * Gets the elements of this list.
	 */
	public abstract ArrayList<ElementClass> getElements();

	/***
	 * Gets the number of elements contained in this list.
	 * 
	 * @return The number of elements contained in this list.
	 */
	public abstract Integer getNumberOfElements();

}