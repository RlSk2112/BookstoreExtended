package bg.rumen.bookstore.exceptions;

public class NoSuchCommentWithIdException extends RuntimeException {

    public NoSuchCommentWithIdException(Integer id) {
        super(String.format("Comment with Id %d not found", id));
    }
}
