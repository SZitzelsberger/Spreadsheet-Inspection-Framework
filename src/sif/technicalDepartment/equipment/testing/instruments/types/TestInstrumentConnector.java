package sif.technicalDepartment.equipment.testing.instruments.types;

import sif.model.elements.IElement;

public abstract class TestInstrumentConnector extends
		AbstractTestInstrument<IElement> {
	AbstractTestInstrument<IElement> firstAbstractTestInstrument;
	AbstractTestInstrument<IElement> secondAbstractTestInstrument;

	@Override
	protected IElement castToElementTyp(IElement element) {
		return element;
	}

	public AbstractTestInstrument<IElement> getFirstAbstractTestInstrument() {
		return this.firstAbstractTestInstrument;
	}

	public AbstractTestInstrument<IElement> getSecondAbstractTestInstrument() {
		return this.secondAbstractTestInstrument;
	}

	public void setFirstAbstractTestInstrument(
			AbstractTestInstrument<IElement> firstAbstractTestInstrument) {
		this.firstAbstractTestInstrument = firstAbstractTestInstrument;
	}

	public void setSecondAbstractTestInstrument(
			AbstractTestInstrument<IElement> secondAbstractTestInstrument) {
		this.secondAbstractTestInstrument = secondAbstractTestInstrument;
	}
}