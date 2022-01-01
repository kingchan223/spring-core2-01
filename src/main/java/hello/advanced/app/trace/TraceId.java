package hello.advanced.app.trace;

import lombok.Getter;
import java.util.UUID;

@Getter
public class TraceId {
    private String id;
    private int level;


    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    public TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        return UUID.randomUUID().toString().substring(0, 8);//랜덤 UUID의 8자리만 사용
    }

    public TraceId createNextId(){
        //id는 그대로 level만 1증가시킨다.
        return new TraceId(id, level+1);
    }

    public TraceId createPrevious(){
        //id는 그대로 level은 1감소시킨다.
        return new TraceId(id, level - 1);
    }

    public boolean isFirstLevel(){
        return level == 0;
    }
}
