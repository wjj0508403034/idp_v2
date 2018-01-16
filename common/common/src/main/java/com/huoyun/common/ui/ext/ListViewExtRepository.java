package com.huoyun.common.ui.ext;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListViewExtRepository extends
		CrudRepository<ListViewExt, Long> {

	@Query("select t from ListViewExt t where t.ownerId = ?1")
	ListViewExt findByOwnerId(Long ownerId);

}
