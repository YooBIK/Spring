package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.VerificationOverTimeImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    @DisplayName("")
    void statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
        //ThreadA: A사용자가 10000원 주문
        statefulService1.order("userA",10000);
        //ThreadB: B사용자가 20000원 주문
        statefulService2.order("userB",20000);
        //ThreadA: A사용자가 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }
    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}