package test.pivotal.pal.tracker;

import org.junit.Test;

import java.util.Map;
import io.pivotal.pal.tracker.EnvironmentController;

import static org.assertj.core.api.Assertions.assertThat;

public class EnvironmentControllerTest {
    @Test
    public void getEnv() throws Exception {
        EnvironmentController controller = new EnvironmentController(
            "8675",
            "12G",
            "34",
            "123.sesame.street"
        );

        Map<String, String> env = controller.getEnv();

        assertThat(env.get("PORT")).isEqualTo("8675");
        assertThat(env.get("MEMORY_LIMIT")).isEqualTo("12G");
        assertThat(env.get("CF_INSTANCE_INDEX")).isEqualTo("34");
        assertThat(env.get("CF_INSTANCE_ADDR")).isEqualTo("123.sesame.street");
    }

}
