package com.example.api.suite.reqres;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Reqres  api/users suite")
@SelectPackages("com.example.api.tests.reqres.users")
public class UsersSuite {
}
