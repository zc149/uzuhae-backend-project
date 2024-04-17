package project.local.repository.mypage;

import org.springframework.data.jpa.repository.JpaRepository;
import project.local.entity.userInfo.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    
    // 이미 존재하는 ID 인지 검증
    boolean existsById(Long id);

    // loadUserByUsername 을 구현할때는 String 파라미터를 넣기 때문에
    // JPA 기본 findById Long 타입은 사용불가라 로그인용으로 하나 만든거
//    User findByLoginId(String id);
}
