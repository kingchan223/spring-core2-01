package hello.advanced.app.v6;

import hello.advanced.app.trace.callback.TraceTemplate;
import hello.advanced.app.trace.logtrace.LogTrace;
import hello.advanced.app.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV6 {
    private final OrderRepositoryV6 orderRepository;
    private final TraceTemplate<Void> template;
    private final LogTrace trace;

    public OrderServiceV6(OrderRepositoryV6 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.trace = trace;
        this.template = new TraceTemplate<>(this.trace);
    }

    public void orderItem(String itemId){
        template.execute(() -> {
            orderRepository.save(itemId);
            return null;
        }, "OrderService.orderItem");
    }
}
