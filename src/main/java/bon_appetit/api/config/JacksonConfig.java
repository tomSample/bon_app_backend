package bon_appetit.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

//Classe pour empêcher les requêtes circulaires (classe A ref B qui ref A etc)
@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        Hibernate5Module hibernateModule = new Hibernate5Module();
        hibernateModule.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
        mapper.registerModule(hibernateModule);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper;
    }
}