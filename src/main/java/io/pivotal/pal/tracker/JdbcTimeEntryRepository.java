package io.pivotal.pal.tracker;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class JdbcTimeEntryRepository implements TimeEntryRepository {

   // public HashMap<Long,TimeEntry> timeEntryMap = new HashMap<Long,TimeEntry>();
    private JdbcTemplate jdbcTemplate;

    public JdbcTimeEntryRepository(DataSource dataSource){
       // dataSource.setUrl(System.getenv("SPRING_DATASOURCE_URL"));
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public TimeEntry create(TimeEntry timeEntry){
//        timeEntryMap.put(timeEntry.getId(),timeEntry);
 /*       String sql = "INSERT INTO time_entries(id, project_id,user_id,date,hours) " +
                "values(999, "+timeEntry.getProjectId()+","+timeEntry.getUserId()+",LocalDate.parse(\"2017-01-09\")"+
                ","+timeEntry.getHours()+")";
*/

     KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

     String sql = "INSERT INTO time_entries(project_id,user_id,date,hours) " +
             "values(?,?,?,?)";

//        PreparedStatement preparedStatement = null;
//        try {
//            preparedStatement = (jdbcTemplate.getDataSource().getConnection()).
//                    prepareStatement(sql, RETURN_GENERATED_KEYS);
//
//            preparedStatement.setLong(1,timeEntry.getProjectId());
//            preparedStatement.setLong(2,timeEntry.getUserId());
//            preparedStatement.setDate(3,java.sql.Date.valueOf(timeEntry.getDate()));
//            preparedStatement.setLong(4,timeEntry.getHours());
//
//            timeEntry = jdbcTemplate.update(preparedStatement, generatedKeyHolder);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

           jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement(sql, RETURN_GENERATED_KEYS);

                statement.setLong(1, timeEntry.getProjectId());
                statement.setLong(2, timeEntry.getUserId());
                statement.setDate(3, java.sql.Date.valueOf(timeEntry.getDate()));
                statement.setInt(4, timeEntry.getHours());

                return statement;
            }
        }, generatedKeyHolder);

/*

        jdbcTemplate.execute(
                "INSERT INTO time_entries (project_id, user_id, date, hours) " +
                        "VALUES (123, 321, '2017-01-09', 8)"
        );
        timeEntry.setId(1);
*/


        return find(generatedKeyHolder.getKey().longValue());
    }

    public TimeEntry find(long id) {
        String sql = "Select * from time_entries where id=?";
        return jdbcTemplate.query("Select * from time_entries where id=?", new Object[]{id}, resultExtractor);
    }

    public List<TimeEntry> list(){
        String sql = "Select * from time_entries";
        List<TimeEntry> list = jdbcTemplate.query(sql, mapper);
        return list;

    }

    public TimeEntry update(long id, TimeEntry timeEntry){
        String sql = "UPDATE time_entries SET project_id=?,user_id=?,date=?,hours=? WHERE ID=? ";

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement(sql, RETURN_GENERATED_KEYS);

                statement.setLong(1, timeEntry.getProjectId());
                statement.setLong(2, timeEntry.getUserId());
                statement.setDate(3, java.sql.Date.valueOf(timeEntry.getDate()));
                statement.setInt(4, timeEntry.getHours());
                statement.setLong(5, id);


                return statement;
            }
        });
        return this.find(id);


    }

    public void delete(long id){
        String sql = "Delete from time_entries where id=?";

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement(sql, RETURN_GENERATED_KEYS);

                   statement.setLong(1, id);


                return statement;
            }
        });
     }

    private final RowMapper<TimeEntry> mapper = (rs, rowNum) -> new TimeEntry(
            rs.getLong("id"),
            rs.getLong("project_id"),
            rs.getLong("user_id"),
            rs.getDate("date").toLocalDate(),
            rs.getInt("hours")
    );

    private final ResultSetExtractor<TimeEntry> resultExtractor =
            (rs) -> rs.next() ? mapper.mapRow(rs, 1) : null;

}

