import java.util.Optional;

@FunctionalInterface
public interface Retr<S, A> {
	public Pair<Optional<S>, Optional<A>> get(Optional<S> x);
}
