import com.lesfurets.jenkins.unit.*
import org.junit.Before
import org.junit.Test

import static com.lesfurets.jenkins.unit.MethodCall.callArgsToString

import static org.assertj.core.api.Assertions.assertThat

class SharedLibTest extends BasePipelineTest {

    @Override
    @Before
    void setUp() throws Exception {
        super.setUp()
    }

    @Test
    void should_execute_without_errors() throws Exception {
        def script = loadScript("vars/test.groovy")
        script.test()

        //printCallStack()

        assertThat(helper.callStack.findAll { call ->
            call.methodName == "echo"
        }.any { call ->
            callArgsToString(call).contains("testing shared lib")
        }).isTrue()
    }
}