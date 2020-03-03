package si.todo

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import java.sql.*
import java.util.Properties

@RestController
class CreateTodoController {

    @PostMapping("/create")
    fun createTodo(@RequestParam(value = "content") content: String): Todo {
        val connectionProps = Properties()
        connectionProps.put("user", "root")
        connectionProps.put("password", "root")

        Class.forName("com.mysql.jdbc.Driver").newInstance()
        val conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/todos", connectionProps)

        val stmnt = conn.prepareStatement("INSERT INTO todo (content) VALUES (?)", Statement.RETURN_GENERATED_KEYS)
        stmnt.setString(1, content)
        stmnt.executeUpdate()

        val generatedKeys = stmnt.getGeneratedKeys()
        if (generatedKeys.next()) {
            return Todo(generatedKeys.getLong(1), content)
        }

        throw Exception("Create failed")
    }
}
