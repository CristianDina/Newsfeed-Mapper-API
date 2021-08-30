package com.internship.pillarglobal.NewsfeedMapperAPI.repositories;

import com.internship.pillarglobal.NewsfeedMapperAPI.models.YahooUKItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YahooUKRepository extends JpaRepository<YahooUKItem,String> {

}
