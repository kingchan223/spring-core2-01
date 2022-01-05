package hello.advanced.app.v6;

import hello.advanced.app.trace.callback.TraceTemplate;
import hello.advanced.app.trace.logtrace.LogTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV6 {
    private final OrderServiceV6 orderService;
    private final TraceTemplate<String> template;
    private final LogTrace trace;

    //@Autowired 생략가능
    public OrderControllerV6(OrderServiceV6 orderService, LogTrace trace) {
        this.orderService = orderService;
        this.trace = trace;
        this.template = new TraceTemplate<>(this.trace);/*생성자에서 Template 생성*/
    }

    @GetMapping("/v6/request")
    public String request(String itemId) {
        return template.execute(() -> {
            orderService.orderItem(itemId);
            return "ok";
        }, "OrderControllerV.request()");
    }
}