package hello.advanced.app.trace.logtrace;

import hello.advanced.app.trace.TraceId;
import hello.advanced.app.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalLogTrace implements LogTrace{
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>(); // traceId 동기화, 동시성 이슈 발생

    @Override
    public TraceStatus begin(String message) {
        syncTraceId();
        TraceId traceId = this.traceIdHolder.get();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    private void syncTraceId(){
        TraceId traceId = this.traceIdHolder.get();
        if(traceId == null) this.traceIdHolder.set(new TraceId());
        else this.traceIdHolder.set(traceId.createNextId());
    }

    @Override//이전과 같음
    public void end(TraceStatus status) {
        complete(status, null);
    }

    @Override//이전과 같음
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(),
                    addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(),
                    addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs, e.toString());
        }
        releaseTraceId();
    }


    private void releaseTraceId(){
        TraceId traceId = this.traceIdHolder.get();
        assert traceId != null;
        if(traceId.isFirstLevel()) this.traceIdHolder.remove();/* remove를 해서 현재 쓰레드의 value를 완전히 삭제한다. */
        else this.traceIdHolder.set(traceId.createPreviousId());
    }

    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) sb.append( (i == level - 1) ? "|" + prefix : "|   ");
        return sb.toString();
    }
}
