package sif.model.elements.basic.tokencontainers;

import java.util.ArrayList;
import java.util.List;

import sif.model.elements.basic.cell.ICellElement;
import sif.model.elements.basic.tokens.ITokenElement;

/**
 * Container interface to store {@link ITokenElement}s in a hierarchical way.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public interface ITokenContainer extends ICellElement {

	/**
	 * Adds the given token to the container.
	 * 
	 * @param token
	 *            The given token.
	 */
	public void add(ITokenElement token);

	/***
	 * Adds the given list of tokens to the container.
	 * 
	 * @param tokenList
	 *            The given list of tokens.
	 */
	public void add(List<ITokenElement> tokenList);

	/**
	 * Gets the depth of the container hierarchy.
	 * 
	 * @return the depth.
	 */
	public Integer getDepth();

	/**
	 * Gets the number of tokens directly contained.
	 * 
	 * @return The number of directly contained tokens.
	 */
	public Integer getNumberOfTokens();

	/**
	 * Gets the list of directly contained tokens.
	 * 
	 * @return The list of tokens.
	 */
	public ArrayList<ITokenElement> getTokens();

	/**
	 * Gets the list of all directly or indirectly contained tokens.
	 * 
	 * @return The list of tokens.
	 */
	public ArrayList<ITokenElement> getAllTokens();

}
