package com.javaTask.urlShortening.repository;

import com.javaTask.urlShortening.model.Statistics;
import com.javaTask.urlShortening.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
}
