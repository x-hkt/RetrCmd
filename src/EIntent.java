import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class EIntent implements GeneralizedAction {
	private final List<IntentComponent> items;

	public EIntent(IntentComponent... itemsgiven) {
		this.items = Arrays.asList(itemsgiven).stream().collect(Collectors.toList());
		this.checkForConflicts();
	}

	public EIntent(List<IntentComponent> itemsgiven) {
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
	    try {
    		List<ISub> alreadySeen = new ArrayList<ISub>();
    		for (IntentComponent component : this.items) {
    			for (ISub toCheckAgainst : alreadySeen) {
    				if (
    					component
    						.getSub()
    						.identifyTarget()
    						.equals(toCheckAgainst.identifyTarget())
    				) {
    					throw new EIntentConflictException(
    						component.getSub().getClass().getName(),
    						toCheckAgainst.getClass().getName(),
    						component.getSub().identifyTarget(),
    						toCheckAgainst.identifyTarget()
    					);
    				}
    			}
    			alreadySeen.add((ISub) component.getSub());
    		}
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
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
