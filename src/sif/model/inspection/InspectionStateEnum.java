package sif.model.inspection;

/***
 * Enumerate the states that an {@link InspectionRequest} can be in.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public enum InspectionStateEnum {
	/**
	 * The state of an inspection request after its creation.
	 */
	INITIAL,
	/**
	 * The state of an inspection request after the policy has been set.
	 */
	POLICY_CHOSEN,
	/**
	 * The state of an inspection after the elements have been scanned.
	 */
	ELEMENTS_SCANNED,
	/**
	 * The state of an inspection requeste after the inspection has been run.
	 */
	VIOLATIONS_DETECTED
}