package hello.advanced.app.v4;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryV4 {
    private final LogTrace trace;/*의존 관계 주입받기*/

    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");/* begin 사용 */
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

