package hello.advanced.app.trace.strategy;

import hello.advanced.app.trace.strategy.code.strategy.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    @Test
    void strategyV4_0() {
        ContextV2 contextV2 = new ContextV2();//의존성 주입
        contextV2.execute(new StrategyLogic1());
        contextV2.execute(new StrategyLogic2());
    }

    //익명 내부 클래스 사용
    @Test
    void strategyV4_1() {
        ContextV2 contextV2 = new ContextV2();//의존성 주입
        contextV2.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });
        contextV2.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
    }

    //람다식 사용
    @Test
    void strategyV4_2() {
        ContextV2 contextV2 = new ContextV2();//의존성 주입
        contextV2.execute(()->log.info("비즈니스 로직1 실행"));
        contextV2.execute(()->log.info("비즈니스 로직2 실행"));
    }
}
