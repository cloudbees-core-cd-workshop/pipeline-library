//imageNameTag.groovy
def call(body) {
  tokens = "${env.JOB_NAME}".tokenize('/')
  env.IMAGE_REPO = tokens[tokens.size()-3].toLowerCase()
  env.IMAGE_NAME = tokens[tokens.size()-2].toLowerCase()
  env.IMAGE_TAG = tokens[tokens.size()-1].toLowerCase()
}
