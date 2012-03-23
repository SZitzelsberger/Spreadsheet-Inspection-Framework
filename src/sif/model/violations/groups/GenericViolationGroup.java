package sif.model.violations.groups;

import java.util.ArrayList;

import sif.model.elements.IElement;
import sif.model.policy.policyrule.AbstractPolicyRule;
import sif.model.violations.ISingleViolation;
import sif.model.violations.IViolationGroup;

/***
 * A generic violation group for arbitrary violations.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class GenericViolationGroup implements IViolationGroup {

	private AbstractPolicyRule policyRule;
	private IElement causingElement;
	private ArrayList<ISingleViolation> groupMembers;
	private StringBuilder descriptionBuilder = new StringBuilder();

	public GenericViolationGroup(AbstractPolicyRule policyRule) {
		this.policyRule = policyRule;
		descriptionBuilder.append("Violation Group");
		groupMembers = new ArrayList<ISingleViolation>();
	}

	@Override
	public void add(ISingleViolation singleViolation) {
		groupMembers.add(singleViolation);
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
	public ArrayList<ISingleViolation> getMembers() {
		return groupMembers;
	}

	@Override
	public AbstractPolicyRule getPolicyRule() {
		return this.policyRule;
	}

	@Override
	public Double getWeightedSeverityValue() {
		Double severityValue = 0.0;
		for (ISingleViolation singleViolation : groupMembers) {
			severityValue = severityValue
					+ singleViolation.getWeightedSeverityValue();
		}
		return severityValue;
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
