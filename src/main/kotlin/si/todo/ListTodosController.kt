package si.todo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ListTodosController {

    @GetMapping("/get")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
        arrayOf(
            Todo(1, "Prepare Barbeque"),
            Todo(2, "Hang Out Washing"),
            Todo(3, "Grocery Shopping")
        )
}
