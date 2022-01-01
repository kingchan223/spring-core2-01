package hello.advanced.app.v1;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.hello_trace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryV1 {
    private final HelloTraceV1 trace;

    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");
            // --- 저장 로직 ---
            if (itemId.equals("ex")) throw new IllegalStateException("예외 발생!");
            //저장 로직을 수행하는데 1초가 걸린다고 가정
            sleep(1000);
            // ---------------
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;//예외를 반드시 던져줘야 한다.
        }
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

