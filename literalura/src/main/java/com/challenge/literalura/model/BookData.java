package com.challenge.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(
        @JsonAlias("id") int id,
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<AuthorData> author,
        @JsonAlias("subjects") List<String> subjects,
        @JsonAlias("languages") List<String> langs,
        @JsonAlias("copyright") boolean copyright,
        @JsonAlias("download_count") int downloads
) {
}
