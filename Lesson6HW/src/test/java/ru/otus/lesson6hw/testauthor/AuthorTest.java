package ru.otus.lesson6hw.testauthor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.lesson6hw.dao.author.AuthorJDBCOperations;
import ru.otus.lesson6hw.dao.author.AuthorRowMapper;
import ru.otus.lesson6hw.domain.Author;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * Created by zhmv on 22.07.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthorTestConfig.class,properties={
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class AuthorTest {
    @Autowired
    AuthorJDBCOperations authorJDBCOperations;
    @Autowired
    AuthorRowMapper authorRowMapper;
    @MockBean
    NamedParameterJdbcTemplate namedJdbcTemplate;

    @Test
    public void testAuthorCount() {
        final Map<String, Object> params = new HashMap<>(1);
        String query = "select count(*) from author";
        when(namedJdbcTemplate.queryForObject(query, params, Integer.class)).thenReturn(1);
        Assert.assertEquals(authorJDBCOperations.count(), 1);
    }

    @Test
    public void testGetById(){
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", 1);
        String query = "select * from author where id = :id";
        when(namedJdbcTemplate.queryForObject(query, params, authorRowMapper))
                .thenReturn(new Author(1, "name1", "name2"));
        Assert.assertNotNull(authorJDBCOperations.getById(1));
        Assert.assertEquals(authorJDBCOperations.getById(1).getId(), 1);
        Assert.assertEquals(authorJDBCOperations.getById(1).getFirstName(), "name1");
        Assert.assertEquals(authorJDBCOperations.getById(1).getSecondName(), "name2");
    }

    @Test
    public void testGetAllAuthor() {
        String query = "select * from author";
        when(namedJdbcTemplate.query(query, authorRowMapper)).thenReturn(new ArrayList<Author>());
        Assert.assertEquals(authorJDBCOperations.getAllAuthor(), new ArrayList<Author>());
    }
}
