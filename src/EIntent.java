import java.util.Arrays;
import java.util.List;

public class EIntent {
	private final List<IntentComponent> items;

	public Intent(IntentComponent... itemsgiven) {
		this.items = Arrays.asList(itemsgiven).stream().collect(Collectors.toList());
		this.checkForConflicts();
	}

	public Intent(List<IntentComponent> itemsgiven) {
		this.items = itemsgiven.stream().collect(Collectors.toList());
		this.checkForConflicts();
	}

	public List<IntentComponent> getIntentComponents() {
		this.checkForConflicts();
		return this.items;
	}

	public void checkForConflicts() {
		////TODO
	}

	public static <T extends ISub> IntentComponent<T> component(
		T sub, Consumer<? super T> consumer
	) { return new IntentComponent<T>(sub, consumer); }

	public static EIntent cat(EIntent x, EIntent y) {
		List<IntentComponent> combined = new ArrayList<IntentComponent>();
		combined.addAll(x.getIntentComponents());
		combined.addAll(y.getIntentComponents());
		return new EIntent(combined);
	}
}
