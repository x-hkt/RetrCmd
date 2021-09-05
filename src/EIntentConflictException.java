import java.util.Map;

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
			"CONFLICT!!! =========================================== ===CONFLICT===\nCONFLICT!!! (EIntentConflictException)\nYou told me to do multiple different things to the same `ISub`.\nThe first `ISub` instance was of the class [ %s ]\nThe other `ISub` instance was of the class [ %s ]\n[ FIRST.identifyTarget() ] returned [ %s ]\n[ OTHER.identifyTarget() ] returned [ %s ]\n-- FIX? maybe try and fix your implementation of `identifyTarget`\nRemember - map entries will be compared using `equals`, as in\n... [ ((Object) x).equals((Object) y) ]\n-- FIX? maybe try and fix your implementation of `equals`\nBoth of these were mentioned in an `EIntent`, and I have no idea which\n`IntentComponent` I should be invoking!\nRemember - The same `ISub` cannot be mentioned twice in an `EIntent`\n-- FIX? remove the duplicate (you have to pick one)"
		), classname1, classname2, res1str, res2str);
	}
}
