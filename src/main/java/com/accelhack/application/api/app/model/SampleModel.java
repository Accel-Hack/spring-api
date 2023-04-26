package com.accelhack.application.api.app.model;

import com.accelhack.application.api.app.domain.Sample;
import com.accelhack.application.api.http.*;
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
    private AHPaging paging;
    private List<Entity> items;

    public static ListEntity from(Selector selector, int total, List<Sample> samples) {
      ListEntity entity = new ListEntity();
      entity.paging = new AHPaging(total, selector.getLimit(), selector.getOffset());
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
  public static class Create implements AHOperand {
    private String name;
    private Instant birthday;
    private Boolean isJapanese;
  }

  @Getter
  public static class Update implements AHOperand {
    private UUID id;
    private String name;
    private Instant birthday;
    private Boolean isJapanese;
  }

  @Getter
  public static class Delete implements AHOperand {
    private UUID id;
  }
}
