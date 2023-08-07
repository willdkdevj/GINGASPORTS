package br.com.supernova.tech.gingasports.infra.repository;

import br.com.supernova.tech.gingasports.domain.enums.Sport;
import br.com.supernova.tech.gingasports.domain.player.dao.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByAlias(String alias);

    Page<Player> findAllByActiveTrue(Pageable page);

//    @Query("""
//            SELECT p FROM Player p
//            WHERE p.active = 1
//            AND p.sport = :sport
//            """)
//    List<Player> findAllBySport(Sport sport);

    List<Player> findByPreference_SportEquals(Sport sport);

    List<Player> findByActiveTrueAndPreference_SportEquals(Sport sport);
}
