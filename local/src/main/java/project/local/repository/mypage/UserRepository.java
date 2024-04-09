package project.local.repository.mypage;

import org.springframework.data.jpa.repository.JpaRepository;
import project.local.entity.userInfo.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
