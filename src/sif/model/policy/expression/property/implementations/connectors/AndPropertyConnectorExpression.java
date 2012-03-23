package sif.model.policy.expression.property.implementations.connectors;

import sif.model.elements.IElement;
import sif.model.policy.expression.property.types.PropertyConnectorExpression;
import sif.technicalDepartment.equipment.testing.instruments.types.AbstractTestInstrument;

public class AndPropertyConnectorExpression extends PropertyConnectorExpression {
	public String getStringRepresentation() {
		return null;
	}

	public AbstractTestInstrument<IElement> interpret() {
		return null;
	}

	public boolean isComplete() {
		return false;
	}
}