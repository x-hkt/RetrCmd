public class IntentComponent<T extends ISub> implements GeneralizedAction {
	private final T sub;
	private final Consumer<? super T> consumer;

	public IntentComponent(T sub, Consumer<? super T> consumer) {
		this.sub = sub;
		this.consumer = consumer;
	}
	
	public T getSub() { return this.sub; }
	public Consumer<? super T> getConsumer() { return this.consumer; }
	
	public void invoke() { this.consumer.accept(this.sub); }
}
