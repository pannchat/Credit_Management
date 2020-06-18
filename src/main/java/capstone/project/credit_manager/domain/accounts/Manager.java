package capstone.project.credit_manager.domain.accounts;

import capstone.project.credit_manager.domain.Department;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("M")
public class Manager extends Account {
    private static final Role role = Role.MANAGER;

    @Builder
    public Manager(String accountId, String password, String name, Department major) {
        super(accountId, password, name, major, role);
    }

}