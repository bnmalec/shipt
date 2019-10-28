import json
from jsonschema import validate
from jsonschema.exceptions import ValidationError
from box import Box
from urllib.parse import urlparse
import pytest

def search_response_for_item_by_key(response, key):
    """Search the http response for an item in a list
    """
    arr = []

    def extract(response, arr, key):
        """Recursively search for values of key in JSON tree."""
        if isinstance(response, dict):
            for k, v in response.items():
                if isinstance(v, (dict, list)):
                    extract(v, arr, key)
                elif k == key:
                    arr.append(v)
        elif isinstance(response, list):
            for item in response:
                extract(item, arr, key)
        return arr

    results = extract(response, arr, key)
    return results

def search_response_for_item_by_name(response, key, name):
    results = search_response_for_item_by_key(response.json(), key)
    assert name in results

def load_json(response):
    """ Load the json so we can work with it """
    dump = json.dumps(response.json())
    json_response = json.loads(dump)
    return json_response

def check_starship_keys(response, **kwargs):
    """ Loop through the values in the required element to look for the provided values
    Parameters
    ----------
    kwargs: str
        The keys we expect to find in the required set for the starship endpoint
    """
    json_response = load_json(response)
    required = json_response['required']
    for k,v in kwargs.items():
        assert v in required

def count_results(response, page):
    """ Return a count of results from the json response """
    json_response = load_json(response)
    results = json_response['results']
    return Box({"count_on_page"+page: len(results)})

def check_count_against_count_results(response, count1, count2, count3, count4):
    """ Compares the stated count against the calculated count 
    Parameters
    ----------
    count1, count2, count3, count4: int
        The calculated counts from each respective page
    """
    json_response = load_json(response)
    total_count = json_response['count']
    paginated_results_count = 0
    paginated_results_count += count1 + count2 + count3 + count4
    assert paginated_results_count == total_count