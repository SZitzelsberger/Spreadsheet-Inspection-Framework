package sif.model.policy.expression.elements.types;

public abstract class ElementsConnectorExpression extends
		AbstractElementsExpression {
	protected AbstractElementsExpression leftExpression;
	protected AbstractElementsExpression rightExpression;

	public boolean isComplete() {
		boolean isComplete = false;
		if (((this.leftExpression != null ? 1 : 0) & (this.rightExpression != null ? 1
				: 0)) != 0) {
			isComplete = this.leftExpression.isComplete()
					& this.rightExpression.isComplete();
		}
		return isComplete;
	}
}