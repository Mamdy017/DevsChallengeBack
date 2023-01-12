package DevsChallenge.example.DevsChallenge.Messages;

public class CategorieExisteDejaException extends Exception {
    private static final long serialVersionUID = 1L;

    public CategorieExisteDejaException(String message) {
        super(message);
    }
}
