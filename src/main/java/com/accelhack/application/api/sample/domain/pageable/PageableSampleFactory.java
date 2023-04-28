package com.accelhack.application.api.sample.domain.pageable;

import com.accelhack.application.api.sample.domain.*;

import java.util.*;

public interface PageableSampleFactory {
  PageableSample create(Integer total, List<Sample> samples);
}
