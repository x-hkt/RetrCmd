@FunctionalInterface
public interface VarargFunction<T, R> {
	public R r(T... args);
}
