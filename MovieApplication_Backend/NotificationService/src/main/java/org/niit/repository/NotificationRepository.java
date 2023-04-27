package org.niit.repository;

import org.niit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotificationRepository extends JpaRepository<User,String> {
}
