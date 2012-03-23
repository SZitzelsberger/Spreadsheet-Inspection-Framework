package sif.model.policy.expression.property.types;

import sif.model.elements.IElement;
import sif.model.policy.expression.Expression;
import sif.technicalDepartment.equipment.testing.instruments.types.AbstractTestInstrument;

public abstract class AbstractPropertyExpression<ElementType extends IElement>
		implements Expression {
	private PropertyPrefix prefix = PropertyPrefix.Must;

	public PropertyPrefix getPrefix() {
		return this.prefix;
	}

	public abstract AbstractTestInstrument<ElementType> interpret();

	public void setPrefix(PropertyPrefix prefix) {
		this.prefix = prefix;
	}
}