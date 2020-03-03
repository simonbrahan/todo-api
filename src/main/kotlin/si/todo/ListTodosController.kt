package si.todo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

import java.sql.*
import java.util.Properties

@RestController
class ListTodosController {

    @GetMapping("/get")
    fun listTodos(): List<Todo> {
        val connectionProps = Properties()
        connectionProps.put("user", "root")
        connectionProps.put("password", "root")

        Class.forName("com.mysql.jdbc.Driver").newInstance()
        val conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/todos", connectionProps)

        val stmnt = conn.createStatement();
        val res = stmnt.executeQuery("SELECT id, content FROM todo;")

        return res.use {
            sequence {
                while (res.next()) {
                    yield(Todo(res.getLong("id"), res.getString("content")))
                }
            }.toList()
        }
    }
}
