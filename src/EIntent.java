import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class EIntent implements GeneralizedAction {
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

	public void invoke() {
		for (IntentComponent component : this.items) {
			component.invoke();
		}
	}

	public void checkForConflicts() {
		List<ISub> alreadySeen = new List<ISub>();
		for (IntentComponent component : this.items) {
			for (ISub toCheckAgainst : alreadySeen) {
				if (
					component
						.getSub()
						.identifyTarget()
						.equals(toCheckAgainst.identifyTarget())
				) {
					throw new EIntentConfictException(
						component.getSub().getClass().getName(),
						toCheckAgainst.getClass().getName(),
						component.getSub().identifyTarget(),
						toCheckAgainst.identifyTarget()
					);
				}
			}
			alreadySeen.add((ISub) component.getSub());
		}
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
