package sif.model.violations.single;

import java.util.HashMap;
import java.util.Iterator;

import sif.model.elements.IElement;
import sif.model.elements.basic.reference.AbstractReference;
import sif.model.elements.basic.tokens.ITokenElement;
import sif.model.policy.policyrule.AbstractPolicyRule;
import sif.model.policy.policyrule.implementations.ReadingDirectionPolicyRule;
import sif.model.violations.ISingleViolation;

/***
 * A custom single violation to record violations of the
 * {@link ReadingDirectionPolicyRule}.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class ReadingDirectionSingleViolation implements ISingleViolation {

	private IElement causingFormula;
	private HashMap<AbstractReference, Integer> nonLeftToRightreferences;
	private HashMap<AbstractReference, Integer> nonTopToBottomreferences;
	private AbstractPolicyRule policyRule;

	public ReadingDirectionSingleViolation() {
		nonLeftToRightreferences = new HashMap<AbstractReference, Integer>();
		nonTopToBottomreferences = new HashMap<AbstractReference, Integer>();
	}

	/**
	 * Adds the given reference as a reference that cannot be read from left to
	 * right. If the reference is equal to
	 * {@link ITokenElement#isSameAs(ITokenElement)} a reference that has
	 * already been added, a counter will be increased.
	 * 
	 * @param violatingReference
	 *            The given reference.
	 */
	public void addNonLeftToRight(AbstractReference violatingReference) {
		Boolean added = false;
		for (AbstractReference reference : nonLeftToRightreferences.keySet()) {
			if (reference.isSameAs(violatingReference)) {
				Integer occurences = nonLeftToRightreferences.get(reference) + 1;
				nonLeftToRightreferences.put(reference, occurences);
				added = true;
				break;
			}
		}

		if (!added) {
			nonLeftToRightreferences.put(violatingReference, 1);
		}
	}

	/**
	 * Adds the given reference as a reference that cannot be read from top to
	 * bottom. If the reference is equal to
	 * {@link ITokenElement#isSameAs(ITokenElement)} a reference that has
	 * already been added, a counter will be increased.
	 * 
	 * @param violatingReference
	 *            The given reference.
	 */
	public void addNonTopToBottom(AbstractReference violatingReference) {
		Boolean added = false;
		for (AbstractReference reference : nonTopToBottomreferences.keySet()) {
			if (reference.isSameAs(violatingReference)) {
				Integer occurences = nonTopToBottomreferences.get(reference) + 1;
				nonTopToBottomreferences.put(reference, occurences);
				added = true;
				break;
			}
		}

		if (!added) {
			nonTopToBottomreferences.put(violatingReference, 1);
		}
	}

	@Override
	public IElement getCausingElement() {
		return causingFormula;
	}

	@Override
	public String getDescription() {
		StringBuilder description = new StringBuilder();

		// Write description for all non left to right references.
		if (!nonLeftToRightreferences.isEmpty()) {
			description
					.append("The following references cannot be read from left to right: ");

			Iterator<AbstractReference> refLeftIterator = nonLeftToRightreferences
					.keySet().iterator();

			while (refLeftIterator.hasNext()) {
				AbstractReference reference = refLeftIterator.next();

				description.append(reference.getValueAsString());

				if (nonLeftToRightreferences.get(reference) > 1) {
					description.append(" ("
							+ nonLeftToRightreferences.get(reference) + "x)");
				}
				if (refLeftIterator.hasNext()) {
					description.append(", ");
				}
			}
		}

		// Write description for all non top to bottom references.
		if (!nonTopToBottomreferences.isEmpty()) {
			description
					.append("\n The following references cannot be read from top to bottom: ");

			Iterator<AbstractReference> refTopIterator = nonTopToBottomreferences
					.keySet().iterator();

			while (refTopIterator.hasNext()) {
				AbstractReference reference = refTopIterator.next();

				description.append(reference.getValueAsString());
				if (nonTopToBottomreferences.get(reference) > 1) {
					description.append(" ("
							+ nonTopToBottomreferences.get(reference) + "x)");
				}
				if (refTopIterator.hasNext()) {
					description.append(", ");
				}
			}
		}

		return description.toString();
	}

	@Override
	public AbstractPolicyRule getPolicyRule() {
		return policyRule;
	}

	@Override
	public Double getWeightedSeverityValue() {
		// Integer numberOfConstants = 0;
		Double severtityValue = 0.0;

		// TODO:Implement severity calculation

		// for (ScalarConstant constant : constants.keySet()) {
		// numberOfConstants = numberOfConstants + constants.get(constant);
		// }
		// switch (numberOfConstants) {
		// case 1:
		// severtityValue = IViolation.SEVERITY_VERY_LOW
		// * getPolicyRule().getSeverityWeight();
		// break;
		// case 2:
		// severtityValue = IViolation.SEVERITY_LOW
		// * getPolicyRule().getSeverityWeight();
		// break;
		// case 3:
		// severtityValue = IViolation.SEVERITY_MEDIUM
		// * getPolicyRule().getSeverityWeight();
		// break;
		// case 4:
		// severtityValue = IViolation.SEVERITY_HIGH
		// * getPolicyRule().getSeverityWeight();
		// break;
		// case 5:
		// severtityValue = IViolation.SEVERITY_VERY_HIGH
		// * getPolicyRule().getSeverityWeight();
		// break;
		// default:
		// severtityValue = IViolation.SEVERITY_VERY_HIGH
		// * getPolicyRule().getSeverityWeight();
		// break;
		// }

		return severtityValue;
	}

	@Override
	public void setCausingElement(IElement element) {
		this.causingFormula = element;
	}

	@Override
	public void setPolicyRule(AbstractPolicyRule policyRule) {
		this.policyRule = policyRule;
	}

}
