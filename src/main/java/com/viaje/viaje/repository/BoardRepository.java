package com.viaje.viaje.repository;

import com.viaje.viaje.model.Board;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
    Board findByTravelPlans(TravelPlans travelPlans);
    List<Board> findAllByBoardStatus(Board.BoardStatus boardStatus);

    List<Board> findAllByUser(Users user);
}
