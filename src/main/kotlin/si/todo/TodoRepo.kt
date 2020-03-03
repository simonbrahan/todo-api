package si.todo

import java.sql.*
import java.util.Properties

class TodoRepo {
    fun getList(): List<Todo> {
        val conn = getConnection()
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

    fun createTodo(content: String): Todo {
        val conn = getConnection()
        val stmnt = conn.prepareStatement("INSERT INTO todo (content) VALUES (?)", Statement.RETURN_GENERATED_KEYS)
        stmnt.setString(1, content)
        stmnt.executeUpdate()

        val generatedKeys = stmnt.getGeneratedKeys()
        if (generatedKeys.next()) {
            return Todo(generatedKeys.getLong(1), content)
        }

        throw Exception("Create failed")
    }

    fun deleteTodo(id: Long) {
        val conn = getConnection()
        val stmnt = conn.prepareStatement("DELETE FROM todo WHERE id = ?")
        stmnt.setLong(1, id)
        stmnt.executeUpdate()
    }

    private fun getConnection(): Connection {
        val connectionProps = Properties()
        connectionProps.put("user", "root")
        connectionProps.put("password", "root")

        Class.forName("com.mysql.jdbc.Driver").newInstance()
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/todos", connectionProps)
    }
}
