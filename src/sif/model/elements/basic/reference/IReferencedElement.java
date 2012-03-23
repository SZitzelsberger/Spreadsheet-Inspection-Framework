package sif.model.elements.basic.reference;

import java.util.ArrayList;

import sif.model.elements.IElement;
import sif.model.elements.basic.address.AbstractAddress;

public interface IReferencedElement extends IElement {

	public void addIncomingReference(AbstractReference reference);

	public AbstractAddress getAbstractAddress();

	public ArrayList<AbstractReference> getIncomingReferences();

}
