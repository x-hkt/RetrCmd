import java.util.Optional;
import java.util.function.Function;

public class RetrMultiMonad {
	public static <A> Retr<BottomType, A> pure(Optional<A> x) {
		return $ -> new Pair<Optional<BottomType>, Optional<A>>(Optional.empty(), x);
	}

	public static <A,B,U1,U2> Function<Retr<U1, A>, Retr<RetrStateW<U1, U2>, B>> bind(
		Function<? super A, ? extends Retr<U2, B>> f
	) {
		return (
			x -> state -> {
				Optional<U1> gstate1q = state.flatMap(RetrStateW::s1);
				Optional<U2> gstate2q = state.flatMap(RetrStateW::s2);
				Pair<Optional<U1>, Optional<A>> pair_state1q_out1q = x.get(gstate1q);
				Optional<U1> state1q = pair_state1q_out1q.getFst();
				Optional<A> out1q = pair_state1q_out1q.getSnd();
				return out1q.map(f).map(
					retr2 -> {
						Pair<Optional<U2>, Optional<B>> pair_state2q_out2q =
							retr2.get(gstate2q);
						Optional<U2> state2q = pair_state2q_out2q.getFst();
						Optional<B> out2q = pair_state2q_out2q.getSnd();
						return state2q.map(
							state2 ->
								new Pair<Optional<RetrStateW<U1, U2>>, Optional<B>>(
									Optional.ofNullable(
										new RetrStateW<>(
											gstate1q,
											Optional.ofNullable(state2)
										)
									),
									out2q
								)
						).orElse(
							new Pair<Optional<RetrStateW<U1, U2>>, Optional<B>>(
								state1q.map(
									y -> new RetrStateW<>(
										Optional.ofNullable(y),
										Optional.empty()
									)
								),
								out2q
							)
						);
					}
				).orElse(
					new Pair<Optional<RetrStateW<U1, U2>>, Optional<B>>(
						Optional.ofNullable(
							new RetrStateW<>(state1q, gstate2q)
						),
						Optional.empty()
					)
				);
			}
		);
	}
}
