package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.PortUnreachableException;
import java.util.UUID;

@Component
@Scope(value = "request")
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("[" + uuid + "]["+ requestURL + "] " + message );
    }

    @PostConstruct
    public void init(){
        String uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] Request Scope Bean Create : " + this);
    }

    @PreDestroy
    public void close(){
        System.out.println("[" + uuid + "] Request Scope Bean Close : " + this);
    }
}
