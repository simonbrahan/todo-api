package si.todo

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class DeleteTodoController {

    @CrossOrigin
    @PostMapping("/delete")
    fun deleteTodo(@RequestParam(value = "id") id: Long) = TodoRepo().deleteTodo(id)
}
