package sif.model.elements.basic.tokens;

import sif.model.elements.IElement;

/**
 * Marker interface for tokens. A token is a basic part of a formula.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public interface ITokenElement extends IElement {

	/**
	 * Gets the value of this token as a string.
	 * 
	 * @return The value of this token as a string.
	 */
	public String getValueAsString();

	/***
	 * Checks if the token is the same as the given token.
	 * 
	 * @param token
	 *            The given token.
	 * @return Whether the tokens are the same.
	 */
	public Boolean isSameAs(ITokenElement token);

}