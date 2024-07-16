package com.viaje.viaje.repository;

import com.viaje.viaje.model.Board;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.model.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
    Board findByTravelPlans(TravelPlans travelPlans);
    List<Board> findAllByBoardStatus(Board.BoardStatus boardStatus);
    List<Board> findAllByUser(Users user);

    @Modifying
    @Transactional
    @Query("UPDATE Board b SET b.boardStatus = :status WHERE b.id = :id")
    void updateBoardStatus(@Param("id") Long id, @Param("status") Board.BoardStatus status);
}
