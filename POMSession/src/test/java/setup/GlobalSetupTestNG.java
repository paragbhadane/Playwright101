package setup;

import config.TestConfig;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class GlobalSetupTestNG {

    @BeforeSuite
            public void globalSetup() {
        GlobalAuthSetup.loginAndSaveState(
                TestConfig.USERNAME,
                TestConfig.PASSWORD,
                TestConfig.USER_STATE);
        //GlobalAuthSetup.createExpiredState(TestConfig.EXPIRED_STATE);
    }
}
