package sif.model.violations;

import java.util.ArrayList;

import sif.model.violations.groups.GenericViolationGroup;

/***
 * A violation group is a set of single violations that share specific criteria
 * and thus should be presented as a group. If a custom violation group is
 * needed, it can be realized by implementing this interface. Otherwise the
 * {@link GenericViolationGroup} can be used.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public interface IViolationGroup extends IViolation {

	/***
	 * Adds the given single violation to the group.
	 * 
	 * @param violation
	 *            The given single violation.
	 */
	public void add(ISingleViolation violation);

	/***
	 * Gets all single violations that have been added to the group.
	 * 
	 * @return The list of single violations belonging to this group.
	 */
	public ArrayList<ISingleViolation> getMembers();

}
