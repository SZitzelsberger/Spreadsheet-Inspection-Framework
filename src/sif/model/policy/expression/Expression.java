package sif.model.policy.expression;

public abstract interface Expression {
	public abstract String getStringRepresentation();

	public abstract boolean isComplete();
}