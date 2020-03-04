package si.todo

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateTodoController {

    @CrossOrigin
    @PostMapping("/create")
    fun createTodo(@RequestParam(value = "content") content: String) = TodoRepo().createTodo(content)
}
