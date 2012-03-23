package sif.model.elements.basic.reference;

import java.util.ArrayList;

import sif.model.elements.basic.address.AbstractAddress;
import sif.model.elements.basic.range.Range;
import sif.model.elements.basic.tokens.ITokenElement;

public class RangeReference extends AbstractReference implements
		IReferencingElement {

	private IReferencingElement referencingElement;
	private Range referencedRange;

	private ArrayList<AbstractReference> outgoingReferences;

	private Boolean isLeftmostColumnRelative = true;
	private Boolean isRightmostColumnRelative = true;
	private Boolean isTopmostRowRelative = true;
	private Boolean isBottommostRowRelative = true;

	public RangeReference() {
		this.outgoingReferences = new ArrayList<AbstractReference>();
	}

	// public RangeReference(CellBasedAddress address) {
	// if (address instanceof CellAddress) {
	// CellAddress cellAddress = (CellAddress) address;
	// this.address = new RangeAddress(cellAddress);
	// }
	// if (address instanceof RangeAddress) {
	// this.address = (RangeAddress) address;
	// }
	// }
	//
	// public RangeReference(CellReference cellReference) {
	// this(cellReference.getReferencedCell().getCellAddress());
	//
	// setLeftmostColumnRelative(cellReference.isColumnRelative());
	// setTopmostRowRelative(cellReference.isRowRelative());
	// setRightmostColumnRelative(cellReference.isColumnRelative());
	// setBottommostRowRelative(cellReference.isRowRelative());
	// }

	@Override
	public void addOutgoingReference(AbstractReference reference) {
		this.outgoingReferences.add(reference);

	}

	@Override
	public AbstractAddress getAbstractAddress() {
		return referencingElement.getAbstractAddress();
	}

	@Override
	public String getLocation() {
		return referencingElement.getLocation();
	}

	@Override
	public ArrayList<AbstractReference> getOutgoingReferences() {
		return this.outgoingReferences;
	}

	@Override
	public IReferencedElement getReferencedElement() {
		return referencedRange;
	}

	public Range getReferencedRange() {
		return referencedRange;
	}

	@Override
	public IReferencingElement getReferencingElement() {
		return referencingElement;
	}

	@Override
	public String getStringRepresentation() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rangereference: " + getValueAsString());
		return builder.toString();
	}

	@Override
	public String getValueAsString() {
		StringBuilder value = new StringBuilder("=");
		if (isReferenceToExternWorksheet()) {
			value.append(referencedRange.getWorksheet().getName() + "!");
		}
		if (!isLeftmostColumnRelative) {
			value.append('$');
		}
		value.append(AbstractAddress.getCharacterFor(referencedRange
				.getLeftmostColumnIndex()));
		if (!isTopmostRowRelative) {
			value.append('$');
		}
		value.append(referencedRange.getTopmostRowIndex());
		value.append(":");

		if (!isRightmostColumnRelative) {
			value.append('$');
		}
		value.append(AbstractAddress.getCharacterFor(referencedRange
				.getRightmostColumnIndex()));
		if (!isBottommostRowRelative) {
			value.append('$');
		}
		value.append(referencedRange.getBottommostRowIndex());

		return value.toString();
	}

	public Boolean isBottommostRowRelative() {
		return this.isBottommostRowRelative;
	}

	public Boolean isLeftmostColumnRelative() {
		return this.isLeftmostColumnRelative;
	}

	@Override
	public Boolean isReferenced(IReferencedElement referencedElement) {
		Boolean isReferenced = false;
		for (AbstractReference outgoingReference : outgoingReferences) {
			if (outgoingReference.getReferencedElement().equals(
					referencedElement)) {
				isReferenced = true;
			} else {
				if (outgoingReference.getReferencedElement() instanceof IReferencingElement) {
					isReferenced = ((IReferencingElement) outgoingReference
							.getReferencedElement())
							.isReferenced(referencedElement);
				}
			}
		}
		return isReferenced;
	}

	@Override
	public Boolean isReferenceToExternWorksheet() {
		return referencedRange.getWorksheet().equals(
				referencingElement.getAbstractAddress().getWorksheet());
	}

	public Boolean isRightmostColumnRelative() {
		return this.isRightmostColumnRelative;
	}

	@Override
	public Boolean isSameAs(ITokenElement token) {
		Boolean isSame = false;
		if (token instanceof RangeReference) {
			RangeReference otherReference = (RangeReference) token;

			if (otherReference.getReferencedRange()
					.equals(getReferencedRange())) {
				if (otherReference.isLeftmostColumnRelative == isLeftmostColumnRelative
						&& otherReference.isRightmostColumnRelative == isRightmostColumnRelative
						&& otherReference.isTopmostRowRelative == isTopmostRowRelative
						&& otherReference.isBottommostRowRelative == isBottommostRowRelative) {
					isSame = true;
				}
			}
		}
		return isSame;
	}

	public Boolean isTopmostRowRelative() {
		return this.isTopmostRowRelative;
	}

	public void setBottommostRowRelative(Boolean isBottommostRowRelative) {
		this.isBottommostRowRelative = isBottommostRowRelative;
	}

	public void setLeftmostColumnRelative(Boolean isLeftmostColumnRelative) {
		this.isLeftmostColumnRelative = isLeftmostColumnRelative;
	}

	@Override
	public void setReferencedElement(IReferencedElement referencedElement) {
		if (referencedElement instanceof Range) {
			this.referencedRange = (Range) referencedElement;
			referencedRange.addIncomingReference(this);
		}

	}

	@Override
	public void setReferencingElement(IReferencingElement referencingElement) {
		this.referencingElement = referencingElement;
		referencingElement.addOutgoingReference(this);
	}

	public void setRightmostColumnRelative(Boolean isRightmostColumnRelative) {
		this.isRightmostColumnRelative = isRightmostColumnRelative;
	}

	public void setTopmostRowRelative(Boolean isTopmostRowRelative) {
		this.isTopmostRowRelative = isTopmostRowRelative;
	}

}
