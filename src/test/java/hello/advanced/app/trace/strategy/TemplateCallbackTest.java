package hello.advanced.app.trace.strategy;

import hello.advanced.app.trace.strategy.code.template_callback.Callback;
import hello.advanced.app.trace.strategy.code.template_callback.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {

    /*
    * 템플릿 콜백 패턴
    * */
    @Test
    void callbackV2(){
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });
        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
    }


    //람다 사용
    @Test
    void callbackV1(){
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(()->log.info("비즈니스 로직1 실행"));
        template.execute(()->log.info("비즈니스 로직2 실행"));
    }
}
