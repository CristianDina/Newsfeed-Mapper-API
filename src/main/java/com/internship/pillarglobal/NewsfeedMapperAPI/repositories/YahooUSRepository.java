package com.internship.pillarglobal.NewsfeedMapperAPI.repositories;
import com.internship.pillarglobal.NewsfeedMapperAPI.models.YahooUSItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YahooUSRepository extends JpaRepository<YahooUSItem,String> {
}
