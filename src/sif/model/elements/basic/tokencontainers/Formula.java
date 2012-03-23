package sif.model.elements.basic.tokencontainers;

import java.util.ArrayList;
import java.util.List;

import sif.model.elements.BasicAbstractElement;
import sif.model.elements.basic.cell.Cell;
import sif.model.elements.basic.cell.CellContentType;
import sif.model.elements.basic.cell.ICellElement;
import sif.model.elements.basic.operator.Operator;
import sif.model.elements.basic.tokens.ITokenElement;
import sif.model.elements.basic.tokens.ScalarConstant;

/**
 * Representation of a formula.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class Formula extends BasicAbstractElement implements ICellElement,
		ITokenContainer {

	private Cell cell;
	private String formulaString;
	private ArrayList<ITokenElement> tokens;

	private CellContentType resultType;

	public Formula() {
		tokens = new ArrayList<ITokenElement>();
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
		return cell;
	}

	/**
	 * Returns all constants contained in the formula.
	 * 
	 * @return The list of all constants.
	 */
	public ArrayList<ScalarConstant> getConstants() {
		ArrayList<ScalarConstant> constants = new ArrayList<ScalarConstant>();
		for (ITokenElement token : getAllTokens()) {
			if (token instanceof ScalarConstant) {
				constants.add((ScalarConstant) token);
			}
		}

		return constants;
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

	public String getDescription() {
		return null;
	}

	@Override
	public String getLocation() {
		return cell.getAbstractAddress().getFullAddress();
	}

	@Override
	public Integer getNumberOfTokens() {
		return tokens.size();
	}

	public CellContentType getResultType() {
		return this.resultType;
	}

	@Override
	public String getStringRepresentation() {
		StringBuilder builder = new StringBuilder();
		builder.append("Formula[" + getValueAsString() + "]");
		return builder.toString();
	}

	@Override
	public ArrayList<ITokenElement> getTokens() {
		return tokens;
	}

	@Override
	public String getValueAsString() {
		return formulaString;
	}

	/**
	 * Returns whether the formula performs any calculation or is only
	 * referencing.
	 * 
	 * @return Whether the formula is calculating or not.
	 */
	public Boolean isCalculating() {
		Integer numberOfOperations = 0;
		for (ITokenElement token : tokens) {
			if (token instanceof Function | token instanceof Operator) {
				numberOfOperations++;
			}
		}
		return numberOfOperations > 0;
	}

	/***
	 * Checks if the formula has the same formula pattern as the given formula.
	 * 
	 * @param formula
	 *            The given formula.
	 * @return Whether the formulas have the same pattern.
	 */
	public Boolean hasSamePatternAs(Formula formula) {
		Boolean sameFormula = false;

		// Check if formula
		if (this.getTokens().size() == formula.getAllTokens().size()) {
			Integer index = this.getAllTokens().size() - 1;
			Boolean differenceFound = false;

			while (index >= 0 & !differenceFound) {
				ITokenElement thisToken = this.getAllTokens().get(index);
				ITokenElement otherToken = formula.getAllTokens().get(index);
				differenceFound = !thisToken.isSameAs(otherToken);
				index = index - 1;
			}
			if (!differenceFound) {
				sameFormula = true;
			}
		}

		return sameFormula;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public void setFormulaString(String formulaString) {
		this.formulaString = "=" + formulaString;
	}

	public void setResultType(CellContentType resultType) {
		this.resultType = resultType;
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