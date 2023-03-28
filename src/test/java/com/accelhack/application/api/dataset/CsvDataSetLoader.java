package com.accelhack.application.api.dataset;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
import org.springframework.core.io.Resource;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;


public class CsvDataSetLoader extends AbstractDataSetLoader {
  @Override
  protected IDataSet createDataSet(Resource resource) throws Exception {
    return new CsvDataSet(resource.getFile());
  }
}
