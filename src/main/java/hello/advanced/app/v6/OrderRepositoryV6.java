package hello.advanced.app.v6;

import hello.advanced.app.trace.callback.TraceTemplate;
import hello.advanced.app.trace.logtrace.LogTrace;
import hello.advanced.app.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV6 {
    private final LogTrace trace;
    private final TraceTemplate<Void> template;

    public OrderRepositoryV6(LogTrace trace) {
        this.trace = trace;
        this.template = new TraceTemplate<>(this.trace);
    }

    public void save(String itemId) {
        template.execute(()-> {
            saveDbLogic(itemId, 1000);
            return null;
        }, "OrderRepository.save()");
    }


    private void saveDbLogic(String itemId, int millis){
        System.out.println(itemId+"을 저장");
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

