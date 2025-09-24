package io.slipknot.spring6.learningtest;

import static java.util.Comparator.comparingInt;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class SortTest {

  @Nested
  class Sort {

    Sort sort;

    @BeforeEach
    void forEach() {
      sort = new Sort();
    }

    public List<String> sortByLength(List<String> list) {
      list.sort(comparingInt(String::length));
      return list;
    }

    @Test
    @DisplayName("길이에 따라 정렬")
    void sort() {
      // 준비(Given)

      // 실행(When)
      List<String> sortedList = sort.sortByLength(Arrays.asList("aa", "b"));

      // 검중(Then)
      Assertions.assertThat(sortedList).isEqualTo(List.of("b", "aa"));
    }

    @Test
    @DisplayName("길이에 따라 정렬 - 아이템 3개")
    void sort3Items() {
      // 준비(Given)

      // 실행(When)
      List<String> sortedList = sort.sortByLength(Arrays.asList("ccc", "a", "bb"));

      // 검중(Then)
      Assertions.assertThat(sortedList).isEqualTo(List.of("a", "bb", "ccc"));
    }

    @Test
    @DisplayName("이미 정렬되어 있는 Item에 대한 테스트")
    void sortAlreadySorted() {
      // 준비(Given)

      // 실행(When)
      List<String> sortedList = sort.sortByLength(Arrays.asList("a", "bb", "ccc"));

      // 검중(Then)
      Assertions.assertThat(sortedList).isEqualTo(List.of("a", "bb", "ccc"));
    }
  }

}