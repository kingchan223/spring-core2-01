package hello.advanced.app.trace.logtrace;

import hello.advanced.app.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldLogTraceTest {

    FieldLogTrace trace = new FieldLogTrace();

    @Test
    void begin_end_level2() {
        TraceStatus trace1 = trace.begin("hello1");
        TraceStatus trace2 = trace.begin("hello2");
        trace.end(trace2);
        trace.end(trace1);
    }


    @Test
    void begin_exception_level2() {
        TraceStatus trace1 = trace.begin("hello1");
        TraceStatus trace2 = trace.begin("hello2");
        trace.exception(trace2, new IllegalStateException());
        trace.exception(trace1, new IllegalStateException());
    }
}


