package br.com.supernova.tech.gingasports.infra.repository;

import br.com.supernova.tech.gingasports.domain.team.dao.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByName(String name);

    Page<Team> findAllByActiveTrue(Pageable record);
}
