package sif.model.violations.groupors;

import sif.model.violations.ISingleViolation;
import sif.model.violations.IViolationGroup;
import sif.model.violations.ViolationGroupor;
import sif.model.violations.groups.GenericViolationGroup;

/***
 * Groups violations whose causing elements are equal (
 * {@link Object#equals(Object)}.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class SameCausingElementGroupor extends ViolationGroupor {

	@Override
	public IViolationGroup createGroupFor(
			ISingleViolation firstSingleViolation,
			ISingleViolation secondSingleViolation) {
		IViolationGroup newViolationGroup = new GenericViolationGroup(
				secondSingleViolation.getPolicyRule());

		newViolationGroup.add(secondSingleViolation);
		newViolationGroup.add(firstSingleViolation);

		return newViolationGroup;
	}

	@Override
	public Boolean mustBeGrouped(ISingleViolation firstSingleViolation,
			ISingleViolation secondSingleViolation) {
		return (firstSingleViolation.getCausingElement()
				.equals(secondSingleViolation.getCausingElement()));
	}

	@Override
	public Boolean mustBeGrouped(ISingleViolation singleViolation,
			IViolationGroup violationGroup) {
		return (singleViolation.getCausingElement()).equals(violationGroup
				.getCausingElement());
	}

	@Override
	public void setGroupElementFor(IViolationGroup violationGroup) {
		if (!violationGroup.getMembers().isEmpty()) {
			violationGroup.setCausingElement(violationGroup.getMembers().get(0)
					.getCausingElement());
		}

	}
}
