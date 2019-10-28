Quickstart
----------

First up run ``pip install tavern``.

Run the tests with ``pytest test_starwars.tavern.yaml -v``

Results should look like this: 

test_starwars.tavern.yaml::Assert that Obi-Wan Kenobi was in the film A New Hope PASSED [ 20%]
test_starwars.tavern.yaml::Assert that the Enterprise is a starship (should fail) FAILED [ 40%]
test_starwars.tavern.yaml::Assert that Chewbacca is a Wookie PASSED      [ 60%]
test_starwars.tavern.yaml::Assert that the /starships endpoint returns specified fields PASSED [ 80%]
test_starwars.tavern.yaml::Assert that the /starships count returned is correct by paging through the results PASSED [100%]

