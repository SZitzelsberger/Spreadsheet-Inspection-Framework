package sif.model.violations;

import sif.model.violations.lists.ViolationList;

/***
 * Violation groupors are used to in {@link ViolationList}s to group
 * {@link ISingleViolation}s to {@link IViolationGroup}s by specific criteria.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public abstract class ViolationGroupor {

	/***
	 * Creates a violation group from two given violations.
	 * 
	 * @param firstSingleViolation
	 *            The first given violation.
	 * @param secondSingleViolation
	 *            The second given violation.
	 * @return A violation group containing the given violations.
	 */
	public abstract IViolationGroup createGroupFor(
			ISingleViolation firstSingleViolation,
			ISingleViolation secondSingleViolation);

	/***
	 * Creates a violation group from the given violation and the given abstract
	 * violation.
	 * 
	 * @param singleViolation
	 * @param violation
	 * @return A violation group containing the given violation and the given
	 *         abstract violation.
	 */
	public IViolationGroup createGroupFor(ISingleViolation singleViolation,
			IViolation violation) {

		IViolationGroup violationGroup = null;

		if (violation instanceof IViolationGroup) {
			violationGroup = createGroupFor(singleViolation,
					((IViolationGroup) violation));
		} else {
			violationGroup = createGroupFor(singleViolation,
					(ISingleViolation) violation);
		}
		setGroupElementFor(violationGroup);

		return violationGroup;

	}

	/***
	 * Create a violation group from the given violation and the given violation
	 * group.
	 * 
	 * @param singleViolation
	 *            The given violation.
	 * @param violationGroup
	 *            The given violation group.
	 * @return A violation group containing the given violation and the
	 *         violations of the given violation group.
	 */
	private IViolationGroup createGroupFor(ISingleViolation singleViolation,
			IViolationGroup violationGroup) {
		IViolationGroup newViolationGroup = violationGroup;
		newViolationGroup.add(singleViolation);
		return newViolationGroup;
	}

	/***
	 * Checks if the given violations must be grouped.
	 * 
	 * @param firstSingleViolation
	 *            The first given violation.
	 * @param secondSingleViolation
	 *            The second given abstract violation.
	 * @return Whether the given violations must be grouped.
	 */
	public abstract Boolean mustBeGrouped(
			ISingleViolation firstSingleViolation,
			ISingleViolation secondSingleViolation);

	/***
	 * Checks if the given violation and the given abstract violation must be
	 * grouped.
	 * 
	 * @param singleViolation
	 *            The given violation.
	 * @param violation
	 *            The given abstract violation.
	 * @return Whether the given violation and the given abstract violation must
	 *         be grouped.
	 */
	public Boolean mustBeGrouped(ISingleViolation singleViolation,
			IViolation violation) {
		Boolean mustBeGrouped = false;

		if (violation instanceof IViolationGroup) {
			mustBeGrouped = mustBeGrouped(singleViolation,
					(IViolationGroup) violation);
		} else {
			mustBeGrouped = mustBeGrouped(singleViolation,
					(ISingleViolation) violation);
		}
		return mustBeGrouped;
	}

	/***
	 * Checks if the given violation and the given violation group must be
	 * grouped.
	 * 
	 * @param singleViolation
	 *            The given violation.
	 * @param violationGroup
	 *            The given violation group.
	 * @return Whether the given violation and the given violation group must be
	 *         grouped.
	 */
	public abstract Boolean mustBeGrouped(ISingleViolation singleViolation,
			IViolationGroup violationGroup);

	public abstract void setGroupElementFor(IViolationGroup violationGroup);

}
