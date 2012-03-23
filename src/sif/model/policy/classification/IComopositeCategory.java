package sif.model.policy.classification;

import java.util.ArrayList;

public interface IComopositeCategory extends ICategory {

	public ArrayList<ICategory> getSubCategories();

	public IComopositeCategory getSuperCategory();

	public Boolean isSubCategory(ICategory category);

	public Boolean isSuperCategory(IComopositeCategory category);
}
