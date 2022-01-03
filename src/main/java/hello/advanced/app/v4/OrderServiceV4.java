package hello.advanced.app.v4;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceV4 {
    private final OrderRepositoryV4 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId){
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");/* beginSync 사용 */
            orderRepository.save(itemId);/* OrderRepository에 TraceID를 넘겨준다. */
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;//예외를 반드시 던져줘야 한다.
        }
    }
}
