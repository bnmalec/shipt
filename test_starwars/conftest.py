from logging import config
import yaml

with open("logging.yaml", "r") as log_spec_file:
    config.dictConfig(yaml.load(log_spec_file))