package org.github.ywoxys.kernel.base.account.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.github.ywoxys.kernel.base.account.permission.enums.AssignedType;
import org.github.ywoxys.kernel.base.account.permission.group.Rank;
import org.github.ywoxys.kernel.base.account.permission.tag.Tag;

@Getter @Setter
@AllArgsConstructor
public class Permission {

    private Rank rank;
    private Tag tag;
    private AssignedType type;
    private String assignedBy;
    private Long time;
}
