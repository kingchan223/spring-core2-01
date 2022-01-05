package hello.advanced.app.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;
/*
* 필드에 전략을 보관하는 방식
* */
@Slf4j
public class ContextV1 {
    private final Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }
    //context는 변하지 않는 로직을 뜻함.
    public void execute(){
        long startTime = System.currentTimeMillis();

        /*-----비즈니스 로직 실행-----*/
        strategy.call(); // 위임을 사용
        /*-----비즈니스 로직 종료-----*/

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
