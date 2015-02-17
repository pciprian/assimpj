package net.assimpj;

public class AssertUtils {

	public static void assertNull(Object object, String message) throws NullPointerException {
		if (object == null) {
			throw new NullPointerException(message);
		}
	}
}
