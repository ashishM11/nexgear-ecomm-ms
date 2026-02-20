package com.ecommerce.app.response;

import java.io.Serializable;
import java.util.Collection;

public record UserRoleResponseRecord(
    Long userRoleId,
        String userRoleName,
        Collection<UserRolePrivilegeResponseRecord> privileges
) implements Serializable {

}
