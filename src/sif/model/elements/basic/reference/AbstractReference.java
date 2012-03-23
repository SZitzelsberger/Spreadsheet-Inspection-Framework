package sif.model.elements.basic.reference;

import sif.model.elements.BasicAbstractElement;
import sif.model.elements.basic.tokencontainers.ITokenContainer;
import sif.model.elements.basic.tokens.ITokenElement;

public abstract class AbstractReference extends BasicAbstractElement implements
		ITokenElement {

	protected ITokenContainer container;

	public ITokenContainer getContainer() {
		return this.container;
	}

	public abstract IReferencedElement getReferencedElement();

	public abstract IReferencingElement getReferencingElement();

	public abstract Boolean isReferenceToExternWorksheet();

	public void setContainer(ITokenContainer container) {
		this.container = container;
	}

	public abstract void setReferencedElement(
			IReferencedElement referencedElement);

	public abstract void setReferencingElement(
			IReferencingElement referencingElement);

}
