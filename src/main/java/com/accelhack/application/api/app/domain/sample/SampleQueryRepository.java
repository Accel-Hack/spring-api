package com.accelhack.application.api.app.domain.sample;

import com.accelhack.application.api.app.domain.PageableSample;
import com.accelhack.application.api.app.dto.SampleQuery;

public interface SampleQueryRepository {
  PageableSample search(SampleQuery query);
}
