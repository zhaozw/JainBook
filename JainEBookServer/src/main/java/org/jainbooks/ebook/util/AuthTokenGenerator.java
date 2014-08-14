package org.jainbooks.ebook.util;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class AuthTokenGenerator {
	private SecureRandom random = new SecureRandom();

	  public String getAuthToken() {
	    return new BigInteger(130, random).toString(32);
	  }
}
