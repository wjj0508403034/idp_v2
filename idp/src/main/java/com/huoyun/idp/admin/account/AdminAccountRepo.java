package com.huoyun.idp.admin.account;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminAccountRepo extends PagingAndSortingRepository<AdminAccount, Long> {

	@Query("select t from AdminAccount t where t.email = ?1")
	AdminAccount findAccountByEmail(String email);
}
