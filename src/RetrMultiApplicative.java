import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RetrMultiApplicative {
	public static <A> Retr<BottomType, A> pure(Optional<A> x) {
		return $ -> new Pair<Optional<BottomType>, Optional<A>>(Optional.empty(), x);
	}

	public static <A,B,U1,U2> Function<Retr<U1, A>, Retr<Pair<U1, U2>, B>> ap(
		Retr<U2, Function<? super A, ? extends B>> f
	) {
		return (
			x -> state -> {
				Optional<U1> gstate1q = state.map(Pair::fst);
				Optional<U2> gstate2q = state.map(Pair::snd);
				Pair<Optional<U1>, Optional<A>> pair_state1q_out1q = x.get(gstate1q);
				Optional<U1> state1q = pair_state1q_out1q.getFst();
				Optional<A> out1q = pair_state1q_out1q.getSnd();
				Pair<Optional<U2>, Optional<Function<? super A, ? extends B>>>
					pair_state2q_out2q = f.get(gstate2q);
				Optional<U2> state2q = pair_state2q_out2q.getFst();
				Optional<Function<? super A, ? extends B>> out2q =
					pair_state2q_out2q.getSnd();
				Optional<B> outretn = (
					out1q.flatMap(
						out1 -> out2q.map(
							out2 -> out2.apply(out1)
						)
					)
				);
				Pair<Optional<Pair<U1, U2>>, Optional<B>> rfinal = new Pair<>(
					Optional.empty(),
					outretn
				);
				return (
					state1q.map(
						state1 ->
							state2q.map(
								state2 ->
									new Pair<>(
										Optional.ofNullable(
											new Pair<>(state1, state2)
										),
										outretn
									)
							).orElse(rfinal)
					).orElse(rfinal)
				);
			}
		);
	}

	public static <A,B,C,U1,U2> BiFunction<
		Retr<U1, A>, Retr<U2, B>, Retr<Pair<U2, Pair<U1, BottomType>>, C>
	> liftA2(
		BiFunction<? super A, ? super B, ? extends C> f
	) {
		return (
			(x1, x2) ->
				RetrMultiApplicative.<B, C, U2, Pair<U1, BottomType>>ap(
					RetrMultiApplicative.<
					    A, Function<? super B, ? extends C>, U1, BottomType
					>ap(
						RetrMultiApplicative.pure(
						    Optional.ofNullable(y1 -> y2 -> f.apply(y1, y2))
						)
					).apply(x1)
				).apply(x2)
		);
	}
}
