import java.util.Arrays;
import java.util.function.BiFunction;

public class Reduction<T> implements VarargFunction<T, T> {
	private final BiFunction<? super T, ? super T, ? extends T> accfn;

	public Reduction(BiFunction<? super T, ? super T, ? extends T> accfn) {
		this.accfn = accfn;
	}

	public static <T1> Reduction<T1> of(
		BiFunction<? super T1, ? super T1, ? extends T> accfnG
	) { return new Reduction<T>(accfnG); }

	public T r(T... args) {
		if (args.length == 0) {
			throw new UnsupportedOperationException(
				"Reduction<T> cannot handle an empty array (no values given => return what?)"
			);
		} else if (args.length == 1) {
			return args[0];
		} else {
			return accfn(
				args[0],
				this.r(Arrays.copyOfRange(args, 1, args.length))
			);
		}
	}
}
