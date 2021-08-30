import java.util.Optional;

public class RetrCmd<Q> extends CommandBase {
	private final Retr<Q, EIntent> cmd;
	private Optional<Q> cmdState;
	private boolean cmdIsFinished;
	private final boolean isAuto;
	
	public RetrCmd(boolean isAuto, Retr<Q, EIntent> cmd, ISub... subs) {
		this.cmd = cmd;
		this.cmdState = Optional.empty();
		this.cmdIsFinished = false;
		for (ISub sub : subs) {
			addRequirements(sub);
		}
		this.isAuto = isAuto;
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
