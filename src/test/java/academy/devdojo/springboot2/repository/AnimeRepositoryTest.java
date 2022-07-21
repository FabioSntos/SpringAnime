package academy.devdojo.springboot2.repository;

import academy.devdojo.springboot2.domain.Anime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@DisplayName("AnimeRepositoryTest")
class AnimeRepositoryTest {
    @Autowired
    private AnimeRepository animeRepository;

    @Test
    void shouldPersistAnimeWhenSuccessful() {
        Anime anime = createAnime();
        Anime saved = animeRepository.save(anime);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo(anime.getName());
    }

    private Anime createAnime() {
        return Anime.builder()
                .name("Dragon Ball")
                .build();
    }

}