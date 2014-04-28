import grails.test.AbstractCliTestCase

class BuildTests extends AbstractCliTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testBuild() {

        execute(["build"])

        assertEquals 0, waitForProcess()
        verifyHeader()
    }
}
