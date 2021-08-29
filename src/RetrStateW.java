import java.util.Optional;

public class RetrStateW<U1, U2> {
	private final Optional<U1> s1val;
	private final Optional<U2> s2val;

	public RetrStateW(Optional<U1> s1val, Optional<U2> s2val) {
		this.s1val = s1val;
		this.s2val = s2val;
	}

	public static <V1, V2> Optional<V1> s1(RetrStateW<V1, V2> x) { return x.getS1(); }
	public static <V1, V2> Optional<V2> s2(RetrStateW<V1, V2> x) { return x.getS2(); }

	public Optional<U1> getS1() { return this.s1val; }
	public Optional<U2> getS2() { return this.s2val; }
}
