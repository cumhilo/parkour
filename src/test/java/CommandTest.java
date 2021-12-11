import me.yushust.message.util.Validate;
import org.bukkit.command.CommandExecutor;
import org.junit.Test;

public class CommandTest {

    @Test
    public void test() {
        String alo = null;
    }

    private boolean isValidName(String name) {
        for (char c : name.toCharArray()) {
            if (Character.isDigit(c) || Character.isAlphabetic(c)) {
                continue;
            }

            System.out.println(c + " is not a valid character so " + name + " is not a valid name!");
            return false;
        }

        System.out.println(name + " is a valid name!");
        return true;
    }
}
