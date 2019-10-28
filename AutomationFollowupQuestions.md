1. If you chose to use a tool or language other than the recommended, briefly explain why.

	> For the api project, I picked Tavern, which is a Python library for writing YAML-based api automation.  I had never used it before, nor had I ever written tests in yaml.  I have been learning artillery.io recently for performance testing, and the tests are written in yaml.  So I thought it would be neat to give Tavern a whirl since I was given this opportunity.

2. How did you go about locating the elements for your tests?

	> Mostly used developer tools to inspect the elements.  Sometimes though it was a bit of trial and error, just trying various locators to see what worked.  I chose to test the shopper application, and it's in an iframe.  Sometimes the elements were tricky to locate.

3. What do you believe are the most common causes for instability in UI automation?

	> Large tests are more likely to be nondeterministic and instable, and UI tests are typically heftier than non-UI tests.  Tests should be small and concise.  Some tools cause instability.  Java webdriver is flaky at times (according to many).  End to end tests are often flaky because there are so many moving parts that all have to work, and when they don't it's hard to automate around them.  Using hard-coded locators, not using DRY in the test framework, not separating out data enough, not dealing with instable tests in a timely manner to prevent the instability from possibly spreading - all of these things can make UI tests instable.

4. How do you make your tests consistent and easy to debug?

	> I try to use reusable components, fluid assertions, well-tested and maintained libraries, etc.  That's why I wanted to try yaml for the api tests - yaml offers a lot of consistency and flexibility that could be really useful for automation.  Plus it's very easy to read and understand.
