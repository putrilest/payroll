package com.yogistore.payroll.dao;

import com.yogistore.payroll.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
