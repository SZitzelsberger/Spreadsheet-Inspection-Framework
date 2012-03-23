package sif.model.violations.groupors;

import sif.model.elements.basic.address.AbstractAddress;
import sif.model.elements.basic.range.Range;
import sif.model.elements.basic.tokencontainers.Formula;
import sif.model.violations.ISingleViolation;
import sif.model.violations.IViolationGroup;
import sif.model.violations.ViolationGroupor;
import sif.model.violations.groups.GenericViolationGroup;

/***
 * Groups violations that have a {@link Formula} as a causing element.
 * Violations are grouped if the formulas have the same pattern ({link
 * Formula#hasSamePatternAs(Formula)}) and are adjacent
 * {@link AbstractAddress#isAdjacent(AbstractAddress)}.
 * 
 * Violations must be added in a specific order to be grouped correctly: The
 * causing formula of a violation must be located to the right or below the
 * causing formulas of all violations that have already been added.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class FormulaBlockGroupor extends ViolationGroupor {

	@Override
	public IViolationGroup createGroupFor(
			ISingleViolation firstSingleViolation,
			ISingleViolation secondSingleViolation) {
		// Create new group and add both violations.
		IViolationGroup newViolationGroup = new GenericViolationGroup(
				secondSingleViolation.getPolicyRule());
		newViolationGroup.add(secondSingleViolation);
		newViolationGroup.add(firstSingleViolation);
		return newViolationGroup;

	}

	@Override
	public Boolean mustBeGrouped(ISingleViolation firstSingleViolation,
			ISingleViolation secondSingleViolation) {
		Boolean mustBeGrouped = false;
		Formula thisFormula = (Formula) firstSingleViolation
				.getCausingElement();
		Formula otherFormula = (Formula) secondSingleViolation
				.getCausingElement();
		// Check if formulas have the same pattern and are adjacent.
		if (thisFormula.hasSamePatternAs(otherFormula)
				& thisFormula
						.getCell()
						.getAbstractAddress()
						.isAdjacent(otherFormula.getCell().getAbstractAddress())) {
			mustBeGrouped = true;
		}

		return mustBeGrouped;
	}

	@Override
	public Boolean mustBeGrouped(ISingleViolation violation,
			IViolationGroup violationGroup) {
		Boolean mustBeGrouped = false;

		Integer index = 0;
		// Check for each member of the group, whether i must be grouped with
		// the single violation.
		while (!mustBeGrouped & index < violationGroup.getMembers().size()) {
			ISingleViolation formulaViolation = violationGroup.getMembers()
					.get(index);
			mustBeGrouped = mustBeGrouped(violation, formulaViolation);
			index = index + 1;
		}

		return mustBeGrouped;
	}

	@Override
	public void setGroupElementFor(IViolationGroup violationGroup) {
		Range range = new Range();
		for (ISingleViolation formulaViolation : violationGroup.getMembers()) {
			range.add(((Formula) formulaViolation.getCausingElement())
					.getCell());
		}
		violationGroup.setCausingElement(range);
	}

}
