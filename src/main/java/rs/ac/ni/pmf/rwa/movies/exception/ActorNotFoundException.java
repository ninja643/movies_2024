package rs.ac.ni.pmf.rwa.movies.exception;

public class ActorNotFoundException extends RuntimeException{
    public ActorNotFoundException() {

        super("Nije pronadjen glumac");
    }
}
