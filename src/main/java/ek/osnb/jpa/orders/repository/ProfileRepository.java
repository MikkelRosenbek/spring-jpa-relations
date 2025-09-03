package ek.osnb.jpa.orders.repository;

import ek.osnb.jpa.orders.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
