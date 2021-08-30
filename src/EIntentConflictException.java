public class EIntentConflictException extends Exception {
	public EIntentConflictException(
		String classname1, String classname2,
		Map<String, Object> res1, Map<String, Object> res2
	) {
		String res1str = res1
			.keySet()
			.stream()
			.map(mkey -> mkey + "= " + map.get(mkey).toString())
			.collect(Collectors.joining(", ", "(1) Map { ", " }"));
		String res2str = res2
			.keySet()
			.stream()
			.map(mkey -> mkey + "= " + map.get(mkey).toString())
			.collect(Collectors.joining(", ", "(2) Map { ", " }"));
		super(String.format(
			"""
			CONFLICT!!! =========================================== ===CONFLICT===
			CONFLICT!!! (EIntentConflictException)
			You told me to do multiple different things to the same `ISub`.
			The first `ISub` instance was of the class [ %s ]
			The other `ISub` instance was of the class [ %s ]
			[ FIRST.identifyTarget() ] returned [ %s ]
			[ OTHER.identifyTarget() ] returned [ %s ]
			-- FIX? maybe try and fix your implementation of `identifyTarget`
			Remember - map entries will be compared using `equals`, as in
			... [ ((Object) x).equals((Object) y) ]
			-- FIX? maybe try and fix your implementation of `equals`
			Both of these were mentioned in an `EIntent`, and I have no idea which
			`IntentComponent` I should be invoking!
			Remember - The same `ISub` cannot be mentioned twice in an `EIntent`
			-- FIX? remove the duplicate (you have to pick one)
			"""
		), classname1, classname2, res1str, res2str);
	}
}
