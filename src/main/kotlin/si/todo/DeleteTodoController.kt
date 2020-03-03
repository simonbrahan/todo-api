package si.todo

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import java.sql.*
import java.util.Properties

@RestController
class DeleteTodoController {

    @PostMapping("/delete")
    fun deleteTodo(@RequestParam(value = "id") id: Long) {
        val connectionProps = Properties()
        connectionProps.put("user", "root")
        connectionProps.put("password", "root")

        Class.forName("com.mysql.jdbc.Driver").newInstance()
        val conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/todos", connectionProps)

        val stmnt = conn.prepareStatement("DELETE FROM todo WHERE id = ?")
        stmnt.setLong(1, id)
        stmnt.executeUpdate()
    }
}
