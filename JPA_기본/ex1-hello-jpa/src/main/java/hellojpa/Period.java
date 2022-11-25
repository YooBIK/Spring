package hellojpa;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Objects;

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

    /*
    자바의 == 비교는 동일성 비교 ! 즉, 참조가 같은지 확인한다.
    그러므로 비교를 위해 equals 함수 override가 필요하다!!
    */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(startDate, period.startDate) && Objects.equals(endDate, period.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
