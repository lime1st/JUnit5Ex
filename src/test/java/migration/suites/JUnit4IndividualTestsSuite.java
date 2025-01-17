package migration.suites;

import migration.categories.IndividualTests;
import migration.categories.JUnit4CustomerTest;
import migration.categories.JUnit4CustomersRepositoryTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)  //  특정한 runner 로 테스트를 실행하도록 지정
@Categories.IncludeCategory(IndividualTests.class)  //  실행할 테스트의 카테고리 지정
@Suite.SuiteClasses({JUnit4CustomerTest.class, JUnit4CustomersRepositoryTest.class})    //  해당 애너테이션이 달린 테스트를 찾아 실행한다.
public class JUnit4IndividualTestsSuite {
}