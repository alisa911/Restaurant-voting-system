package com.plotva.votingsystem.repository;

import com.plotva.votingsystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    Vote getVoteByIdAndUserId(int id, int userId);

    List<Vote> getAllByUserId(int userId);

    @Query("SELECT COUNT(v) FROM Vote v WHERE v.restaurant.id=:id AND v.votingDate=:date")
    int getCount(@Param("id") int restaurantId, @Param("date") LocalDate date);

    @Transactional
    int deleteVoteByIdAndUserId(int id, int userId);
}
