public class Pair<J1, J2> {
	private final J1 item1;
	private final J2 item2;

	public Pair(J1 item1, J2 item2) {
		this.item1 = item1;
		this.item2 = item2;
	}

	public static <I1, I2> I1 fst(Pair<I1, I2> x) { return x.getFst(); }
	public static <I1, I2> I2 snd(Pair<I1, I2> x) { return x.getSnd(); }

	public J1 getFst() { return this.item1; }
	public J2 getSnd() { return this.item2; }
}
