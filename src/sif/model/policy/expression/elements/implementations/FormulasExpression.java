package sif.model.policy.expression.elements.implementations;

import sif.model.elements.basic.tokencontainers.Formula;
import sif.model.policy.expression.elements.types.ElementsExpression;
import sif.technicalDepartment.equipment.testing.selection_devices.implementations.SelectionDevice;
import sif.technicalDepartment.equipment.testing.selection_devices.types.AbstractSelectionDevice;

public final class FormulasExpression extends ElementsExpression<Formula> {
	public String getDescription() {
		return null;
	}

	public Class<Formula> getElementType() {
		return Formula.class;
	}

	public String getStringRepresentation() {
		return null;
	}

	public AbstractSelectionDevice interpret() {
		return new SelectionDevice<Formula>();
	}
}