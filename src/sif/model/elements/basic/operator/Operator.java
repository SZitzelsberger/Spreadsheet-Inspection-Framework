package sif.model.elements.basic.operator;

import java.util.ArrayList;
import java.util.List;

import sif.model.elements.BasicAbstractElement;
import sif.model.elements.basic.cell.Cell;
import sif.model.elements.basic.tokencontainers.ITokenContainer;
import sif.model.elements.basic.tokens.IOperationTokenElement;
import sif.model.elements.basic.tokens.ITokenElement;

/**
 * Represents an operator, such as + or -.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class Operator extends BasicAbstractElement implements
		IOperationTokenElement, ITokenContainer {

	private ITokenContainer container;
	private ArrayList<ITokenElement> tokens;
	private OperatorEnum operatoryType;

	public Operator() {
		this.tokens = new ArrayList<ITokenElement>();
	}

	@Override
	public void add(ITokenElement token) {
		this.tokens.add(token);

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
		return this.container;
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

	@Override
	public Integer getNumberOfTokens() {
		return tokens.size();
	}

	public OperatorEnum getOperatoryType() {
		return operatoryType;
	}

	@Override
	public String getStringRepresentation() {
		return "Operator [" + getValueAsString() + "]";
	}

	@Override
	public ArrayList<ITokenElement> getTokens() {
		return tokens;
	}

	@Override
	public String getValueAsString() {
		return operatoryType.toString().toLowerCase();
	}

	@Override
	public Boolean isSameAs(ITokenElement token) {
		Boolean isSame = false;
		if (token instanceof Operator) {
			Operator other = (Operator) token;
			if (other.getOperatoryType() == this.getOperatoryType()) {
				isSame = true;
			}
		}
		return isSame;
	}

	public void setContainer(ITokenContainer container) {
		this.container = container;
	}

	public void setOperatoryType(OperatorEnum operatoryType) {
		this.operatoryType = operatoryType;
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
