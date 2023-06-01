package com.example.api.suite.reqres;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import static com.example.api.filters.TagStore.NEGATIVE;

@Suite
@SuiteDisplayName("Reqres api/users suite - only negative tests")
@SelectPackages("com.example.api.tests.reqres.users")
@IncludeTags(NEGATIVE)
public class UsersNegativeSuite {
}
