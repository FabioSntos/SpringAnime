package academy.devdojo.springboot2.repository;

import academy.devdojo.springboot2.domain.Anime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.*;


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

    @Test
    void shouldUpdateAnimeWhenSuccessful() {
        Anime anime = createAnime();
        Anime saved = animeRepository.save(anime);

        anime.setName("DeathNote");

        Anime animeUpdated = animeRepository.save(anime);

        assertThat(animeUpdated.getId()).isNotNull();

        assertThat(animeUpdated.getName()).isEqualTo(saved.getName());
    }

    @Test
    void shouldRemoveAnimeWhenSuccessful() {
        Anime anime = createAnime();

        Anime saved = animeRepository.save(anime);

        animeRepository.delete(saved);

        Optional<Anime> animeOptional = animeRepository.findById(saved.getId());

        assertThat(animeOptional).isEmpty();

    }

    @Test
    void shouldFindAnimeByNameWhenSuccessful() {
        Anime anime = createAnime();

        Anime saved = animeRepository.save(anime);

        List<Anime> byName = animeRepository.findByName(anime.getName());

        assertThat(byName.isEmpty()).isFalse();

        assertThat(byName.contains(saved)).isTrue();


    }

    @Test
    void shouldReturnEmptyListWhenTriesFindByNameWhenNoAnimeIsFounded() {
        List<Anime> byName = animeRepository.findByName("anime.getName()");

        assertThat(byName.isEmpty()).isTrue();

    }

    @Test
    void shouldThrowExceptionWhenNameIsEmptyWhileSavingAnime(){
        Anime anime = new Anime();

//        assertThatThrownBy(() -> this.animeRepository.save(anime))
//                .isInstanceOf(ConstraintViolationException.class);

        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.animeRepository.save(anime))
                .withMessageContaining("Name is required");
    }

    private Anime createAnime() {
        return Anime.builder()
                .name("Dragon Ball")
                .build();
    }

}