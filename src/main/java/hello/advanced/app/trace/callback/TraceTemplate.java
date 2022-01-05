package hello.advanced.app.trace.callback;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.logtrace.LogTrace;

public class TraceTemplate<T> {
    private final LogTrace trace;

    public TraceTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public <T> T execute(TraceCallback<T> callback, String message) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);
            T result = callback.call();/*로직*/
            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
