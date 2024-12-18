package com.app.playerservicejava.repository;
import com.app.playerservicejava.model.Player;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, String> {
    List<Player> findAllByBirthMonthAndBirthYear(String birthMonth, String birthYear);
    Page<Player> findAllByBirthMonthAndBirthYear(String birthMonth, String birthYear, Pageable pageable);
    @Query("SELECT P FROM Player P WHERE P.finalGame IS NOT NULL AND P.deathYear IS NOT NULL")
    List<Player> findActiveRetiredPlayers();
    @Query(value = "SELECT * FROM PLAYERS P WHERE P.FINALGAME IS NOT NULL AND P.DEATHYEAR IS NOT NULL", nativeQuery = true)
    List<Player> findActiveRetiredPlayersNative();
    @Query(value = "SELECT * FROM PLAYERS P WHERE P.NAMEGIVEN = ?1", nativeQuery = true)
    List<Player> findPlayersByNameGivenNative(String nameGiven);
    @Query(value = "SELECT * FROM PLAYERS P WHERE P.NAMEGIVEN = :nameGiven", nativeQuery = true)
    List<Player> findPlayersByNameGivenNativeNamedParam(@Param("nameGiven") String nameGiven);

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE PLAYERS SET NAMEGIVEN = NULL WHERE NAMEGIVEN = :nameGiven",
            nativeQuery = true
    )
    int deletePlayersByNameGiven(@Param("nameGiven") String nameGiven);
    <T> Collection<T> findByLastName(String lastName, Class<T> type);
}
