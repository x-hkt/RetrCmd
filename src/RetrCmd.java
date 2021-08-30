import java.util.Optional;

public abstract /*WRAPPER*/ class RetrCmd<Q> extends CommandBase {
	private final Retr<Q, EIntent> cmd;
	private Optional<Q> cmdState;
	private boolean cmdIsFinished;
	private ISub[] subs;
	private final boolean isAuto;
	
	public RetrCmd(boolean isAuto, Retr<Q, EIntent> cmd, ISub... subs) {
		this.cmd = cmd;
		this.cmdState = Optional.empty();
		this.cmdIsFinished = false;
		this.subs = subs;
		this.isAuto = isAuto;
		for (ISub sub : this.subs) {
			addRequirements(sub);
		}
	}
	
	@Override
	public void initialize() {}
	
	@Override
	public void execute(boolean interrupted) {
		////TODO
	}
	
	@Override
	public void end() {}
	
	@Override
	public boolean isFinished() { return this.cmdIsFinished; }
}
