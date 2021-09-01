package com.internship.pillarglobal.NewsfeedMapperAPI.repositories;

import com.internship.pillarglobal.NewsfeedMapperAPI.models.MsnUKItem;
import com.internship.pillarglobal.NewsfeedMapperAPI.models.MsnUKItemForDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MsnUKRepository extends JpaRepository<MsnUKItemForDB,String> {
}
