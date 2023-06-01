package com.example.api.suite.reqres;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import static com.example.api.filters.TagStore.POSITIVE;

@Suite
@SuiteDisplayName("Reqres api/users suite - only positive tests")
@SelectPackages("com.example.api.tests.reqres.users")
@IncludeTags(POSITIVE)
public class UsersPositiveSuite {
}
