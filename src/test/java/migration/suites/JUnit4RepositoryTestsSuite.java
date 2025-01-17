package migration.suites;

import migration.categories.JUnit4CustomerTest;
import migration.categories.JUnit4CustomersRepositoryTest;
import migration.categories.RepositoryTests;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(RepositoryTests.class)
@Suite.SuiteClasses({JUnit4CustomerTest.class, JUnit4CustomersRepositoryTest.class})
public class JUnit4RepositoryTestsSuite {
}