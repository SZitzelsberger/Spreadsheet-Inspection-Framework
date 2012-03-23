package sif.model.policy.expression.elements.types;

import java.util.ArrayList;

import sif.model.elements.IElement;

public abstract class ElementsExpression<ElementType extends IElement> extends
		AbstractElementsExpression {
	public ArrayList<Class<? extends IElement>> getcompatibleElementTypes() {
		this.elementTypes.add(getElementType());
		return this.elementTypes;
	}

	public abstract String getDescription();

	public abstract Class<ElementType> getElementType();

	@Override
	public boolean isComplete() {
		return true;
	}
}