package com.internship.pillarglobal.NewsfeedMapperAPI.repositories;

import com.internship.pillarglobal.NewsfeedMapperAPI.models.MsnUKItemForDB;
import com.internship.pillarglobal.NewsfeedMapperAPI.models.MsnUSItemForDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MsnUSRepository extends JpaRepository<MsnUSItemForDB,String> {
}
