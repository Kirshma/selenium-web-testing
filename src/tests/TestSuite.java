import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import testDriven.TransportWebsiteTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({tests.FirstTest.class, locators.LocatorsTestSuite.class, ParameterizedWebsiteTitleTest.class, tests.navigation.NavigationTest.class,
        otherFunctionality.OtherFunctionalityTest.class, TransportWebsiteTest.class})
public class TestSuite {
}
