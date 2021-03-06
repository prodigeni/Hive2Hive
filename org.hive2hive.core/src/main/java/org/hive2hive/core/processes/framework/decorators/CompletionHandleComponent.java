package org.hive2hive.core.processes.framework.decorators;

import org.hive2hive.core.processes.framework.RollbackReason;
import org.hive2hive.core.processes.framework.abstracts.ProcessDecorator;
import org.hive2hive.core.processes.framework.exceptions.InvalidProcessStateException;
import org.hive2hive.core.processes.framework.exceptions.ProcessExecutionException;
import org.hive2hive.core.processes.framework.interfaces.IProcessComponent;
import org.hive2hive.core.processes.framework.interfaces.IProcessComponentListener;

/**
 * A {@link ProcessDecorator} that executes an event, defined in the {@link ICompletionHandle}, after completion of the
 * wrapped {@link IProcessComponent}.
 * 
 * @author Christian
 * 
 */
public class CompletionHandleComponent extends ProcessDecorator {

	private final ICompletionHandle handle;

	public CompletionHandleComponent(IProcessComponent decoratedComponent, ICompletionHandle handle) {
		super(decoratedComponent);
		this.handle = handle;
	}

	@Override
	protected void doExecute() throws InvalidProcessStateException, ProcessExecutionException {
		IProcessComponentListener listener = new IProcessComponentListener() {
			public void onSucceeded() {
				handle.onCompletionSuccess();
			}

			public void onFailed(RollbackReason reason) {
				handle.onCompletionFailure(reason);
			}
		};

		decoratedComponent.attachListener(listener);
		decoratedComponent.start();
	}

	@Override
	protected void doPause() throws InvalidProcessStateException {
		decoratedComponent.pause();
	}

	@Override
	protected void doResumeExecution() throws InvalidProcessStateException {
		decoratedComponent.resume();
	}

	@Override
	protected void doResumeRollback() throws InvalidProcessStateException {
		decoratedComponent.resume();
	}

	@Override
	protected void doRollback(RollbackReason reason) throws InvalidProcessStateException {
		decoratedComponent.cancel(reason);
	}

}
