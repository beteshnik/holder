using System;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading;
using NUnit.Framework;
using Selenium;

namespace SeleniumTests
{
[TestFixture]
public class incorrect_password
{
private ISelenium selenium;
private StringBuilder verificationErrors;

[SetUp]
public void SetupTest()
{
selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://eventsystem.bmw.customers");
selenium.Start();
verificationErrors = new StringBuilder();
}

[TearDown]
public void TeardownTest()
{
try
{
selenium.Stop();
}
catch (Exception)
{
// Ignore errors if unable to close the browser
}
Assert.AreEqual("", verificationErrors.ToString());
}

[Test]
public void TheIncorrect_passwordTest()
{
			selenium.Open("http://eventsystem.bmw.customers/LogOn?ReturnUrl=%2f");
			Assert.AreEqual("Вход в систему - BMW Event System", selenium.GetTitle());
			selenium.Type("id=UserName", "sdf");
			selenium.Type("id=Password", "sdf");
			selenium.Click("css=button.form__button.form__button_login");
			selenium.WaitForPageToLoad("30000");
			Assert.AreEqual("Вход в систему - BMW Event System", selenium.GetTitle());
}
}
}
