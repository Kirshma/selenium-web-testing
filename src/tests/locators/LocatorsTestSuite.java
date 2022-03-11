package locators;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ tests.locators.LocatorsTest1IdAndName.class, LocatorsTest2Xpath.class, locators.LocatorsTest3Xpath2.class })
public class LocatorsTestSuite {

}
