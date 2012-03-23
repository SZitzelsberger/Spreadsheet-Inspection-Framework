package sif.model.policy.expression.elements.types;

import java.util.ArrayList;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import sif.model.elements.IElement;
import sif.model.policy.expression.Expression;
import sif.technicalDepartment.equipment.testing.selection_devices.types.AbstractSelectionDevice;

public abstract class AbstractElementsExpression implements Expression,
		TreeModel {
	protected ArrayList<Class<? extends IElement>> elementTypes;

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getChild(Object parent, int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		// TODO Auto-generated method stub
		return 0;
	}

	public abstract ArrayList<Class<? extends IElement>> getcompatibleElementTypes();

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStringRepresentation() {
		// TODO Auto-generated method stub
		return null;
	}

	public abstract AbstractSelectionDevice interpret();

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLeaf(Object node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// TODO Auto-generated method stub

	}
}