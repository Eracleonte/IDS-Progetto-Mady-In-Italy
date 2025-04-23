package it.cs.unicam.progetto.mady.in.italy.repos.users;

import it.cs.unicam.progetto.mady.in.italy.model.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     *
     * Approves the User with the specified id
     *
     * @param id the id of the User
     */
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.approved = true WHERE u.id = :id")
    void approve(@Param("id") Integer id);

    /**
     *
     * Deletes the User with the specified id due to it being rejected
     *
     * @param id the id of the User
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id = :id")
    void reject(@Param("id") Integer id);

}
