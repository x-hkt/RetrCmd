public class EIntentConflictException extends Exception {
	public EIntentConflictException(String classname1, String classname2) {
		super(String.format(
			"""
			CONFLICT!!! =========================================== ===CONFLICT===
			CONFLICT!!! (EIntentConflictException)
			You told me to do multiple different things to the same `ISub`.
			The first `ISub` instance was of the class [ %s ]
			The other `ISub` instance was of the class [ %s ]
			[ FIRST.hasSameTarget(OTHER) ] returned [ true ]
			-- FIX? maybe try and fix your implementation of `hasSameTarget`
			Both of these were mentioned in an `EIntent`, and I have no idea which
			`IntentComponent` I should be invoking!
			Remember - The same `ISub` cannot be mentioned twice in an `EIntent`
			-- FIX? remove the duplicate (you have to pick one)
			"""
		), classname1, classname2);
	}
}
