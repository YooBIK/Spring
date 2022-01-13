package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION,classes = Configuration.class)
)
public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }


}
