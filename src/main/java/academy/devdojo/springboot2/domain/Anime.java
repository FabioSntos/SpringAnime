package academy.devdojo.springboot2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Anime {
    Long id;
    private String name;
}
