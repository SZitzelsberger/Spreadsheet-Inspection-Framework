package sif.model.elements.basic.tokencontainers;

import java.util.ArrayList;
import java.util.List;

import sif.model.elements.BasicAbstractElement;
import sif.model.elements.basic.cell.Cell;
import sif.model.elements.basic.cell.ICellElement;
import sif.model.elements.basic.tokens.IOperationTokenElement;
import sif.model.elements.basic.tokens.ITokenElement;

/**
 * Representation of a function.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class Function extends BasicAbstractElement implements
		IOperationTokenElement, ICellElement, ITokenContainer {

	private ITokenContainer container;
	private String name;
	private ArrayList<ITokenElement> tokens;

	public Function() {
		tokens = new ArrayList<ITokenElement>();
	}

	@Override
	public void add(ITokenElement formulaToken) {
		this.tokens.add(formulaToken);
	}

	@Override
	public void add(List<ITokenElement> tokenList) {
		for (ITokenElement token : tokenList) {
			this.tokens.add(token);
		}
	}

	@Override
	public Cell getCell() {
		return container.getCell();
	}

	public ITokenContainer getContainer() {
		return container;
	}

	@Override
	public Integer getDepth() {
		Integer maxDepth = 0;
		for (ITokenElement token : tokens) {
			if (token instanceof ITokenContainer) {
				ITokenContainer container = (ITokenContainer) token;
				Integer pathDepth = container.getDepth() + 1;
				if (pathDepth > maxDepth) {
					maxDepth = pathDepth;
				}
			}
		}
		return maxDepth;
	}

	@Override
	public String getLocation() {
		return getCell().getLocation();
	}

	public String getName() {
		return name;
	}

	public Integer getNumberOfArguments() {
		return tokens.size();
	}

	@Override
	public Integer getNumberOfTokens() {
		return tokens.size();
	}

	@Override
	public String getStringRepresentation() {
		return "Function [" + name + "]";
	}

	@Override
	public ArrayList<ITokenElement> getTokens() {
		return tokens;
	}

	@Override
	public String getValueAsString() {
		// TODO
		return null;
	}

	@Override
	public Boolean isSameAs(ITokenElement token) {
		Boolean isSame = false;

		if (token instanceof Function) {
			Function otherFunction = (Function) token;

			if (otherFunction.getName().equals(name)
					&& otherFunction.tokens.size() == this.tokens.size()) {
				Integer index = this.tokens.size() - 1;
				Boolean differenceFound = false;
				while (index >= 0 & !differenceFound) {
					ITokenElement thisToken = this.tokens.get(index);
					ITokenElement otherToken = otherFunction.tokens.get(index);
					differenceFound = !thisToken.isSameAs(otherToken);
					index = index - 1;
				}
				if (!differenceFound) {
					isSame = true;
				}
			}
		}

		return isSame;
	}

	public void setContainer(ITokenContainer container) {
		this.container = container;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public ArrayList<ITokenElement> getAllTokens() {
		ArrayList<ITokenElement> allTokens = new ArrayList<ITokenElement>();

		for (ITokenElement token : tokens) {
			allTokens.add(token);
			if (token instanceof ITokenContainer) {
				allTokens.addAll(((ITokenContainer) token).getAllTokens());
			}

		}
		return allTokens;
	}
}