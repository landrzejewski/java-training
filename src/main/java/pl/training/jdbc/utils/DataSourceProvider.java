package pl.training.jdbc.utils;

import javax.sql.DataSource;

public interface DataSourceProvider {

    DataSource get();

}
