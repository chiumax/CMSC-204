import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheckerUtility {

	/**
	 * 
	 * @param p
	 * @return if password is valid or not
	 * @throws LengthException
	 * @throws NoDigitException
	 * @throws NoUpperAlphaException
	 * @throws NoLowerAlphaException
	 * @throws NoSpecialCharacterException
	 * @throws InvalidSequenceException
	 */
	public static boolean isValidPassword(java.lang.String p) throws LengthException, NoDigitException,
			NoUpperAlphaException, NoLowerAlphaException, NoSpecialCharacterException, InvalidSequenceException {

		checkLength(p);
		if (checkLength(p) && checkUpperAlpha(p) && checkLowerAlpha(p) && checkDigit(p)
				&& checkSpecialCharacterException(p) && checkInvalidSequence(p)) {
			return true;
		}

		return false;
	}

	/**
	 * 
	 * @param p
	 * @return false if weak
	 * @throws WeakPasswordException
	 */
	public static boolean isWeakPassword(java.lang.String p) throws  WeakPasswordException {
		if (p.length() < 10) {
			throw new WeakPasswordException();
		}
		return false;

	}

	/**
	 * 
	 * @param p
	 * @return array of passwords that are invalid followed by their error messages
	 */
	public static java.util.ArrayList<java.lang.String> getInvalidPasswords(
			java.util.ArrayList<java.lang.String> p) {
		ArrayList<String> invalid = new ArrayList<>();
		for(int i = 0; i < p.size(); i++) {
			try{
				isValidPassword(p.get(i));
			}catch(Exception exception) {
				invalid.add(p.get(i) + " " + exception.getMessage());
			}
		}
		return invalid;
	}

	/**
	 * 
	 * @param p
	 * @return true is password is at least 6 characters long
	 * @throws LengthException
	 */
	private static boolean checkLength(String p) throws LengthException {
		if (p.length() < 6) {
			throw new LengthException();
		}
		return true;

	}

	/**
	 * 
	 * @param p
	 * @return true if there is a digit in password
	 * @throws NoDigitException
	 */
	private static boolean checkDigit(String p) throws NoDigitException {
		Pattern r = Pattern.compile("[0-9]");
		Matcher m = r.matcher(p);

		if (m.find()) {
			return true;
		}
		throw new NoDigitException();

	}

	/**
	 * 
	 * @param p
	 * @return true if there is a uppercase in password
	 * @throws NoUpperAlphaException
	 */
	private static boolean checkUpperAlpha(String p) throws NoUpperAlphaException {
		Pattern r = Pattern.compile("[A-Z]");
		Matcher m = r.matcher(p);

		if (m.find()) {
			return true;
		}
		throw new NoUpperAlphaException();
	}

	/**
	 * 
	 * @param p
	 * @return true if there is a lowercase in password
	 * @throws NoLowerAlphaException
	 */
	private static boolean checkLowerAlpha(String p) throws NoLowerAlphaException {
		Pattern r = Pattern.compile("[a-z]");
		Matcher m = r.matcher(p);

		if (m.find()) {
			return true;
		}
		throw new NoLowerAlphaException();
	}

	/**
	 * 
	 * @param p
	 * @return true if there is a non alphanumeric character in password
	 * @throws NoSpecialCharacterException
	 */
	public static boolean checkSpecialCharacterException(String p) throws NoSpecialCharacterException {
		Pattern r = Pattern.compile("[^a-zA-Z0-9]");
		Matcher m = r.matcher(p);

		if (m.find()) {
			return true;
		}
		throw new NoSpecialCharacterException();
	}

	/**
	 * 
	 * @param p
	 * @return true if there is no sequence of same characters longer than 2 in password
	 * @throws InvalidSequenceException
	 */
	private static boolean checkInvalidSequence(String p) throws InvalidSequenceException {
		Pattern r = Pattern.compile("((.)\\2{2,})");
		Matcher m = r.matcher(p);

		if (m.find()) {
			throw new InvalidSequenceException();
		}
		return true;

	}

}
