package repository;

import com.sprints.OnlineVotingSystem.model.Election;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface ElectionRepository extends CrudRepository<Election, Long> {


    @Query("SELECT e FROM Election e WHERE :currentTime BETWEEN e.startTime AND e.endTime")
    List<Election> findActiveElections(@Param("currentTime") LocalDateTime currentTime);


    @Query("SELECT e FROM Election e WHERE e.startTime > :currentTime")
    List<Election> findUpcomingElections(@Param("currentTime") LocalDateTime currentTime);


    @Query("SELECT e FROM Election e WHERE e.endTime < :currentTime")
    List<Election> findCompletedElections(@Param("currentTime") LocalDateTime currentTime);

    List<Election> findByTitleContaining(String title);

    @Query("SELECT e FROM Election e WHERE e.startTime >= :startDate AND e.endTime <= :endDate")
    List<Election> findElectionsByDateRange(@Param("startDate") LocalDateTime startDate,
                                            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT e FROM Election e LEFT JOIN FETCH e.candidates WHERE e.election_id = :electionId")
    Optional<Election> findElectionWithCandidates(@Param("electionId") Long electionId);


    @Modifying
    @Query("UPDATE Election e SET e.endTime = :newEndTime WHERE e.election_id = :electionId")
    int updateElectionEndTime(@Param("electionId") Long electionId, @Param("newEndTime") LocalDateTime newEndTime);


    Optional<Election> findByTitle(String title);

    boolean existsByTitle(String title);


    List<Election> findByStartTimeAfter(LocalDateTime dateTime);

    List<Election> findByEndTimeBefore(LocalDateTime dateTime);

    @Query("SELECT COUNT(e) FROM Election e")
    long countAllElections();

    @Query("SELECT e FROM Election e ORDER BY e.startTime ASC")
    List<Election> findAllOrderByStartTime();

    @Query("SELECT e FROM Election e ORDER BY e.startTime DESC")
    List<Election> findAllOrderByStartTimeDesc();

    @Query("SELECT e FROM Election e WHERE LOWER(e.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Election> findByTitleContainingIgnoreCase(@Param("title") String title);

    @Query("SELECT e FROM Election e WHERE e.startTime < :endTime AND e.endTime > :startTime")
    List<Election> findElectionsOverlappingWithTimeRange(@Param("startTime") LocalDateTime startTime,
                                                         @Param("endTime") LocalDateTime endTime);

    @Query("SELECT e FROM Election e WHERE :currentTime BETWEEN e.startTime AND e.endTime ORDER BY e.startTime ASC")
    Optional<Election> findCurrentActiveElection(@Param("currentTime") LocalDateTime currentTime);

    @Query("SELECT e FROM Election e LEFT JOIN FETCH e.votes WHERE e.election_id = :electionId")
    Optional<Election> findElectionWithVotes(@Param("electionId") Long electionId);

    @Query("SELECT COUNT(e) > 0 FROM Election e WHERE e.election_id != :electionId AND e.startTime < :endTime AND e.endTime > :startTime")
    boolean existsConflictingElection(@Param("electionId") Long electionId, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}