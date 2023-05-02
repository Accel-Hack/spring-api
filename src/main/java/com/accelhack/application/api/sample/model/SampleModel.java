package com.accelhack.application.api.sample.model;

import com.accelhack.application.api.sample.domain.Sample;
import com.accelhack.commons.model.Operand;
import com.accelhack.commons.model.Paging;
import jakarta.validation.constraints.Min;
import lombok.Getter;

import java.time.Instant;
import java.util.*;

public class SampleModel {

  @Getter
  public static class Entity {
    private UUID id;
    private String name;
    private Instant birthday;
    private Boolean isJapanese;

    public static Entity from(Sample sample) {
      Entity entity = new Entity();
      entity.id = sample.getId();
      entity.name = sample.getName();
      entity.birthday = sample.getBirthday();
      entity.isJapanese = sample.getIsJapanese();
      return entity;
    }
  }

  @Getter
  public static class ListEntity {
    private Paging paging;
    private List<Entity> items;

    public static ListEntity from(Selector selector, int total, List<Sample> samples) {
      ListEntity entity = new ListEntity();
      entity.paging = new Paging(total, selector.getLimit(), selector.getOffset());
      entity.items = samples.stream().map(Entity::from).toList();
      return entity;
    }
  }

  @Getter
  public static class Selector {
    private String name;

    @Min(1)
    private Integer limit;

    @Min(0)
    private Integer offset;

    public Selector(String name, Integer limit, Integer offset) {
      this.name = name;
      this.limit = Optional.ofNullable(limit).orElse(20);
      this.offset = Optional.ofNullable(offset).orElse(0);
    }
  }

  @Getter
  public static class Create implements Operand {
    private String name;
    private Instant birthday;
    private Boolean isJapanese;
  }

  @Getter
  public static class Update implements Operand {
    private UUID id;
    private String name;
    private Instant birthday;
    private Boolean isJapanese;
  }

  @Getter
  public static class Delete implements Operand {
    private UUID id;
  }
}
