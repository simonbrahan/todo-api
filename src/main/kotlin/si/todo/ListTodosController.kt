package si.todo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RestController

@RestController
class ListTodosController {

    @CrossOrigin
    @GetMapping("/get")
    fun listTodos() = TodoRepo().getList()
}
