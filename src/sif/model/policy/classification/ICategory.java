package sif.model.policy.classification;

import sif.model.policy.policyrule.AbstractPolicyRule;

/**
 * Categories are used to classify the violations reported by
 * {@link AbstractPolicyRule}. Implements the Composite pattern with
 * {@link IComopositeCategory} and {@link ILeafCategory}. No Categories are used
 * so far.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public interface ICategory {

	/**
	 * Gets the description of the category.
	 * 
	 * @return The description.
	 */
	public String getDescription();

	/**
	 * Gets the name of the category.
	 * 
	 * @return The name.
	 */
	public String getName();

}
