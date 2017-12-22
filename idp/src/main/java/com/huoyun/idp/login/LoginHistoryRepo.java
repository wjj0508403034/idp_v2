package com.huoyun.idp.login;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginHistoryRepo extends PagingAndSortingRepository<LoginHistory, Long>{

}
