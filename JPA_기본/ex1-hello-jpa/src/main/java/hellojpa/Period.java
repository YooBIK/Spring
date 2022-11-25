package hellojpa;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

/*
임베디드 타입이라는 것을 명시,
기본 생성자 필수 !
 */
@Embeddable
public class Period {

    public Period() {
    }

    public Period(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Period setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Period setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }
}
