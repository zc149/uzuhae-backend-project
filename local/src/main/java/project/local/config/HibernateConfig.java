package project.local.config;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {

    @Bean
    public Hibernate5Module hibernate5Module() {
        Hibernate5Module module = new Hibernate5Module();
        // 지연 로딩된 엔티티를 강제로 로드하는 옵션 활성화
        module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
        return module;
    }
}
