package sif.model.elements.basic.tokens;

import sif.model.elements.BasicAbstractElement;

/***
 * Place holder class for unsupported tokens.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class UnsupportedToken extends BasicAbstractElement implements
		ITokenElement {

	@Override
	public String getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStringRepresentation() {
		return "Unsupported Token";
	}

	@Override
	public String getValueAsString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isSameAs(ITokenElement token) {
		Boolean isSame = false;
		if (token instanceof UnsupportedToken) {
			isSame = true;
		}
		return isSame;
	}

}
