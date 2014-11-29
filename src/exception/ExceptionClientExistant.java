package exception;

@SuppressWarnings("serial")
public class ExceptionClientExistant extends Exception {
	public ExceptionClientExistant(String msg) {
		super(msg);
	}
}
