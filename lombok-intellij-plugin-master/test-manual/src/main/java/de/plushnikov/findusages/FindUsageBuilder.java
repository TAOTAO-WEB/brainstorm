package de.plushnikov.findusages;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

@Builder
@Getter
@Accessors(prefix = "m")
public class FindUsageBuilder {
  private int mFoo;
  private String mBar;

  public static void main(String[] args) {
    FindUsageBuilder findUsageBuilder = FindUsageBuilder.builder()
      .bar("bar")
      .foo(1981)
      .build();

    System.out.println("Bar is: " + findUsageBuilder.getBar());
    System.out.println("Foo is: " + findUsageBuilder.getFoo());
  }
}
