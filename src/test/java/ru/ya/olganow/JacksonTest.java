package ru.ya.olganow;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ru.ya.olganow.model.Info;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonTest {

    @Test
    void jsonCatTest() throws Exception {
        File file = new File("src/test/resources/cat.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Info info = objectMapper.readValue(file, Info.class);
        assertThat(info.name).isEqualTo("Matroskin");
        assertThat(info.owner).isEqualTo("Fedor");
        assertThat(info.age).isEqualTo(3);
        assertThat(info.passport.id).isEqualTo("ABS123");
        assertThat(info.passport.isValid);

    }

}

