package hello.advanced.app.trace.thread_local;

import hello.advanced.app.trace.thread_local.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {
    private final ThreadLocalService fieldService = new ThreadLocalService();

    @Test
    void field(){
        log.info("main start");
        Runnable userA = ()-> fieldService.logic("userA");
        Runnable userB = ()-> fieldService.logic("userB");

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(100);/*이제는 100일 때도 동시성 문제가 발생하지 않는다. */
        threadB.start();

        sleep(3000);//메인 쓰레드 종료 대기
        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
