package hello.advanced.app.v4;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderControllerV4 {
    private final OrderServiceV4 orderService;
    private final LogTrace trace;/*의존 관계 주입받기*/

    @GetMapping("/v4/request")
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");/*begin 사용*/
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";

        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
