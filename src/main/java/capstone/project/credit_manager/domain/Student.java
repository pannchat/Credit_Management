package capstone.project.credit_manager.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("S")
public class Student extends Account{

//    @OneToOne(fetch = FetchType.LAZY)
//    private Department major;

    @OneToOne(fetch = FetchType.LAZY)
    private Department multiMajor;

    @OneToOne(fetch = FetchType.LAZY)
    private Department subMajor;

    @OneToOne(fetch = FetchType.LAZY)
    private Department beforeMajor;

    private boolean isTransfer;

    private boolean isTransferDepartment;

    @Enumerated(EnumType.STRING)
    private StudentStatus studentStatus;

}
