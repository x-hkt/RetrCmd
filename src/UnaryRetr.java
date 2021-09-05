import java.util.Optional;
import java.util.function.UnaryOperator;

public class UnaryRetr<A> implements Retr<A, A> {
	private final UnaryOperator<Optional<A>> unaryoperator;

	public UnaryRetr(UnaryOperator<Optional<A>> unaryoperator) {
		this.unaryoperator = unaryoperator;
	}
	
	public Pair<Optional<A>, Optional<A>> get(Optional<A> state) {
		Optional<A> result = this.unaryoperator.apply(state);
		return new Pair<>(result, result);
	}
}
