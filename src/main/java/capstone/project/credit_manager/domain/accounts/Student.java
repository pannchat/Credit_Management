package capstone.project.credit_manager.domain.accounts;

import capstone.project.credit_manager.domain.Department;
import capstone.project.credit_manager.domain.StudentStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("S")
public class Student extends Account {
    private static final Role role = Role.STUDENT;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department multiMajor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department subMajor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department beforeMajor;

    private boolean isTransfer;

    private boolean isTransferDepartment;

    @Enumerated(EnumType.STRING)
    private StudentStatus studentStatus;

    @Builder
    public Student(String accountId, String password, String name, Department major, Department multiMajor, Department subMajor, Department beforeMajor, boolean isTransfer, boolean isTransferDepartment, StudentStatus studentStatus) {
        super(accountId, password, name, major, role);
        this.multiMajor = multiMajor;
        this.subMajor = subMajor;
        this.beforeMajor = beforeMajor;
        this.isTransfer = isTransfer;
        this.isTransferDepartment = isTransferDepartment;
        this.studentStatus = studentStatus;
    }

    public void update(Student student) {
        super.update(student);
        this.multiMajor = student.multiMajor;
        this.subMajor = student.subMajor;
        this.beforeMajor = student.beforeMajor;
        this.isTransfer = student.isTransfer;
        this.isTransferDepartment = student.isTransferDepartment;
        this.studentStatus = student.studentStatus;
    }

    public String getMultiMajorName() {
        return multiMajor == null ? null : multiMajor.getName();
    }

    public String getSubMajorName() {
        return subMajor == null ? null : subMajor.getName();
    }

    public String getBeforeMajorName() {
        return beforeMajor == null ? null : beforeMajor.getName();
    }

    public Long getMultiMajorId() {
        return multiMajor == null ? null : multiMajor.getId();
    }

    public Long getSubMajorId() {
        return subMajor == null ? null : subMajor.getId();
    }

    public Long getBeforeMajorId() {
        return beforeMajor == null ? null : beforeMajor.getId();
    }
}
