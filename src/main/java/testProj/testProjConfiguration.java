package testProj;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class testProjConfiguration extends Configuration {
    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

//    @JsonProperty("database")
//    public DataSourceFactory getDataSourceFactory() {
//        return database;
//    }

//    @JsonProperty("database")
//    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
//        this.database = dataSourceFactory;
//    }
}
