package sif.model.elements.containers;

import java.util.ArrayList;

import sif.model.elements.IElement;

/**
 * A simple element list to store spreadsheet elements.
 * 
 * @author Sebastian Zitzelsberger
 * 
 * @param <ElementClass>
 */
public class SimpleElementList<ElementClass extends IElement> extends
		AbstractElementList<ElementClass> {

	private ArrayList<ElementClass> elements = new ArrayList<ElementClass>();

	public SimpleElementList(Class<ElementClass> elementClass) {
		super(elementClass);
	}

	@Override
	public void add(ElementClass element) {
		elements.add(element);

	}

	@Override
	public Class<ElementClass> getElementClass() {
		return this.elementClass;
	}

	public ArrayList<ElementClass> getElements() {
		return elements;
	}

	@Override
	public Integer getNumberOfElements() {
		return elements.size();
	}

}