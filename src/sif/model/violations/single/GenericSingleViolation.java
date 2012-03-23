package sif.model.violations.single;

import sif.model.elements.IElement;
import sif.model.policy.policyrule.AbstractPolicyRule;
import sif.model.violations.ISingleViolation;

/***
 * A generic single violation to record the violation of any policy rule.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class GenericSingleViolation implements ISingleViolation {

	private Double severityValue = 0.0;
	private AbstractPolicyRule policyRule;
	private IElement causingElement;
	private StringBuilder descriptionBuilder = new StringBuilder();

	/**
	 * Appends the given decription part to the description.
	 * 
	 * @param descriptionPart
	 */
	public void appendToDescription(String descriptionPart) {
		this.descriptionBuilder.append(descriptionPart);
	}

	@Override
	public IElement getCausingElement() {
		return causingElement;
	}

	@Override
	public String getDescription() {
		return descriptionBuilder.toString();
	}

	@Override
	public AbstractPolicyRule getPolicyRule() {
		return policyRule;
	}

	@Override
	public Double getWeightedSeverityValue() {
		return severityValue;
	}

	public void setBaseSeverityValue(Double severityValue) {
		this.severityValue = severityValue * policyRule.getSeverityWeight();
	}

	@Override
	public void setCausingElement(IElement element) {
		this.causingElement = element;
	}

	@Override
	public void setPolicyRule(AbstractPolicyRule policyRule) {
		this.policyRule = policyRule;
	}

}
