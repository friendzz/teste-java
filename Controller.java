import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService = new UserService();

    @GetMapping("/{id}")
    public String getUser(@PathVariable int id) {
        try {
            return userService.getUserById(id);
        } catch (SQLException e) {
            return "Error fetching user: " + e.getMessage();
        }
    }

    @PostMapping
    public String createUser(@RequestBody User user) {
        try {
            userService.createUser(user);
            return "User created successfully!";
        } catch (SQLException e) {
            return "Error creating user: " + e.getMessage();
        }
    }
}