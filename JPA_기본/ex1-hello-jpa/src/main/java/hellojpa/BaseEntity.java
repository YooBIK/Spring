package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*
@MappedSuperclass
상속 관계 매핑이 아님
이 클래스는 엔티티가 아님 -> 테이블이 생성되지 않음
테이블과 관계 없음
공통적인 매핑 정보를 모으는 역할
속성을 상속받은 클래스에 매핑 정보만 제공
보통 굳이 생성해서 사용할 일이 없으므로 추상 클래스로 선언하는 것을 권장
 */
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "CREATE_MEMBER")
    private String createdBy;
    private LocalDateTime createdDate;
    private String modifiedBy;
    private LocalDateTime modifiedDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
