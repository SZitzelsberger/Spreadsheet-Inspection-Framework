package sif.model.policy.expression.policyrule;

import sif.model.policy.expression.Expression;
import sif.model.policy.expression.elements.types.AbstractElementsExpression;
import sif.model.policy.expression.property.types.AbstractPropertyExpression;
import sif.technicalDepartment.equipment.testing.facilities.types.CompositeTestFacility;
import sif.technicalDepartment.equipment.testing.instruments.types.AbstractTestInstrument;
import sif.technicalDepartment.equipment.testing.selection_devices.types.AbstractSelectionDevice;

public class PolicyRuleExpression implements Expression {
	AbstractElementsExpression abstractElementsExpression;
	AbstractPropertyExpression<?> abstractPropertyExpression;

	public String getStringRepresentation() {
		return null;
	}

	public CompositeTestFacility interpret() {
		CompositeTestFacility testFacility = new CompositeTestFacility();

		AbstractSelectionDevice abstractSelectionDevice = this.abstractElementsExpression
				.interpret();
		AbstractTestInstrument<?> abstractTestInstrument = this.abstractPropertyExpression
				.interpret();

		testFacility.setElementGroupSelector(abstractSelectionDevice);
		testFacility.setAbstractTestInstrument(abstractTestInstrument);

		return testFacility;
	}

	public boolean isComplete() {
		return this.abstractElementsExpression.isComplete()
				& this.abstractPropertyExpression.isComplete();
	}
}