package si.todo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ListTodosController {

    @GetMapping("/get")
    fun listTodos() = TodoRepo().getList()
}
