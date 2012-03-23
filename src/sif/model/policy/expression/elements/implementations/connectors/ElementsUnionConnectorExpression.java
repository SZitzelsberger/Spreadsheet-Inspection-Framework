package sif.model.policy.expression.elements.implementations.connectors;

import java.util.ArrayList;

import sif.model.elements.IElement;
import sif.model.policy.expression.elements.types.ElementsConnectorExpression;
import sif.technicalDepartment.equipment.testing.selection_devices.types.AbstractSelectionDevice;

public class ElementsUnionConnectorExpression extends
		ElementsConnectorExpression {
	public ArrayList<Class<? extends IElement>> getcompatibleElementTypes() {
		// this.elementTypes =
		// ElementClassHelper.getCommomElementClasses(this.leftExpression.getcompatibleElementTypes(),this.rightExpression.getcompatibleElementTypes());

		return this.elementTypes;
	}

	public String getStringRepresentation() {
		return "(" + this.leftExpression.getStringRepresentation() + " " + "OR"
				+ " " + this.rightExpression.getStringRepresentation() + ")";
	}

	public AbstractSelectionDevice interpret() {
		return null;
	}
}