import java.util.ArrayList;

public class Notation {
	private static NotationQueue<String> q;
	private static NotationStack<String> s;

	private Notation() {
	}

	/**
	 * Evaluates a postfix expression from a string to a double
	 * 
	 * @param postfixExpr
	 * @return postfix expression evaluated as double
	 * @throws InvalidNotationFormatException
	 */
	public static double evaluatePostfixExpression(java.lang.String postfixExpr) throws InvalidNotationFormatException {
		return 0;
	}

	/**
	 * Convert the Postfix expression to the Infix expression
	 * 
	 * @param postfix expression
	 * @return infix expression
	 * @throws InvalidNotationFormatException
	 */
	public static java.lang.String convertPostfixToInfix(java.lang.String postfix)
			throws InvalidNotationFormatException {
		return null;
	}

	/**
	 * Convert an infix expression into a postfix expression
	 * 
	 * @param infix expression
	 * @return postfix expression
	 * @throws InvalidNotationFormatException
	 */
	public static java.lang.String convertInfixToPostfix(java.lang.String infix) throws InvalidNotationFormatException {
		q = new NotationQueue<String>(infix.length());
		s = new NotationStack<String>(infix.length());

		String[] i = infix.split("");

		try {
			for (String c : i) {
				
				// if space
				if (c.equals(" ")) {
					continue;
				}

				// if integer
				if (isInteger(c)) {
					q.enqueue(c);
				}

				// if left parenthesis
				if (c.equals("(")) {
					s.push(c);
				}

				// if operator
				if (isOperator(c)) {
					if (!s.isEmpty() && isOperator(s.top()) && isHigherEqualPrecedence(s.top(),c)) {
						q.enqueue(s.pop());
					}
					s.push(c);
				}

				// if right parenthesis
				if (c.equals(")")) {
					while (!s.isEmpty() && isOperator(s.top())) {
						q.enqueue(s.pop());
					}
					if(!s.isEmpty() && s.top().equals("(")) {
						s.pop();
					} else {
						throw new Exception();
					}
				}
			}

			// pop remaining operators into queue
			while (!s.isEmpty() && isOperator(s.top())) {
				q.enqueue(s.pop());
			}
		} catch (Exception e) {
			throw new InvalidNotationFormatException();
		}

		return q.toString();
	}

	public static double evaluateInfixExpression(String infixExpr) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private static boolean isOperator(String s) {
		switch (s) {
		case "+":
		case "-":
		case "*":
		case "/":
			return true;
		default:
			return false;
		}
	}

	/**
	 * checks precedence in operators
	 * 
	 * @param a
	 * @param b
	 * @return if operator a >= b in precedence
	 */
	private static boolean isHigherEqualPrecedence(String a, String b) {
		int a_1;
		int b_1;
		
		a_1 = a=="*" || a=="/" ? 1 : 0;
		b_1 = b=="*" || b=="/" ? 1 : 0;
		
		return a_1>=b_1;
	}

}
