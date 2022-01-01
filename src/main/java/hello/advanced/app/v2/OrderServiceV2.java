package hello.advanced.app.v2;

import hello.advanced.app.trace.TraceId;
import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.hello_trace.HelloTraceV1;
import hello.advanced.app.trace.hello_trace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceV2 {
    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 trace;

    public void orderItem(TraceId traceId, String itemId){
        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId, "OrderService.orderItem()");/* beginSync 사용 */
            orderRepository.save(status.getTraceId(), itemId);/* OrderRepository에 TraceID를 넘겨준다. */
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;//예외를 반드시 던져줘야 한다.
        }
    }
}
