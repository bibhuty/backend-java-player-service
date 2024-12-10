package com.app.playerservicejava.repository;
import com.app.playerservicejava.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, String> {
    List<Player> findAllByBirthMonthAndBirthYear(String birthMonth, String birthYear);
    Page<Player> findAllByBirthMonthAndBirthYear(String birthMonth, String birthYear, Pageable pageable);
}
