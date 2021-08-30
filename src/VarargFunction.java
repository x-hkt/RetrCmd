@FunctionalInterface
public interface VarargFunction<T, R> {
	public R apply(T... args);
}
