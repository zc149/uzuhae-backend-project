package project.local.repository.mypage;

import org.springframework.data.jpa.repository.JpaRepository;
import project.local.entity.userInfo.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    
    // 이미 존재하는 ID 인지 검증
    boolean existsById(Long id);

}
