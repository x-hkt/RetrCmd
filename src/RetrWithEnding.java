import java.util.Optional;

public class RetrWithEnding<S> implements WithEndingFunction<EIntent, Retr<S, EIntent>> {
	private final Retr<S, EIntent> original;

	public RetrWithEnding(Retr<S, EIntent> original) {
		this.original = original;
	}
	
	public static <S> RetrWithEnding<S> of(Retr<S, EIntent> original) {
		return new RetrWithEnding(original);
	}
	
	public Retr<S, EIntent> withEnding(EIntent x) {
		return (
			stateq -> {
				Pair<Optional<S>, Optional<EIntent>> pair_stateq_outq =
					this.original.get(stateq);
				Optional<EIntent> outq = pair_stateq_outq.getSnd();
				return stateq.isPresent() ? pair_stateq_outq : new Pair<>(
					Optional.empty(),
					Optional.ofNullable(outq.map(y -> EIntent.cat(x, y)).orElse(x))
				);
			}
		);
	}
}
