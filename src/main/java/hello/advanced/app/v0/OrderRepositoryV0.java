package hello.advanced.app.v0;

import org.springframework.stereotype.Repository;


@Repository
public class OrderRepositoryV0 {
    public void save(String itemId) {
        //저장 로직
        if (itemId.equals("ex")) throw new IllegalStateException("예외 발생!");
        //저장 로직을 수행하는데 1초가 걸린다고 가정
        sleep(1000);
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

