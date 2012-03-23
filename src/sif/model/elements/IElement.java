package sif.model.elements;

public interface IElement {

	/***
	 * Gets the location of this element.
	 * 
	 * @return The location of this element.
	 */
	public String getLocation();

	/***
	 * Gets this element as a string.
	 * 
	 * @return The string representation of this element.
	 */
	public String getStringRepresentation();

	/***
	 * Gets the value of this element as a String.
	 * 
	 * @return The value of this element as a string.
	 */
	public abstract String getValueAsString();

}